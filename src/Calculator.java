
public class Calculator {

	public static void main(String[] args) {
		String infix = "2-3*(4/5+6)*((7-8)/9)";
		
		calculator(infix);
		
	}
	
	public static void calculator(String infix) {
		
		List<String> ownStack = new List<String>();
		
		char token;
		int i=0;
		
		String postFix = "";
		
		while ( i < infix.length() ) {
			token = infix.charAt(i++); // get next token
		
			String strToken = Character.toString(token);
			
			switch (getType(token)) {
			case 0: // Operator
				if(ownStack.getSize() > 0) {
					if(token != ')') { 
						if (getPrecedence(token) > getPrecedence(ownStack.getTop().charAt(0)) || ownStack.getTop().equals("(")) {
							ownStack.push(strToken);
						} else if(getPrecedence(token) == getPrecedence(ownStack.getTop().charAt(0))) {
							if(!ownStack.getTop().equals("(")) {
								while(getPrecedence(token) <= getPrecedence(ownStack.getTop().charAt(0))) {
									postFix = postFix + ownStack.getTop();
									ownStack.pop();
								}
								ownStack.push(strToken);
							}
						} else {
							while(getPrecedence(token) >= getPrecedence(ownStack.getTop().charAt(0)) || !ownStack.getTop().equals("(")) {
								postFix = postFix + ownStack.getTop();
								ownStack.pop();
							}
							ownStack.push(strToken);
						}
					} else {
						while(!ownStack.getTop().equals("(")) {
							postFix = postFix + ownStack.getTop();
							ownStack.pop();
						}
						ownStack.pop();
					}
				} else {
					ownStack.push(strToken);
				}
				break;
			case 1: // Operand
				postFix = postFix + token;
				break;
			case 2: // Not found!
				System.out.println("Character not found: " + token);
				break;
			}
			System.out.println(i + " token: " + token + " postFix: " + postFix + " stack: " + ownStack.transverse());
		}
		
		while(ownStack.getSize() > 0) {
			postFix = postFix + ownStack.getTop();
			ownStack.pop();
		}
		
		System.out.println(postFix);
	}
	
	private static int getType(char character){
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
		switch(character) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		case '(':
			return 3;
		case ')':
			return 3;
		default:
			return 4;
		}
	}
}
