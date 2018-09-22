#include <iostream>

#include "Person.hpp"
#include "Professor.hpp"
#include "Student.hpp"

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

    student::Student * newStudent = std::move(aStudent);

    newStudent->print();
    newStudent->study();

    // at this point in time aStudnet is actually not guaranteed to be a value
    // since the newStudent now has ownership of the object.

    delete aStudent;
    delete aPerson;

    student::Student * student = (student::Student *) person::Person::create(1);
    student->study();

    return 0;
}