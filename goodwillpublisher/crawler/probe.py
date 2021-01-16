# –– coding: utf-8 –
import requests
from selenium import webdriver
from selenium.webdriver.common.alert import Alert
import config as conf
from urllib import parse
from bs4 import BeautifulSoup
import math
import json
import time
import sys
from dbconn import DBConn
dbconn = DBConn()

def getCurrentDate():
    now = time.localtime()
    timestamp = "%04d-%02d-%02d-%02d:%02d:%02d" % (
        now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
    return timestamp

def getTimestamp():
    now = time.localtime()
    timestamp = "%04d%02d%02d%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
    return timestamp


def articleDonga(keyword, startDate, endDate, topicName):
    exceptionType = 200

    if keyword == '':
        exceptionType = 400
    elif startDate == '' or endDate == '':
        exceptionType = 401
    else:
        encQuery = parse.quote(keyword)
        encQuery = encQuery.replace('_', ' ')
        url = conf.urlFormat['donga'] % (startDate, endDate, encQuery)

        result = requests.get(url=url)
        bsObj = BeautifulSoup(result.content, "html.parser")
        items = bsObj.findAll("h2")

        try:
            _totalCountStr = str(items[0].findAll("span")[0])
            _totalCountStr = _totalCountStr.replace('총 ', '')
            _totalCountStr = _totalCountStr.replace(' 건 검색', '')
            _totalCountStr = _totalCountStr.replace('<span>(', '')
            _totalCountStr = _totalCountStr.replace(')</span>', '')

            totalArticleCount = int(_totalCountStr)
            totalPageCount = int(math.ceil(totalArticleCount / 15))

            dbconn.insertState(topicName, 'donga', keyword, startDate, endDate)


        except:
            totalArticleCount = 0
            totalPageCount = 0
            exceptionType = 402

    print(json.dumps(
        {
            "exceptionType": exceptionType,
            "exceptionMessage": conf.ExceptionMessage[exceptionType],
            "totalArticleCount": totalArticleCount,
            "totalPageCount": totalPageCount,
            "echo": {
                "site": "donga",
                "keyword": keyword,
                "startDate": startDate,
                "endDate": endDate,
                "topicName": topicName
            },
            "timestamp": getTimestamp()
        }, ensure_ascii=False))


if __name__ == "__main__":

    site = sys.argv[1]
    keyword = sys.argv[2]
    startDate = sys.argv[3]
    endDate = sys.argv[4]
    topicName = sys.argv[5]

    if site != '' and keyword != '' and startDate != '' and endDate != '' and topicName != '':
        if site == 'donga':
            articleDonga(keyword, startDate, endDate, topicName)
    else:
        print(json.dumps(
            {
                "exceptionType": 403,
                "exceptionMessage": conf.ExceptionMessage[403],
                "echo": {
                    "site": site,
                    "keyword": keyword,
                    "startDate": startDate,
                    "endDate": endDate,
                    "topicName": topicName
                },
                "timestamp": getTimestamp()
            }, ensure_ascii=False))
