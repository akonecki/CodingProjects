#include <stdlib.h>
#include <stdio.h>

typedef union AUNION {
    int i;
    char array [4];
} aunion_t;

typedef struct TreeNode {
    int value;
    struct TreeNode * left;
    struct TreeNode * right;
} treenode_t;

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

treenode_t * insert(treenode_t ** root_ptr, const int value) {
    if (*root_ptr == NULL) {
        // tree is empty
        malloc_wrapper((void *)(root_ptr), sizeof(treenode_t));
        (*root_ptr)->value = value;
        return *root_ptr;
    }
    else {
        if ((*root_ptr)->value >= value) {
            // go left
            (*root_ptr)->left = insert(&(*root_ptr)->left, value);
            //((*root_ptr)->left) = insert(&(*root_ptr)->left, value);
        } 
        else {
            (*root_ptr)->right = insert(&(*root_ptr)->right, value);
        }    
        return *root_ptr;
    }
}

void traversal(const treenode_t * root_ptr) {
    if (root_ptr == NULL) {
        return;
    }
    else {
        traversal(root_ptr->left);
        printf("%d ", root_ptr->value);
        traversal(root_ptr->right);
        return;
    }
}

int main(int argc, char * argv[]) {
    int * intPtr = NULL;
    volatile int number = 0;
    treenode_t * root = NULL;

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

    insert(&root, 1);
    printf("root is %p\n", root);
    traversal(root);
    insert(&root, 2);
    traversal(root);
    insert(&root, 3);
    traversal(root);
    insert(&root, 1);
    traversal(root);
    insert(&root, 2);
    traversal(root);
    insert(&root, 3);
    traversal(root);
    insert(&root, -1);
    traversal(root);
    insert(&root, 0);
    traversal(root);


    return 0;
}