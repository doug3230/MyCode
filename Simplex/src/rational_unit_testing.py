'''
Created on Jan 23, 2014

@author: Richard
'''
import unittest

from rational import Rational as Rat
from rational_functions import gcd, normalize

class RatTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testConstructorAndEquality(self):
        self.assertEqual(Rat(121,132), Rat(11,12), "Terms not reduced by gcd properly")
        self.assertEqual(Rat(0, 5), Rat(0,2), "0s not standardized")
        self.assertEqual(Rat(-9001,666), Rat(9001, -666), "Negatives not working")
        self.assertEqual(Rat(43), 43, "Cannot compare to integers left")
        self.assertEqual(2, Rat(6,3), "Cannot compare to integers right")
        self.assertEqual(1.23456, Rat(123456, 100000), "Cannot compare to floats left")
        self.assertEqual(Rat(1,2), 0.5, "Cannot compare to floats right")
        pass
    
    def testGCD(self):
        self.assertEqual(gcd(21,49),7, "GCD(21, 49) needs to equal 7")
        self.assertEqual(gcd(-21,49),7, "Single negative left case not working")
        self.assertEqual(gcd(21,-49),7, "Single negative right case not working")
        self.assertEqual(gcd(-21,-49),7, "Double negative case not working")
        self.assertEqual(gcd(7,0),7, "0 left case not handled properly")
        self.assertEqual(gcd(0,7),7, "0 right case not handled properly")
        self.assertRaises(AssertionError, gcd, 0, 0)
        pass
    
    def testNormalize(self):
        self.assertEqual(normalize(2,4), (1,2))
        self.assertEqual(normalize(0,-9999), (0,1))
        self.assertEqual(normalize(59, -2), (-59, 2))
        pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()