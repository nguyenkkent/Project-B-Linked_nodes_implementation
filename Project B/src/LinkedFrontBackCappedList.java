public class LinkedFrontBackCappedList<T extends Comparable<? super T>> implements FrontBackCappedListInterface<T>, Comparable<LinkedFrontBackCappedList<T>> {

	private Node head, tail;
	private int capacity;
	private int numberOfEntries;

//	public LinkedFrontBackCappedList(int capacity) {
//		if (capacity <= 0)
//			throw new NegativeArraySizeException("Invalid capacity size");
//		else if (capacity == 1) {
//			Node tempNode = new Node(null);
//			head = tempNode;
//			tail = tempNode;
//			this.capacity = 1;
//			numberOfEntries = 0;
//		}			
//		else { //add to the head like normal
//			Node tailNode = new Node(null);
//			tail = tailNode;
//			head = tailNode;
//			for (int i=0; i<capacity-1; i++) {
//				Node tempNode = new Node(null, head);
//				head = tempNode;
//				tail = tempNode;
//			}
//			this.capacity = capacity;
//			numberOfEntries = 0;			
//		}
//		else {//add to the tail instead
//			Node temp = new Node(null);
//			tail = temp;
//			head = temp;
//			for (int i=0; i<capacity-1; i++) {
//				Node tempNode = new Node(null);
//				tail.next = tempNode;
//				tail = tempNode;			
//			}
//			this.capacity = capacity;
//			numberOfEntries = 0;
//		}
//	}
	public LinkedFrontBackCappedList(int capacity) {
		head = null;
		tail = null;
		this.capacity = capacity;
		numberOfEntries = 0;
	}
	

	@Override
	public String toString() {
		if (isEmpty())
			return "[]" + "\t" + "size=0" + "\t" + "capacity=" + capacity;
		else if (numberOfEntries == 1) {
			return "[" + head.data + "]" + "\t" + "size=" + numberOfEntries + "\t" + "capacity=" + capacity + "\t" + "head=" + head.data + " " +"tail=" +tail.data;			
		}
		else {
			String contents = "";
			Node currentNode = head;
			contents += currentNode.data;
			currentNode = currentNode.next;
			for (int i=0; i< size()-1; i++) {			
				contents = contents + " ," + currentNode.data;
				currentNode = currentNode.next;
			}
			return "[" + contents + "]" + "\t" + "size=" + numberOfEntries + "\t" + "capacity=" + capacity + "\t" + "head=" + head.data + " " +"tail=" +tail.data;
		}
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public boolean isFull() {
		return numberOfEntries == capacity;
	}
	
	public int size(){
		return numberOfEntries;
	}
	
	public T getEntry(int index) {
		if (numberOfEntries == 0 || (index < 0) || (index >= size()))
			return null;
		else  {
			Node currentNode = head;
			for (int i=0; i < index; i++) 
				currentNode = currentNode.next;			
			return currentNode.data;
		}
	}
	
	public void clear() {
		if(!isEmpty()) {
			head = null;
			tail = null;
			numberOfEntries = 0;
		}
	}
	
	public boolean addFront(T data) {
		if (isEmpty()) {
			addBack(data);
			return true;
		}
		else if (!isFull()) {
			Node newNode = new Node(data, head);
			head = newNode;
			numberOfEntries++;
			return true;
		}
		else
			return false;
	}
	
	public boolean addBack(T data) {
		if (isEmpty()) {
			Node newNode = new Node(data);
			head = newNode;
			tail = newNode;
			numberOfEntries++;
			return true;
		}
		else if (!isFull()) {
			Node newNode = new Node(data);
			tail.next = newNode;
			tail = newNode;
			numberOfEntries++;
			return true;
		}
		else
			return false;
	}
	
	public T removeFront() {
		T result = null;
		if (isEmpty())
			return result;	
		else if (size() == 1) {
			result = head.data;
			clear();
		}
		else {
			result = head.data;
			head = head.next;
			numberOfEntries--;
		}
		return result;
	}
	
	public T removeBack() {
		T result = null;
		
		if (isEmpty())
			return result;
		
		else if (size() == 1) {
			result = head.data;
			clear();
		}
		else {
			result = tail.data;
			Node currentNode = head;
			while (currentNode.next.next != null) 
				currentNode = currentNode.next;
			tail = currentNode;						
			currentNode.next = null;
			numberOfEntries--;
		}
		return result;
	}
	
	public boolean contains(T data) {
		boolean found = false;
		if (isEmpty())
			return found;
		else {
			Node currentNode = head;
			while ((currentNode != null) && (!found)) {
				if ((currentNode.data).equals(data)) 
					found = true;
				else
					currentNode = currentNode.next;
			}
			return found;
		}
	}
	
	public int indexOf(T data) {
		int result = -1;
		if(isEmpty())
			return result;
		else {
			boolean found = false;
			int index = 0;
			Node currentNode = head;
			while ((currentNode != null) && (!found)) {
				if ((currentNode.data).equals(data)) {
					found = true;
					result = index;
				}							
				else {
					currentNode = currentNode.next;
					index++;
				}
			}
			return result;
		}
	}

	public int lastIndexOf(T data) {
		int result = -1;
		if(isEmpty())
			return result;
		else {
			int index = 0;
			Node currentNode = head;
			while (currentNode != null){
				if ((currentNode.data).equals(data)) {
					result = index;
				}
				currentNode = currentNode.next;
				index++;
			}
			return result;
		}
	}	
	
	@Override
	public int compareTo(LinkedFrontBackCappedList<T> other) {
		if (this.isEmpty() && !other.isEmpty()) {
			return -1;
		}
		else if (!this.isEmpty() && other.isEmpty()) {
			return 1;
		}
		else if (this.isEmpty() && other.isEmpty()) {
			return 0;
		}
		else {			
			Node currentNode = head;
			Node otherCurrentNode = other.head;
			while (currentNode != null && otherCurrentNode != null ) {
				if ((currentNode.data).compareTo(otherCurrentNode.data) == 0) { //both items are the same
					currentNode = currentNode.next;
					otherCurrentNode = otherCurrentNode.next;
				}
				else {
					return (currentNode.data).compareTo(otherCurrentNode.data); //found a difference
				}
			}
			
			//if exited the first while loop then one of the node chain is longer than the other
			int invokingIndexCounter = 0;
			int paramIndexCounter = 0;			
			while (currentNode != null) {
				currentNode = currentNode.next;
				invokingIndexCounter++;
			}
			while (otherCurrentNode != null) {
				otherCurrentNode = otherCurrentNode.next;
				paramIndexCounter++;
			}
								
			return Integer.valueOf(invokingIndexCounter).compareTo(Integer.valueOf(paramIndexCounter));
			
		}
	}
	
	public class Node {
		public T data; 
		public Node next; 

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
	}//end of node class
	
	
}//end of class
