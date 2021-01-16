# –– coding: utf-8 –
import requests
from bs4 import BeautifulSoup
import yaml
import re
from kafka import KafkaConsumer, TopicPartition
from json import dumps, loads
import json
import sys
from kafka import KafkaProducer
import time
import os
import config as conf
from dbconn import DBConn
dbconn = DBConn()

flow = ''


def getCurrentDate():
    now = time.localtime()
    timestamp = "%04d-%02d-%02d-%02d:%02d:%02d" % (
        now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
    return timestamp


def findElements(soup, queries):
    for __query in queries:
        for __tag in __query.keys():
            mAttr = __query[__tag]
            for __selector in mAttr.keys():
                mName = mAttr[__selector]
                soup = soup.find(__tag, {__selector: mName})

    return soup


def filter(contents):
    result = contents.replace('\r\n', '')
    result = result.replace('\n', '')
    result = re.sub('(<script)\s{0,1}((?!</script>).)*(</script>)', '', result, 0)
    result = re.sub('<.+?>', '', result, 0)

    return result


def compile(form, contents):
    result = re.compile(form).findall(contents)
    return result


def decompose(soup, queries):
    for __query in queries:
        for __tag in __query.keys():
            mAttr = __query[__tag]
            for __selector in mAttr.keys():
                mName = mAttr[__selector]
                for __e in soup.find_all(__tag, {__selector: mName}):
                    __e.decompose()

    return soup


def scrapper_donga(no, link):
    item = {
        'no': no, 'link': link, 'date': 'no data',
        'title': '기사의 링크가 잘못되었습니다.',
        'contents': '본문 내용을 불러올 수 없습니다.'
    }

    try:
        result = requests.get(url=link)
        soup = BeautifulSoup(result.content, "html.parser")

        titleSrc = flow['title']
        dateSrc = flow['date']
        articleSrc = flow['article']
        articleDecomposeSrc = flow['articleDecompose']

        tmp = str(findElements(soup, titleSrc))
        __title = filter(tmp)

        tmp = str(findElements(soup, dateSrc))
        tmp = filter(tmp)
        tmp = compile('\d{4}[-.]\d{2}[-.]\d{2}\s\d{2}:\d{2}', tmp)
        __date = ''
        for __t in tmp:
            __date = __t.replace('.', '-')
            break

        tmp = findElements(soup, articleSrc)
        tmp = str(decompose(tmp, articleDecomposeSrc))
        __contents = filter(tmp)

        item['title'] = __title
        item['date'] = __date
        item['contents'] = __contents
    except:
        print("[ERROR] 해당 링크의 기사를 불러올 수 없습니다.".encode('utf-8'))
        print("\t>>> ", link)

    return item


if __name__ == "__main__":

    processNo, topicName, startOffset, endOffset = sys.argv[1:5]

    prefixDir = os.getcwd() + "\\src\\main\\resources\\"
    # prefixDir = ""
    yamlFilename = prefixDir + "parse-rule-donga.yaml"
    # yamlFilename = "parse-rule-donga.yaml"

    with open(yamlFilename, "rt", encoding="utf-8") as stream:
        flow = yaml.load(stream, Loader=yaml.FullLoader)

    # topicName = '38fd6dd8679c23c9c734b1018ea2b396'
    # scrapper.exe 1 38fd6dd8679c23c9c734b1018ea2b396 0 19
    # python scrapper.py 1 38fd6dd8679c23c9c734b1018ea2b396 0 19
    consumer = KafkaConsumer(
        bootstrap_servers=['localhost:9092'],
        session_timeout_ms=10000,
        enable_auto_commit=False,
        group_id=topicName)

    topicPartition = TopicPartition(topicName, 0)
    consumer.assign([topicPartition])
    consumer.seek(topicPartition, int(startOffset))

    contents = []
    for message in consumer:
        value = str(message.value).replace("b'", '').replace("'", '')

        try:
            value = json.loads(value)
        except:
            value = {}
        # print(str(value).encode('euc-kr'))
        if 'url' in value.keys():
            data = scrapper_donga(int(value['no']), value['url'])
            contents.append(data)

        offset = int(message.offset)
        if offset == int(endOffset): break

    # print(str(contents).encode('utf-8'))
    contentsTxt = ''
    for i in range(0, len(contents)):
        contentsTxt += str(contents[i])
        if i == len(contents) - 1: break
        contentsTxt += ','

    # print(contentsTxt.encode('euc-kr'))

    if not os.path.isdir(prefixDir + 'data'):
        os.mkdir(prefixDir + 'data')

    if not os.path.isdir(prefixDir + 'data\\' + topicName):
        os.mkdir(prefixDir + 'data\\' + topicName)

    filename = prefixDir + 'data\\' + topicName + '\\' + topicName + '-' + processNo + '.bak'
    with open(filename, "w", encoding="utf-8") as f:
        f.write(contentsTxt)

    dbconn.updateState_Processing(topicName, int(endOffset))

    producer = KafkaProducer(acks=0, compression_type='gzip', bootstrap_servers=['localhost:9092'],
                             value_serializer=lambda x: x.encode('euc-kr'))

    producer.send('work_state_logs', value=str(json.dumps({
        "topic_work_id": topicName,
        "update_date": getCurrentDate(),
        "process_no": processNo,
        "work_state": "finished"
    }, ensure_ascii=False)))
    producer.flush()
    producer.close()

    consumer.seek_to_end(topicPartition)
    updatedOffset = consumer.position(topicPartition) - 1
    if int(endOffset) == updatedOffset:
        packagedFileName = prefixDir + 'data\\' + topicName +'-final.json'
        with open(packagedFileName, "w", encoding="utf-8") as f:
            contents = ''
            for i in range(0, int(processNo)+1):
                filename = prefixDir + 'data\\' + topicName + '\\' + topicName + '-' + str(i) + '.bak'

                while not os.path.isfile(filename):
                    if os.path.isfile(filename): break
                    time.sleep(1)

                with open(filename, "rt", encoding="utf-8") as stream:
                    contents += str(stream.read())
                if i == int(processNo): break
                contents += ','
            f.write('[' + contents + ']')

        dbconn.updateState_Finished(topicName, int(endOffset), packagedFileName)

    dbconn.close()
