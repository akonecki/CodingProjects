#include <cstdlib>
#include <iostream>
#include <set>

using namespace std;

class MyComparable { 
    public:
        bool operator() (const int & num1, const int & num2) {
            if (num1 < num2) {
                return false;
            }
            return true;
        }
};

struct classcomp {
  bool operator() (const int& lhs, const int& rhs) const
  {return lhs<rhs;}
};

template <typename T>
void print(T data) {
    cout << endl;
    for (auto it = data.begin(); it != data.end(); ++it) {
        cout << *it << " ";
    }
    cout << endl;
}

int main(int argc, char * argv[]) {
    set<int> myset;
    set<int, MyComparable> compSet;

    for (int i = 0; i < 10; i++) {
        myset.insert(i);
        compSet.insert(i);
    }

    print(myset);
    print(compSet);

    return 0;
}