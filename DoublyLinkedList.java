package patilProject03;

import java.util.NoSuchElementException;

public class DoublyLinkedList <T> {
	
	private Node header;
	private Node trailer;
	private int size;
	
	public DoublyLinkedList() {
		
		header = new Node(null);
		trailer = new Node(null);
		header.next = trailer;
		trailer.prev = header;
		size = 0;
	}
	
   public boolean isEmpty() {
		
		return header == null;
	}
	
	public int size() {
		
		return size;
	}
	
	public void addFirst(T v) throws IllegalArgumentException {
		
		addBetween(header, header.next, v);
	}
	
	public void addLast(T v) {
		
		addBetween(trailer.prev, trailer, v);
	}
	
    public T removeFirst () {
    	
        return removeBetween(header, header.next.next);
	}
    
    public T removeLast() {
		
		return removeBetween(trailer.prev.prev, trailer);
	}

	private void addBetween(Node before, Node after, T v) {
		 
		if (before == null || after == null) {
			
			throw new IllegalArgumentException("Either before or after nodes are null");
		}
		
		if (before.next != after) {
			
			throw new IllegalArgumentException("The two nodes before and after are not neighbors");
		}
		
		Node newNode = new Node(v);
		newNode.prev = before;
		newNode.next = after;
		before.next = newNode;
		after.prev = newNode;
		size++;
	}
	
	public String toString() {
		
		String r = "List:";
		Node current = header.next;
		
		while(current != trailer) {
			
			r += current.toString() + " ";
			current = current.next;	
		}
		
		return r;
	}
	
	private class Node {
		
		private Node next;
		private Node prev;
		private T value;
		
		public Node(T v) {
			
			this.next = null;
			this.prev = null;
			this.value = v;
		}
		
		public String toString() {
			
			return value.toString();
		}
	}
	
	public int search(T value) {
		
		int index = 0;
		
		if (header == null) {
			
			System.out.println("List is empty");
			return -1;
		}
	
		Node current = header;
		T curValue = null;
		boolean match = false;
		
		while (current != null) {
			
			curValue = current.value;
			
			if (curValue == value) {
				
				match = true;
				return index;
			
			} else {
				
				current = current.next;
				index++;
			}
		}
		
		if (match == false) {
			
			return -1;
		}
		
		return index;
	}
	
	public T get(int index) throws IndexOutOfBoundsException {
		
		Node current = header.next;
		int nodeCount = 0;
		T returnVal = null;
		
		while(current != null) {
			current = current.next;
			nodeCount++;
		}
		
		current = header.next;
				
		if(index > nodeCount-1 || index < 0) {
			
			throw new IndexOutOfBoundsException();
		}
		
		if(header.next == null) {
			
			returnVal = null;
		}
		
		else if (header.next.next == null) {
			
			returnVal = header.next.value;
			header.next = null;
		}

		if (index > 0) {
			
			for(int i = 0; i < index-1; i++) {
				
				current = current.next;
			}
			
			returnVal = current.next.value;
		
		} else {
			
			returnVal = header.next.value;
			header.next = header.next.next;
		}

		current.next = current.next.next;
		return returnVal;
		
	}
	
	public T first() {
		
		if (header.next != null) {
			
			return header.next.value;
		
		} else {
			
			throw new NoSuchElementException("Cannot get first value from an empty list.");
		}
	}


	public T removeBetween(Node node1, Node node2) {
		
		//check if either is null
        if (node1 ==  null || node2 == null) {
                
            throw new IllegalArgumentException("Must have valid parameters");
        }
                
        //Check for an empty list
		if (header.next ==  trailer) {
			
			throw new NoSuchElementException("Cannot delete from an empty list");
		}
        
		//check that given nodes are 1 apart
        if(node1.next.next != node2) {
                        
            throw new IllegalArgumentException("Must be 1 node apart");
         }
		
         T valueToReturn = node1.next.value;
	        
		 node1.next = node2;
		 node1.prev = node1;
		 size--;
		 return valueToReturn;
	}
}
