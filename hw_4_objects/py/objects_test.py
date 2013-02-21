import unittest
from RetroPrinter import RetroPrinter
from sys import argv

from objects import *

class RetroTest(unittest.TestCase):

    def __init__(self, methodName, outputName):
        super(RetroTest, self).__init__(methodName) # call superconstructor
        self.outputName = outputName

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def init_student(self, n, a, m):
        s = Student()
        s.name = n
        s.age = a
        s.major = m
        return s

    def init_students(self):
        ret = []
        ret.append(self.init_student("James Bond", 38, "Bartending"))
        ret.append(self.init_student("Charlie Parker", 50, "Music"))
        ret.append(self.init_student("Marky Mark", 20, "Music"))
        ret.append(self.init_student("Bob Bobson", 21, "Landscape Architecture"))
        return ret

    def test_make_student(self):
        n = "Geronimo"
        a = 40
        m = "Landscape Architecture"
        s = make_student(n, a, m)
        self.assertEqual(n, s.name)
        self.assertEqual(a, s.age)
        self.assertEqual(m, s.major)

    def test_make_student_older(self):
        many = self.init_students()
        for s in many:
            a = s.age
            make_student_older(s)
            self.assertEqual(a+1, s.age, "Student did not get older")

    def test_copy_student(self):
        many = self.init_students()
        for s in many:
            s_copy = copy_student(s)
            self.assertIsNotNone(s_copy)
            self.assertTrue(s_copy.name == s.name)
            self.assertTrue(s_copy.age == s.age)
            self.assertTrue(s_copy.major == s.major)
            self.assertFalse(s_copy is s)

    def test_is_older(self):
        many = self.init_students()
        self.assertTrue(is_older(many[0])) # 38
        self.assertTrue(is_older(many[1])) # 50
        self.assertFalse(is_older(many[2])) # 20
        self.assertFalse(is_older(many[3])) # 21

    def test_is_musician(self):
        many = self.init_students()
        self.assertFalse(is_musician(many[0]))
        self.assertTrue(is_musician(many[1]))
        self.assertTrue(is_musician(many[2]))
        self.assertFalse(is_musician(many[3]))

    def test_is_older_musician(self):
        many = self.init_students()
        self.assertFalse(is_older_musician(many[0]))
        self.assertTrue(is_older_musician(many[1]))
        self.assertFalse(is_older_musician(many[2]))
        self.assertFalse(is_older_musician(many[3]))


if __name__ == '__main__':
    retro = False
    if (len(argv) > 1 and argv[1] == "--retrograde"):
        retro = True
    print "Retro mode: " + str(retro)
    suite = unittest.TestSuite()
    # now pair the above functions to the retrograde name
    tests = [
        RetroTest("test_make_student", "MakeStudent"),
        RetroTest("test_make_student_older", "MakeStudentOlder"),
        RetroTest("test_copy_student", "CopyStudent"),
        RetroTest("test_is_older", "IsOlder"),
        RetroTest("test_is_musician", "IsMusician"),
        RetroTest("test_is_older_musician", "IsOlderMusician"),
        ]
    for test in tests:
        suite.addTest(test)
    if retro:
        result = RetroPrinter()
        print "Running Python test suite..."
        suite.run(result)
        print "Done running Python test suite."
    else:
        runner = unittest.TextTestRunner()
        runner.run(suite)
