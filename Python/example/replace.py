#!/usr/bin/python
s = open("modify.txt").read()
s = s.replace('id=1', 'id=2')
f = open("modify.txt", 'w')
f.write(s)
f.close()

#
#import string
#s = open("modify.txt","r+")
#for line in s.readlines():
#   print line
#   string.replace(line, 'id=1','id=2')
#   print line
#s.close()

