package patilProject03;

public class DLLQueue<T> implements Queue<T> {
	
	public DoublyLinkedList<T> queue;
	
	public DLLQueue() {
		
		queue = new DoublyLinkedList<T>();
	}

	@Override
	public int size() {
		
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		
		return queue.isEmpty();
	}

	@Override
	public void enqueue(T v) {
		
		queue.addFirst(v);
	}
	
	@Override
	public T dequeue() {
		
		return queue.removeLast();
	}
	
	@Override
	public T first() {
		
		return queue.first();
	}
	
	public String toString() {
		
		String list = "";
		
		for (int i = 0; i < queue.size(); i++) {
			
			list += queue.get(i) + " ";
		}
		
		return list;
	}

}
