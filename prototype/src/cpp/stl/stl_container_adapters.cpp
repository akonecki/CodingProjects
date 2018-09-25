#include <iostream>
#include <cstdlib>
#include <queue>

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

int main(int argc, char * argv[]) {
    queue<int> queue;

    for (int i = 0; i < 10; i++) {
        queue.push(i);
    }

    print(queue);
    cout << queue.size() << endl;
    print(queue);
    
    return 0;
}