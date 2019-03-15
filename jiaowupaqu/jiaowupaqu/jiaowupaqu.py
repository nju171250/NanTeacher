import requests
from requests.exceptions import RequestException
import pickle
import re
import interact_db as db
import _thread

major = ['010', '020', '030', '040', '050', '060', '070', '080', '090', '100', '110', '120', '130', '140', '150', '160', '170', '180', '190', '200', '210', '220', '230', '241', '250', '261', '270', '280', '291', '811', '821', '831', '841', '851', '861', '871', '881', '891', 'A41', 'A91', 'AA1', 'AC1', 'AD1']
curGrade = ('2018', '2017', '2016', '2015', '2014', '2013', '2012')
curSpeciality = ('01' , '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '81', '82', '83', '84', '85', '86', '87', '88', '89', 'A4', 'A9', 'AA', 'AC', 'AD')
curTerm = ('20182', '20181', '20172', '20171', '20162', '20161', '20152', '20151', '20142', '20141', '20132', '20131')
def get_one_page(url):
  cookies = None
  with open('cookies','rb') as file:
    cookies = pickle.load(file)
  try:
    response = requests.get(url, cookies=cookies)
    if response.status_code == 200:
      return response.text
    return None
  except RequestException: 
    return None


def parse_one_page(html):
  pattern = re.compile('<tr.*?<td>.*?jiaowu/student/elective.*?<u>(\d+)</u>.*?<td valign="middle">(.*?)</td>.*?<td align="center" valign="middle">(.*?)</td>.*?<td align="center" valign="middle">(.*?)</td>.*?<td align="center" valign="middle">(.*?)</td>.*?<td align="center" valign="middle">(.*?)</td>.*?<td align="center" valign="middle">(.*?)</td>.*?<td valign="middle">(.*?)</td>.*?</tr>', re.S)
  items = re.findall(pattern, html)
  for item in items:
    db.insert_course(item)
    # print(items)
    
def thread_insert(grade, term):
  for x in major:
    url = 'http://elite.nju.edu.cn/jiaowu/student/teachinginfo/allCourseList.do?method=getCourseList&curTerm=' + term + '&curSpeciality=' + x + '&curGrade=' + grade
    html = get_one_page(url)
    parse_one_page(html)


def main():
  print('开始')
  for grade in curGrade:
    for term in curTerm:
      thread_insert(grade, term)
if __name__ == '__main__':
  main()