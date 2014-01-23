'''
Created on Jan 23, 2014

@author: Richard
'''
import unittest

from rational import Rational

class RatTest(unittest.TestCase):


    def setUp(self):
        pass


    def tearDown(self):
        pass

    def testConstructor(self):
        self.assertEqual(Rational(1,2), Rational(2,4), "Terms not reduced by gcd properly")
        self.assertEqual(Rational(0, 5), Rational(0,2), "0s not standardized")
        self.assertEqual(Rational(-9001,666), Rational(9001, -666), "Negatives not working")
        pass


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()