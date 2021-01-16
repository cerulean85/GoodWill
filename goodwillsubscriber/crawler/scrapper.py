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

        yamlFilename = "parse-rule-donga.yaml"
        with open(yamlFilename, "rt", encoding="utf-8") as stream:
            flow = yaml.load(stream, Loader=yaml.FullLoader)

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
        print("[ERROR] 해당 링크의 기사를 불러올 수 없습니다.")
        print("\t>>> ", link)

    return item


if __name__ == "__main__":

    processNo, topicName, startOffset, endOffset = sys.argv[1:5]
    # topicName='38fd6dd8679c23c9c734b1018ea2b396'
    # startOffset='0'
    # endOffset ='20'
    #sys.argv[1:4]
    # topicName = '38fd6dd8679c23c9c734b1018ea2b396'
    consumer = KafkaConsumer(
        bootstrap_servers=['localhost:9092'],
        session_timeout_ms=10000,
        enable_auto_commit=False,
        group_id=topicName)

    topicPartition = TopicPartition(topicName, int(startOffset))
    consumer.assign([topicPartition])
    consumer.seek(topicPartition, int(startOffset))

    contents = []
    for message in consumer:
        value = str(message.value).replace("b'", '').replace("'", '')
        try:
            value = json.loads(value)
            # print(value['no'], value['url'])
            data = scrapper_donga(int(value['no']), value['url'])
            contents.append(data)
        except:
            contents.append('')

        offset = int(message.offset)
        if offset == int(endOffset): break

    print(contents)

    # def writeFile(self, filename):
    #     f = open(filename, "w", encoding='UTF8')
    #     print('파일 %s 쓰는 중... 잠시만 기다려주세요.' %(filename))
    #
    #     contents = ''
    #     result = self.behavior.linkDictionary
    #     for key in result.keys():
    #         contents += str(result[key][0]) + ',' + result[key][1] + ',' + key + '\n'
    #     f.write(contents)
    #     f.close()
    #     print('완료되었습니다.')

    producer = KafkaProducer(acks=0, compression_type='gzip', bootstrap_servers=['localhost:9092'],
                             value_serializer=lambda x: x.encode('euc-kr'))

    producer.send('work_state_logs', value=str(json.dumps({
        "topic_work_id": topicName,
        "update_date": getCurrentDate(),
        "process_no": processNo,
        "work_state": "finished"
    }, ensure_ascii=False)))
    producer.flush()

    # print(contents)