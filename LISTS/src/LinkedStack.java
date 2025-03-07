public class LinkedStack {
    private Node tail;
    private int size;

    public void push(int value) {
        Node node = new Node(value);
        if (tail != null) {
            node.setPrev(tail);
        }
        tail = node;
        size++;
    }

    public int pop() {
        if (this.isEmpty()) {
            return -1;
        }
        int result = tail.getValue();
        tail = tail.getPrev();
        size--;
        return result;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.isEmpty()) {
            Node currentNode = tail;
            int i = size;
            while (i != 0) {
                sb.append(currentNode.getValue()).append(" ");
                if (currentNode.getPrev() != null) {
                    sb.append("->");
                }
                currentNode = currentNode.getPrev();
                i--;
            }
        } else {
            return "EMPTY";
        }
        return sb.toString();
    }
}