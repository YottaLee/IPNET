from setup import mysql
# 执行sql

def saveEvaluation(patent,value):
    with mysql() as cursor:
        cursor.execute("select patent_id from patent where patent_name = \""+patent+"\"")
        patentId = cursor.fetchall()[0]['patent_id']
        cursor.execute("select * from evaluation where patentid = \""+patentId+"\"")
        newId = cursor.fetchall()
        if newId == None:
            cursor.execute("select max(id) from evaluation")
            newnewId = cursor.fetchall()+1
            executeStr = "INSERT INTO evaluation(patentid,evaluation) values (\""+patentId+"\","+str(value)+")"
        else:
            executeStr = "UPDATE evaluation SET evaluation = '"+str(value)+"' WHERE patentid = \""+patentId+"\" "
        cursor.execute(executeStr)

