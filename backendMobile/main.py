from flask import Flask ,request
import pymysql

mydb = pymysql.connect(
    host="127.0.0.1",
    port=3306,
    user="root",
    password="",
    database="androiddb",
)
mycursor = mydb.cursor()
app = Flask(__name__)
@app.post('/save')
def save():
    res = request.form
    sql = "INSERT INTO user (name , age) VALUES (%s, %s)"
    val = (res['name'], res['age'])
    mycursor.execute(sql, val)
    mydb.commit()
    return str("Recored Has Been Saved ,200!")

if __name__ == '__main__':
    app.run(host='127.0.0.1', threaded=True)