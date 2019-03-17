#!/usr/bin/python3
# -*- coding:utf8 -*-
import requests
import pickle

def login():

    codeUrl = 'http://elite.nju.edu.cn/jiaowu/ValidateCode.jsp'
    code = requests.get(url=codeUrl)
    cookies = code.cookies  # 保存验证码cookies
    f = open('Code.png', 'wb')
    f.write(code.content)  # 按照content(二进制)写入
    f.close()

    codeNum = input()  # 手动打开Code.png输入验证码

    jw = requests.session()  # 开始登录教务

    data = {'userName': '171250655',
            'password': '*******',
            'returnUrl': 'null',
            'ValidateCode': codeNum}

    jwRes = jw.post("http://elite.nju.edu.cn/jiaowu/login.do", data=data, cookies=cookies)
    return cookies

if __name__ == '__main__':
    cookies  = login()
    with open('cookies','wb') as file:
        pickle.dump(cookies,file)
