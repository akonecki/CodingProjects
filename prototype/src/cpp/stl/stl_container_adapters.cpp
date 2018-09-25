#include <iostream>
#include <cstdlib>
#include <queue>
#include <vector>
#include <stack>

using namespace std;

template <typename T>
void print(T data) {
    // Queue by default does not by default support iteration.  Significantly 
    // more restricted in access.
    while (!data.empty()) {
        cout << data.front() << " ";
        data.pop();
    }
    cout << endl;
}

template <typename T>
void printTop(T data) {
    // Queue by default does not by default support iteration.  Significantly 
    // more restricted in access.
    while (!data.empty()) {
        cout << data.top() << " ";
        data.pop();
    }
    cout << endl;
}

class MyComparison {
    public:
        bool operator() (const int & num1, const int & num2) {
            if (num1 < num2) {
                return false;
            }
            return true;
        }
};

int main(int argc, char * argv[]) {
    queue<int> queue;
    priority_queue<int> pqueue;
    int values [] = {15, 38, 1, 14, 76, 32};
    stack<int> stack;

    for (int i = 0; i < 10; i++) {
        queue.push(i);
        pqueue.push(i);
        stack.push(i);
    }

    print(queue);
    cout << queue.size() << endl;
    print(queue);
    printTop(stack);
    printTop(pqueue);

    priority_queue<int, vector<int>, MyComparison> comparePQueue (values, values+4);
    printTop(comparePQueue);
    return 0;
}