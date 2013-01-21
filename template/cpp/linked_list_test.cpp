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

TEST(LinkedListTest, Report) {
  node* top = NULL; // empty list
  string exp ("");
  string out = report(top);
  EXPECT_NE(string::npos, out.find(exp, 0)) << "Empty list should report '' or ' '";
  top = init_node(1);
  node* two = init_node(2);
  node* three = init_node(3);
  top->next = two;
  two->next = three;
  exp = "1 2 3";
  out = report(top);
  EXPECT_NE(string::npos, out.find(exp, 0)) << "List should report '1 2 3' or '1 2 3 '";
}

TEST(LinkedListTest, InitNode) {
  node* top = init_node(1);
  EXPECT_EQ(1, top->data) << "Newly initialized node should have '1' for data";
  EXPECT_TRUE(top->next == NULL) << "Newly initialized node should have null 'next'";
}

TEST(LinkedListTest, InsertEmpty) {
  node* top = init_node(0);
  append_data(&top, 3);
  EXPECT_EQ(4, top->next->data) << "This error put here on purpose so you can see it fail.";
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
