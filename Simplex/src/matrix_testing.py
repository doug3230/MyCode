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

A = Matrix([[R(2),R(5),R(8)],
            [R(1),R(2),R(5)],
            [R(1),R(3),R(7)]])

b = Matrix([[R(18)], [R(8)], [R(10)]])
minus_c = Matrix([[R(-6), R(-10), R(-34)]])

B = Matrix([[R(1),R(2),R(8)],
            [R(0), R(1), R(5)],
            [R(0), R(1), R(7)]])

B_Inv = Matrix([[R(1), R(-3), R(1)],
                [R(0), R(7,2), R(-5,2)],
                [R(0), R(-1,2), R(1,2)]])

c_B = Matrix([[R(0), R(6), R(34)]])

b_ = Matrix([[R(18)], [R(10)], [R(16)]])
b__ = Matrix([[R(13)], [R(15,2)], [R(10)]])
minus_c_ = Matrix([[R(-6), R(-15), R(-34)]])
minus_c__ = Matrix([[R(-8), R(-12), R(-32)]])
c_B__ = Matrix([[R(0), R(8), R(32)]])
y__ = c_B__ * B_Inv

y = c_B * B_Inv

A_ = Matrix([[R(2),R(6),R(8)],
            [R(1),R(3,2),R(5)],
            [R(1),R(3),R(7)]])

A__ = Matrix([[R(2),R(5),R(8),R(1)],
             [R(1),R(2),R(5),R(0)],
             [R(1),R(3),R(7),R(4)]])

minus_c_4 = Matrix([[R(-6), R(-10), R(-34), R(-9)]])

def print_matrices():
    print(minus_c + (y * A), y, y * b)
    print()
    print(B_Inv * A)
    print()
    print(B_Inv)
    print()
    print(B_Inv * b)
    return

print(minus_c_4 + (y * A__))
print()
print(B_Inv * A__)


