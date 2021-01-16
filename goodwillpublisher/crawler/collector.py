# –– coding: utf-8 –
import requests
import config as conf
from urllib import parse
from bs4 import BeautifulSoup
import json
from json import dumps
import time
import sys
from kafka import KafkaProducer
import hashlib
from dbconn import DBConn
dbconn = DBConn()


def getTimestamp():
    now = time.localtime()
    timestamp = "%04d%02d%02d%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
    return timestamp


def getCurrentDate():
    now = time.localtime()
    timestamp = "%04d-%02d-%02d-%02d:%02d:%02d" % (
        now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
    return timestamp


def exploreDonga(keyword, startDate, endDate, targetPageNo, totalPageCount, topicName, urlNo):
    linkDictionary = {}
    exceptionType = 200
    enableWork = True
    if int(totalPageCount) == 0:
        exceptionType = 404
        enableWork = False

    if enableWork:
        pageCount = 1 + 15 * (int(targetPageNo) - 1)
        encQuery = parse.quote(keyword)
        encQuery = encQuery.replace('_', ' ')
        url = "https://www.donga.com/news/search?p=%s&query=%s&check_news=1&more=1&sorting=2&range=1&search_date=&search_date=5&v1=%s&v2=%s" \
              % (str(pageCount), encQuery, startDate, endDate)
        print(url)
        result = requests.get(url=url)
        bsObj = BeautifulSoup(result.content, "html.parser")
        items = bsObj.findAll("div", {"class": "searchList"})
        for item in items:
            __p = item.find("p", {"class": "txt"})
            try:
                addr = __p.find("a").attrs['href']
                try:
                    addr = __p.find("a").attrs['href']
                    linkDictionary[addr][0] = linkDictionary[addr][0] + 1
                    linkDictionary[addr][1] = keyword
                except:
                    linkDictionary[addr] = [1, keyword]
            except:
                exceptionType = 402

    producer = KafkaProducer(acks=0, compression_type='gzip', bootstrap_servers=['localhost:9092'],
                             value_serializer=lambda x: x.encode('euc-kr'))

    for url in linkDictionary:
        urlNo = urlNo + 1
        producer.send(topicName, value=str(json.dumps({'no': urlNo, 'site': 'donga', 'url': url}, ensure_ascii=False)))

    producer.send('work_state_logs', value=str(json.dumps({
        "topic_work_id": topicName,
        "update_date": getCurrentDate(),
        "work_state": "waiting",
        "last_url_no": len(linkDictionary)
    }, ensure_ascii=False)))
    producer.flush()

    print(json.dumps(
        {
            "urlPackageId": topicName,
            "exceptionType": exceptionType,
            "exceptionMessage": conf.ExceptionMessage[exceptionType],
            "totalArticleCount": len(linkDictionary),
            "data": linkDictionary,
            "echo": {
                "keyword": keyword,
                "startDate": startDate,
                "endDate": endDate,
                "totalPageCount": totalPageCount,
                "targetPageNo": targetPageNo
            }
        }, ensure_ascii=False))

    return urlNo


if __name__ == "__main__":

    site, keyword, startDate, endDate, totalPageCount, topicName = sys.argv[1:7]

    if site != '' and keyword != '' and startDate != '' and endDate != '' and totalPageCount != '' and topicName != '':
        if site == 'donga':
            urlNo = 0
            for targetPageNo in range(1, int(totalPageCount) + 1):
                urlNo = exploreDonga(keyword, startDate, endDate, targetPageNo, totalPageCount, topicName, urlNo)

            dbconn.updateState_Waiting(topicName, urlNo)
            dbconn.close()
            exit()


    else:
        print(json.dumps(
            {
                "exceptionType": 403,
                "exceptionMessage": conf.ExceptionMessage[403],
                "totalArticleCount": 0,
                "data": {},
                "echo": {
                    "site": site,
                    "keyword": keyword,
                    "startDate": startDate,
                    "endDate": endDate,
                    "totalPageCount": totalPageCount,
                    "targetPageNo": 0
                }
            }, ensure_ascii=False))
