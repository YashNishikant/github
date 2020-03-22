
package trivia;

import java.util.Scanner;

public class trivia {
	public static void main(String[] args) {
		
			int totalPoints = 0;    	// Keep ongoing tally of correct answers
			int numberOfQuestions = 21;	// Change to match the total number of operative questions
			totalPoints += q1();
			totalPoints += q2();
			totalPoints += q3();
			totalPoints += q4();
			totalPoints += q5();
			totalPoints += q6();
			totalPoints += q7();
			totalPoints += q8();
			totalPoints += q9();
			totalPoints += q10();
			totalPoints += q11();
			totalPoints += q12();
			totalPoints += q13();
			totalPoints += q14();
			totalPoints += q15();
			totalPoints += q16();
			totalPoints += q17();
			totalPoints += q18();
			totalPoints += q19();
			totalPoints += q20();
			totalPoints += q21();
			System.out.println("\nYou scored "+totalPoints+" out of "+numberOfQuestions);
	
	
			

	}
	
	public static int q1(){  // Change _YOURNUMBER_ to the question number you were assigned.
		System.out.println("\nQuestion # 1");
		Scanner reader = new Scanner(System.in);
		System.out.println("Consider: public static ObjectType Identifier(object a, object b)\nWhich keyword represents the return type?");
		System.out.println("(A) public");
		System.out.println("(B) static");
		System.out.println("(C) ObjectType");
		System.out.println("(D) Identifier");
		System.out.print("\nYour answer --> ");
		String correct = "C";
		String ans = reader.nextLine();
		switch(ans){
			case "C": case "c":
				System.out.println("Correct!");
				return 1;
			default:
				System.out.println("Incorrect! It was " + correct);
				return 0;
			
		}
	}

	public static int q2(){  // Change _YOURNUMBER_ to the question number you were assigned.
			System.out.println("\nQuestion # 2");
			Scanner reader = new Scanner(System.in);
			System.out.println("Consider: public static ObjectType Identifier(object a, object b)\nWhich keyword represents the method name?");
			System.out.println("(A) public");
			System.out.println("(B) static");
			System.out.println("(C) ObjectType");
			System.out.println("(D) Identifier");
			System.out.print("\nYour answer --> ");
			String correct = "D";
			String ans = reader.nextLine();
			switch(ans){
				case "D": case "d":
					System.out.println("Correct!");
					return 1;
				default:
					System.out.println("Incorrect! It was " + correct);
					return 0;
				
			}
	}
	
	public static int q3(){  // Change _YOURNUMBER_ to the question number you were assigned.
			System.out.println("\nQuestion # 3");
			Scanner reader = new Scanner(System.in);
			System.out.println("Consider: public static ObjectType Identifier(object a, object b)\nWhich parameters does this function take?");
			System.out.println("(A) Object Type");
			System.out.println("(B) object a");
			System.out.println("(C) object b");
			System.out.println("(D) Both B and C");
			System.out.print("\nYour answer --> ");
			String correct = "D";
			String ans = reader.nextLine();
			switch(ans){
				case "D": case "d":
					System.out.println("Correct!");
					return 1;
				default:
					System.out.println("Incorrect! It was " + correct);
					return 0;
				
			}
	}

	
	public static int q4(){
		System.out.println("\nQuestion #4");
		Scanner reader = new Scanner(System.in);
		System.out.print("What standards must an if statement meet in order to work?\nA)The parentheses should have an integer\nB)The parentheses should have an conditional\nC)The parentheses should have a string\nD)The parentheses should have a double\n\nYour answer --> ");
		String correct = "B";
		
		String ans = reader.nextLine();
		switch (ans){
			case "B" : case "b":
			System.out.println("Correct!");
			return 1;
			default:
			System.out.println("Incorrect! It was " + correct);
			return 0;
		}
		
	}

	public static int q5(){
		System.out.println("\nQuestion #5");
		Scanner reader = new Scanner(System.in);
		System.out.print("What data types can switch statements be used with?\nA)Doubles\nB)Only Strings\nC)Only Integers\nD)Integers and Strings\n\nYour answer --> ");
		String correct = "D";
		
		String ans = reader.nextLine();
		switch (ans){
			case "D" : case "d":
			System.out.println("Correct!");
			return 1;
			default : 
				System.out.println("Incorrect! It was " + correct);
				return 0;
		}

	}
	
