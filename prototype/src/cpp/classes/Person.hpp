#ifndef __PERSON_HPP__
// Library does not exist within the linking structure yet.
#define __PERSON_HPP__

namespace person {

    class Person {
        private:
            int id;
            char name [25];
        
        public:
            Person(int id, char name [], int nameLength);
            void print();
    };
}

#else
#endif