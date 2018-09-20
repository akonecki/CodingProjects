#include <iostream>
#include "Student.hpp"

using namespace std;

namespace student {
    // Student::Student() : person::Person(0, NULL, -1) {}

    Student::Student(int id, char * name, int length) : person::Person(id, name, length) {}

    void Student::study() {
        printf("The student %d is studying.\n", this->id);
    }
}