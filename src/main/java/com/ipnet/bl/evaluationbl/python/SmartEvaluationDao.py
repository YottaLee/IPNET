from setup import mysql
# 执行sql

def saveEvaluation(patent_id,value):
    with mysql() as cursor:
        # cursor.execute("select max(id) from automatic_tag")
        # newId = cursor.fetchall()[0]['max(id)']
        # if newId == None:
        #     newId = 0
        executeStr = "insert into evaluation(patentid,evaluation) values(\"patent_id\",value)"
        cursor.execute(executeStr)

