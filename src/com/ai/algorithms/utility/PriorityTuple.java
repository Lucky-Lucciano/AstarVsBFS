package com.ai.algorithms.utility;

public class PriorityTuple<X, Y> {
	public X node;
	public Y prirority;
	
	public PriorityTuple(X item, Y priority) {
		this.node = item;
		this.prirority = priority;
	}
}
