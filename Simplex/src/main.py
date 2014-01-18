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
my_other_matrix = Matrix([[0,5],[6,7]])

print(my_matrix.add(my_other_matrix))
print(my_matrix.subtract(my_other_matrix))
