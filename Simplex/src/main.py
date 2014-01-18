"""
-------------------------------------------------------
[program file name]
[description of main program]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-17
-------------------------------------------------------
"""
from matrix import Matrix

my_matrix = Matrix([[1,2], [3,4]])
print(my_matrix.rows(), my_matrix.cols())
for row in my_matrix._data:
    print(row)
print(my_matrix.pivot(0, 1))

#print(my_matrix.pivot(0, 1))
