#include <stdlib.h>
#include <stdio.h>

typedef union AUNION {
    int i;
    char array [4];
} aunion_t;

void malloc_wrapper(const void ** ptr, const size_t numberOfBytes) {
    printf("malloc_wrapper arg ptr address %p\n", &ptr);
    printf("malloc_wrapper arg ptr contents address %p\n", ptr);
    printf("malloc_wrapper ptr value address %p\n", *ptr);
    *ptr = (void *) calloc(numberOfBytes, sizeof(char));
    printf("after malloc_wrapper arg ptr address %p\n", &ptr);
    printf("after malloc_wrapper arg ptr contents address %p\n", ptr);
    printf("after malloc_wrapper ptr value address %p\n", *ptr);
}

int isBigEndian() {
    aunion_t my;

    my.i = 0x12345678;

    printf("%p %p %p %p\n", &my.array[0], &my.array[1], &my.array[2], &my.array[3]);
    printf("%02x %02x %02x %02x\n", my.array[0], my.array[1], my.array[2], my.array[3]);

    return !my.array[0];
}

int main(int argc, char * argv[]) {
    int * intPtr = NULL;

    printf("intPtr is at address %p\n", &intPtr);
    malloc_wrapper((void *)&intPtr, 4);
    if (intPtr == NULL) {
        printf("could not malloc\n");
        return -1;
    }

    printf("intPtr is at address %p\n", &intPtr);
    printf("intPtr is pointing to %p\n", intPtr);

    free (intPtr);

    printf("is big endian result : %08x\n", isBigEndian());

    return 0;
}