/*

  some_driver.cpp

*/
#include "some_header.h"
#include "UTFramework.h"
#include <cstring>

using namespace Thilenius;
using namespace std;

extern int RETROGRADE_MODE;

SUITE_BEGIN("Some Suite Name")

TEST_BEGIN("TestName")
{
  
  //  IsTrue("Returned A Node", four != NULL, 
  //	 "A Null node was returned by the init_node( ) function.");

}TEST_END

SUITE_END

void printUsage(char call[]) {
    cout << " Usage: " << call << " [--retrograde]" << endl;
}

int main (int argc, char* argv[]) 
{
  if (argc == 2 && strcmp(argv[1], "--retrograde") == 0) RETROGRADE_MODE = 1;
  else if (argc != 1) { 
    printUsage(argv[0]);
    return -1;
  }

  UTFrameworkInit;

}
