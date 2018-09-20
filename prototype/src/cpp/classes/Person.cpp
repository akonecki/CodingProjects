#include <iostream>
#include "Person.hpp"

using namespace std;

namespace person {
    Person::Person(int id, char * name, int nameLength) {
        this->id = id;
        
        for (int index = 0; index < nameLength && index < 25; index++) {
            this->name[index] = name[index];
        }
    }

    void Person::print() {
        printf("%d\n", this->id);
    }
}
