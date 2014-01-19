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
from matrix import Matrix

COMMANDS = (("Clear", "Clears all matrices from memory."),
            ("Help", "Display commands and descriptions."), 
            ("Load", "Load a new matrix."),
            ("Pivot", "Perform a pivot on the matrix."),
            ("Quit", "Quit program."),
            ("Undo", "Change to previous matrix."))

def print_help():
    for command in COMMANDS:
        print("{0}: {1}".format(command[0], command[1]))
    return

def prompt_for_size():
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
    rows = int(input("Enter the number of rows: "))
    cols = int(input("Enter the number of columns: "))
    return (rows, cols)

def prompt_for_location():
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
    row_index = int(input("Enter the row index: "))
    col_index = int(input("Enter the column index: "))
    return (row_index, col_index) 

def prompt_for_matrix(rows, cols):
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
    values = []
    for i in range(rows):
        row = []
        for j in range(cols):
            entry = input("Enter entry ({0}, {1}): ".format(i, j))
            if entry.startswith("-") and entry[1:].isdigit():
                entry = int(entry)
            elif entry.isdigit():
                entry = int(entry)
            else:
                entry = float(entry)
            row.append(entry)
        values.append(row)
    return Matrix(values)
    
