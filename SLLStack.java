package patilProject03;

public class SLLStack<T> implements Stack<T> {
	
	private SinglyLinkedList<T> stack;
	
	public SLLStack() {
		
		stack = new SinglyLinkedList<T>();
	}
	
	@Override
	public int size() {
		
		return stack.size();
	}
	
	@Override
	public boolean isEmpty() {
		
		return stack.isEmpty();
	}
	
	@Override
	public void push(T v) {
		
		stack.addFirst(v);
	}
	
	@Override
	public T pop() {
		
		return stack.removeFirst();
	}
	@Override
	public T top() {
		
		return stack.get(0);
	}


}
