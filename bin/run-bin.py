#!/usr/bin/python

#from struct import pack 
#from struct import unpack 
#from struct import *
import struct

f = open('data/test.txt', 'rb')
for rec in f:
    print struct.unpack('BBB', rec)


s = struct.Struct("< 3B 3B")
print s.unpack("abc123")
