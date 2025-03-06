import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        LinkedStack stack = new LinkedStack();

        System.out.println(stack); // EMPTY

        stack.push(5);
        System.out.println(stack); // 5

        stack.push(15);
        System.out.println(stack); // 15 -> 5

        stack.push(25);
        System.out.println(stack); // 25 -> 15 -> 5

        System.out.println(stack.pop()); // 25
        System.out.println(stack); // 15 -> 5

        System.out.println(stack.pop()); // 15
        System.out.println(stack); // 5

        System.out.println(stack.pop()); // 5
        System.out.println(stack); // EMPTY
    }

}