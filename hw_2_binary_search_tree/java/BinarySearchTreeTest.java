import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class BinarySearchTreeTest {
    
    public static void main(String[] args) {
	JUnitCore core = new JUnitCore();
	core.addListener(new RetroPrinter());
	core.run(BinarySearchTreeTest.class);
    }
    
    BinarySearchTree tree;

    private BTNode handBuildNode(int data) {
	BTNode node = new BTNode();
	node.data = data;
	node.left = null;
	node.right= null;
	return node;
    }

    private BinarySearchTree buildSimpleTree() {
	BTNode top = handBuildNode(1);
	top.left = handBuildNode(0);
	top.right = handBuildNode(4);
	top.right.left = handBuildNode(3);
	top.right.left.left = handBuildNode(2);
	top.right.right = handBuildNode(5);
	BinarySearchTree ret = new BinarySearchTree();
	ret.setRootNode(top);
	return ret;
    }
    
    @Before
    public void init() {
	tree = new BinarySearchTree();
    }
    
    @Test
    public void testInitializeNode() {
	BTNode n = tree.initNode(42);
	assertNotNull("New node should be non-null.", n);
	assertEquals("New node should have data of 42.", 42, n.data);
	assertNull("New node shoudl have null children.", n.left);
	assertNull("New node shoudl have null children.", n.right);
    }
    
    @Test
    public void testInsert() {
	BTNode top = handBuildNode(2);
	BTNode one = handBuildNode(1);
	BTNode three = handBuildNode(3);
	BTNode four = handBuildNode(4);
	tree.setRootNode(top);
	tree.insert(one);
	tree.insert(three);
	tree.insert(four);
  
	assertTrue("1 should be a left child of parent node 2.", 
		   top.left == one);
	
	assertTrue("3 should be a right child of parent node 2.", 
		   top.right == three);
		   
	assertTrue("4 should be in the lower right of tree.", 
		   top.right != null && top.right.right == four);
    }
    
    @Test
    public void testInsertData() {
	BTNode top = handBuildNode(2);
	tree.setRootNode(top);
	tree.insertData(1);
	tree.insertData(3);
	tree.insertData(4);
  
	assertTrue("1 should be a left child of parent node 2.", 
		   top.left != null && top.left.data == 1);
	
	assertTrue("3 should be a right child of parent node 2.", 
		   top.right != null && top.right.data == 3);
		   
	assertTrue("4 should be in the lower right of tree.", 
		   top.right != null && top.right.right != null &&
		   top.right.right.data == 4);
    }
    
    @Test
    public void testSize() {
	assertEquals("Empty tree size should be 0", 0, tree.size());
	tree = buildSimpleTree();
	assertEquals("Size should be 6", 6, tree.size());
    }
    
    @Test
    public void testContains() {
	tree = buildSimpleTree();
	for (int i=0; i < 6; i++) {
	    assertTrue("Tree does not contain " + i, tree.contains(i));
	}
	assertFalse("Tree does contains " + -1 + " but it shouldn't", tree.contains(-1));
	assertFalse("Tree does contains " + 42 + " but it shouldn't", tree.contains(42));
	assertFalse("Tree does contains " + 99 + " but it shouldn't", tree.contains(99));
    }
    
    @Test
    public void testGetNode() {
	tree = buildSimpleTree();
	BTNode top = tree.getRootNode();

	BTNode zero	= tree.getNode(0);
	BTNode one	= tree.getNode(1);
	BTNode two	= tree.getNode(2);
	BTNode three	= tree.getNode(3);
	BTNode four	= tree.getNode(4);
	BTNode five	= tree.getNode(5);

	boolean results = 
	    zero == top.left &&
	    one == top &&
	    two == top.right.left.left &&
	    three == top.right.left &&
	    four == top.right &&
	    five == top.right.right;

	assertTrue("getNode() isn't returning the right nodes.", results);
    }
    
    @Test
    public void testRemove() {
	// Create separate scopes for this test.
	{
	    // Hand build a node with 0, 1, 2, 3, 4, 5 in it.
	    tree = buildSimpleTree();
	    
	    // remove the leaf node 5
	    tree.remove(5);
	    BTNode top = tree.getRootNode();
	    boolean result = top != null && 
		top.data == 1 &&
		top.right != null && 
		top.right.data == 4 &&
		top.left != null	&& 
		top.left.data == 0 &&
		top.right.left != null && 
		top.right.left.data == 3 &&
		top.right.left.left != null && 
		top.right.left.left.data == 2 &&
		top.right.right == null;
	    
	    assertTrue("Failed to remove a leaf node.", result);
	}
	{
	    // Hand build a node with 0, 1, 2, 3, 4, 5 in it.
	    tree = buildSimpleTree();      
	    
	    // remove the branch node 3
	    tree.remove(3);
	    BTNode top = tree.getRootNode();
	    boolean result = top != null &&
		top.data == 1 &&
		top.right != null && 
		top.right.data == 4 &&
		top.left != null	&& 
		top.left.data == 0 &&
		top.right.left != null && 
		top.right.left.data == 2 &&
		top.right.right != null && 
		top.right.right.data == 5;
	    
	    assertTrue("Failed to remove a branch with one child.", result);
	    
	}
	{
	    // Hand build a node with 0, 1, 2, 3, 4 in it.
	    tree = buildSimpleTree();
	    
	    // remove the trunk node 1
	    // Note that there are two possible results for this depending on
	    // what bias the tree is.
	    tree.remove(1);
	    BTNode top = tree.getRootNode();
	    
	    // Right biased
	    boolean result1 = top != null && 
		top.data == 2 &&
		top.left != null && 
		top.left.data == 0 &&
		top.right != null && 
		top.right.data == 4 &&
		top.right.left != null && 
		top.right.left.data == 3 && 
		top.right.right != null	&& 
		top.right.right.data == 5;
	    
	    // Left biased
	    boolean result2 = top != null && 
		top.data == 0 &&
		top.right != null && 
		top.right.data == 4 &&
		top.right.left != null && 
		top.right.left.data == 3 &&
		top.right.left.left != null && 
		top.right.left.left.data == 2 &&
		top.right.right != null && 
		top.right.right.data == 5;
	    
	    assertTrue("Failed to remove a node with two children", result1 || result2);
	}
    }
    
    @Test
    public void testToArray() {
	// Hand build a node with 1, 2, 3, 4 in it.
	tree = buildSimpleTree();
  
	int[] trueArray = {0, 1, 2, 3, 4, 5};
	int[] results = tree.toArray();
	
	assertEquals("The result array should have size " + 6, 6, results.length);

	assertTrue("The toArray() function does not return the elements in order.",
		   results[0] == 0 && 
		   results[1] == 1 && 
		   results[2] == 2 && 
		   results[3] == 3 && 
		   results[4] == 4 && 
		   results[5] == 5);
    }
    
}
