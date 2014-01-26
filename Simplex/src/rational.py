"""
-------------------------------------------------------
[class file name]
[description of classes]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-21
-------------------------------------------------------
"""
import rational_functions

class Rational:
    """
    -------------------------------------------------------
    [class description]
    -------------------------------------------------------
    public variables:
        [variable name - description of variable]
    public methods:
        [method name - description of method] 
    -------------------------------------------------------
    """
    
    def __init__(self, num, den=1):
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
        assert num == int(num), "numerator must be an integer"
        assert den == int(den), "denominator must be an integer"
        assert den != 0, "denominator cannot be 0"
        
        num, den = rational_functions.normalize(num, den)
        #by default has them as floats for some reason
        self._num = int(num)
        self._den = int(den)
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
        if self.is_int():
            return str(self._num)
        else:
            return "{0:d} / {1:d}".format(self._num, self._den)
          
    def num(self):
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
        return self._num 
    
    def den(self):
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
        return self._den
    
    def __add__(self, other):
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
        try:
            new_num = (self._num * other._den) + (self._den * other._num)
            new_den = (self._den * other._den)
            return Rational(new_num, new_den)
        except AttributeError:
            return (self + rational_functions.parse_number(other))
        
    def __sub__(self, other):
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
        try:
            new_num = (self._num * other._den) - (self._den * other._num)
            new_den = (self._den * other._den)
            return Rational(new_num, new_den)
        except AttributeError:
            return (self - rational_functions.parse_number(other)) 
        
    def __mul__(self, other):
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
        try:
            new_num = (self._num * other._num)
            new_den = (self._den * other._den)
            return Rational(new_num, new_den)
        except AttributeError:
            return (self * rational_functions.parse_number(other)) 
    
    def __truediv__(self, other):
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
        try:
            assert not other.is_zero(), "cannot divide by 0."
            new_num = (self._num * other._den)
            new_den = (self._den * other._num) 
            return Rational(new_num, new_den)
        except AttributeError:
            return (self / rational_functions.parse_number(other)) 
    
    def __eq__(self, other):
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
        try: 
            same_num = (self._num == other._num)
            same_den = (self._den == other._den)
            return same_num and same_den
        except AttributeError:
            return (self == rational_functions.parse_number(other))
    
    def __lt__(self, other):
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
        try:
            lhs = (self._num * other._den)
            rhs = (other._num * self._den)
            return (lhs < rhs)
        except AttributeError:
            return (self < rational_functions.parse_number(other))
        
    def __le__(self, other):
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
        return (self < other or self == other)
    
    def is_zero(self):
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
        return (self._num == 0)
    
    def is_int(self):
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
        return (self._den == 1)  
    
    def to_float(self):
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
        return (self._num / self._den)
        
