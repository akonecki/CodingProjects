#include <stdlib.h>
#include <stdio.h>

typedef struct NODE {
    struct NODE *left_child;
    struct NODE *right_child;
    int value;
}node;

typedef struct TREE {
    node *root;
}tree;

typedef struct LIST_NODE {
    struct LIST_NODE *next_list_node;
    node *tree_node;
}list_node;

typedef struct LIST {
    list_node *head;
    list_node *tail;
}list;

void addNodeToBST(node *root, node *node_to_add) {
    printf("Root value is %c  New Node value is %c\n", root->value, node_to_add->value);
    
    // Does not take into account node of equal values due to not wanting to do dynamic memory.
    if(node_to_add->value < root->value) {
        if(root->left_child != NULL)
            addNodeToBST(root->left_child, node_to_add);
        else
            root->left_child = node_to_add;
    }
    else if(node_to_add->value > root->value) {
        if(root->right_child != NULL)
            addNodeToBST(root->right_child, node_to_add);
        else
            root->right_child = node_to_add;
    }
    
    return;
}

void preOrderVisit(node *node_to_visit) {
    if(node_to_visit == NULL)
        return;
    printf("%c ", node_to_visit->value);
    preOrderVisit(node_to_visit->left_child);
    preOrderVisit(node_to_visit->right_child);
}

void preOrderDFS(tree *tree_to_search) {
    // Definition of pre-order DFS is to examine / operate on the current node then go to left child
    // Then go right once all left children and grandchildren have been visited.  Using the natural
    // stack of the program to maintain oder for returning.
    
    printf("\nPerforming DFS-PreOrder\n");
    preOrderVisit(tree_to_search->root);
    printf("\n");
    return;
}

void inOrderVisit(node *node_to_visit) {
    if(node_to_visit == NULL)
        return;
        
    inOrderVisit(node_to_visit->left_child);
    printf("%c ", node_to_visit->value);
    inOrderVisit(node_to_visit->right_child);
}

void inOrderDFS(tree *tree_to_search) {
    // Definition of in order DFS is to go to the lowest left child leaf node display value then go
    // through the value in order of display.  If the BST is not setup correctly this will be quite
    // evident in the outcome of this DFS transversal procedure.
    printf("\nPerforming DFS-InOrder\n");
    inOrderVisit(tree_to_search->root);
    printf("\n");
    
    return;
}

void postOrderVisit(node *node_to_visit) {
    if(node_to_visit == NULL)
        return;
    
    postOrderVisit(node_to_visit->left_child);
    postOrderVisit(node_to_visit->right_child);
    printf("%c ", node_to_visit->value);
    
    return;
}

void postOrderDFS(tree *tree_to_search) {
    // Post is to always display the child leaves first from left to right before display the
    // parent.  Will still use the program stack to maintain the stack order for the visiting nodes.
    printf("\nPerforming DFS-PostOrder\n");
    postOrderVisit(tree_to_search->root);
    printf("\n");
    
    return;
}

void EnQueue(list *queue, node *node_to_add) {
    list_node *enqueue_element = NULL;
    
    enqueue_element = (list_node *)malloc(sizeof(list_node));
    if(enqueue_element == NULL)
        return;
    
    //printf("Address of allocation %08x for Node %c\n", (unsigned)enqueue_element, node_to_add->value);
    enqueue_element->tree_node = node_to_add;
    enqueue_element->next_list_node = NULL;
    
    if(queue->head == NULL && queue->tail == NULL) {
        queue->head = enqueue_element;
        queue->tail = enqueue_element;
    }
    else {
        queue->tail->next_list_node = enqueue_element;
        queue->tail = enqueue_element;
    }
}

node *DeQueue(list *queue) {
    list_node *queue_element = NULL;
    node *node_to_dequeue = NULL;
    
    if(queue->head == NULL)
        return NULL;
    else {
        queue_element = queue->head;
        if(queue->tail == queue_element)
            queue->tail = NULL;
        if(queue_element == NULL)
            return NULL;
        queue->head = queue_element->next_list_node;
        node_to_dequeue = queue_element->tree_node;
        //printf("Address of free 0x%08x\n", (unsigned)queue_element);
        free(queue_element);
    }
    
    return node_to_dequeue;
}

void BFSVisit(list *queue, node *node_to_visit) {
    node *next_node_to_visit = NULL;
    
    if(node_to_visit == NULL)
        return;
    //EnQueue(queue, node_to_visit);
    if(node_to_visit->left_child != NULL)
        EnQueue(queue, node_to_visit->left_child);
    if(node_to_visit->right_child != NULL)
        EnQueue(queue, node_to_visit->right_child);
    printf("%c ", node_to_visit->value);
    next_node_to_visit = DeQueue(queue);
    if(node_to_visit != NULL)
        BFSVisit(queue, next_node_to_visit);
    else
        return;
}

void BFS(tree *tree_to_search) {
    // The Breadth first search requires that the parent then the left then right children are
    // explored prior to going to the children.  Typicall BFS algorithms use a queue to maintain
    // node accesses.
    list queue = {0};
    node *current_node = NULL;
    
    printf("\nPerforming BFS\n");

    BFSVisit(&queue, tree_to_search->root);
    
    printf("\n");
    
    return;
}

int main(int argc, char *argv[]) {
    tree the_tree;
    node nodes[9] = {0};
    nodes[0].value = 'F';
    nodes[1].value = 'B';
    nodes[2].value = 'G';
    nodes[3].value = 'A';
    nodes[4].value = 'D';
    nodes[5].value = 'I';
    nodes[6].value = 'C';
    nodes[7].value = 'E';
    nodes[8].value = 'H';
    int index = 0;
    
    for(index = 1; index < 9; index++) {
        addNodeToBST(&nodes[0], &nodes[index]);
    }
    the_tree.root = &nodes[0];
    preOrderDFS(&the_tree);
    inOrderDFS(&the_tree);
    postOrderDFS(&the_tree);
    BFS(&the_tree);
    
    return 0;
}