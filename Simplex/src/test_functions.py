"""
-------------------------------------------------------
[function library file name]
[description of functions]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-18
-------------------------------------------------------
"""
#[import statements]

#[constants]

def pivot_test(matrix, matrix_name = "matrix"):
    """
    -------------------------------------------------------
    [function description]
    -------------------------------------------------------
    Preconditions:
       [parameter name - parameter description (parameter type and constraints)]
    Postconditions:
       [returns: or prints:]
       [return value name - return value description (return value type)] 
    -------------------------------------------------------
    """
    for i in range(matrix.rows()):
        for j in range(matrix.cols()):
            if (matrix.entry(i, j) == 0): continue
            print("Pivot on {0} at ({1}, {2})".format(matrix_name, i, j))
            print(matrix.pivot(i, j))
            print()
    return
