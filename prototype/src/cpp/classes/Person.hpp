#ifndef __PERSON_HPP__
// Library does not exist within the linking structure yet.
#define __PERSON_HPP__

namespace person {

    class Person {
        // Use of protected, allowing child classes to have direct access.  This
        // should only be allowed if the Person class is not going to be publicly
        // exposed since a developer can then just make a new class that inherits
        // from the `Person` class to then act directly on the class instance 
        // attributes.
        protected:
            int id;
            char name [25];
        
        public:
            Person();
            Person(const Person &);
            Person(int id, char * name, int nameLength);
            void print();
    };
}

#else
#endif