
public class List<E> {

	// Reference to list head.
	private Node<E> head = null;

	// The number of items in the list
	private int size = 0;
	
	public void push(E item) {
		if(size == 0) {
			head = new Node<E>(item);
		} else {
			head = new Node<E>(item, head);
		}
		size++;
	}

	public void pop() {
		head = head.next;
		size--;
	}

	public E getTop() {
		return head.data;
	}
	
	public String transverse() {
		Node<String> nodeRef = (Node<String>)head;
		StringBuilder result = new StringBuilder();
		for(int i=0; i<size; i++) {
			result.append(nodeRef.data);
			if (nodeRef.next != null) {
				result.append(" ");
			}
			nodeRef = nodeRef.next; // Advance down the list
		}
		return result.toString();
	}
	
	public void clear() {
		head = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
}
