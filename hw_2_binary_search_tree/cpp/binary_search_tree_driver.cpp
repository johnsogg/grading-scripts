/*

  binary_search_tree_driver.cpp



             .__.       .============================.
            .(\\//).  .-[ A Gift: Pre-written Driver ]
           .(\\()//)./  `============================'
       .----(\)\/(/)----.
       |     ///\\\     |
       |    ///||\\\    |    N O T E:
       |   //`||||`\\   |
       |      ||||      |  This is the last time I will give you
       |      ||||      |  such comprehensive testing code. Next
       |      ||||      |  assignment this will be substantially
       |      ||||      |  shorter and incomplete. On purpose.
       |      ||||      |  Because I am mean. And also because
       |      ||||      |  writing your own testing code is an
       '------====------'  absolutely imperative skill.

   Take careful note of how this driver is structured. You will be
   responsible for writing something like this in the future. Once you
   have a real job writing software for a living, you will most likely
   be expected to write test code as a matter of course.

   Note:
   The framework will detect and recover from a seg-fault. To see what
   code caused the seg-fault, find the test that ran BEFORE the fault. 
   Then look at the test after it below.

   This is the same file that RetroGrade uses to assign scores. The
   good news is that you can run this on your own machine without
   uploading to RetroGrade. To run in RetroGrade mode, run th

*/
#include "binary_search_tree.h"
#include "UTFramework.h"

using namespace Thilenius;
using namespace std;

extern int RETROGRADE_MODE;
bt_node* HandBuildNode ( int data );
bt_node* HandBuildTree ( );
bool ArrayContains ( int arr[], int size, int value );


SUITE_BEGIN("Binary Search Tree")

TEST_BEGIN("InitializeNode")
{
  bt_node* four = init_node(4);
  
  IsTrue("Returned A Node", four != NULL, 
	 "A Null node was returned by the init_node( ) function.");
  IsTrue("Data", four != NULL && four->data == 4, 
	 "The data has not been correctly set in the returned node.");
  IsTrue("Left Null", four != NULL && four->left == NULL, 
	 "The node->left field should be null.");
  IsTrue("Right Null", four != NULL && four->right == NULL, 
	 "The node->right field should be null.");
}TEST_END

TEST_BEGIN("Insert")
{
  bt_node* top = HandBuildNode(2);
  bt_node* one = HandBuildNode(1);
  bt_node* three = HandBuildNode(3);
  bt_node* four = HandBuildNode(4);
  
  insert(&top, one);
  insert(&top, three);
  insert(&top, four);
  
  IsTrue("Left Insert", top->left == one, 
	 "1 should be a left child of parent node 2.");
  IsTrue("Right Insert", top->right == three, 
	 "3 should be a right child of parent node 2.");
  IsTrue("Chained Insert", top->right != NULL && top->right->right == four, 
	 "4 should be in the lower right of tree.");
}TEST_END

TEST_BEGIN("InsertData")
{
  bt_node* top = HandBuildNode(2);
  
  insert_data(&top, 1);
  insert_data(&top, 3);
  insert_data(&top, 4);
  
  IsTrue("Left Insert Data", top->left != NULL && top->left->data == 1, 
	 "1 should be a left child of parent node 2.");
  IsTrue("Right Insert Data", top->right != NULL && top->right->data == 3, 
	 "3 should be a right child of parent node 2.");
  IsTrue("Chained Insert Data", top->right != NULL && 
	 top->right->right != NULL && top->right->right->data == 4, 
	 "4 should be in the lower right of tree.");
}TEST_END

TEST_BEGIN("Size")
{
  // Hand build a tree of size 6
  bt_node* top = HandBuildTree( );
  
  stringstream ss;
  ss << "Size( ) returned [" << size(top) << "] for a tree of size [6].";
  
  IsTrue("Size", size(top) == 6, ss.str());
}TEST_END

TEST_BEGIN("Contains")
{
  // Hand build a node with 0, 1, 2, 3, 4, 5 in it.
  bt_node* top = HandBuildTree( );
  
  bool containsReal = contains(top, 0) &&
    contains(top, 1) &&
    contains(top, 2) && 
    contains(top, 3) && 
    contains(top, 4) &&
    contains(top, 5);
  
  bool containsFake = !contains(top, -2) &&
    !contains(top, -1) && 
    !contains(top, 99) && 
    !contains(top, 42); 
  
  IsTrue("Contains Real", containsReal, 
	 "Contains( ) returned false for a node that actually exists.");
  IsTrue("Contains Fake", containsFake, 
	 "Contains( ) returned true for a node that don't actually exist.");
}TEST_END

TEST_BEGIN("GetNode")
{
  // Hand build a node with 1, 2, 3, 4 in it.
  bt_node* top = HandBuildTree();
  
  bt_node* Zero	= get_node(top, 0);
  bt_node* One	= get_node(top, 1);
  bt_node* Two	= get_node(top, 2);
  bt_node* Three	= get_node(top, 3);
  bt_node* Four	= get_node(top, 4);
  bt_node* Five	= get_node(top, 5);
  
  bool results = Zero == top->left &&
    One == top &&
    Two == top->right->left->left &&
    Three == top->right->left &&
    Four == top->right &&
    Five == top->right->right;
  
  IsTrue("Get Node", results, "get_node( ) is not returning the correct node.");
}TEST_END

