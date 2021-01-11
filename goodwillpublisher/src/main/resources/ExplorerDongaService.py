# –– coding: utf-8 –
from ExplorerService import ExplorerService
from ExplorerDongaBehavior import ExplorerDongaBehavior
from Validator import Validator as v
import calendar


class ExplorerDongaService(ExplorerService):

    def __init__(self):
        self.behavior = ExplorerDongaBehavior()

    def workByMonth(self, keyword, _year, _startMonth, _endMonth):
        for month in range(_startMonth, _endMonth+1):
            monthStr = ('0' if month < 10 else '') + str(month)
            lastDay = calendar.monthrange(_year, month)[1]
            _startDate = str(_year) + monthStr + '01'
            _endDate = str(_year) + monthStr + str(lastDay)
            self.work(keyword, _startDate, _endDate)

    def simulator(self, keyword, startDate, endDate):
        self.extract(keyword, startDate, endDate)
        self.writeFile('link_donga.txt')

    def piece(self, filename, keyword, startDate, endDate):
        self.extract(keyword, startDate, endDate)
        self.writeFile(filename)

