/*

  linked_list_test.cpp

 */

#include "linked_list.h"
#include "gtest/gtest.h"
#include <iostream>
#include "RetroPrinter.h"
#include <string>

using ::testing::InitGoogleTest;
using ::testing::UnitTest;
using ::testing::TestEventListeners;
using std::string;
using std::cout;
using std::endl;

namespace {
  
  // The fixture for testing linked lists
  class LinkedListTest : public ::testing::Test {
  private:
    
  protected:
    
    LinkedListTest() {
    }
    
    virtual ~LinkedListTest() {
    }
    
    // If the constructor and destructor are not enough for setting up
    // and cleaning up each test, you can define the following methods:
    
    virtual void SetUp() {
      // Code here will be called immediately after the constructor (right
      // before each test).
    }
    
    virtual void TearDown() {
      // Code here will be called immediately after each test (right
      // before the destructor).
    }
  }; // ends class LinkListTest
  
  node* build_three_node_list(int one, int two, int three) {
    node* top = new node;
    top->data = one;
    top->next = new node;
    top->next->data = two;
    top->next->next = new node;
    top->next->next->data = three;
    top->next->next->next = NULL;
    return top;
  }

  node* scan(node* top, int how_many) {
    int count = 0;
    node* cursor;
    for (cursor = top; count < how_many && cursor != NULL; cursor = cursor->next) {
      count++;
    }
    return cursor;
  }

  bool expect_all(int vals[], int size, node** top) {
    bool ret = true;
    node* cursor = *top;
    for (int i=0; i < size; i++) {
      if (cursor == NULL || cursor->data != vals[i]) {
	if (cursor == NULL) {
	  cout << "Cursor became null." << endl;
	} else if (cursor->data != vals[i]) {
	  cout << cursor->data << " != " << vals[i] << endl;
	}
	ret = false;
	break;
      }
      cursor = cursor->next;
    }
    // if (!ret) {
    //   cout << "In expect_all, returning " << ret << " because: " << endl;
    //   cout << "  Array contents: " << endl;
    //   for (int i=0; i < size; i++) {
    // 	cout << vals[i] << " ";
    //   }
    //   cout << endl;
    //   cout << "   List content: " << endl;
    //   cursor = *top;
    //   while (cursor !=NULL) { 
    // 	cout << cursor->data << " ";
    // 	cursor = cursor->next;
    //   }
    //   cout << endl;
    // }
    return ret;
  }

  TEST(LinkedListTest, InitNode) {
    node* top = init_node(1);
    EXPECT_EQ(1, top->data) << "Newly initialized node should have '1' for data";
    EXPECT_TRUE(top->next == NULL) << "Newly initialized node should have null 'next'";
  }
  
  TEST(LinkedListTest, Report) {
    node* top = NULL; // empty list
    string exp ("");
    string out = report(top);
    EXPECT_NE(string::npos, out.find(exp, 0)) << "Empty list should report '' or ' '";
    top = build_three_node_list(1, 2, 3);
    exp = "1 2 3";
    out = report(top);
    EXPECT_NE(string::npos, out.find(exp, 0)) << "List should report '1 2 3' or '1 2 3 '";
  }
  
  TEST(LinkedListTest, AppendData) {
    node* top = build_three_node_list(42, 74, 51);
    append_data(&top, 10);
    node* four = scan(top, 3);
    EXPECT_TRUE(NULL != four);
    EXPECT_EQ(10, four->data);
    append_data(&top, 99);
    node* five = scan(top, 4);
    EXPECT_TRUE(NULL != five);
    EXPECT_EQ(99, five->data);
  }

  TEST(LinkedListTest, Append) {
    node* top = build_three_node_list(42, 74, 51);
    node* ap_ten = init_node(10);
    append(&top, ap_ten);
    node* four = scan(top, 3);
    EXPECT_TRUE(NULL != four);
    EXPECT_EQ(10, four->data);
    node* ap_ninenine = init_node(99);
    append(&top, ap_ninenine);
    node* five = scan(top, 4);
    EXPECT_TRUE(NULL != five);
    EXPECT_EQ(99, five->data);
  }

