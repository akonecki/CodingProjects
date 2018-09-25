#include <iostream>
#include <vector>
#include <list>
#include <forward_list>
#include <array>
#include <deque>
#include <algorithm>

using namespace std;

template <typename T> void printVector(T data) {
    // Forward list does not have a size attribute
    // cout << data.size() << endl;
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
    deque<int> myDeque;
    array<int,6> myArray;
    vector<int> data;
    list<int> myList;
    forward_list<int> singlyLinkedList;

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

    singlyLinkedList.push_front(1);
    singlyLinkedList.push_front(5);
    singlyLinkedList.push_front(4);
    singlyLinkedList.push_front(3);
    singlyLinkedList.sort();
    printVector(singlyLinkedList);

    myArray.fill(-1);
    myArray.at(3) = 0;
    printVector(myArray);

    myDeque.push_front(5);
    myDeque.push_back(1);
    printVector(myDeque);
    std::sort(myDeque.begin(), myDeque.end());
    printVector(myDeque);


    return 0;
}