package model;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class Attacker {

	// Keep track of the secrets for each fake identity
	private final BigInteger senderSecret;
	private final BigInteger receiverSecret;

	// The genuine sent message used to create forged messages
	private final Message originalMessage;

	// Instantiate an attack on the given message
	public Attacker(Message originalMsg) {
		// Store the message we need to make our spoofs
		this.originalMessage = originalMsg;

		// Make two secrets to use in comms with sender and receiver
		SecureRandom rnd = new SecureRandom();
		senderSecret = BigInteger.probablePrime(Participant.MODULUS_LENGTH, rnd);
		receiverSecret = BigInteger.probablePrime(Participant.MODULUS_LENGTH, rnd);
	}

	// Fake the message that we intercepted
	public Message spoofMessage() {
		return new Message(
			originalMessage.getModulus(),
			originalMessage.getBase(),
			originalMessage.getBase().modPow(senderSecret, originalMessage.getModulus())
		);
	}

	// Fake a response to the message we intercepted
	public Message spoofResponse() {
		return new Message(
			originalMessage.getBase().modPow(receiverSecret, originalMessage.getModulus())
    );
	}

	// Spoof key to act as a genuine receiver to Alice
	public BigInteger spoofReceiverKey() {
		return originalMessage.getValue().modPow(receiverSecret, originalMessage.getModulus());
	}

	// Spoof key to act as a genuine sender to Bob, given his response to previous spoof msg
	public BigInteger spoofSenderKey(Message originalResponse) {
		return originalResponse.getValue().modPow(senderSecret, originalMessage.getModulus());
	}

	// View fake secrets used:

	public BigInteger getFakedSenderSecret() {
		return senderSecret;
	}

	public BigInteger getFakedReceiverSecret() {
		return receiverSecret;
	}

}
