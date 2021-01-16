# –– coding: utf-8 –
import pymysql
import time


class DBConn:
    dbconn = pymysql.connect(
        user='root',
        passwd='123456',
        host='127.0.0.1',
        db='goodwill',
        charset='utf8'
    )

    def getCurrentDate(self):
        now = time.localtime()
        timestamp = "%04d-%02d-%02d-%02d:%02d:%02d" % (
            now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
        return timestamp

    def insertState(self, topicName, siteType, keyword, startDate, endDate):

        currentDate = self.getCurrentDate()

        sql = "INSERT INTO work_state(topic_name, current_state, site_type, keyword, start_date, end_date, update_date) " \
            + "VALUES(%s, %s, %s, %s, %s, %s, now())"
        cursor = self.dbconn.cursor()

        cursor.execute(sql, (topicName, 'enrolled', siteType, keyword, startDate, endDate))
        self.dbconn.commit()

    def updateState_Waiting(self, topicName, totalWorkCount):
        currentDate = self.getCurrentDate()
        cursor = self.dbconn.cursor()
        sql = "UPDATE work_state SET " \
              + "     current_state = %s ," \
              + "     total_work_count = %s," \
              + "     update_date = now()" \
              + " WHERE topic_name = %s"
        cursor.execute(sql, ('waiting', totalWorkCount, topicName))
        self.dbconn.commit()

    def updateState_Processing(self, topicName, finishedWorkCount):
        currentDate = self.getCurrentDate()
        cursor = self.dbconn.cursor()
        sql = "UPDATE work_state SET " \
              + "       current_state = %s," \
              + "       finished_work_count = %s," \
              + "       update_date         = now()" \
              + " WHERE topic_name          = %s"
        cursor.execute(sql, ('processing', finishedWorkCount, topicName))
        self.dbconn.commit()

    def updateState_Finished(self, topicName, finishedWorkCount, packagedFileName):
        currentDate = self.getCurrentDate()
        cursor = self.dbconn.cursor()
        sql = "UPDATE work_state SET " \
              + "       current_state = %s," \
              + "       finished_work_count = %s," \
              + "       packaged_file_name  = %s," \
              + "       update_date         = now()" \
              + " WHERE topic_name          = %s"
        cursor.execute(sql, ('finished', finishedWorkCount, packagedFileName, topicName))
        self.dbconn.commit()

    def close(self):
        self.dbconn.close()

# if __name__ == "__main__":
#
#     testTopicName = '0c1a20aa5318ac014fccd8e7f1713bf1'
#     testSiteType = 'donga'
#     testKeyword = '비대면'
#     testStartDate = '20201001'
#     testEndDate = '20201031'
#     testTotalWorkCount = 100
#
#     dbconn = DBConn()
#     dbconn.insertState(testTopicName, testSiteType, testKeyword, testStartDate, testEndDate)
#
#     dbconn.updateState_Waiting(testTopicName, testTotalWorkCount)
#     dbconn.updateState_Processing(testTopicName, testTotalWorkCount)
#     dbconn.updateState_Finished(testTopicName, testTotalWorkCount, 'sample-final.json')