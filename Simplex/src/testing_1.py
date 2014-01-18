"""
-------------------------------------------------------
[program file name]
[description of main program]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-18
-------------------------------------------------------
"""
from matrix import Matrix
import test_functions

A = Matrix([[1, 2], [3, 4]])
B = Matrix([[5, 6], [7, 8]])
C = Matrix([[9, 10, 11], [12, 0, 14]])

TEST_MATRICES = (A, B, C)
TEST_NAMES = ("A", "B", "C")

SIZE_TESTS = ((A, B), (A, C), (B, C))
SIZE_TEST_NAMES = (("A", "B"), ("A", "C"), ("B", "C"))
ADD_TESTS = ((A, B), (B, A))
ADD_TEST_NAMES = ("A + B", "B + A")
SUB_TESTS = ((A, B), (B, A))
SUB_TEST_NAMES = ("A - B", "B - A")

for i in range(len(TEST_MATRICES)):
    matrix = TEST_MATRICES[i]
    name = TEST_NAMES[i]
    print("Matrix {0}:".format(name))
    print(matrix)
    print("Rows: {0}, Columns: {1}".format(matrix.rows(), matrix.cols()))
    if (matrix.is_square()):
        print("{0} is a square matrix.".format(name))
    else:
        print("{0} is not a square matrix.".format(name))
    print()

for i in range(len(SIZE_TESTS)):
    left = SIZE_TESTS[i][0]
    left_name = SIZE_TEST_NAMES[i][0]
    right = SIZE_TESTS[i][1]
    right_name = SIZE_TEST_NAMES[i][1]
    if (left.is_same_size(right)):
        print("{0} and {1} are the same size.".format(left_name, right_name))
    else:
        print("{0} and {1} are not the same size.".format(left_name, right_name))
    print()

for i in range(len(ADD_TESTS)):
    left = ADD_TESTS[i][0]
    right = ADD_TESTS[i][1]
    print("{0}:".format(ADD_TEST_NAMES[i]))
    print(left.add(right))
    print()
    
for i in range(len(SUB_TESTS)):
    left = SUB_TESTS[i][0]
    right = SUB_TESTS[i][1]
    print("{0}:".format(SUB_TEST_NAMES[i]))
    print(left.subtract(right))
    print()
    
for i in range(len(TEST_MATRICES)):
    test_functions.pivot_test(TEST_MATRICES[i], TEST_NAMES[i])
