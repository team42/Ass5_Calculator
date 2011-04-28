
public class Calc {

	public static void main(String[] args) {
		String infix = "2-3*(4/5+6)*((7-8)/9)";
		
		calculator(infix);
		
	}
	
	public static void calculator(String infix) {
		
		// Initialize stack
		List<String> ownStack = new List<String>();
		
		// Other variables
		char token = '?';
		int i=0;
		String postFix = "";
		
		// Scan all characters
		while ( i < infix.length() ) {
			token = infix.charAt(i++); // get next token
		
			// Convert from char to string
			String strToken = Character.toString(token);
			
			// Switch on current character
			switch(token) {
			// Start bracket
			// Push to stack
			case '(':
				ownStack.push(strToken);
				break;
			// End bracket
			// Push from stack till start bracket is found
			case ')':
				while(!ownStack.getTop().equals("(")) {
					postFix = postFix + ownStack.getTop();
					ownStack.pop();
				}
				ownStack.pop();
				break;
			// Default
			default:
				// Identify current character as operator or operand
				switch (getType(token)) {
				case 0: // Operator
					// if stack size is above 0 - do as normal
					if(ownStack.getSize() > 0) {
						// if precedence of new token is larger than precedence of top of stack
						// or if top of stack is start bracket
						// Push to stack
						if (getPrecedence(token) > getPrecedence(ownStack.getTop().charAt(0)) || ownStack.getTop().equals("(")) {
							ownStack.push(strToken);
						// If precedence of new token is equal to precedence of top of stack
						// While token precedence is smaller or equal to precedence of top of stack
						// -- Move top of stack to output
						// Push token to stack
						} else if(getPrecedence(token) == getPrecedence(ownStack.getTop().charAt(0))) {
							while(getPrecedence(token) <= getPrecedence(ownStack.getTop().charAt(0))) {
								postFix = postFix + ownStack.getTop();
								ownStack.pop();
							}
							ownStack.push(strToken);
						} else {
						// Else
						// While token precedence is bigger or equal to precedence of top of stack
						// -- Move top of stack to output
						// Push token to stack
							while(getPrecedence(token) >= getPrecedence(ownStack.getTop().charAt(0)) || !ownStack.getTop().equals("(")) {
								postFix = postFix + ownStack.getTop();
								ownStack.pop();
							}
							ownStack.push(strToken);
						}
					// If stack size is 0, just push to stack
					} else {
						ownStack.push(strToken);
					}
					break;
				case 1: // Operand
					// Add operand to output
					postFix = postFix + token;
					break;
				default: // Not found!
					// Print error message
					System.out.println("Character not found: " + token);
					break;
				}
				break;
			}
			//System.out.println(i + " token: " + token + " postFix: " + postFix + " stack: " + ownStack.transverse());
		}
		
		// when there's no more characters
		// -- Add rest of stack to output
		while(ownStack.getSize() > 0) {
			postFix = postFix + ownStack.getTop();
			ownStack.pop();
		}
		
		System.out.println(postFix);
		
		evaluate(postFix);
	}
	
	private static void evaluate(String postFix) {
		List<Double> calcList = new List<Double>();
		
		String postFixChar = "";
		
		// Scan all characters
		for(int i=0; i<postFix.length(); i++) {
			
			// Transform char to string
			postFixChar = Character.toString(postFix.charAt(i));
			
			// Variables
			double operand1 = 0.0;
			double operand2 = 0.0;
			double result = 0.0;
			
			// If character is an operand, push to the stack
			if (getType(postFix.charAt(i)) == 1) {
				calcList.push(Double.parseDouble(postFixChar));
			// If character is an operator
			// Get two top fields of the stack and calculate them by the operator
			// Push result to stack
			} else {
				operand2 = calcList.getTop();
				calcList.pop();
				operand1 = calcList.getTop();
				calcList.pop();
				result = calculate(operand1, operand2, postFix.charAt(i));
				calcList.push(result);
			}
		}
		
		// Print result from the top of the stack
		System.out.println(calcList.getTop());
			
	}
	
	private static double calculate(double operand1, double operand2, char operator) {
		// Calculate the two operands by the operator
		switch(operator) {
		case '+':
			System.out.println(operand1 + " + " + operand2);
			return operand1+operand2;
		case '-':
			System.out.println(operand1 + " - " + operand2);
			return operand1-operand2;
		case '*':
			System.out.println(operand1 + " * " + operand2);
			return operand1*operand2;
		case '/':
			System.out.println(operand1 + " / " + operand2);
			return operand1/operand2;
		default:
			return 0;
		}
	}
	
	private static int getType(char character){
		// Return 1 if operand
		// Return 0 if operator
		// Return 2 if unknown
		switch(character) {
		case '0':
			return 1;
		case '1':
			return 1;
		case '2':
			return 1;
		case '3':
			return 1;
		case '4':
			return 1;
		case '5':
			return 1;
		case '6':
			return 1;
		case '7':
			return 1;
		case '8':
			return 1;
		case '9':
			return 1;
		case '+':
			return 0;
		case '-':
			return 0;
		case '*':
			return 0;
		case '/':
			return 0;
		case '(':
			return 0;
		case ')':
			return 0;
		default:
			return 2;
		}
	}
	
	private static int getPrecedence(char character) {
		// plus and minus have 1 precedence
		// multiply and divide have 2 precedence
		// Return 4 if unknown
		switch(character) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		default:
			return 4;
		}
	}
}
