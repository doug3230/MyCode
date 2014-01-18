"""
-------------------------------------------------------
[function library file name]
[description of functions]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-17
-------------------------------------------------------
"""
#[import statements]

#[constants]

def is_empty_data(data):
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
    return (data == [[]])
    

def is_table_data(data):
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
    if (data == [[]]):
        return True
    else:
        i = 1
        rows = len(data[0])
        while i < len(data) and len(data[i]) == rows:
            i += 1
        return (i == len(data))