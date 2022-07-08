//Jamie Shannon
//5/27/22
//This project takes operands and operators and performs desired operations on the operands which are stored in a stack

package stackcalculator;
import java.util.Scanner;
import java.util.Stack;
import java.util.*;

public class Calcualtor {
	
	//method to check if a string is a number
	public static boolean isNumber(String string) {
		int num;
		
		try {
			num = Integer.parseInt(string);
			return true;
		} catch(NumberFormatException e){
			
		}
		return false;
	}

	public static void main(String[] args) {
		
		//Keeps track of the last result
		int result = 0;
		
		boolean cont = true;
		
		//Creating stack for integers
		Stack<Integer> intStack = new Stack<Integer>();
		
		//Creating a set for accepted operators
		Set<String> operators = new HashSet<String>();
		
		operators.add("+");
		operators.add("-");
		operators.add("/");
		operators.add("*");
		operators.add("%");
		
		//Creating a set of accepted special characters
		Set<String> specialCharacters = new HashSet<String>();
		
		specialCharacters.add("<<");
		specialCharacters.add("\\");
		specialCharacters.add("^");
		
		Scanner userInput = new Scanner(System.in);
		System.out.print("Enter your line:");
		String listOfActions = userInput.nextLine();
		
		//converting the input into an array of strings
		String[] input = listOfActions.split(" ", 0);
				
		while(cont) {
			
			for(String a : input) {
				
				if (isNumber(a)) {
					int x = Integer.parseInt(a);
					intStack.push(x);
					continue;
				}
				
				if(operators.contains(a)){
					
					//Makes sure there are enough elements in the stack to perform an operation
					if (intStack.size() < 2) {
						System.out.println("not enough operands");
						continue;
					}
					
					//addition
					if ((a).equals("+")) {
						int add = 0;
						add += intStack.pop();
						add += intStack.pop();
						result = add;
						intStack.push(add);
						continue;
					}
					
					//subtraction
					if ((a).equals("-")) {
						int sub = 1;
						sub *= intStack.pop();
						sub -= intStack.pop();
						result = sub;
						intStack.push(sub);
						continue;
					}
					
					//division
					if ((a).equals("/")) {
						int numerator = intStack.pop();
						int divider = intStack.pop();
						
						//exception if division is by zero
						if (divider == 0) {
							System.out.println("Invalid operation, division by zero");
							intStack.push(divider);
							intStack.push(numerator);
							continue;
						}
						
						int div = (numerator /= divider);
						result = div;
						intStack.push(div);
						continue;
					}
					
					//multiplication
					if ((a).equals("*")){
						int multi = 1;
						multi *= intStack.pop();
						multi *= intStack.pop();
						result = multi;
						intStack.push(multi);
						continue;
					}
					
					//modular arithmetic
					if ((a).equals("%")) {
						int numerator = intStack.pop();
						int divider = intStack.pop();
						
						//exception if divisor is zero
						if (divider == 0) {
							System.out.println("Invalid operation, division by zero");
							intStack.push(divider);
							intStack.push(numerator);
							continue;
						}
						
						int modul = (numerator %= divider);
						result = modul;
						intStack.push(modul);
						continue;
					}
																	
				}
				
				if (specialCharacters.contains(a)) {
					
					if ((a).equals("<<")) {
						System.out.println(intStack);
						continue;
					}
					
					if ((a).equals("^")) {
						
						if (intStack.size() == 0) {
							System.out.println("nothing in stack");
							continue;
						}
						
						result = intStack.pop();
						System.out.println(result);
						continue;
					}
					
					if ((a).equals("\\")){
						System.out.println("Result: " + result);
						cont = false;
						break;
					}
					
				}
				
				//If action is neither an operator or special character or a number it is an invalid input
				if (!operators.contains(a) && !specialCharacters.contains(a) && !isNumber(a)) {
					System.out.println("Invalid Input");
					continue;
				}
					
			}
			cont = false;
		}
	}
}
