import java.lang.reflect.Array;
import java.util.Arrays;

public class MinIntHeap {
	private int capacity = 10;
	private int size = 0;
	
	int[] items = new int[capacity];
	
	private int getLeftChildIndex(int parentIndex) {
		return 2*parentIndex + 1;
	}
	
	private int getRightChildIndex(int parentIndex) {
		return 2*parentIndex + 2;
	}
	
	private int getParentIndex(int childIndex) {
		return (childIndex-1)/2;
	}
	
	private boolean hasLeftChild(int index) {
		return this.getLeftChildIndex(index) < this.size;
	}
	
	private boolean hasRightChild(int index) {
		return this.getRightChildIndex(index) < this.size;
	}
	
	private boolean hasParentIndex(int index) {
		return this.getParentIndex(index) >= 0;
	}
	
	private int getLeftChild(int index) {
		return this.items[this.getLeftChildIndex(index)];
	}
	
	private int getRightChild(int index) {
		return this.items[this.getRightChildIndex(index)];
	}
	
	private int getParent(int index) {
		return this.items[this.getParentIndex(index)];
	}
	
	private void swap(int index1, int index2) {
		int temp = this.items[index1];
		this.items[index1] = this.items[index2];
		this.items[index2] = temp;
	}
	
	private void ensureExtraCapacity() {
		if(this.size == this.capacity) {
			this.items = Arrays.copyOf(this.items, capacity*2);
			this.capacity *= 2; 
		}
	}
	
	private int peek() {
		if(this.size == 0)
			throw new IllegalStateException();
		return this.items[0];
	}
	
	private int poll() {
		if(this.size == 0)
			throw new IllegalStateException();
		int item = this.items[0];
		this.items[0] = this.items[this.size-1];
		this.size--;
		this.heapifyDown();
		return item;
	}
	
	private void add(int item) {
		this.ensureExtraCapacity();
		this.items[size++] = item;
		this.heapifyUp();
	}
	
	private void heapifyUp() {
		int index = this.size-1;
		while(this.hasParentIndex(index) && this.items[index] < this.items[this.getParentIndex(index)]) {
			this.swap(index, this.getParentIndex(index));
			index = this.getParentIndex(index);
		}
	}
	
	private void heapifyDown() {
		int index = 0;
		while(this.hasLeftChild(index)) {
			int smallerIndex = this.getLeftChildIndex(index);
			if(this.hasRightChild(index)) {
				smallerIndex = this.getLeftChild(index) <= this.getRightChild(index) ? smallerIndex : this.getRightChildIndex(index);
			}
			if(items[index] < items[smallerIndex]) {
				this.swap(index, smallerIndex);
				index = smallerIndex;
			} else
				break;
		}
	}
}
