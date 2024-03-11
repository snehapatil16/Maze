package patilProject03;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
	
	private Node head;
	private int size;
	
	public SinglyLinkedList() {
		
		head = null;
		size = 0;
	}
	
	private class Node {
		
		private T value;
		private Node next;
		
		public Node(T v) {
			
			this.value = v;
			this.next = null;
		}
	}
	
	public void addLast(T value) {
		
		Node newNode = new Node(value);
		
		if (isEmpty()) {
			
			head = new Node(value);
			size++;
		
		} else {
			
			Node current = head;
			
			while (current.next != null) {
				
				current = current.next;
			}
			
			current.next = newNode;
			size++;
		}
	}

	public T removeFirst() {
		
		if (isEmpty()) {
			
			throw new NoSuchElementException("Can't remove an element because the list is empty.");
		}
		
		T removeNode = null;
		
		if (head.next == null) {
			
			removeNode = head.value;
			head = null;
		
		} else {
			
			removeNode = head.value;
			head = head.next;
		}
		
		size--;
		return removeNode;
	}

	public T removeLast() {
		
		if (isEmpty()) {
			
			throw new NoSuchElementException("The list is empty.");
		}
		
		T removeNode = null;
		
		if (head.next == null) {
			
			removeNode = head.value;
			head = null;
		
		} else {
			
			Node current = head;
			
			while (current.next.next != null) {
				
				current = current.next;
			}
			
			removeNode = current.next.value;
			current.next = null;
		}
		
		size--;
		return removeNode;
	}

	public boolean remove(T value) {
		
		if (isEmpty()) {
			
			return false;
		}
		
		if (head.value.equals(value)) {
			
			head = head.next;
			size--;
			return true;
		
		} else {
			
			Node current = head;
			
			while (current.next.next != null) {
				
				if (current.next.value.equals(value)) {
					
					current.next = current.next.next;
					size--;
					return true;
				
				} else {
					
					current = current.next;
				}
			}
			
			return false;
		}
	}

	public int size() { 
		
		return size;
	}

	public boolean isEmpty() {
		
		return head == null;
	}

	public void addFirst(T value) {
		
		if (isEmpty()) {
			
			this.head = new Node(value);
		
		} else {
			
			Node newNode = new Node(value);
			newNode.next = head;
			this.head = newNode;
		}
		
		this.size++;
	}

	public T get(int index) {
		
		if (index < 0 || index > size) {
			
			throw new IndexOutOfBoundsException("Entered index is larger than the list");
		
		} else if (index == 0) {
			
			if (head.value == null) {
				
				throw new NoSuchElementException("The list is empty");
			}
			
			return head.value;
		
		} else {
			
			Node current = head;
			
			for (int i = 1; i < index; i++) {
				
				current = current.next;
			}
			
			return current.value;
		}
	}

}