  TEST(LinkedListTest, InsertData) {
    node* top = build_three_node_list(30, 20, 10);
    int initial_three[] = { 30, 20, 10 };
    EXPECT_TRUE(expect_all(initial_three, 3, &top));

    insert_data(&top, 0, 4); // list is now 4, 30, 20, 10
    cout << "FYI: should be 4, 30, 20, 10: " << report(top) << endl;
    int vals[] = { 4, 30, 20, 10 };
    EXPECT_TRUE(expect_all(vals, 4, &top)); 

    insert_data(&top, 2, -8); // list is now 4, 30, -8, 20, 10
    cout << "FYI: should be 4, 30, -8, 20, 10: " << report(top) << endl;
    int vals2[] = { 4, 30, -8, 20, 10 };
    EXPECT_TRUE(expect_all(vals2, 5, &top));

    insert_data(&top, 5, 99); // list is now 4, 30, -8, 20, 10, 99
    cout << "FYI: should be 4, 30, -8, 20, 10, 99: " << report(top) << endl;
    int vals3[] = { 4, 30, -8, 20, 10, 99 };
    EXPECT_TRUE(expect_all(vals3, 6, &top));
  }

  TEST(LinkedListTest, Insert) {
    node* top = build_three_node_list(7, 98, -34);
    
    // add at beginning
    node* beginning = init_node(5);
    insert(&top, 0, beginning);
    cout << "FYI: should be 5, 7, 98, -34: " << report(top) << endl;
    int vals[] = {5, 7, 98, -34};
    EXPECT_TRUE(expect_all(vals, 4, &top));

    // add in middle
    node* middle = init_node(20);
    insert(&top, 2, middle);
    cout << "FYI: should be 5, 7, 20, 98, -34: " << report(top) << endl;
    int vals2[] = { 5, 7, 20, 98, -34 };
    EXPECT_TRUE(expect_all(vals2, 5, &top));

    // add at end
    node* ending = init_node(800);
    insert(&top, 5, ending);
    cout << "FYI: should be 5, 7, 20, 98, -34, 800: " << report(top) << endl;
    int vals3[] = { 5, 7, 20, 98, -34, 800 };
    EXPECT_TRUE(expect_all(vals3, 6, &top));
  }

  TEST(LinkedListTest, Remove) {
    node* top = build_three_node_list(7, 86, 210);
    int vals[] = {7, 86, 210 };
    EXPECT_TRUE(expect_all(vals, 3, &top));

    // remove start
    remove(&top, 0);
    int vals2[] = {86, 210 };
    EXPECT_TRUE(expect_all(vals2, 2, &top));

    // reset and remove mid
    top = build_three_node_list(7, 86, 210);
    remove(&top, 1);
    int vals3[] = {7, 210 };
    EXPECT_TRUE(expect_all(vals3, 2, &top));    

    // reset and remove end
    top = build_three_node_list(7, 86, 210);
    remove(&top, 2);
    int vals4[] = {7, 86 };
    EXPECT_TRUE(expect_all(vals4, 2, &top));
  }

  TEST(LinkedListTest, Size) {
    node* empty = NULL;
    EXPECT_EQ(0, size(empty));

    node* top = build_three_node_list(8, 30, -43);
    EXPECT_EQ(3, size(top));
  }

  TEST(LinkedListTest, Contains) {
    node* top = build_three_node_list(7, 0, -210);
    EXPECT_TRUE(contains(top, -210));
    EXPECT_TRUE(contains(top, 0));
    EXPECT_TRUE(contains(top, 7));
    EXPECT_FALSE(contains(top, -21));
    EXPECT_FALSE(contains(top, 21));
    EXPECT_FALSE(contains(top, 43));
  }

}  // namespace

int main(int argc, char **argv) {
  InitGoogleTest(&argc, argv);
  UnitTest& unit_test = *UnitTest::GetInstance();
  TestEventListeners& listeners = unit_test.listeners();

  // if we don't want the default listener printing anything, remove it:
  delete listeners.Release(listeners.default_result_printer());

  // if we do want our custom listener to record success/fail, add it:
  listeners.Append(new RetroPrinter);

  return RUN_ALL_TESTS();
}
