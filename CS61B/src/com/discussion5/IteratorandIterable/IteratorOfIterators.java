 package com.discussion5.IteratorandIterable;
import java.util.*;

public class IteratorOfIterators implements Iterator<Integer>{
	LinkedList<Iterator<Integer>> list; 
	
	public IteratorOfIterators(List<Iterator<Integer>> a) {
		list = new LinkedList<>();
		for(Iterator<Integer> iterator: a) {
			if(iterator.hasNext()) {
				list.addLast(iterator);
			}
		}
		
		
	}

	@Override
	public boolean hasNext() {
		return !list.isEmpty();
	}

	@Override
	public Integer next() {

		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		//Step 1: extract the next element 
		Iterator<Integer> nextIterator = list.removeFirst();
		int ans = nextIterator.next();
		
		//Step 2: prepare for the next time next is called
		if(nextIterator.hasNext()) {
			list.addLast(nextIterator);
		}
		
		//Step 3: return the next element
		return ans;
	}
	
	
}
