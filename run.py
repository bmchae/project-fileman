#!/usr/bin/python

import struct


filename = 'data/test.txt'

sep = { 
4:'empno', 
3:'sal', 
2:'deptno'
}

#sep = (4,3,2)


fmt = ''.join('%ds' % w for w in sep.iterkeys()) 
#fmt = ''.join('%ds' % w for w in sep) 
parse = struct.Struct(fmt).unpack_from

print 'fmt:{!r}'.format(fmt)
print 'record size: {}'.format(struct.calcsize(fmt))

f = open(filename, 'rb')
for line in f:
    columns = parse(line)
    print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    print columns
    print "empno  : %s" %(columns[0])
    print "sal    : %s" %(columns[1])
    print "deptno : %s" %(columns[2])


