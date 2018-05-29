package model;

import java.math.BigInteger;

// EXERCISE 2 - Part 2 - Question 1

public final class Message {

	// Member fields for the components of a message
	private final BigInteger modulus;
	private final BigInteger base;
	private final BigInteger value;

	// Constructor for a message being sent A->B
	public Message(final BigInteger modulus, final BigInteger base, final BigInteger value) {
		this.modulus = modulus;
		this.base = base;
		this.value = value;
	}

	// Constructor for a message (response) being sent B->A -- i.e value only
	public Message(final BigInteger value) {
		this.modulus = null;
		this.base = null;
		this.value = value;
	}

	// Accessor methods:

	public BigInteger getModulus() {
		return modulus;
	}

	public BigInteger getBase() {
		return base;
	}

	public BigInteger getValue() {
		return value;
	}

}
