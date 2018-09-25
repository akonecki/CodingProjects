#include <iostream>
#include <vector>

using namespace std;

void printVector(vector<int> data) {
    cout << data.size() << endl;
    for (auto i = data.begin(); i != data.end(); i++) {
        cout << *i << " ";
    }
    cout << endl;

    for (int i = 0; i < data.size(); i++) {
        cout << data[i] << " ";
    }
    cout << endl;

    for (int i = 0; i < data.size(); i++) {
        cout << data.at(i) << " ";
    }
    cout << endl;
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

    return 0;
}