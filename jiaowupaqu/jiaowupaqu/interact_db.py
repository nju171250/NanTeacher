'''
数据库交互文件
'''
import config
import re
import pymysql

connector = pymysql.connect(host = config.mysql_host, 
                            user = config.mysql_user, 
                            passwd = config.mysql_pwd, 
                            database = config.mysql_db,
                            charset = config.mysql_charset)
cursor = connector.cursor()

def insert_course(course):
  cursor.execute("SELECT course_id FROM course where course_id = " + course[0])
  myresult = cursor.fetchone()     # fetchall() 获取所有记录, fetchone() 获取一个
  if myresult == None:
    sql = "INSERT INTO `njuTeacher`.`course`(`course_id`, `course_name`, `course_type`, `course_academy`, `course_credit`, `course_area`, `course_teacher`) " + "VALUES('" + course[0] + "','" + course[1] + "','" + course[2] + "','" + course[3] + "','" + course[4] + "','" + course[6] + "','" + course[7] + "')"
    cursor.execute(sql)     
    connector.commit()
    print('课程' + course[0] + '插入成功') 
  else:
    print('课程' + course[0] + '已存在')

def insert_teacher():
  cursor.execute("SELECT course_teacher,course_id,course_academy FROM course")
  myresult = cursor.fetchall()
  print(myresult)
  i = 0
  for course in myresult:
    teachers = str(course[0]).replace(' ', '').split(",")
    for teacher in teachers:
      cursor.execute("SELECT teacher_name FROM teacher where teacher_name = '" + teacher + "'")
      result = cursor.fetchone()
      if result == None:
        # 插入老师
        sql = "INSERT INTO `njuTeacher`.`teacher`(`teacher_id`, `teacher_name`, `teacher_collegeName`, `teacher_department`, `teacher_score`) VALUES ('" + str(i) + "', '" + teacher + "', '南京大学', '" + course[2] + "', " + str(100) + ")"
        cursor.execute(sql)     
        connector.commit()
        # 更新老师的id到对应课程
        # sql = "UPDATE `njuTeacher`.`course` SET `teacher_id` = '" + str(i) + "' WHERE `course_id` = '05000240'"
        i = i + 1
        print(teacher + '插入成功')
      else:
        print(teacher + '已存在')

if __name__ == '__main__':
  insert_teacher()