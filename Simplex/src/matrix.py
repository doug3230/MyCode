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
        Matrix(values) - constructs a Matrix object using a 2d array of numerical values.
    -------------------------------------------------------
    """
    
    def __init__(self, values):
        """
        -------------------------------------------------------
        constructs a Matrix object using values from a 2D array.
        called in main program using Matrix(values)
        -------------------------------------------------------
        Preconditions:
           values - a two-dimensional array of numerical values. 
                    (the two-dimensional array's inner arrays 
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
        [method description]
        -------------------------------------------------------
        Preconditions:
           [parameter name - parameter description (parameter type and constraints)]
        Postconditions:
           [returns: or prints:]
           [return value name - return value description (return value type)] 
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
        [method description]
        -------------------------------------------------------
        Preconditions:
           [parameter name - parameter description (parameter type and constraints)]
        Postconditions:
           [returns: or prints:]
           [return value name - return value description (return value type)] 
        -------------------------------------------------------
        """
        return copy.deepcopy(self._data) 
        
    
    def set_data(self, values):
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
        assert not matrix_util.is_empty_data(values), "Cannot make a Matrix object without values."
        assert matrix_util.is_table_data(values), "Cannot make a Matrix object using non-tabular values."
        self._data = values
        return