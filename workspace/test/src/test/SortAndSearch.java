package test;

import java.util.Scanner;

public class SortAndSearch{

public static void main(String[]args){

    Scanner scan = new Scanner(System.in);
    
    System.out.print("What is your name? ");
    String input = scan.nextLine();                
 
    String[] firstlast= input.split(" ");
    
    song(firstlast[0],firstlast[1]);
  
}
public static void song(String a, String b) {

    System.out.println(a + " " + a + ", " + "bo-B" + a.substring(1));  
    System.out.println("Banana-fana fo-F" + a.substring(1)); 
    System.out.println("Fee-fi-mo-M" + a.substring(1)); 
    System.out.println(a.toUpperCase() + "!\n"); 

    System.out.println(b + " " + b + ", " + "bo-B" + b.substring(1));  
    System.out.println("Banana-fana fo-F" + b.substring(1)); 
    System.out.println("Fee-fi-mo-M" + b.substring(1)); 
    System.out.println(b.toUpperCase() + "!"); 
    
    }
}