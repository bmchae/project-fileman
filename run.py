#!/usr/bin/python

import struct

sep = (4, 3, 2)
fmt = ''.join('%ds' % w for w in sep) 
parse = struct.Struct(fmt).unpack_from

print 'fmt:{!r}'.format(fmt)
print 'record size: {}'.format(struct.calcsize(fmt))

f = open('data/test.txt', 'rb')
for line in f:
    columns = parse(line)
    print columns