TEST_BEGIN("Remove")
{
  // Create separate scopes for this test.
  {
    // Hand build a node with 0, 1, 2, 3, 4, 5 in it.
    bt_node* top = HandBuildTree();
    
    // remove the leaf node 5
    remove( &top, 5 );
    bool result = top != NULL && 
      top->data == 1 &&
      top->right != NULL && 
      top->right->data == 4 &&
      top->left != NULL	&& 
      top->left->data == 0 &&
      top->right->left != NULL && 
      top->right->left->data == 3 &&
      top->right->left->left != NULL && 
      top->right->left->left->data == 2 &&
      top->right->right == NULL;
    
    IsTrue("Remove Leaf", result, "Failed to remove a leaf node.");
  }
  {
    // Hand build a node with 0, 1, 2, 3, 4, 5 in it.
    bt_node* top = HandBuildTree();
    
    // remove the branch node 3
    remove( &top, 3 );
    bool result = top != NULL &&
      top->data == 1 &&
      top->right != NULL && 
      top->right->data == 4 &&
      top->left != NULL	&& 
      top->left->data == 0 &&
      top->right->left != NULL && 
      top->right->left->data == 2 &&
      top->right->right != NULL && 
      top->right->right->data == 5;
    
    IsTrue("Remove Branch", result, "Failed to remove a branch with one child.");
  }
  {
    // Hand build a node with 0, 1, 2, 3, 4 in it.
    bt_node* top = HandBuildTree();
    
    // remove the trunk node 1
    // Note that there are two possible results for this depending on
    // what bias the tree is.
    remove( &top, 1 );
    
    // Right biased
    bool result1 = top != NULL && 
      top->data == 2 &&
      top->left != NULL && 
      top->left->data == 0 &&
      top->right != NULL && 
      top->right->data == 4 &&
      top->right->left != NULL && 
      top->right->left->data == 3 && 
      top->right->right != NULL	&& 
      top->right->right->data == 5;
    
    // Left biased
    bool result2 = top != NULL && 
      top->data == 0 &&
      top->right != NULL && 
      top->right->data == 4 &&
      top->right->left != NULL && 
      top->right->left->data == 3 &&
      top->right->left->left != NULL && 
      top->right->left->left->data == 2 &&
      top->right->right != NULL && 
      top->right->right->data == 5;
    
	// Special case
	bool result3 = top != NULL						&& top->data == 4 &&
		top->right != NULL				&& top->right->data == 5 &&
		top->left != NULL				&& top->left->data == 3 &&
		top->left->left != NULL			&& top->left->left->data == 2 &&
		top->left->left->left != NULL	&& top->left->left->left->data == 0;

		IsTrue("Remove Trunk", result1 || result2 || result3, 
		"Failed to remove the trunk ( branch with two children ).");
  }
  
}TEST_END

TEST_BEGIN("ToArray")
{
  // Hand build a node with 1, 2, 3, 4 in it.
  bt_node* top = HandBuildTree();
  
  int trueArray[] = {0, 1, 2, 3, 4, 5};
  int results[6] = {-1, -1, -1, -1, -1, -1};
  to_array(top, results);
  
  bool returnedAnthing = results[0] != -1 &&
    results[1] != -1 &&
    results[2] != -1 &&
    results[3] != -1 &&
    results[4] != -1 &&
    results[5] != -1;
  
  bool correctElements = returnedAnthing &&
    ArrayContains(results, 6, 0) &&
    ArrayContains(results, 6, 1) &&
    ArrayContains(results, 6, 2) &&
    ArrayContains(results, 6, 3) &&
    ArrayContains(results, 6, 4) &&
    ArrayContains(results, 6, 5);
  
  IsTrue("Returned Anything", returnedAnthing, 
	 "The to_array( ) function return what was sent in.");
  IsTrue("Correct Elements", correctElements, 
	 "The to_array( ) function does not return the correct elements.");
  IsTrue("Correct Order", results[0] == 0 && results[1] == 1 && 
	 results[2] == 2 && results[3] == 3 && results[4] == 4 && results[5] == 5, 
	 "The to_array( ) function does not return the elements in order.");
  
}TEST_END

SUITE_END

bt_node* HandBuildNode ( int data )
{
  bt_node* top = new bt_node();
  top->data = data;
  top->left = NULL;
  top->right = NULL;
  
  return top;
}

bt_node* HandBuildTree ( )
{
  // Hand build an unbalanced tree with 0, 1, 2, 3, 4, 5 in it.
  bt_node* top = HandBuildNode(1);
  top->left = HandBuildNode(0);
  top->right = HandBuildNode(4);
  top->right->left = HandBuildNode(3);
  top->right->left->left = HandBuildNode(2);
  top->right->right = HandBuildNode(5);
  
  return top;
}

bool ArrayContains ( int arr[], int size, int value )
{
  for ( int i = 0; i < size; i++ )
    {
      if ( arr[i] == value )
	return true;
    }
  
  return false;
}

int main (int argc, char* argv[]) 
{
  if (argc > 1) {
    RETROGRADE_MODE = 1;
  }
  UTFrameworkInit;
  
}
