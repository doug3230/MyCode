"""
-------------------------------------------------------
[program file name]
[description of main program]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-22
-------------------------------------------------------
"""
from rational import Rational as R
from rational_functions import parse_float

rat = parse_float(1.23) 
print("{0} = {1}".format(rat, rat.to_float()))
