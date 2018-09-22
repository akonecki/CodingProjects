#include <iostream>
#include "Person.hpp"
#include "Student.hpp"
#include "Professor.hpp"

using namespace std;

namespace person {
    Person::~Person() {
        cout << "Person Deconstrutor Called\n";
    }

    Person::Person() {
        cout << "Default Person Constructor Called\n";
    }

    Person::Person(const Person &toCopy) {
        this->id = -999;
        
        for (int index = 0; index < 25; index++) {
            this->name[index] = toCopy.name[index];
        }
    }

    Person::Person(int id, char * name, int nameLength) : id(id) {
        
        for (int index = 0; index < nameLength && index < 25; index++) {
            this->name[index] = name[index];
        }
    }

    void Person::print() {
        printf("%d\n", this->id);
    }

    Person * Person::create(int selector) {
        if (selector == 1) {
            return new student::Student;
        }
        else if (selector == 2) {
            return new professor::Professor;
        }
        else {
            return new Person;
        }
    }
}
