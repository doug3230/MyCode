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
        [method name - description of method] 
    -------------------------------------------------------
    """
    
    def __init__(self, values):
        """
        -------------------------------------------------------
        constructs a Matrix object using values from a 2D array.
        called in main program using Matrix(values)
        -------------------------------------------------------
        Preconditions:
           values - a two-dimensional array of numerical data. 
                    (the two-dimensional array's inner arrays 
                     must all be of the same length.)
        Postconditions:
           returns a new Matrix object storing the data. 
        -------------------------------------------------------
        """
        self.set_data(values)
        return
    
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
        assert not is_empty_data(values), "Cannot make a Matrix object without values."
        assert is_table_data(values), "Cannot make a Matrix object using non-tabular values."
        self._data = values
        return
    
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