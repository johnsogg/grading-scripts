import unittest
from RetroPrinter import RetroPrinter

from basic_functions import *

class RetroTest(unittest.TestCase):

    def __init__(self, methodName, outputName):
        super(RetroTest, self).__init__(methodName) # call superconstructor
        self.outputName = outputName

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_four(self):
        maybe_four = return_four()
        self.assertEqual(4, maybe_four, "return_four should return 4")

    def test_add_numbers(self):
        self.assertEqual(7, add_numbers(2, 5))
        self.assertEqual(9, add_numbers(10, -1))
        self.assertEqual(14, add_numbers(7, 7))
        self.assertEqual(100, add_numbers(900, -800))

    def test_repeat_yourself(self):
        self.assertEqual("NomNomNom", repeat_yourself("Nom", 3))
        self.assertEqual("Tricky!", repeat_yourself("Tricky!", 1))
        self.assertEqual("uuuuuuuuuu", repeat_yourself("u", 10))

    def test_add_one_to_all(self):
        input_list = [5, 10, 40, 19]
        expected_list = [6, 11, 41, 20]
        self.assertEqual(expected_list, add_one_to_all(input_list))

    def test_is_odd(self):
        self.assertEqual(False, is_odd(4))
        self.assertEqual(True, is_odd(3))
        self.assertEqual(False, is_odd(10))
        self.assertEqual(True, is_odd(-13))
        self.assertEqual(False, is_odd(40))
        self.assertEqual(True, is_odd(397321))
    
    def test_which_coin(self):
        self.assertEqual("Penny", which_coin(1))
        self.assertEqual("Nickel", which_coin(5))
        self.assertEqual("Dime", which_coin(10))
        self.assertEqual("Quarter", which_coin(25))

    def test_get_primes_below(self):
        massive = [ 2,   3,  5,  7, 11, 
                    13, 17, 19, 23, 29, 
                    31, 37, 41, 43, 47, 
                    53, 59, 61, 67, 71, 
                    73, 79, 83, 89, 97, 
                    101, 103]
        self.assertEquals(massive[:massive.index(11)], get_primes_below(11))
        self.assertEquals(massive[:massive.index(43)], get_primes_below(42))
        self.assertEquals(massive[:massive.index(47)], get_primes_below(44))
        self.assertEquals(massive[:massive.index(101)], get_primes_below(100))

if __name__ == '__main__':
    suite = unittest.TestSuite()
    tests = [
        RetroTest("test_four", "Four"),
        RetroTest("test_add_numbers", "AddNumbers"),
        RetroTest("test_repeat_yourself", "RepeatYourself"),
        RetroTest("test_add_one_to_all", "AddOneToAll"),
        RetroTest("test_is_odd", "IsOdd"),
        RetroTest("test_which_coin", "WhichCoin"),
        RetroTest("test_get_primes_below", "GetPrimesBelow"),
        ]
    for test in tests:
        suite.addTest(test)
    result = RetroPrinter()
    print "Running Python test suite..."
    suite.run(result)
    print "Done running Python test suite."

