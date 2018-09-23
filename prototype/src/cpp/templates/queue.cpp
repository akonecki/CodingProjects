#include <cstddef>
#include <cassert>
#include <iostream>

using namespace std;

template <typename T> class Queue {
    private:
        template <typename D> class Node {
            public: 
                D  mData = NULL;
                Node<D> * mNext = NULL;
                Node(D data) : mData(data) {}
        };

        int mSize = 0;
        Node<T> * mHead = NULL;
        Node<T> * mTail = NULL;

    public:
        ~Queue() {
            Node<T> * node = mHead;
            Node<T> * next = NULL;
            
            mHead = NULL;
            mTail = NULL;
            mSize = 0;

            while (node != NULL) {
                next = node->mNext;
                delete node;
                node = next;
            }
        }

        void enqueue(T data) {
            Node<T> * node = new Node<T>(data);

            if (mHead == NULL) {
                mHead = node;
                mTail = node;
            }
            else {
                mTail->mNext = node;
                mTail = node;
            }

            this->mSize++;
        }

        T dequeue() {
            Node<T> * node = NULL;
            T data;

            if (this->mSize == 0) {
                return data;
            }
            else {
                node = this->mHead;
                this->mHead = this->mHead->mNext;

                if (this->mHead == NULL) {
                    this->mTail = NULL;
                }
                this->mSize--;
            }

            data = node->mData;
            delete node;
            return data;
        }

        T peek() {
            T data;
            if (this->mSize == 0) {
                return data;
            }
            else {
                data = this->mHead->mData;
                return data;
            }
        }

        bool isEmpty() {
            return this->mSize == 0;
        }

        int size() {
            return this->mSize;
        }
};

int main(int argc, char * argv[]) {

    Queue<int> * queueOfInts = new Queue<int>();
    queueOfInts->enqueue(5);
    queueOfInts->enqueue(1);
    queueOfInts->enqueue(9);
    queueOfInts->enqueue(51);

    assert (queueOfInts->size() == 4);

    while (queueOfInts->size() > 0) {
        cout << queueOfInts->dequeue() << endl;
    }

    assert (queueOfInts->size() == 0);
    assert (queueOfInts->isEmpty());

    delete queueOfInts;


    return 0;
}