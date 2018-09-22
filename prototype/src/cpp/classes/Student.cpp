#include <iostream>
#include "Student.hpp"

using namespace std;

namespace student {
    Student::Student() : person::Person(0, NULL, -1) {}

    Student::Student(int id, char * name, int length) : person::Person(id, name, length) {}

    // Copy constructor
    Student::Student(const Student &student) {
        // Addition of the copy constructor to student require the definition of
        // an empty constructor of the parent.
        this->isStudent = false;
        this->isClone = true;
    }

    Student::~Student() {
        cout << "Student Deconstructor Called\n";
    }

    void Student::study() {
        printf("The student %d is studying.\n", this->id);
    }

    int Student::totalNumberOfStudents() {
        return 0;
    }
}