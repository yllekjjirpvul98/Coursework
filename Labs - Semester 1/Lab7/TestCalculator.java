public class TestCalculator {
	
	Calculator c;
	
	public TestCalculator() {
		this.c = new Calculator();
	}
	
	//test whether parsing can add, multiply and return null
	public boolean testParser() {	
		
		//test adding
		boolean test1 = c.x("12 + 5") == 17;
		if (!test1) { 
			System.out.println("[FAIL] Basic parsing fails to add");
		}else {
			System.out.println("[ OK ] Parser adds correctly");
		}
		
		//test multiplying
		boolean test2 = c.x("12 x 5") == 60;
		if (!test2) {
			System.out.println("[FAIL] Basic parsing fails to multiply");
		}else {
			System.out.println("[ OK ] Parser multiplies correctly");
		}
		
		//test unsupported operator
		boolean test3 = c.x("12 [ 3") == null;
		if (!test3) {
			System.out.println("[FAIL] Parser does not return null for operator which are not supported");
		}else {
			System.out.println("[ OK ] Parser return null for operators which are not supported");
		}
		if (test1 && test2 && test3) {
			return true;
		}else {
			return false;
		}
}
	
	//test adding operations
	public boolean testAdd() {
		
		//test calculator can add positive number correctly
		boolean test1 = c.x("12 + 5") == new Double(17);
		if (!test1) {
			System.out.println("[FAIL] Calculator adds incorrectly");
		} else {
			System.out.println("[ OK ] Calculator can add positive number");
		}
		
		//test calculator can add negative numbers correctly
		boolean test2 = c.x("-12 + -8") == -20;
		if (!test2) {
			System.out.println("[FAIL] Calculator adds with negative number incorrectly");
		}else {
			System.out.println("[ OK ] Calculator can add negative number correctly");
		}
		if (test1 && test2) {
			return true;
		}else {
			return false;
		}
	}
	
	//testing multiplication
	public boolean testMultiplication() {
		
		//test multiplying two positive numbers
		boolean test1 = c.x("12 x 5") == 60;
		if (!test1) {
			System.out.println("[FAIL] Calculator multiplies incorrectly");
		}else {
			System.out.println("[ OK ] Calculator can multiply positive number");
		}
		
		//test multiplying two negative numbers
		boolean test2 = c.x("-12 x -5") == 60;
		if (!test2) {
			System.out.println("[FAIL] Calculator multiplies by negative incorrectly");
		}else {
			System.out.println("[ OK ] Calculator can multiply negative number");
		}
		if (test1 && test2) {
			return true;
		}else {
			return false;
		}
	}

}
