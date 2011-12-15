#coding:utf8

import urllib

data = urllib.urlopen('http://upload.cnodejs.net/3/d/3d33cc1c3da4fbd5b15eaf37c6bf8238.txt').read()

data = data.decode('gbk')

print dir(data.index(u'汇总结果')), data.index(u'汇总结果'), data.index(u'路由信息')
start = data.index(u'汇总结果')
end = data.index(u'路由信息')
print data[start:end]