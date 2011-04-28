
public class Node<E> {
	
	public E data;
	
	public Node<E> next = null;
	
	public Node(E dataItem) {
		data = dataItem;
		next = this;
	}
	
	public Node(E dataItem, Node<E> nextNodeRef) {
		data = dataItem;
		next = nextNodeRef;
	}
}
