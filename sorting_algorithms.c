#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ARRAY_SIZE 10

void mergeArray(int *start_of_array_of_numbers, int size) {
    // Will enter this method with a left and right array of numbers that are previsouly sorted.
    // Now need to merge them together.  Left most of each right and left side arrays is always the
    // lowest number.  Left side will always have one more if the size is odd.
    int index = 0, left_index = 0, right_index = (size /  2) + (size % 2), *buffer = NULL;
    
    // Need to alloc memory based on current size.
    buffer = (int *)malloc(sizeof(int) * size);
    
    if(buffer == NULL)
        return;
    
    printf("\n\nSize is : %d  Right Index Start is %d\n", size, right_index);
    
    for(index = 0; index < size; index++) {
        if(left_index < (size / 2 + size % 2) && (right_index >= size || start_of_array_of_numbers[left_index] < start_of_array_of_numbers[right_index])) {
            buffer[index] = start_of_array_of_numbers[left_index++];
        }
        else {
            buffer[index] = start_of_array_of_numbers[right_index++];
        }
    }
    
    // Now translate the data back to the original array.
    for(index = 0; index < size; index++) {
        start_of_array_of_numbers[index] = buffer[index];
    }
    
    // Free dynamic memory.
    free(buffer);
}

void recursiveMergeSort(int *start_of_array_of_numbers, int size) {
    printf("%d ", size);
    
    // Once the size is less than 2 cant break into a smaller sub-array.
    if(size < 2)
        return;
    
    // Left side first
    recursiveMergeSort(start_of_array_of_numbers,(size / 2) + (size % 2));
    // Right side second
    recursiveMergeSort(&start_of_array_of_numbers[(size / 2) + (size % 2)], (size / 2));
    // Merge the array back together.
    mergeArray(start_of_array_of_numbers, size);
}

// Break up each component of the array into their constitent parts. Then build back up.
void mergeSort(int *array_of_numbers) {
    // Merge sort can not be done with constant memory space.
    recursiveMergeSort(array_of_numbers, ARRAY_SIZE);
    return;
}

void bubbleSort(int *array_of_numbers) {
    // Bubble sort bubbles the highes value element to the end by comparing current element to next
    // element, as long as there is a next element.  The end of the array will be sorted first.
    int index = 0, end_position = 1, temp = 0;
    
    for(end_position = 1; end_position < ARRAY_SIZE; end_position++) {
        for(index = 0; index < (ARRAY_SIZE - end_position); index++) {
            if(array_of_numbers[index] > array_of_numbers[index+1]) {
                temp = array_of_numbers[index];
                array_of_numbers[index] = array_of_numbers[index+1];
                array_of_numbers[index+1] = temp;
            }
        }
    }
    
    return;
}

void selectionSort(int *array_of_numbers) {
    // Based on sorting the lowest value first.  Constant looping of lowest value search with an
    // increment from the base of the array until the end of the array.
    int index = 0, current_min_index = 0, current_min_value = 0, min_index= 0, temp = 0;
    
    for(current_min_index = 0; current_min_index < ARRAY_SIZE; current_min_index++) {
        current_min_value = array_of_numbers[current_min_index];
        min_index = current_min_index;
        
        for(index = current_min_index + 1; index < ARRAY_SIZE; index++) {
            if(array_of_numbers[index] < current_min_value) {
                min_index = index;
                current_min_value = array_of_numbers[index];
            }
        }
        
        temp = array_of_numbers[current_min_index];
        array_of_numbers[current_min_index] = array_of_numbers[min_index];
        array_of_numbers[min_index] = temp;
    }
    return;
}

int main(int argc, char *argv[]) {
    int array_of_numbers[ARRAY_SIZE] = {0}, index = 0;
    
    srand(time(NULL));
    
    for(index = 0; index < ARRAY_SIZE; index++) {
        array_of_numbers[index] = rand();
        printf("%d\n", array_of_numbers[index]);
    }
    
    //mergeSort(array_of_numbers);
    //bubbleSort(array_of_numbers);
    selectionSort(array_of_numbers);
    
    printf("\n\n");
    for(index = 0; index < ARRAY_SIZE; index++) {
        printf("%d\n",array_of_numbers[index]);
    }
    
    return 0;
}