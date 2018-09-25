#include <iostream>
#include <vector>
#include <list>

using namespace std;

template <typename T> void printVector(T data) {
    cout << data.size() << endl;
    for (auto i = data.begin(); i != data.end(); i++) {
        cout << *i << " ";
    }
    cout << endl;

    // These only work for contiguous memory containers.
    /*
    for (int i = 0; i < data.size(); i++) {
        cout << data[i] << " ";
    }
    cout << endl;

    for (int i = 0; i < data.size(); i++) {
        cout << data.at(i) << " ";
    }
    cout << endl;
    */
}

int foo(int i) {
    return 0;
}

void swapVector(vector<int>  *data) {    
    for (int i = 0; i < data->size() / 2; i++) {
        int temp = data->at(i);
        data->at(i) = data->at(data->size() - 1 - i);
        data->at(data->size() - 1 - i) = temp;
        cout << data->at(i) << " " << data->at(data->size() - 1 - i) << " ";
    }
    cout << endl;
}

int main(int argc, char * argv []) {
    
    vector<int> data;
    list<int> myList;

    for (int i = 0; i < 10; i++) {
        data.push_back(i);
    }

    printVector(data);

    for (int i = 0; i < data.size() / 2; i++) {
        int temp = data.at(i);
        data.at(i) = data.at(data.size() - 1 - i);
        data.at(data.size() - 1 - i) = temp;
        cout << data.at(i) << " " << data.at(data.size() - 1 - i) << " ";
    }
    cout << endl;

    swapVector(&data);
    printVector(data);

    myList.push_back(1);
    myList.push_front(2);
    auto it = myList.begin();
    it++;
    myList.insert(it, 3);
    printVector(myList);
    return 0;
}