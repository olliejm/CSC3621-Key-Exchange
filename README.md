# CSC3621-Key-Exchange
Modelling a Diffie-Hellman key exchange in Java for CSC3621 coursework component

Feedback: 

Part 1 - 24/25.
Frequency analysis works as expected. Decryption works as expected, but the frequency analysis of wikipedia was hardcoded in the program. The program could have used the frequency analysis of the plain text as reference. This would allow for example, a piece of ciphered archaic english to be decrypted by providing the program with a plain file containing a sample of archaic english, without changing the code. Nice use of javadoc. The report is very well written, and explains in detail the relevant aspects of the implementation. It would be great if the report had discussed the scenarios where you expect your decryption strategy to fail.

Part 2 - 25/25.
Good work. Very nice and easy to read report. * I do not see that Vigenere cipher is “a set of interwoven caesar ciphers”, rather I see that the caesar cipher is a special case of vigenere cipher when the length of the key is 1. * Notice that a single ciphertext encrypted with the Vigenere cipher with random key is unbreakable.

Total: 49/50
