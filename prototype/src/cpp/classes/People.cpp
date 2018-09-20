#include <iostream>

#include "Student.hpp"
#include "Person.hpp"

using namespace std;

int main (int argc, char * argv []) {
    
    person::Person * aPerson = new person::Person(5, NULL, -1);
    aPerson->print();
    student::Student * aStudent = new student::Student(5, "hello", 6);
    aStudent->print();

    delete aStudent;
    delete aPerson;

    return 0;
}