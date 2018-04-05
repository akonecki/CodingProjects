#include <stdio.h>

void reverse(char *str);

void reverse(char *str) {
    int length = 0, index;
    char temp;

    if (str == 0) {
        return;
    }

    while (str[length] != 0) {
        length++;
    }

    for (index = 0; index < (length / 2); index++) {
        fprintf(stdout, "%d %d\n", index, length - index - 1);
        if (index > (length - 1)) {
            fprintf(stdout, "Invalid index.");
        }
        if ((length - index - 1) < 0) {
            fprintf(stdout, "Negative index");
        }
        if ((length - index - 1) > length) {
            fprintf(stdout, "Length is invalid");
        }
        
        temp = str[index];
        str[index] = str[length - index - 1];
        str[length - index - 1] = temp;
    }
}

int main(int argc, char *argv[]) {
    char string1[] = "hello world";
    char *string2 = "";
    char *string3 = "a";

    reverse(string1);
    // reverse(string2);
    // reverse(string3);

    fprintf(stdout, "%s %s %s\n",  string1, string2, string3);

    return 0;
}