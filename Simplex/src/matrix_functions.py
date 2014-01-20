"""
-------------------------------------------------------
matrix_functions
A library of functions to help with the matrix class.
These are functions that wouldn't really be considered
to be matrix operations but are used in carrying out
matrix operations (helper functions so to speak.)
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-17
-------------------------------------------------------
"""

def is_empty_data(data):
    """
    -------------------------------------------------------
    determines if a 2d data structure doesn't
    have any values and thus cannot be used to
    construct a matrix.
    -------------------------------------------------------
    Preconditions:
       data - a 2d data structure meant to contain numbers (float[][])
    Postconditions:
       return True if data has no values, False otherwise.
    -------------------------------------------------------
    """
    return (data == [] or data == [[]])
    

def is_table_data(data):
    """
    -------------------------------------------------------
    determines if a 2d data structure is in tabular form.
    that is, its rows all have the same length. 
    -------------------------------------------------------
    Preconditions:
       data - a collection of numbers (float[][])
    Postconditions:
       returns True if data is in tabular form, False otherwise.
    -------------------------------------------------------
    """
    if (data == [[]]):
        return True
    else:
        i = 1
        rows = len(data[0])
        while i < len(data) and len(data[i]) == rows:
            i += 1
        return (i == len(data))

def max_str_length(data):
    """
    -------------------------------------------------------
    determines the largest string width needed
    when printing numbers as a matrix.
    -------------------------------------------------------
    Preconditions:
       data - a collection of numbers (float[][])
    Postconditions:
       returns the maximum number of characters needed
               when printing the values in data.
    -------------------------------------------------------
    """
    assert not is_empty_data(data), "No data means no length."
    max_length = 0
    for row in data:
        for entry in row:
            cur_length = len(str(entry))
            max_length = max(cur_length, max_length)
    return max_length