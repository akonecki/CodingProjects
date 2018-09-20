#include <iostream>
#include "Person.hpp"

using namespace std;

namespace person {
    Person::Person(int id, char name[], int nameLength) {
        this->id = id;
        
        for (int index = 0; index < nameLength && index < 25; index++) {
            this->name[index] = name[index];
        }
    }

    void Person::print() {
        printf("%d\n", this->id);
    }
}

int main (int argc, char * argv []) {
    
    person::Person * aPerson = new person::Person(5, NULL, -1);
    aPerson->print();



    return 0;
}
