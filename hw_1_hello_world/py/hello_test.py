import unittest

from RetroPrinter import RetroPrinter

class RetroTest(unittest.TestCase):
    
    def __init__(self, methodName, outputName):
        super(RetroTest, self).__init__(methodName) # call superconstructor
        self.outputName = outputName

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testHelloWorld(self):
        pass

if __name__ == '__main__':
    suite = unittest.TestSuite() 
    tests = [ 
        RetroTest("testHelloWorld", "HelloWorld"),
        ]

    for test in tests:
        suite.addTest(test)
    
    result = RetroPrinter()
    print "Running Python test suite..."
    suite.run(result)
    print "... done running Python test suite."
