public class Solution {

    // Odd question of using an array to implement three stacks.  Would be lot 
    // of room for misrepresentation of the problem.
    // need to first determine if all three stacks are contained within the 
    // single array.
    // need to determine if the array is going to be dynamically available for
    // resizing.
    // need to determine if the stacks will be three separate entities or if
    // you need to have a method for selecting one for where to store data.

    // stack is already used in normal programming in a generic array in terms 
    // of a program's stack space for function and variables.
    // stack grows down while a heap grows up, since not creating a heap can 
    // replace with another stack.  so the one problem is where to put the third
    // stack.
    // third stack can be in front / behind / middle of the two stacks growing
    // toward each other.
    // need to determine if stacks are going to be dynamic within the given 
    // array as well.  Say place down three headers, if one stack gets majority
    // and has run into the other does it keep growing (by moving the other) or
    // should it return error in the capability of performing an insert

    // Solution talks about static stack definition in addition to dynamic in
    // which the stacks evenutally fill up all the available memory.  This is
    // quite expensive in the fact that it moves entire stacks around.  If the
    // goal is for optimal memory usage then this may be of interest, however
    // the run time cost can go onto the order of O(N^2).
}