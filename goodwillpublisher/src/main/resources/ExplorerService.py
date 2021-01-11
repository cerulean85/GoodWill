# –– coding: utf-8 –
class ExplorerService:

    behavior = None

    def work(self, _keyword, _startDate, _endDate):
        print("*** 검색정보: %s, %s ~ %s ***" % (_keyword, _startDate, _endDate))
        self.behavior.setQuery(_keyword, _startDate, _endDate)
        self.behavior.schedule()
        self.behavior.batch()

    def getMonth(self, date):
        monthStr = date[4:6]
        if len(monthStr) == 2 and monthStr[0:1] == 0:
            return int(monthStr[1:1])
        else:
            return int(monthStr[0:2])

    def writeFile(self, filename):
        f = open(filename, "w", encoding='UTF8')
        print('파일 %s 쓰는 중... 잠시만 기다려주세요.' %(filename))

        contents = ''
        result = self.behavior.linkDictionary
        for key in result.keys():
            contents += str(result[key][0]) + ',' + result[key][1] + ',' + key + '\n'
        f.write(contents)
        f.close()
        print('완료되었습니다.')

    def extract(self, keyword, startDate, endDate):
        if keyword == '': exit()

        startYear = 0
        endYear = 0
        startMonth = 0
        endMonth = 0
        try:
            startYear = int(startDate[0:4])
            endYear = int(endDate[0:4])
        except:
            print('달력정보(year)를 알 수 없습니다.')
            exit()

        try:
            startMonth = self.getMonth(startDate)
            endMonth = self.getMonth(endDate)
        except:
            print('달력정보(month)를 알 수 없습니다.')
            exit()

        if startYear == endYear:
            self.workByMonth(keyword, startYear, startMonth, endMonth)

        if startYear != endYear:
            self.workByMonth(keyword, startYear, startMonth, 12)
            for year in range(startYear - 1, endYear):
                self.workByMonth(keyword, year, 1, 12)
            self.workByMonth(keyword, endYear, 1, endMonth)