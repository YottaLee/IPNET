from setup import mysql
# 执行sql

def saveEvaluation(patent,value):
    with mysql() as cursor:
        cursor.execute("select patent_id from patent where patent_name = \""+patent+"\"")
        patentId = cursor.fetchall()[0]['patent_id']
        cursor.execute("select * from evaluation where patentid = \""+patentId+"\"")
        newId = cursor.fetchall()
        if len(newId) == 0:
            cursor.execute("select max(id) from evaluation")
            newId = cursor.fetchall()[0]['max(id)']+1
            executeStr = "INSERT INTO evaluation(id,patentid,money,over,evaluation) values ("+str(newId)+",\""+patentId+"\",0,true,"+str(value)+")"
        else:
            print (newId)
            executeStr = "UPDATE evaluation SET evaluation = '"+str(value)+"' WHERE patentid = \""+patentId+"\" "
        cursor.execute(executeStr)