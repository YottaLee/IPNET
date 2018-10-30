import pymysql
import contextlib
"""
python的数据库连接配置
"""

# 定义上下文管理器，连接后自动关闭连接
@contextlib.contextmanager
def mysql(host='120.79.232.126', port=3306, user='root', passwd='mysql', db='ipnet', charset='utf8'):
    conn = pymysql.connect(host=host, port=port, user=user, passwd=passwd, db=db, charset=charset)
    cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
    try:
        yield cursor
    finally:
        conn.commit()
        cursor.close()
        conn.close()