package utilities;

import java.io.PrintWriter;
import java.math.BigInteger;

import model.*;

public class DHKeyExchange {

	public static void main(String[] args) {
		try (PrintWriter pw = new PrintWriter(LinearSolver.OUTPUT_DIR + "part2-output.txt")) {
			boolean attack = true;

			// Print report for exchange without attack
			pw.print("EXERCISE 2 - Part 2 - Question 2:\n");
			pw.print(exchangeReport(!attack));

			// Now print report for exchange with attack
			pw.print("\nEXERCISE 2 - Part 2 - Question 4b:\n");
			pw.print(exchangeReport(attack));

			// Flush
			pw.flush();
		} catch (Exception e) { e.printStackTrace(); }
	}

	private static String exchangeReport(boolean attacked) {
		StringBuilder sb = new StringBuilder();

		// EXERCISE 2 - Part 2 - Question 2
		if (!attacked) {
			// Create Alice and print secret
			Participant alice = new Participant();
			sb.append("secretA =" + alice.getSecret() + "\n");

			// Create Bob and print secret
			Participant bob = new Participant();
			sb.append("secretB =" + bob.getSecret() + "\n");

			// Capture message 1, which Alice sends to Bob, and print info
			Message msg1 = alice.computeMessage();
			sb.append("msg1.modulus =" + msg1.getModulus() + "\n");
			sb.append("msg1.base =" + msg1.getBase() + "\n");
			sb.append("msg1.valueA =" + msg1.getValue() + "\n");

			// Capture message 2, Bob's response to Alice, and print info
			Message msg2 = bob.computeResponse(msg1);
			sb.append("msg2.valueB =" + msg2.getValue() + "\n");

			// Compute key (according to Alice), print
			BigInteger keyA = alice.computeKey(msg2);
			sb.append("keyA =" + keyA + "\n");

			// Compute key (according to Bob), SHOULD match Alice's, print
			BigInteger keyB = bob.computeKey(msg1);
			sb.append("keyB =" + keyB + "\n");

			// Return complete string
			return sb.toString();
		}

		// EXERCISE 2 - Part 2 - Question 4b
		else {
			// Initialise participants
			Participant alice = new Participant();
			Participant bob = new Participant();
			Attacker eve;

			// Alice and Bob's secrets (not used by Eve - display only)
			sb.append("secretA =" + alice.getSecret() + "\n");
			sb.append("secretB =" + bob.getSecret() + "\n");

			// Alice computes first message
			Message msg1 = alice.computeMessage();

			// Show contents of the real message, for completion
			sb.append("msg1.modulus =" + msg1.getModulus() + "\n");
			sb.append("msg1.base =" + msg1.getBase() + "\n");
			sb.append("msg1.valueA =" + msg1.getValue() + "\n");

			// Eve can now intercept and replace it
			eve = new Attacker(msg1);

			// Eve's fake secrets, again for completion
			sb.append("secretEA (Eve's 'fake' sender identity secret) =" + eve.getFakedSenderSecret() + "\n");
			sb.append("secretEB (Eve's 'fake' receiver identity secret) =" + eve.getFakedReceiverSecret() + "\n");

			// Eve creates a spoofed message for Bob from Alice's message
			Message replaceMsg1 = eve.spoofMessage();
			sb.append("fakeMsg1.modulus =" + replaceMsg1.getModulus() + "\n");
			sb.append("fakeMsg1.base =" + replaceMsg1.getBase() + "\n");
			sb.append("fakeMsg1.valueA =" + replaceMsg1.getValue() + "\n");

			// Bob computes a response to Eve's replacement message
			Message msg2 = bob.computeResponse(replaceMsg1);

			// Contents of Bob's real response to Eve's fake message
			sb.append("msg2.valueB =" + msg2.getValue() + "\n");

			// Eve replace's Bob's response with her own and sends to Alice
			Message replaceMsg2 = eve.spoofResponse();
			sb.append("fakeMsg2.valueB =" + replaceMsg2.getValue() + "\n");

			// Key between Alice and Eve
			BigInteger keyA = alice.computeKey(replaceMsg2);
			sb.append("keyA (Between Alice and Eve) =" + keyA + "\n");
			sb.append("keyEA (Between Alice and Eve) =" + eve.spoofReceiverKey() + "\n");

			// Key between Eve and Bob
			BigInteger keyB = bob.computeKey(replaceMsg1);
			sb.append("keyB (Between Eve and Bob) =" + keyB + "\n");
			sb.append("keyEB (Between Eve and Bob) =" + eve.spoofSenderKey(msg2) + "\n");

			// Return complete string
			return sb.toString();
		}
	}

}
