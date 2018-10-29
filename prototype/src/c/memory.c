#include <stdlib.h>
#include <stdio.h>

void malloc_wrapper(const void ** ptr, const size_t numberOfBytes) {
    printf("malloc_wrapper arg ptr address %p\n", &ptr);
    printf("malloc_wrapper arg ptr contents address %p\n", ptr);
    printf("malloc_wrapper ptr value address %p\n", *ptr);
    *ptr = (void *) calloc(numberOfBytes, sizeof(char));
    printf("after malloc_wrapper arg ptr address %p\n", &ptr);
    printf("after malloc_wrapper arg ptr contents address %p\n", ptr);
    printf("after malloc_wrapper ptr value address %p\n", *ptr);
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

    return 0;
}