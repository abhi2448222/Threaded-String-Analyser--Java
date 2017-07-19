import java.util.Scanner;

public class AnalyzeString {

	static int digitCount = 0;
	static int upperCount = 0;
	static boolean isPalin = false;

	public static class DigitCount extends Thread {
		String analyse;

		public DigitCount(String analyse) {
			this.analyse = analyse;
		}

		public void run() {
			for (int i = 0; i < analyse.length(); i++) {
				if (analyse.charAt(i) >= 48 && analyse.charAt(i) <= 57)
					digitCount++;
			}
		}
	}

	public static class UpperCount extends Thread {
		String analyse;

		public UpperCount(String analyse) {

			this.analyse = analyse;
		}

		public void run() {
			for (int i = 0; i < analyse.length(); i++) {
				if (analyse.charAt(i) >= 65 && analyse.charAt(i) <= 90)
					upperCount++;
			}
		}
	}

	public static class IsPalindrome extends Thread {
		String analyse;

		public IsPalindrome(String analyse) {
			this.analyse = analyse;
		}

		public void run() {
			for (int i = 0, j = analyse.length() - 1; i < analyse.length() / 2; i++, j--) {
				if (analyse.charAt(i) != analyse.charAt(j)) {
					isPalin = false;
					return;

				}

			}
			isPalin = true;

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();

		// Creating Instances of 3 threads
		AnalyzeString.DigitCount dig = new AnalyzeString.DigitCount(str);
		AnalyzeString.UpperCount upp = new AnalyzeString.UpperCount(str);
		AnalyzeString.IsPalindrome palin = new AnalyzeString.IsPalindrome(str);

		// Starting the threads
		dig.start();
		upp.start();
		palin.start();

		// Waiting for all the threads to finish Execution
		try {
			dig.join();
			upp.join();
			palin.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Displaying Digit Count, Upper count and whether the String is a
		// Palindrome or not
		System.out.println("The Digit count of the String : " + digitCount);
		System.out.println("The UpperCase letters count of the String : " + upperCount);

		if (isPalin)
			System.out.println("The Given String is a Palindrome");
		else
			System.out.println("The Given String is Not a Palindrome");

	}

}
