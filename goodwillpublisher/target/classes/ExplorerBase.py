# –– coding: utf-8 –
from Validator import Validator as v


class ExplorerBase:

    keywords = ''
    startDate = ''
    endDate = ''
    chromeDriver = None

    totalCount = 0
    def getTotalCount(self):
        return self.totalCount

    lastPageNo = 0
    linkDictionary = {}


    def setQuery(self, keyword, startDate, endDate):
        self.setKeyword(keyword)
        self.setStartDate(startDate)
        self.setEndDate(endDate)

    def setKeyword(self, keyword):
        if keyword != '':
            self.keyword = keyword

    def setStartDate(self, d):
        if not v.checkDateForm(d):
            self.startDate = ''
            raise RuntimeError('날짜 형식이 잘못되었습니다.')
        else:
            self.startDate = d

    def setEndDate(self, d):
        if not v.checkDateForm(d):
            self.endDate = ''
            raise RuntimeError('날짜 형식이 잘못되었습니다.')
        else:
            self.endDate = d

    def schedule(self):
        print('(schedule) 함수가 구현되지 않았습니다.')
        raise Exception

    def explore(self):
        print('(explore) 함수가 구현되지 않았습니다.')
        raise Exception
