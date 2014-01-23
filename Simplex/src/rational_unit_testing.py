'''
Created on Jan 23, 2014

@author: Richard
'''
import unittest

from rational import Rational as Rat

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


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()