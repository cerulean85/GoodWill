# –– coding: utf-8 –
from ExplorerDongaService import ExplorerDongaService
# from ExplorerYNAService import ExplorerYNAService
# from ExplorerJoongangService import ExplorerJoongangService
# from threading import Thread
# from time import sleep
# import time

# parametes) SaveFileName, Keyword, StartDate, EndDate
# types) string, string, string, string
# ex) jooo.txt, untact, 202001, 202011
def pieceDonga(f1, kw, sd, ed):
    s = ExplorerDongaService()
    s.piece(f1, kw, sd, ed)