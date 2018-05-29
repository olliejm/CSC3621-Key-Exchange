package model;

import java.math.BigInteger;
import java.security.SecureRandom;

// EXERCISE 2 - Part 2 - Question 1

public final class Participant {
	
	// Our constant modulus size as specified by the question
	public static final int MODULUS_LENGTH = 1024;
	
	// Field stores the secret of participant
	private final BigInteger secret;
	
	// Fields to hold sent or received messages for computation
	private Message sent = null;
	private Message received = null;
	
	// Construct a new participant with a random secret of given modulus
	public Participant() {
		SecureRandom rnd = new SecureRandom();
		this.secret = BigInteger.probablePrime(MODULUS_LENGTH, rnd);
	}
	
	// Compute a message to be sent from this participant
	public Message computeMessage() {
		// Random generator
		SecureRandom rnd = new SecureRandom();
			
	    // Compute our fields
		BigInteger modulus = BigInteger.probablePrime(MODULUS_LENGTH, rnd);
		BigInteger base = BigInteger.probablePrime(MODULUS_LENGTH, rnd);
		BigInteger value = base.modPow(secret, modulus);
		
		// Nullify received message so we don't get mixed states
		received = null;
			
		// Return the message and also store it
		return sent = new Message(modulus, base, value);
	}
	
	// Compute a response to a message from another participant
	public Message computeResponse(Message msg) {
		// Nullify sent message so we don't get mixed states
		sent = null;
		
		// Store the received message
		received = msg;
		return new Message(msg.getBase().modPow(secret, msg.getModulus()));
	}
	
	// Compute key for session with sender of input message
	public BigInteger computeKey(Message msg) {
		// If this participant is currently a sender
		if (sent != null) {
			// Compute the key
		    BigInteger result = msg.getValue().modPow(secret, sent.getModulus());
		    // Reset participant status
			sent = null;
			// Return
			return result;
		}
		
		// If this participant is currently a receiver
		if (received != null) {
			// Compute the key
			BigInteger result = msg.getValue().modPow(secret, received.getModulus());
			// Reset participant status
			received = null;
			// Return
			return result;
		}
		
		// Unknown participant state, we can't do anything useful
		throw new UnsupportedOperationException
		("A participant must have sent or received a message prior to key computation");
	}
	
	// Include this method to allow printing for coursework output .txt file
	public BigInteger getSecret() {
		return secret;
	}

}
