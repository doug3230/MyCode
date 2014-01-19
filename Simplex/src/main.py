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
import functions

PROMPT = ">> "
matrix_states = []

print("Welcome to the Simplex program.")
print('Enter "Help" for a list of commands.')
command = input(PROMPT).lower()
while command != "quit":
    try:
        if command == "help":
            functions.print_help()
        elif command == "clear":
            matrix_states = []
        elif command == "load":
            rows, cols = functions.prompt_for_size()
            matrix = functions.prompt_for_matrix(rows, cols)
            matrix_states.append(matrix)
        elif command == "pivot":
            r, c = functions.prompt_for_location()
            cur_matrix = matrix_states[-1]
            pivoted_matrix = cur_matrix.pivot(r, c)
            matrix_states.append(pivoted_matrix)
        elif command == "undo":
            if len(matrix_states) > 0:
                matrix_states.pop()
        else:
            print('Invalid command, enter "Help" for a list of commands.')
    except Exception as inst:
        print("Error: {0}".format(inst))
        
    if len(matrix_states) > 0:
        print(matrix_states[-1])
    command = input(PROMPT).lower()
