package com.ai.algorithms.utility;

import java.util.ArrayList;
import java.util.List;

//I'm using an unsorted array for this example, but ideally this
// would be a binary heap. Find a binary heap class:
// * https://bitbucket.org/BlueRaja/high-speed-priority-queue-for-c/wiki/Home
// * http://visualstudiomagazine.com/articles/2012/11/01/priority-queues-with-c.aspx
// * http://xfleury.github.io/graphsearch.html
// * http://stackoverflow.com/questions/102398/priority-queue-in-net
public class PriorityQueue<T> {	    
    private List<PriorityTuple<T, Integer>> elements = new ArrayList<PriorityTuple<T, Integer>>();
    private boolean top = false;
    
    public int size() {
        return elements.size();
    }
    
    public void enqueue(T item, Integer priority) {
    	elements.add(new PriorityTuple<T, Integer>(item, priority));
    }

    public T dequeue() {
        int bestIndex = 0;
        List<Integer> bestArr = new ArrayList<Integer>();
        
        if(top) {
        	for(int i = 0; i < elements.size(); i++) {
	            if(elements.get(i).prirority < elements.get(bestIndex).prirority) {
	                bestIndex = i;
	                bestArr.add(i);
	            }
	        }
        	
        	top = false;
        } else {
        	for(int i = 0; i < elements.size(); i++) {
	            if(elements.get(i).prirority <= elements.get(bestIndex).prirority) {
	                bestIndex = i;
	                bestArr.add(i);
	            }
	        }
        	
        	top = true;
        }
        
        T bestItem = elements.get(bestIndex).node;
        elements.remove(bestIndex);
        return bestItem;
    }
}
