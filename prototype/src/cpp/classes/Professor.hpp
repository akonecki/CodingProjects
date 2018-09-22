#ifndef __PROFESSOR_HPP__
#define __PROFESSOR_HPP__

#include "Person.hpp"

namespace professor {

    class Professor : public person::Person {
        private:
            bool canTeach = true;

        public:
            Professor();
            ~Professor();
    };

};

#else
#endif