"""
-------------------------------------------------------
main_functions.py
A library of functions for use in the main program.
These functions are mainly for performing I/O tasks.
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-17
-------------------------------------------------------
"""
from matrix import Matrix
import rational_functions

# command names and descriptions
COMMANDS = (("Clear", "Clears all matrices from memory."),
            ("Help", "Display commands and descriptions."),
            ("Load", "Load a new matrix."),
            ("Load Rows", "Load a new matrix, entering values row by row."),
            ("Pivot", "Perform a pivot on the matrix."),
            ("Quit", "Quit program."),
            ("Undo", "Undo previous command"))

def print_help():
    """
    -------------------------------------------------------
    prints each command name and a description of what it does.
    -------------------------------------------------------
    Preconditions:
       none
    Postconditions:
       prints each command name and description on a line.
       one line per command. 
    -------------------------------------------------------
    """
    for command in COMMANDS:
        print("{0}: {1}".format(command[0], command[1]))
    return

def prompt_for_size():
    """
    -------------------------------------------------------
    asks the user for the number of rows and columns.
    -------------------------------------------------------
    Preconditions:
       none
    Postconditions:
       returns a tuple containing the number of rows and columns
               entered by the user in (rows, cols) format.
    -------------------------------------------------------
    """ 
    rows = int(input("Enter the number of rows: "))
    cols = int(input("Enter the number of columns: "))
    return (rows, cols)

def prompt_for_location():
    """
    -------------------------------------------------------
    asks the user which entry of a matrix they would like to
    do stuff with.
    -------------------------------------------------------
    Preconditions:
       none
    Postconditions:
       returns a tuple containing the row index and column index
               entered by the user in (row_index, column_index) format.
    -------------------------------------------------------
    """
    row_index = int(input("Enter the row index: "))
    col_index = int(input("Enter the column index: "))
    return (row_index, col_index) 

def prompt_for_matrix(rows, cols):
    """
    -------------------------------------------------------
    prompts the user for the entries to put in the matrix.
    -------------------------------------------------------
    Preconditions:
       rows - the number of rows the matrix will have (int > 0)
       cols - the number of columns the matrix will have (int > 0)
    Postconditions:
       returns a matrix with the values entered by the user. 
    -------------------------------------------------------
    """
    values = []
    for i in range(rows):
        row = []
        for j in range(cols):
            entry = input("Enter entry ({0}, {1}): ".format(i, j))
            rat_value = convert_to_rational(entry) 
            row.append(rat_value)
        values.append(row)
    return Matrix(values)

def prompt_for_matrix_row_by_row(rows, cols):
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
    print("Enter values separated by commas.")
    matrix_rows = []
    for i in range(rows):
        input_line = input("Row {0}:".format(i))
        new_row = input_line.split(",")
        assert len(new_row) == cols, "invalid row, wrong number of values"
        for j in range(len(new_row)):
            new_row[j] = new_row[j].strip()
            new_row[j] = convert_to_rational(new_row[j])
        matrix_rows.append(new_row)
    return Matrix(matrix_rows)
    

def convert_to_rational(str_value):
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
    if ("." in str_value):
        rat_value = rational_functions.parse_float(str_value)
    else:
        rat_value = rational_functions.parse_string(str_value)
    return rat_value