	public static int q6(){
		System.out.println("\nQuestion #6");
		Scanner reader = new Scanner(System.in);
		System.out.print("What do all methods in this course start with?\nA)Public Static\nB)Main\nC)Import\nD)Package\n\nYour answer --> ");
		String correct = "A";
		
		String ans = reader.nextLine();
		switch (ans){
			case "A" : case "a":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}


	
	public static int q7(){
		System.out.println("\nQuestion #7");
		Scanner reader = new Scanner(System.in);
		System.out.print("Which is the proper syntax to initiate new a Scanner?\nA)Scanner reader = new Scanner(System.out);\nB)scanner Reader = New scanner(system.Out);\nC)Scanner reader = new Scanner(System.in);\nD)Scanner java = new pineapple(SyStEm.In);\n\nYour answer --> ");
		
		String correct = "C";
		String ans = reader.nextLine();
		switch (ans){
			case "C" : case "c":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q8(){
		System.out.println("\nQuestion #8");
		Scanner reader = new Scanner(System.in);
		System.out.print("What is the proper line of code to import a Scanner?\nA)Import Scanner;\nB)import java.util.Scanner;\nC)importJavaScannerNowPlease;\nD)import java.util.scanner;\n\nYour answer --> ");
		
		String correct = "B";
		String ans = reader.nextLine();
		switch (ans){
			case "B" : case "b":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q9(){
		System.out.println("\nQuestion #9");
		Scanner reader = new Scanner(System.in);
		System.out.print("What is the most common thing to name a Scanner?\nA)Billy Bong\nB)Donald J.Trump\nC)Cybertruck\nD)reader\n\nYour answer --> ");
		String correct = "D";
		
		String ans = reader.nextLine();
		switch (ans){
			case "D" : case "d":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	
	public static int q10(){
		System.out.println("\nQuestion #10");
		Scanner reader = new Scanner(System.in);
		System.out.print("In a Math.Random statement, what indicates the range?\nint dice1 = (int)(Math.random()*6)+1;\nA)Math.random\nB)6\nC)int dice1\nD)+1\n\nYour answer --> ");
		
		String correct = "B";
		String ans = reader.nextLine();
		switch (ans){
			case "B" : case "b":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q11(){
		System.out.println("\nQuestion #11");
		Scanner reader = new Scanner(System.in);
		System.out.print("Where do you cast a Math.Random statement?\nint dice1 = (int)(Math.random()*6)+1;\nA)(int)\nB)6\nC)int dice1\nD)+1\n\nYour answer --> ");
		String correct = "A";
		
		String ans = reader.nextLine();
		switch (ans){
			case "A" : case "a":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q12(){
		System.out.println("\nQuestion #12");
		Scanner reader = new Scanner(System.in);
		System.out.print("In a Math.Random statement, what indicates the starting point of the range?\nint dice1 = (int)(Math.random()*6)+1;\nA)Math.random\nB)6\nC)int dice1\nD)+1\n\nYour answer --> ");
		String correct = "D";
		
		String ans = reader.nextLine();
		switch (ans){
			case "D" : case "d":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	
	public static int q13(){
		System.out.println("\nQuestion #13");
		Scanner reader = new Scanner(System.in);
		System.out.print("What is the correct syntax for an if statement\nA)If {a == b}  \nB)if (a = b) \nC)if (a == b)\nD)If (a == b} \n\nYour answer --> ");
		
		String correct = "C";
		String ans = reader.nextLine();
		switch (ans){
			case "C" : case "c":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q14(){
		System.out.println("\nQuestion #14");
		Scanner reader = new Scanner(System.in);
		System.out.print("Find the error:\nif(a = b) {\nSystem.out.println(\"Hello\");\n}\nA)Curly braces placed incorrectly\nB)System.out statement has syntax errors\nC)\"Hello\" should be in single 'quotes'\nD)a = b should be a == b\n\nYour answer --> ");
		
		String correct = "D";
		String ans = reader.nextLine();
		switch (ans){
			case "D" : case "d":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q15(){
		System.out.println("\nQuestion #15");
		Scanner reader = new Scanner(System.in);
		System.out.print("Find the error:\nSystem.out.Println('hi there')\nA)No semicolon, and system is spelled incorrectly\nB)There are single quotes instead of double quotes and there are no braces\nC)P in println is capital, single quotes should be double quotes, and there should be a semicolon at the end\nD)None of the above\n\nYour answer --> ");
		
		String correct = "C";
		String ans = reader.nextLine();
		switch (ans){
			case "C" : case "c":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q16(){
		System.out.println("\nQuestion #16");
		Scanner reader = new Scanner(System.in);
		System.out.print("What should be the first line in your code?(Ignore Scanner)\nA)public class main \nB)public static void main (String[]args)\nC)public class //name\nD)None of the above\n\nYour answer --> ");
		String correct = "C";
		String ans = reader.nextLine();
		switch (ans){
			case "C" : case "c":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}

	public static int q17(){
		System.out.println("\nQuestion #17");
		Scanner reader = new Scanner(System.in);
		System.out.print("When should you indent your code?\nA)After every { \nB)After every ;\nC)After every (\nD)All of the Above\n\nYour answer --> ");
		String correct = "A";
		String ans = reader.nextLine();
		switch (ans){
			case "A" : case "a":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}

	public static int q18(){
		System.out.println("\nQuestion #18");
		Scanner reader = new Scanner(System.in);
		System.out.print("Which line would create the error \"C:\\Users\\Student\\Documents\\Trivia.java:10: error: ';' expected\"?\nA)System.out.println(\"\"); \nB)case \"Haver\":\nC)Error does not exist.\nD)None of the above\n\nYour answer --> ");
		String ans = reader.nextLine();
		String correct = "D";
		switch (ans){
			case "D" : case "d":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q19(){
		System.out.println("\nQuestion #19");
		Scanner reader = new Scanner(System.in);
		System.out.print("What can you NOT specify in a switch statement?\nA)Ints\nB)Doubles\nC)Strings\nD)None of the above\n\nYour answer --> ");
		String ans = reader.nextLine();
		String correct = "B";
		switch (ans){
			case "B" : case "b":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}

	public static int q20(){
		System.out.println("\nQuestion #20");
		Scanner reader = new Scanner(System.in);
		System.out.print("Which is NOT a good reason to use switch statements?\nA)When there aren't many conditions\nB)When there isn't a range of values\nC)Comparing to a value that can change\nD)When there is a range of values\n\nYour answer --> ");
		String ans = reader.nextLine();
		String correct = "D";
		switch (ans){
			case "D" : case "d":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
	public static int q21(){
		System.out.println("\nQuestion #21");
		Scanner reader = new Scanner(System.in);
		System.out.print("What is the correct syntax for a switch statement?\nA)switch(trivia){\nB)switch(trivia);\nC)switch[trivia]{\nD)Switch{trivia}{\n\nYour answer --> ");
		String ans = reader.nextLine();
		String correct = "A";
		switch (ans){
			case "A" : case "a":
			System.out.println("Correct!");
			return 1;
			default :System.out.println("Incorrect! It was " + correct);
			return 0;
		}
	}
	
}