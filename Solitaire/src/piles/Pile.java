package piles;

import java.util.ArrayList;
import java.util.Iterator;

public class Pile<E> implements Iterable<E> {

	// Data Members
	// ------------
	private boolean isEnforcingRules;
	private ArrayList<E> elements;

	// Constructors
	// ------------
	public Pile() {
		this.elements = new ArrayList<E>();
		this.isEnforcingRules = false;
	}

	// Methods
	// -------
	public boolean isEnforcingRules() {
		return isEnforcingRules;
	}

	public void enforceRules() {
		this.isEnforcingRules = true;
	}

	public void dontEnforceRules() {
		this.isEnforcingRules = false;
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public Iterator<E> iterator() {
		return elements.iterator();
	}

	public void push(E element) {
		if (isEnforcingRules && !canPushLegally(element))
			throw new RuntimeException("Cannot push " + element + " to Pile.");
		elements.add(0, element);
	}

	public boolean canPushLegally(E element) {
		return true;
	}

	public E draw() {
		if (elements.isEmpty())
			throw new RuntimeException("Cannot draw from an empty Pile.");
		return elements.remove(0);
	}
}