package UnumberCalculatorWithUnits;

import java.util.Scanner;
import java.math.*;

/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @Modified Snehitha Beechani
 * 
 * @version 4.00	2017-10-18 Long integer implementation of the CalculatorValue class baseline
 * 
 * @version 4.01    2017-02-15 Implemented the subtraction, multiplication, division and square root operations for long integer calculator
 * 
 * @version 4.02	2018-02-16 Double implementation of the CalculatorValue class, modified all the parameters and methods accordingly
 * 
 * @version 4.03    2018-02-18 Added new methods to compute resultant error terms for all the available operations.
 * 
 * @version 4.04    2018-02-19 Integrated with FloatingPointRecognizer and ErrorTermRecognizer classes, to facilitate validation
 * 					of operand values and error values respectively
 * 
 * @version 4.05   2018-03-07 UNumber implementation of the CalculatorValue class, modified all the parameters and methods accordingly
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	UNumber measuredValue = new UNumber (0.0);
	String errorMessage = "";
	UNumber errorValue = new UNumber (0.0); // to store resultant error term
	UNumber resultValue = new UNumber(0.0); // to store resultant operand value
	boolean sign;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on the UNumber. 
	 * @param UNumber v
	 */
	public CalculatorValue(UNumber v) {
		measuredValue = v;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
		errorValue = v.errorValue;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 * 'type' parameter differentiates between the operand and error term, which helps us using various FSM's to validate the input.
	 * Based on the type String s is passed through FPR or ETR for validation.
	 * 
	 * @param s operand value
	 * @param type type specifier
	 */
	public CalculatorValue(String s, String type) {
		measuredValue = new UNumber(0.0);
		if (s.length() == 0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		sign=negative;
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an integer value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		String errorMsg = "";
		// if the type of the parameter is operand, we use floating point recognizer to validate.
		if(type.equals("operand"))
		{
			errorMsg = FloatingPointRecognizer.checkMeasureValue(s);
			System.out.println(errorMsg);
		}
		// if the type of the parameter is error term, we use Error term recognizer to validate.
		if (type.equals("errorterm"))
		{
			errorMsg = Errortermrecognizer.checkErrorTerm(s);
			System.out.println(errorMsg);
		}
		
		
		if (!errorMsg.equals("")) {				// set the errorMessage if the validation error is not null	                            		  
			this.setErrorMessage(errorMsg);         
			tempScanner.close();								
			return;												
		}
		
		// Convert the user-entered string to a Unumber value and see if something else is following it
		
		String InputString = tempScanner.next();				// Convert the value and check to see
		BigDecimal bg = new BigDecimal(InputString);
		String StringNotation = bg.toPlainString();
		String ModifiedString = StringNotation.replace(".", "");
		measuredValue = new UNumber(ModifiedString,StringNotation.split("\\.")[0].length(),!negative,40);
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			measuredValue =new UNumber(0.0);								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)		// Return the proper value based on the sign flag
			measuredValue = new UNumber(ModifiedString,StringNotation.split("\\.")[0].length(),!negative,40);
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific Double value
	 */
	public void setValue(Double v){
		measuredValue = new UNumber(0.0);
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
		errorValue = v.errorValue;
		}
	
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 */
	
	/*
	 * This method returns the calculated value as string, when operated on operands.
	 */
	public String toString() {
		return resultValue + "";
	}
	
	/*
	 * This method returns the calculated value as string, when operated on error terms
	 */
	public String toStringerr()
	{
		errorValue = new UNumber(errorValue, 1);
		return errorValue + "";
	}
	
	/*****
	 * This is the debug toString method
	 * It allows us to verify results using a test bed. 
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorValue = " + errorValue + "\nerrorMessage = " + errorMessage + "\n";
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 *   
	 *  This method is for addition
	 */
	public void add(CalculatorValue v) {
		resultValue = new UNumber(measuredValue);
		resultValue.add(new UNumber(v.measuredValue));
		errorMessage = "";
	}

	/*****
	 * The remaining methods are subtraction, multiplication and division which are similar in
	 * implementation.
	 * 
	 * @param v
	 */
	public void sub(CalculatorValue v) {
		resultValue = new UNumber(measuredValue,40);
		resultValue.sub(new UNumber(v.measuredValue,40));
		measuredValue = new UNumber(resultValue);
		errorMessage = "";
	}

	public void mpy(CalculatorValue v) {
		resultValue = new UNumber(measuredValue);
		resultValue.mpy(new UNumber(v.measuredValue));
		measuredValue = new UNumber(resultValue);
		errorMessage = "";
	}
	
	// If the second operand is equal to zero, we throw an exception by updating errorMessage to "zero division error"
	public void div(CalculatorValue v) {
		if(!v.measuredValue.isZero()) {
			resultValue = new UNumber(measuredValue);
			resultValue.div(new UNumber(v.measuredValue));
			measuredValue = new UNumber(resultValue);
			errorMessage = "";
		}
		else {
			errorMessage = "Zero division error";
		}
	}
	
	// Throw exception for negative operand by updating the error message
	public void sqrt(CalculatorValue v) {
		if((v.measuredValue.compareTo(new UNumber(0.0)) > 0)) {
			resultValue = new UNumber(SquareRoot.squareRoot(v.measuredValue));
			errorMessage = "";
		}
		else if (v.measuredValue.isZero())
		{
			resultValue = new UNumber(0.0);
		}
		else {
			errorMessage = "Not defined for negative operands";
		}
	}
	
	/*****
	 * The  methods are for performing operations on error terms, these are similar in implementation to the above methods
	 *  
	 * @param v second operand value
	 * @param x first error term value
	 * @param y second error term value
	 */
	public void errorAdd(CalculatorValue v,CalculatorValue x, CalculatorValue y)
	{		
		errorValue =new UNumber(x.measuredValue) ;
		errorValue.add(y.measuredValue);
		errorValue.abs();
		errorMessage = "";
	}
	
	public void errorSub(CalculatorValue v,CalculatorValue x, CalculatorValue y) {
		errorValue =new UNumber(x.measuredValue) ;
		errorValue.add(y.measuredValue);
		errorValue.abs();
		errorMessage = "";
	}
	
	public void errorMpy(CalculatorValue v,CalculatorValue x, CalculatorValue y) {
		if(measuredValue != new UNumber(0.0) && v.measuredValue != new UNumber(0.0))
		{
			//errorValue = Math.abs((x.measuredValue/measuredValue) + (y.measuredValue/v.measuredValue))*(measuredValue*v.measuredValue);
			UNumber term1 = new UNumber(x.measuredValue); 
			term1.div(measuredValue);
			UNumber term2 =  new UNumber(y.measuredValue);
			term2.div(v.measuredValue);
			UNumber term3 =  new UNumber(measuredValue);
			term3.mpy(v.measuredValue);
			term1.add(term2);
			term1.mpy(term3);
			errorValue = new UNumber(term1);
			errorValue.abs();
		}		
		errorMessage = "";
	}	
	
	public void errorDiv(CalculatorValue v,CalculatorValue x, CalculatorValue y) {
		if(!v.measuredValue.isZero()) {
			if(!measuredValue.isZero())//avoid computing resultant error, if any of the operand values is zero to avoid zero division error
			{
				//errorValue = Math.abs((x.measuredValue/measuredValue) + (y.measuredValue/v.measuredValue))*(measuredValue/v.measuredValue);
				UNumber term1 = new UNumber(x.measuredValue);
				term1.div(measuredValue);
				UNumber term2 = new UNumber(y.measuredValue);
				term2.div(v.measuredValue);
				UNumber term3 = new UNumber(measuredValue);
				term3.div(v.measuredValue);
				term1.add(term2);
				term1.mpy(term3);		
				errorValue = new UNumber(term1);
				errorValue.abs();
				
			}
		errorMessage = "";
		}
		else {
			errorMessage = "Zero division error";
		}
	}
	
	public void errorSqrt(CalculatorValue u, CalculatorValue x) {
		if((u.measuredValue.compareTo(new UNumber(0.0)) >= 0)) {
			if((u.measuredValue.compareTo(new UNumber(0.0)) >0))
			{
				//errorValue = Math.abs((0.5)*(x.measuredValue/u.measuredValue)*(Math.sqrt(u.measuredValue)));
			 resultValue = new UNumber(SquareRoot.squareRoot(u.measuredValue));
			 errorValue = new UNumber(x.measuredValue);
			 errorValue.div(u.measuredValue);
			 errorValue.mpy(new UNumber(resultValue));
			 errorValue.mpy(new UNumber(0.5));
			 errorValue.abs();
			}

		errorMessage = "";
		}
		else {
			errorMessage = "Not defined for negative operands";
		}
	}
	
}