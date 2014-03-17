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

A = Matrix(((1,0,0),(0,1,0),(0,0,2)))
A_T = Matrix(((1,0,0),(0,1,0),(0,0,2)))
Inv = Matrix(((1,0,0),(0,1,0),(0,0,0.25)))
print(A * A_T * Inv)
