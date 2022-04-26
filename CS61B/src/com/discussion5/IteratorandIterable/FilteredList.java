package com.discussion5.IteratorandIterable;
import java.util.*;

public class FilteredList<T> implements Iterable<T> {
	private List<T> L;
	private Predicate<T> filter; 
	
	
	public FilteredList(List<T> L, Predicate<T> filter) {
		this.L = L; 
		this.filter = filter; 	
	}
	
	@Override 
	public Iterator<T> iterator(){
		return new FilterListIterator();
	}
	
	private class FilterListIterator implements Iterator<T>{
		
		private int index; 
		
		public FilterListIterator() {
			index = 0; 
			moveIndex();
		}

		@Override
		public boolean hasNext() {			
			return index < L.size();
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			//STEP 1: extract the item to output
			T answer = L.get(index); 
			
			//STEP 2: prepare for the next time we call next
			moveIndex();
			
			//STEP 3: return item we got before  
			return answer;
		}

		
/**	Move the index instance attribute to the next spot in the list
 *  that satisfies the predicate 
 */
		
		private void moveIndex() {
			while(hasNext() && !filter.test(L.get(index))) {
				index += 1;
			}
			
		}
		
	}
}
 