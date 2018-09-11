# Challenge 30 - Expression Evaluation
## Description
>
Given a string that represents a mathematical expression perform the indicated operations on the operands to find the result.

### Problem Discussion
- no parenthesis
- no only support integers

### Problem Labels
- Stack

### Problem Considerations
#### Consideration 1
- two stacks, one for numbers and one for operators.
- keep pushing operands and operators onto their respective stack
- when an decrease in operator order changes than process all higher operations first
- then continue pushing
- when reach the end then pop off operations & operands off the stack to find value

#### Consideration 2
- find the value using a two pass system
- first perform all operations for teh * and /.
- second perform all operations for the + and -.
- this will still be done like in the above `Consideration 1` with a two stack system.