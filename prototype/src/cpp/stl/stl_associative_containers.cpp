#include <cstdlib>
#include <iostream>
#include <set>
#include <map>

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
    int values [] = {1, 15, 29, 13, 11, 7, 9, 11, -1};
    multiset<int> myset (values, values+8);
    set<int, MyComparable> compSet;
    map<int, int> myMap;

    for (int i = 0; i < 10; i++) {
        myset.insert(i);
        compSet.insert(i);
        myMap.insert(pair<int, int> (i, i * -1));
    }

    print(myset);
    print(compSet);
    // print(myMap);

    return 0;
}