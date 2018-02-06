package com.javatutorialhq.java.examples;
 
import java.util.Scanner;
 
import static java.lang.System.*;
 
/*
 * This example source code demonstrates the use of 
 * intValue() method of Integer class.
 */
 
public class IntegerShortValue {
 
	public static void main(String[] args) {
		// Ask user input
		System.out.print("Enter Desired Value:");
		// declare the scanner object
		Scanner scan = new Scanner(System.in);
		// use scanner to get the value from user console
		int intValue = scan.nextInt();
		// close the scanner object
		scan.close();
		Integer myValue = new Integer(intValue);
		out.println("int Value is " + myValue.intValue());
 
	}
 
}