"""
-------------------------------------------------------
test_functions
A library of functions for performing testing.
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-18
-------------------------------------------------------
"""

def pivot_test(matrix, matrix_name = "matrix"):
    """
    -------------------------------------------------------
    Takes a matrix and shows what happens when you pivot on
    each of the matrix's nonzero entries.
    -------------------------------------------------------
    Preconditions:
       matrix - an arbitrary Matrix object (Matrix)
    Postconditions:
       prints the Matrix objects returned when you call the
              pivot operation on matrix at each of its nonzero
              entries.
    -------------------------------------------------------
    """
    for i in range(matrix.rows()):
        for j in range(matrix.cols()):
            if (matrix.entry(i, j) == 0): continue
            print("Pivot on {0} at ({1}, {2})".format(matrix_name, i, j))
            print(matrix.pivot(i, j))
            print()
    return
