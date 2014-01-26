"""
-------------------------------------------------------
main
A simple program for working with matrices.
Allows the user to load matrices, and pivot
on their entries in order to carry out the
Simplex algorithm.

This is also handy for solving systems of
equations since pivoting really just corresponds
to doing a step of Gaussian Elimination.
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-17
-------------------------------------------------------
"""
import main_functions

PROMPT = ">> "
matrix_states = []

print("Welcome to the Simplex program.")
print('Enter "Help" for a list of commands.')
command = input(PROMPT).lower()
while command != "quit":
    try:
        if command == "help":
            main_functions.print_help()
        elif command == "clear":
            matrix_states = []
        elif command == "load":
            rows, cols = main_functions.prompt_for_size()
            matrix = main_functions.prompt_for_matrix(rows, cols)
            matrix_states.append(matrix)
        elif command == "load rows":
            rows, cols = main_functions.prompt_for_size()
            matrix = main_functions.prompt_for_matrix_row_by_row(rows, cols)
            matrix_states.append(matrix)
        elif command == "pivot":
            r, c = main_functions.prompt_for_location()
            cur_matrix = matrix_states[-1]
            pivoted_matrix = cur_matrix.pivot(r, c)
            matrix_states.append(pivoted_matrix)
        elif command == "undo":
            if len(matrix_states) > 0:
                matrix_states.pop()
        elif command == "print floats":
            if len(matrix_states) == 0:
                print("No values to print")
            else:
                rat_matrix = matrix_states[0]
                float_matrix = main_functions.convert_to_floats(rat_matrix)
                print(float_matrix)
        else:
            print('Invalid command, enter "Help" for a list of commands.')
    except Exception as inst:
        print(type(inst).__name__)
        print("Error: {0}".format(inst))
        
        
    if len(matrix_states) > 0 and command != "print floats":
        print(matrix_states[-1])
    command = input(PROMPT).lower()
