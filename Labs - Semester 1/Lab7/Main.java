
public class Main {

	public static void main(String[] args) {
		//create new calcalator object
		TestCalculator testcal = new TestCalculator();
		boolean output1 = testcal.testParser();
		boolean output2 = testcal.testAdd();
		boolean output3 = testcal.testMultiplication();
		//if all testing are working
		if (output1 && output2 && output3) {
			System.out.println("Congratulations, your Calculator appears to be working.");
		}
	}
}
