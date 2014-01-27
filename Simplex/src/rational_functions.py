"""
-------------------------------------------------------
[function library file name]
[description of functions]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-21
-------------------------------------------------------
"""

def gcd(a, b):
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
    assert a != 0 or b != 0, "gcd(0, 0) is not defined."
    a, b = abs(a), abs(b)
    return gcd_aux(max(a, b), min(a, b))

def gcd_aux(a, b):
    if (b == 0):
        return a
    else:
        return gcd_aux(b, a % b)

def normalize(num, den):
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
    d = gcd(num, den)
    num, den = num / d, den / d
    
    if (den < 0):
        num, den = -num, -den
    if (num == 0):
        den = 1 
    return num, den    