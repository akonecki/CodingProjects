#include <iostream>
#include <fstream>
using namespace std;


void printLastKLinesOfFile(ifstream ifs, int k) {
    if (k < 1) {
        return;
    }

    
}

int main(int argc, char * argv[]) {
    ifstream ifs;
    ifs.open("file.txt");

    printLastKLinesOfFile(ifs, 3);

    ifs.close();

    return 0;
}