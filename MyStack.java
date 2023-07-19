//anas el malki 
//300248697
import java.util.Stack;
 
public class MyStack<E> {
    private Stack<E> stack;
 
    // Constructor to create empty Stack.
    public MyStack() { 
        stack = new Stack<E>(); }
 
    // method to check if stack is empty or not.
    public boolean empty() {
         return stack.empty(); }

    // method to push an element into the stack
    public E push(E item) {
        return stack.push(item); }


    // method to remove and return the element at the top of the stack
    public E pop() { 
        return stack.pop(); }
 
   
}