class MyStack {

    java.util.Queue<Integer> q1;
    java.util.Queue<Integer> q2;
    public MyStack() {
        q1 = new java.util.LinkedList<>();
        q2 = new java.util.LinkedList<>();
    }   
    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        java.util.Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    public int pop() {
        return q1.remove();
    }
    
    public int top() {
        return q1.peek();
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}