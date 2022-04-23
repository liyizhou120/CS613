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
		
		public FilterListIterator() {
			
		}

		@Override
		public boolean hasNext() {
			
			return false;
		}

		@Override
		public T next() {
			
			
			return null;
		}
		
	}
}
