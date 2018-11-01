from setup import mysql
# 执行sql

def saveEvaluation(patent,value):
    with mysql() as cursor:
        cursor.execute("select patent_id from patent where patent_name = \""+patent+"\"")
        patentId = cursor.fetchall()[0]['patent_id']
        # if newId == None:
        #     newId = 0
        executeStr = "UPDATE evaluation SET evaluation = '"+str(value)+"' WHERE patentid = \""+patentId+"\" "
        cursor.execute(executeStr)

