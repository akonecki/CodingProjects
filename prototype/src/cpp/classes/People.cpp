#include <iostream>

#include "Student.hpp"
#include "Person.hpp"

using namespace std;

int main (int argc, char * argv []) {
    
    person::Person * aPerson = new person::Person(5, NULL, -1);
    aPerson->print();

    // Ideally not good to reference direct non-mutuable definitions into a
    // pointer reference.
    student::Student * aStudent = new student::Student(5, "hello", 6);
    aStudent->print();

    // Introduction of a child class method only.
    aStudent->study();

    //student::Student * copy = new student::Student(aStudent);

    person::Person * fakePerson = new person::Person(*aPerson);
    fakePerson->print();

    student::Student * fakeStudent = new student::Student(*aStudent);
    fakeStudent->print();
    fakeStudent->study();

    delete aStudent;
    delete aPerson;

    return 0;
}