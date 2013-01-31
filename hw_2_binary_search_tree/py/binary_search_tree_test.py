import unittest
from RetroPrinter import RetroPrinter
from sys import argv

from binary_search_tree import *

class RetroTest(unittest.TestCase):

    def __init__(self, methodName, outputName):
        super(RetroTest, self).__init__(methodName) # call superconstructor
        self.outputName = outputName

    def mknode(self, num):
        ret = bt_node()
        ret.data = num
        return ret

    def private_inorder_fetch(self, node, numbers):
        if node is None:
            return
        else:
            self.private_inorder_fetch(node.left, numbers)
            numbers.append(node.data)
            self.private_inorder_fetch(node.right, numbers)

    def build_simple_tree(self):
        top = self.mknode(1)
        top.left = self.mknode(0)
        top.right = self.mknode(4)
        top.right.left = self.mknode(3)
        top.right.left.left = self.mknode(2)
        top.right.right = self.mknode(5)
        ret = BinarySearchTree()
        ret.root_node = top
        return ret;

    def build_big_tree(self):
        # from Peyman:
        # remove does not work for the following example if I want to remove 20
        #                    20
        #                  /    \
        #               10       27
        #              /   \      \
        #           8       12     28
        #        /   \     /  \
        #      6      9   11   14
        # inorder starts out as:
        # [ 6, 8, 9, 10, 11, 12, 14, 20, 27, 28 ]
        top = self.mknode(20)
        top.left = self.mknode(10)
        top.left.left = self.mknode(8)
        top.left.left.left = self.mknode(6)
        top.left.left.right = self.mknode(9)
        top.left.right = self.mknode(12)
        top.left.right.left = self.mknode(11)
        top.left.right.right = self.mknode(14)
        top.right = self.mknode(27)
        top.right.right = self.mknode(28)
        ret = BinarySearchTree()
        ret.root_node = top
        return ret

    def setUp(self):
        self.tree = BinarySearchTree()

    def tearDown(self):
        pass

    def test_init_node(self):
        n = self.tree.init_node(42)
        self.assertIsNotNone(n, "New node should not be None")
        self.assertEqual(42, n.data, "New node should have data = 42")
        self.assertIsNone(n.left, "New node should have None children")
        self.assertIsNone(n.right, "New node should have None children")

    def test_insert_data(self):
        top = self.mknode(2)
        self.tree.root_node = top

        self.tree.insert_data(1)
        expected = [1, 2]
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Insert failed to result in tree with 1 2 inorder walk")
        
        self.tree.insert_data(3)
        expected = [1, 2, 3]
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Insert failed to result in tree with 1 2 3 inorder walk")

        self.tree.insert_data(4)
        expected = [1, 2, 3, 4]
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Insert failed to result in tree with 1 2 3 4 inorder walk")

    def test_insert(self):
        top = self.mknode(2)
        self.tree.root_node = top
        
        self.tree.insert(self.mknode(1))
        expected = [1, 2]
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Insert failed to result in tree with 1 2 inorder walk")
        
        self.tree.insert(self.mknode(3))
        expected = [1, 2, 3]
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Insert failed to result in tree with 1 2 3 inorder walk")

        self.tree.insert(self.mknode(4))
        expected = [1, 2, 3, 4]
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Insert failed to result in tree with 1 2 3 4 inorder walk")

    def test_size(self):
        self.assertEqual(0, self.tree.size(), "Empty tree size should be 0")
        self.tree = self.build_simple_tree()
        self.assertEqual(6, self.tree.size(), "Tree should have size of 6")

    def test_contains(self):
        self.tree = self.build_simple_tree()
        for i in range(0, 6):
            self.assertTrue(self.tree.contains(i), "Tree does not contain " + str(i))
        for i in range (6, 10):
            self.assertFalse(self.tree.contains(i), "Tree claims to contain " + str(i) + " but it should not.")

    def test_get_node(self):
        self.tree = self.build_simple_tree()
        top = self.tree.root_node
        zero = self.tree.get_node(0)
        one = self.tree.get_node(1)
        two = self.tree.get_node(2)
        three = self.tree.get_node(3)
        four = self.tree.get_node(4)
        five = self.tree.get_node(5)

        result = (zero == top.left and 
            one == top and
            two == top.right.left.left and
            three == top.right.left and
            four == top.right and
            five == top.right.right)
        self.assertTrue(result, "get_node isn't returning the right node")

    def test_remove(self):
        self.tree = self.build_big_tree()
        expected = [ 6, 8, 9, 10, 11, 12, 14, 20, 27, 28 ]

        # ensure it starts out as intended. this should always pass
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Initial tree was malformed. Our fault, not yours")

        # remove the top node, 20
        self.tree.remove(20)
        expected.remove(20)
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Removing top node (20) did not work")

        # remove a leaf, 9
        self.tree.remove(9)
        expected.remove(9)
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Removing leaf node (9) did not work") 
        
        # remove a middle node, 27
        self.tree.remove(27)
        expected.remove(27)
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Removing middle node (27) did not work") 

    def test_to_array(self):
        self.tree = self.build_big_tree()
        expected = [ 6, 8, 9, 10, 11, 12, 14, 20, 27, 28 ]

        # ensure it starts out as intended. this should always pass
        actual = []
        self.private_inorder_fetch(self.tree.root_node, actual)
        self.assertEqual(expected, actual, "Initial tree was malformed. Our fault, not yours")

        # now ensure that the user code's to_array does the right thing.
        actual = self.tree.to_array()
        self.assertEqual(expected, actual, "to_array didn't return the right values")        

if __name__ == '__main__':
    retro = False
    if (len(argv) > 1 and argv[1] == "--retrograde"):
        retro = True
    print "Retro mode: " + str(retro)
    suite = unittest.TestSuite()
    tests = [
        RetroTest("test_init_node", "InitializeNode"),
		RetroTest("test_insert", "Insert"),
		RetroTest("test_insert_data", "InsertData"),
		RetroTest("test_size", "Size"),
		RetroTest("test_contains", "Contains"),
		RetroTest("test_get_node", "GetNode"),
		RetroTest("test_remove", "Remove"),
		RetroTest("test_to_array", "ToArray"),
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

