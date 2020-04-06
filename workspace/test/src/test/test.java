package test;

public class test 
{

	public static void main(String[] args) {
	
		int num = 8;		
		int total = 5;
	
		for(int i = 2; i < 365; i++) {
		
		total += num;
		
		System.out.println(total + " + " + num);
		
		num += 3;
		
		}
	}
}