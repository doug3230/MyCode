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

A = Matrix([[  R(1), R(1)],
            [R(-3), R(3)],
            [  R(1), R(0)]])

b = Matrix([[R(7)], [R(15)], [R(4)]])
c = Matrix([[R(0), R(1)]])
c_b = Matrix([[R(0), R(1), R(0)]])

B_inv = Matrix([[R(1), R(-1,3), R(0)], 
                [R(0), R(1, 3), R(0)],
                [R(0), R(0),    R(1)]])

print((c_b * (B_inv * A)) - c)
print(B_inv * A)
print(c_b * B_inv)
print(B_inv)
print(c_b * (B_inv * b))
print(B_inv * b)