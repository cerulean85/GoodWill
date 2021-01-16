# –– coding: utf-8 –
ChromUrl = 'C:/chromedriver_win32/chromedriver.exe'

urlFormat = {
    "donga": "https://www.donga.com/news/search?check_news=1&more=1&sorting=2&range=1&search_date=&search_date=5&v1=%s&v2=%s&query=%s"
}

ExceptionMessage = {

    200: '',
    201: '검색결과가 존재하지 않습니다.',
    400: '키워드가 잘못 입력되었습니다.',
    401: '날짜가 잘못 입력되었습니다.',
    402: '잘못된 링크입니다.',
    403: '알 수 없는 오류가 발생하였습니다.',
    404: '예약된 수집 작업이 없습니다.'

}

def getSaveFilePath(filename):
    return '/src/' + filename