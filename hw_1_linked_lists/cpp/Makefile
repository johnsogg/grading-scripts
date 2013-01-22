# This top block is all you will probably need to change
STUDENT_IMPL_FILE 	= linked_list.cpp
STUDENT_OBJ_FILE 	= linked_list.o
PRIMARY_HEADER_FILE 	= linked_list.h
UNIT_TEST_IMPL_FILE 	= linked_list_test.cpp
UNIT_TEST_OBJ_FILE 	= linked_list_test.o
UNIT_TEST_BINARY 	= linked_list_test

# Points to the root of Google Test
GTEST_DIR = /usr/local/gtest-1.6.0
GTEST_LIB = $(GTEST_DIR)/make/gtest_main.a
USER_DIR = .
CPPFLAGS += -I$(GTEST_DIR)/include
CXXFLAGS += -g -Wall -Wextra
GTEST_HEADERS = $(GTEST_DIR)/include/gtest/*.h \
	$(GTEST_DIR)/include/gtest/internal/*.h

# House-keeping build targets.

all : $(UNIT_TEST_BINARY)

clean :
	rm -f $(TESTS) *.o $(UNIT_TEST_BINARY)

RetroPrinter.o : $(USER_DIR)/RetroPrinter.cpp \
                 $(GTEST_HEADERS)
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $(USER_DIR)/RetroPrinter.cpp

# Assignment-specific build rules below.

$(STUDENT_OBJ_FILE): $(USER_DIR)/$(STUDENT_IMPL_FILE) \
                $(USER_DIR)/$(PRIMARY_HEADER_FILE) \
                $(GTEST_HEADERS)
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $(USER_DIR)/$(STUDENT_IMPL_FILE)

$(UNIT_TEST_OBJ_FILE) : $(USER_DIR)/$(UNIT_TEST_IMPL_FILE) \
                     $(USER_DIR)/$(PRIMARY_HEADER_FILE) \
                     $(GTEST_HEADERS)
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $(USER_DIR)/$(UNIT_TEST_IMPL_FILE)

$(UNIT_TEST_BINARY) : $(STUDENT_OBJ_FILE) $(UNIT_TEST_OBJ_FILE) RetroPrinter.o \
		   $(GTEST_LIB)
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -pthread $^ -o $@
