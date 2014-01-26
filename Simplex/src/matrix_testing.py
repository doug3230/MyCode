"""
-------------------------------------------------------
[program file name]
[description of main program]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-23
-------------------------------------------------------
"""
from matrix import Matrix
from rational import Rational as R

obj = Matrix([[R(3), R(6)]])


A = Matrix([[R(1), R(1)],
            [R(1), R(3)],
            [R(1), R(0)]])

B = Matrix([[R(1),R(1),R(0)], [R(0), R(3), R(0)], [R(0), R(0), R(1)]])
B_inv = Matrix([[R(1), R(-1,3), R(0)], [R(0), R(1,3), R(0)], [R(0), R(0), R(1)]])

c = Matrix([[R(0),R(6),R(0)]])
b = Matrix([[R(7)], [R(15)], [R(4)]])
y = c*B_inv


print(y*A - obj)
print(y)
print(y*b)
print(B_inv*A)
print(B_inv)
print(B_inv * b)

