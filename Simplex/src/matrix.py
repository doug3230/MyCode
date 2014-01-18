"""
-------------------------------------------------------
matrix.py
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-17
-------------------------------------------------------
"""
import copy
import matrix_util

#[constants]

class Matrix:
    """
    -------------------------------------------------------
    Used for storing numbers in a tabular form. 
    This is so that mathematical operations can be performed
    and important information can be deduced.
    -------------------------------------------------------
    public variables:
        [variable name - description of variable]
    public methods:
        Matrix(values) - constructs a Matrix object using a 2d list of numerical values.
    -------------------------------------------------------
    """
    
    def __init__(self, values):
        """
        -------------------------------------------------------
        constructs a Matrix object using values from a 2D list.
        called in main program using Matrix(values)
        -------------------------------------------------------
        Preconditions:
           data - a two-dimensional list of numerical values (float[][]) 
                    (the two-dimensional list's inner lists 
                     must all be of the same length.)
        Postconditions:
           returns a new Matrix object storing the data. 
        -------------------------------------------------------
        """
        self.set_data(values)
        return
    
    def __str__(self):
        """
        -------------------------------------------------------
        Converts matrix to string form for printing.
        The string form consists of a small table with entries
        separated by commas as denoted in standard Linear Algebra books.
        -------------------------------------------------------
        Preconditions:
           none
        Postconditions:
           returns matrix as a string (string)
        -------------------------------------------------------
        """
        length = matrix_util.max_str_length(self._data)
        string_form = ""
        for row in self._data:
            string_form += "|{0:^{1}}".format(row[0],length)
            for entry in row[1:]:
                string_form += ", {0:^{1}}".format(entry,length)
            string_form += "|\n"
        return string_form
        
    def get_data(self):
        """
        -------------------------------------------------------
        Returns a copy of the matrix's data in a 2d list.
        -------------------------------------------------------
        Preconditions:
           none
        Postconditions:
           returns matrix's numerical values in a 2d list (float[][])
        -------------------------------------------------------
        """
        return copy.deepcopy(self._data) 
        
    
    def set_data(self, values):
        """
        -------------------------------------------------------
        Changes this matrix's values to those passed in.
        -------------------------------------------------------
        Preconditions:
           values - a 2d list of numerical values (float[][]) 
                    (values must have inner lists that all have the 
                    same length and must have at least one value) 
        Postconditions:
           matrix discards its old data and sets the values as its new data
        -------------------------------------------------------
        """ 
        assert not matrix_util.is_empty_data(values), "Cannot make a Matrix object without values."
        assert matrix_util.is_table_data(values), "Cannot make a Matrix object using non-tabular values."
        self._data = values
        return
    
    def rows(self):
        """
        -------------------------------------------------------
        The number of rows in this matrix
        -------------------------------------------------------
        Preconditions:
           none
        Postconditions:
           return the number of rows in the matrix (int)
        -------------------------------------------------------
        """
        return len(self._data)
    
    def cols(self):
        """
        -------------------------------------------------------
        The number of columns in this matrix
        -------------------------------------------------------
        Preconditions:
           none
        Postconditions:
           return the number of columns in the matrix (int)
        -------------------------------------------------------
        """
        return len(self._data[0])
    
    def is_same_size(self, other):
        """
        -------------------------------------------------------
        is this matrix the same size as the other?
        -------------------------------------------------------
        Preconditions:
           other - the Matrix to compare with (Matrix)
        Postconditions:
           returns True if matrix and other have the same number of rows and columns,
                   False otherwise (boolean)
        -------------------------------------------------------
        """
        same_rows = self.rows() == other.rows()
        same_cols = self.cols() == other.cols()
        return (same_rows and same_cols)
    
    def is_square(self):
        """
        -------------------------------------------------------
        is this matrix is a square Matrix?
        -------------------------------------------------------
        Preconditions:
           none
        Postconditions:
           returns True if matrix has the same number of rows as it has columns,
                   False otherwise (boolean)
        -------------------------------------------------------
        """ 
        return (self.rows() == self.cols())
    
    def add(self, other):
        """
        -------------------------------------------------------
        [method description]
        -------------------------------------------------------
        Preconditions:
           [parameter name - parameter description (parameter type and constraints)]
        Postconditions:
           [returns: or prints:]
           [return value name - return value description (return value type)] 
        -------------------------------------------------------
        """
        assert self.is_same_size(other), "Cannot perform addition on matrices with different dimensions."
        addition_values = []
        for i in range(self.rows()):
            new_row = []
            for j in range(self.cols()):
                new_row.append(self._data[i][j] + other._data[i][j])
            addition_values.append(new_row)
        return Matrix(addition_values)
    
    def subtract(self, other):
        """
        -------------------------------------------------------
        [method description]
        -------------------------------------------------------
        Preconditions:
           [parameter name - parameter description (parameter type and constraints)]
        Postconditions:
           [returns: or prints:]
           [return value name - return value description (return value type)] 
        -------------------------------------------------------
        """
        assert self.is_same_size(other), "Cannot perform subtraction on matrices with different dimensions."
        subtraction_values = []
        for i in range(self.rows()):
            new_row = []
            for j in range(self.cols()):
                new_row.append(self._data[i][j] - other._data[i][j])
            subtraction_values.append(new_row)
        return Matrix(subtraction_values)
                
        