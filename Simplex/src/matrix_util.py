"""
-------------------------------------------------------
[function library file name]
[description of main_functions]
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
    return (data == [] or data == [[]])
    

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

def max_str_length(data):
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
    assert not is_empty_data(data), "No data means no length."
    max_length = 0
    for row in data:
        for entry in row:
            cur_length = len(str(entry))
            max_length = max(cur_length, max_length)
    return max_length