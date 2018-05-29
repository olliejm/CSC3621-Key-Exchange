package utilities;
import java.io.PrintWriter;
import java.math.BigInteger;

public class LinearSolver {
	
	// Output directory for files
	public static final String OUTPUT_DIR = "/Users/ojm/Documents/workspace/CSC3621 DH Key Exchange/src/";
	
	// Input values for question 1 - 2a
	public static final BigInteger QUESTION_2a_a = new BigInteger("34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
	public static final BigInteger QUESTION_2a_b = new BigInteger("45292384209127917243621242398573220935835723464332452353464376432246757234546765745246457656354765878442547568543334677652352657235");
	public static final BigInteger QUESTION_2a_N = new BigInteger("643808006803554439230129854961492699151386107534013432918073439524138264842370630061369715394739134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");
	
	// Input values for question 1 - 2b
	public static final BigInteger QUESTION_2b_a = new BigInteger("4325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
	public static final BigInteger QUESTION_2b_b = new BigInteger("24243252873562935279236582385723952735639239823957923562835832582635283562852252525256882909285959238420940257295265329820035324646");
	public static final BigInteger QUESTION_2b_N = new BigInteger("342487235325934582350235837853456029394235268328294285895982432387582570234238487625923289526382379523573265963293293839298595072093573204293092705623485273893582930285732889238492377364284728834632342522323422");
	
	// EXERCISE II - 1 - 2a + 2b
	public static void main(String[] args) {
		// Capture solutions
	    BigInteger question2aX = solve(QUESTION_2a_a, QUESTION_2a_b, QUESTION_2a_N);
	    BigInteger question2bX = solve(QUESTION_2b_a, QUESTION_2b_b, QUESTION_2b_N);
	    
	    try (PrintWriter pw = new PrintWriter(OUTPUT_DIR + "part1-output.txt")) {
			// Print report for exchange without attack
			pw.print("EXERCISE 2 - Part 1 - Question 2a:\n");
			pw.print("N =" + QUESTION_2a_N + "\n");
			pw.print("a =" + QUESTION_2a_a + "\n");
			pw.print("b =" + QUESTION_2a_b + "\n");
			pw.print("(Solution) x =" + question2aX + "\n");
			
			// Print report for exchange without attack
			pw.print("\nEXERCISE 2 - Part 1 - Question 2a:\n");
			pw.print("N =" + QUESTION_2b_N + "\n");
			pw.print("a =" + QUESTION_2b_a + "\n");
			pw.print("b =" + QUESTION_2b_b + "\n");
			pw.print("(Solution) x =" + question2bX + "\n");
			
			// Flush
			pw.flush();
		} catch (Exception e) { e.printStackTrace(); }	
	}
	
	// EXERCISE II - 1 - 1
	public static BigInteger solve(BigInteger a, BigInteger b, BigInteger N) {
		// Try mod inverse to determine if solvable
		BigInteger modi;
		
		try {
			// Determine if a is relatively prime to N
			modi = a.modInverse(N);
		} catch (ArithmeticException e) {
			// If not, return null (no solution)
			return null;
		}
		
		return (b.negate().multiply(modi)).mod(N);
	}

}
