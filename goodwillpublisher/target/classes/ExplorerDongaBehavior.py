# –– coding: utf-8 –
import requests
from Validator import Validator as v
from selenium import webdriver
from selenium.webdriver.common.alert import Alert
import Config as conf
from urllib import parse
from bs4 import BeautifulSoup
import math
from ExplorerBase import ExplorerBase

class ExplorerDongaBehavior(ExplorerBase):

    def schedule(self):

        if self.keyword == '' or self.startDate == '' or self.endDate == '':
            print('키워드 혹은 날짜 선택이 잘못 되었습니다.')
            exit()

        encQuery = parse.quote(self.keyword)
        url = "https://www.donga.com/news/search?check_news=1&more=1&sorting=2&range=1&search_date=&search_date=5&v1=%s&v2=%s&query=%s" % (
        self.startDate, self.endDate, encQuery)
        print(url)
        result = requests.get(url=url)
        bsObj = BeautifulSoup(result.content, "html.parser")
        items = bsObj.findAll("h2")
        _totalCountStr = str(items[0].findAll("span")[0])
        _totalCountStr = _totalCountStr.replace('총 ', '')
        _totalCountStr = _totalCountStr.replace(' 건 검색', '')
        _totalCountStr = _totalCountStr.replace('<span>(', '')
        _totalCountStr = _totalCountStr.replace(')</span>', '')

        self.totalCount = int(_totalCountStr)
        self.lastPageNo = int(math.ceil(self.totalCount / 15))

        print("총 검색 기사개수: %d , 총 페이지수: %d" % (self.totalCount, self.lastPageNo))

    def explore(self, targetPageNo):
        keyword = self.keyword
        lastPageNo = self.lastPageNo
        linkDictionary = self.linkDictionary
        if lastPageNo == 0:
            print('키워드 [ %s ] 예약된 수집 작업이 없음...' % (keyword))
            exit()

        if 0 < lastPageNo < targetPageNo:
            print('키워드 [ %s ] 수집완료!!' % (keyword))
            exit()

        pageCount = 1 + 15 * (targetPageNo - 1)
        encQuery = parse.quote(self.keyword)
        url = "https://www.donga.com/news/search?p=%s&query=%s&check_news=1&more=1&sorting=2&range=1&search_date=&search_date=5&v1=%s&v2=%s" \
              % (str(pageCount), encQuery, self.startDate, self.endDate)

        result = requests.get(url=url)
        bsObj = BeautifulSoup(result.content, "html.parser")
        items = bsObj.findAll("div", {"class": "searchList"})
        for item in items:
            __p = item.find("p", {"class": "txt"})
            addr = __p.find("a").attrs['href']

            try:
                linkDictionary[addr][0] = linkDictionary[addr][0] + 1
                linkDictionary[addr][1] = keyword
            except:
                linkDictionary[addr] = [1, keyword]

        # print(linkDictionary)

    def batch(self):
        for i in range(1, self.lastPageNo + 1):
            self.explore(i)
            # print("%d / %d" % (i, self.lastPageNo))
        # print(self.linkDictionary)
        return self.linkDictionary
