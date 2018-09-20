#ifndef __STUDENT_HPP__
#define __STUDENT_HPP__

#include "Person.hpp"

namespace student {

    class Student : public person::Person {
        private:
            bool isStudent = true;

        public:
            // Student();
            Student(int id, char * name, int nameLength);
    };

};

#else

#endif