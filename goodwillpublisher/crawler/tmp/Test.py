# –– coding: utf-8 –

from TestBase import TestBase
import requests
from urllib import parse
from bs4 import BeautifulSoup
import math

class Test(TestBase):

    def addition(self, a, b):
        c = a + b
        result = requests.get(url='http://naver.com')
        # print(" %d ", % ( self._testCode ))
        print(result.content)
        print("ㅋㅋㅋㅋㅋ22")
        return c