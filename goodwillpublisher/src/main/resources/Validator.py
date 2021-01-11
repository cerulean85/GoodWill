# –– coding: utf-8 –
import datetime


class Validator:

    @staticmethod
    def checkDateForm(d, form='%Y%m%d'):
        try:
            _d = datetime.datetime.strptime(d, form).date()
            return True
        except ValueError:
            return False
