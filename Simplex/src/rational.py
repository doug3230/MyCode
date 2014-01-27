"""
-------------------------------------------------------
rational.py
contains code for the Rational class.
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
    The Rational class models fractions used in math.
    It has two attributes:
    -numerator (the top of the fraction)
    -denominator (the bottom of the fraction)
    Both of these are integers with the denominator not being 0.
    -------------------------------------------------------
    Operations:
        Rational(num, den = 1) - Initializes Rational.
        str                    - Converts Rational to string form.
        num                    - Rational's numerator.
        den                    - Rational's denominator.
        +                      - Sum operation.
        -                      - Subtraction operation.
        *                      - Multiplication operation.
        /                      - Division operation.
        ==                     - Test for equality.
        <                      - Less than comparator.
        <=                     - Less than or equal to comparator.
                               - (and other comparators by extension.)
        is_zero                - Checks if Rational is equal to 0.
        is_int                 - Checks if Rational is an integer.
        to_float               - Rational as a floating point number.
    -------------------------------------------------------
    """
    
    def __init__(self, num, den=1):
        """
        -------------------------------------------------------
        Initializes self.
        -------------------------------------------------------
        Preconditions:
           num - the passed in numerator (int)
           den - the passed in denominator (int != 0)
                 (den is optional and defaults to 1)
        Postconditions:
           Constructs an instance of the Rational class. 
           
        Note: to make working with Rationals easier, the stored
        numerator and denominator have no factors in common.
        Thus Rational(2,4) has the same numerator and denominator
        as  Rational(1,2). Also, negative Rationals have their 
        numerators as negative and the 0 Rational has a denominator
        of 1.
        -------------------------------------------------------
        """
        assert num == int(num), "numerator must be an integer"
        assert den == int(den), "denominator must be an integer"
        assert den != 0, "denominator cannot be 0"
        
        num, den = rational_functions.normalize(num, den)
        # by default has them as floats for some reason
        self._num = int(num)
        self._den = int(den)
        return
    
    def __str__(self):
        """
        -------------------------------------------------------
        Converts self to string form.
        -------------------------------------------------------
        Postconditions:
           returns - If self is an integer, the string form of that integer.
                     Otherwise returns a string of form "'num' / 'den'" (string)
        -------------------------------------------------------
        """
        if self.is_int():
            return str(self._num)
        else:
            return "{0:d} / {1:d}".format(self._num, self._den)
          
    def num(self):
        """
        -------------------------------------------------------
        self's numerator.
        -------------------------------------------------------
        Postconditions:
           returns - the top number of the fraction (int)
        -------------------------------------------------------
        """
        return self._num 
    
    def den(self):
        """
        -------------------------------------------------------
        self's denominator.
        -------------------------------------------------------
        Postconditions:
           returns - the bottom number of the fraction (int)
        -------------------------------------------------------
        """ 
        return self._den
    
    def __add__(self, other):
        """
        -------------------------------------------------------
        The sum of self and other.
        -------------------------------------------------------
        Preconditions:
           other - the right operand of the sum (Rational or float)
        Postconditions:
           returns - the value resulting from the sum (Rational)
        -------------------------------------------------------
        """
        try:
            new_num = (self._num * other._den) + (self._den * other._num)
            new_den = (self._den * other._den)
            return Rational(new_num, new_den)
        except AttributeError:
            return (self +rational_functions.parse_number(other))
        
    def __sub__(self, other):
        """
        -------------------------------------------------------
        The subtraction from self of other.
        -------------------------------------------------------
        Preconditions:
           other - the right operand of the subtraction (Rational or float)
        Postconditions:
           returns - the value resulting from the subtraction (Rational)
        -------------------------------------------------------
        """
        try:
            new_num = (self._num * other._den) - (self._den * other._num)
            new_den = (self._den * other._den)
            return Rational(new_num, new_den)
        except AttributeError:
            return (self -rational_functions.parse_number(other)) 
        
    def __mul__(self, other):
        """
        -------------------------------------------------------
        The product of self and other.
        -------------------------------------------------------
        Preconditions:
            other - the right operand of the multiplication (Rational or float)
        Postconditions:
           returns - the value resulting from the multiplication (Rational)
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
        The division from self of other.
        -------------------------------------------------------
        Preconditions:
           other - the right operand of the division (Rational != 0 or float != 0)
        Postconditions:
           returns - the value resulting from the division (Rational) 
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
        Is self equal to other?
        -------------------------------------------------------
        Preconditions:
           other - the right operand of the '==' test for equality (Rational or float)
        Postconditions:
           returns - True if self and other have the same numerator and denominator,
                     False otherwise.
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
        Is self less than other?
        -------------------------------------------------------
        Preconditions:
           other - the right operand of the '<'comparison (Rational or float)
        Postconditions:
           returns - True if the fraction corresponding to self is less than the
                     fraction corresponding to other, False otherwise.
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
        Is self less than or equal to other?
        -------------------------------------------------------
        Preconditions:
           other - the right operand of the '<=' comparison (Rational or float)
        Postconditions:
           returns - True if the fraction corresponding to self is less than
                     or equal to the fraction corresponding to other, False otherwise.
        -------------------------------------------------------
        """
        return (self < other or self == other)
    
    def is_zero(self):
        """
        -------------------------------------------------------
        Is self equal to 0?
        -------------------------------------------------------
        Postconditions:
           returns - True if the fraction corresponding to self is 0,
                     False otherwise.
        -------------------------------------------------------
        """
        return (self._num == 0)
    
    def is_int(self):
        """
        -------------------------------------------------------
        Is self an integer?
        -------------------------------------------------------
        Postconditions:
           returns - True if the fraction corresponding to self is an integer,
                     False otherwise.
        -------------------------------------------------------
        """ 
        return (self._den == 1)  
    
    def to_float(self):
        """
        -------------------------------------------------------
        self as a floating point number.
        -------------------------------------------------------
        Postconditions:
           returns - self's numerator divided by its denominator (float)
        -------------------------------------------------------
        """ 
        return (self._num / self._den)
        
