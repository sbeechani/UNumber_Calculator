
package UnumberCalculatorWithUnits;

/**
 * <p> Title: BusinessLogic Class. </p>
 * 
 * <p> Description: The code responsible for performing the calculator business logic functions. 
 * This method deals with CalculatorValues and performs actions on them.  The class expects data
 * from the User Interface to arrive as Strings and returns Strings to it.  This class calls the
 * CalculatorValue class to do computations and this class knows nothing about the actual 
 * representation of CalculatorValues, that is the responsibility of the CalculatorValue class and
 * the classes it calls.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @Modified by Snehitha Beechani
 * 
 * @version 4.00	2014-10-18 The JavaFX-based GUI implementation of a long integer calculator 
 * 
 * @version 4.01    2017-02-15 Implemented the subtraction, multiplication, division and square root operations for long integer calculator
 * 
 * @version 4.02	2018-02-16 Double implementation of the BusinessLogic class, modified all the parameters and methods accordingly
 * 
 * @version 4.03    2018-02-18 Added new methods to set error terms, to set and get error term error messages and also to compute
 * 					resultant error term for all the five operations 							
 * 
 * @version 4.04    2018-03-07 UNumber implementation of the BusinessLogic class, modified all the parameters and methods accordingly 
 * 
 * @version 4.05    2018-03-08 Modified all the functions linked to CalculatorValue class to perform arithmetic operations such that 
 * 					they take in conversion factors from UserInterface class and send the modified operand values for computation 
 * 
 */

public class BusinessLogic {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major calculator values 
	private CalculatorValue operand1 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue Error1 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue operand2 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue Error2 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue result = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue Error = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue modifiedop1 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue modifieder1 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue modifiedop2 = new CalculatorValue(new UNumber(0.0));
	private CalculatorValue modifieder2 = new CalculatorValue(new UNumber(0.0));
	private String operand1ErrorMessage = "";
	private boolean operand1Defined = false;
	private String Error1ErrorMessage = "";
	private String operand2ErrorMessage = "";
	private boolean operand2Defined = false;
	private String Error2ErrorMessage = "";
	private String resultErrorMessage = "";
	private boolean ErrorFlag = false;
	private UNumber ConversionFactor1 ;
	private UNumber ConversionFactor2 ;

	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	
	/**********
	 * This method initializes all of the elements of the business logic aspect of the calculator.
	 * There is no special computational initialization required, so the initialization of the
	 * attributes above are all that are needed.
	 */
	public BusinessLogic() {
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into operand1, any associated error message
	 * into operand1ErrorMessage, and sets flags accordingly.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand1(String value) {
		operand1Defined = false;							// Assume the operand will not be defined
		if (value.length() <= 0) {						// See if the input is empty. If so no error
			operand1ErrorMessage = "";					// message, but the operand is not defined.
			return true;									// Return saying there was no error.
		}
		operand1 = new CalculatorValue(value,"operand");			// If there was input text, try to convert it
		operand1ErrorMessage = operand1.getErrorMessage();	// into a CalculatorValue and see if it
		if (operand1ErrorMessage.length() > 0) 			// worked. If there is a non-empty error 
			return false;								// message, signal there was a problem.
		operand1Defined = true;							// Otherwise, set the defined flag and
		return true;										// signal that the set worked,type of 
	}

	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into operand2, any associated error message
	 * into operand1ErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand2(String value) {			// The logic of this method is exactly the
		operand2Defined = false;							// same as that for operand1, above.
		if (value.length() <= 0) {
			operand2ErrorMessage = "";
			return true;
		}
		operand2 = new CalculatorValue(value,"operand");
		operand2ErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		operand2Defined = true;
		return true;
	}
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into Error1, any associated error message
	 * into Error1ErrorMessage.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setError1(String value) {			// The logic of this method is exactly the same as
		if (value.length() <= 0) {						// for that of operand 1 above.
			Error1 = new CalculatorValue(new UNumber(0.0));
			Error1ErrorMessage = "";
			return true;
		}
		Error1 = new CalculatorValue(value,"errorterm");
		ErrorFlag = true;
		Error1ErrorMessage = Error1.getErrorMessage();
		if (Error1ErrorMessage.length() > 0)
			return false;
		return true;
	}
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into Error2, any associated error message
	 * into Error2ErrorMessage.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setError2(String value) {			// The logic of this method is exactly the same as
		if (value.length() <= 0) {						// for that of operand 1 above.
			Error2 = new CalculatorValue(new UNumber(0.0));
			Error2ErrorMessage = "";
			return true;
		}
		Error2 = new CalculatorValue(value,"errorterm");
		ErrorFlag = true;
		Error2ErrorMessage = Error2.getErrorMessage();
		if (Error2ErrorMessage.length() > 0)
			return false;
		return true;
	}

	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into result, any associated error message
	 * into resuyltErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is similar to that for operand1 above. (There is no defined flag.)
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setResult(String value) {				
		if (value.length() <= 0) {						
			operand2ErrorMessage = "";
			return true;
		}
		result = new CalculatorValue(value,"result");
		resultErrorMessage = result.getErrorMessage();
		if (resultErrorMessage.length() > 0)
			return false;
		return true;
	}
	
	
	/**********
	 * This public method takes an input String, checks to see if there is a non-empty input string.
	 * If so, it places the converted CalculatorValue into Error, any associated error message
	 * into resuyltErrorMessage, and sets flags accordingly.
	 * 
	 * The logic of this method is similar to that for operand1 above. (There is no defined flag.)
	 * 
	 * @param value
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setError(String value) {		//this always returns true, no verification for error in result
		return true;
	}
	
	/**********
	 * This public setter sets the String with error in operand1.
	 * 
	 * @return
	 */
	public void setOperand1ErrorMessage(String m) {
		operand1ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String with error in operand1, if exists.
	 *  
	 * @return and error message or an empty String
	 */
	public String getOperand1ErrorMessage() {
		return operand1ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String with error in Error1.
	 * 
	 * @return
	 */
	public void setError1ErrorMessage(String m) {
		Error1ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String with error in Error1, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getError1ErrorMessage() {
		return Error1ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String with error in operand2.
	 * 
	 * @return
	 */
	public void setOperand2ErrorMessage(String m) {
		operand2ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand2, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String
	 */
	public String getOperand2ErrorMessage() {
		return operand2ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error in Error2.
	 * 
	 * @return
	 */
	public void setError2ErrorMessage(String m) {
		Error2ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String with error in operand2, if exists.
	 * 
	 * @return and error message or an empty String
	 */
	public String getError2ErrorMessage() {
		return Error2ErrorMessage;
	}
	
	/**********
	 * This public setter sets the String with error in the result.
	 * 
	 * @return
	 */
	public void setResultErrorMessage(String m) {
		resultErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String with error in the result, if exists.
	 *  
	 * @return and error message or an empty String
	 */
	public String getResultErrorMessage() {
		return resultErrorMessage;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand1. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand1Defined() {
		return operand1Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand2. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand2Defined() {
		return operand2Defined;
	}
	
	public boolean getErrorFlag()
	{
		return ErrorFlag;
	}

	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/**********
	 * This toString method invokes the toString method of the result type (CalculatorValue is this 
	 * case) to convert the value from its hidden internal representation into a String, which can be
	 * manipulated directly by the BusinessLogic and the UserInterface classes.
	 */
	public String toString() {
		return result.toString();
	}
	
	/**********
	 * This public toString method is used to display all the values of the BusinessLogic class in a
	 * textual representation for debugging purposes.
	 * 
	 * @return a String representation of the class
	 */
	public String debugToString() {
		String r = "\n******************\n*\n* Business Logic\n*\n******************\n";
		r += "operand1 = " + operand1.toString() + "\n";
		r += "     operand1ErrorMessage = " + operand1ErrorMessage+ "\n";
		r += "     operand1Defined = " + operand1Defined+ "\n";
		r += "Error1 = " + Error1.toString() + "\n";
		r += "     Error1ErrorMessage = " + Error1ErrorMessage+ "\n";
		r += "operand2 = " + operand2.toString() + "\n";
		r += "     operand2ErrorMessage = " + operand2ErrorMessage+ "\n";
		r += "     operand2Defined = " + operand2Defined+ "\n";
		r += "Error2 = " + Error2.toString() + "\n";
		r += "     Error2ErrorMessage = " + Error2ErrorMessage+ "\n";
		r += "result = " + result.toString() + "\n";
		r += "     resultErrorMessage = " + resultErrorMessage+ "\n";
		r += "Error = " + Error.toString() + "\n";
		r += "*******************\n\n";
		return r;
	}

	/**********************************************************************************************

	Business Logic Operations (e.g. addition)
	
	**********************************************************************************************/
	
	/**********
	 * This public method computes the sum of the two operands using the CalculatorValue class method 
	 * for addition. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String addition(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		result = new CalculatorValue(modifiedop1);		
		result.add(modifiedop2);
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
	
	/**********
	 *Below are the methods used to calculate subtraction, multiplication, division and square root
	 *Implementation is very similar to the addition.
	 * 
	 * @return
	 */
	public String subtraction(String cf1, String cf2) {		
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		result = new CalculatorValue(new UNumber(modifiedop1.measuredValue));
		result.sub(new CalculatorValue(new UNumber(modifiedop2.measuredValue)));
		System.out.println(result.measuredValue);
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
	
	public String multiplication(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		result = new CalculatorValue(modifiedop1);
		result.mpy(modifiedop2);
		resultErrorMessage = result.getErrorMessage();
		return result.toString();
	}
	
	public String division(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		result = new CalculatorValue(modifiedop1);
		result.div(modifiedop2);
		// checking for the error message
		if(result.getErrorMessage()=="") {
			resultErrorMessage = result.getErrorMessage();
			return result.toString();
		}
		// If an error message gets generated, we update result value to a null value.
		else {
			resultErrorMessage = result.getErrorMessage();
			return "";
		}
	}
	
	public String squareRoot() {
		result = new CalculatorValue(operand1);
		result.sqrt(operand1);
		// checking for the error message
		if(result.getErrorMessage().length()==0) {
			resultErrorMessage = result.getErrorMessage();
			return result.toString();
		}
		// If an error message gets generated, we update result value to a null value.
		else {
			resultErrorMessage = result.getErrorMessage();
			return "";
		}
	}
	
	/*
	 * Following are the methods used to perform arithmetic operations, with error terms.
	 * We create a new calculator value class object called error and operate on it.
	 */

	public String errorAddition(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifieder1 = new CalculatorValue(new UNumber(Error1.measuredValue));
		modifieder2 = new CalculatorValue(new UNumber(Error2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		modifieder1.measuredValue.mpy(ConversionFactor1);
		modifieder2.measuredValue.mpy(ConversionFactor2);
		Error = new CalculatorValue(modifiedop1);
		Error.errorAdd(modifiedop2,modifieder1, modifieder2); // Implementation is similar to above methods
		return Error.toStringerr();
	}	

	public String errorSubtraction(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifieder1 = new CalculatorValue(new UNumber(Error1.measuredValue));
		modifieder2 = new CalculatorValue(new UNumber(Error2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		modifieder1.measuredValue.mpy(ConversionFactor1);
		modifieder2.measuredValue.mpy(ConversionFactor2);
		Error = new CalculatorValue(modifiedop1);
		Error.errorSub(modifiedop2,modifieder1, modifieder2); // Implementation is similar to above methods
		return Error.toStringerr();
	}
	
	public String errorMultiplication(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifieder1 = new CalculatorValue(new UNumber(Error1.measuredValue));
		modifieder2 = new CalculatorValue(new UNumber(Error2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		modifieder1.measuredValue.mpy(ConversionFactor1);
		modifieder2.measuredValue.mpy(ConversionFactor2);
		Error = new CalculatorValue(modifiedop1);
		Error.errorMpy(modifiedop2,modifieder1, modifieder2);   // Implementation is similar to above methods
		return Error.toStringerr();
	}
	
	public String errorDivision(String cf1, String cf2) {
		String ModifiedString = cf1.replace(".", "");
		ConversionFactor1 = new UNumber(ModifiedString,cf1.split("\\.")[0].length(),true,40);
		ModifiedString = cf2.replace(".", "");
		ConversionFactor2 = new UNumber(ModifiedString,cf2.split("\\.")[0].length(),true,40);
		modifiedop1 = new CalculatorValue(new UNumber(operand1.measuredValue));
		modifiedop2 = new CalculatorValue(new UNumber(operand2.measuredValue));
		modifieder1 = new CalculatorValue(new UNumber(Error1.measuredValue));
		modifieder2 = new CalculatorValue(new UNumber(Error2.measuredValue));
		modifiedop1.measuredValue.mpy(ConversionFactor1);
		modifiedop2.measuredValue.mpy(ConversionFactor2);
		modifieder1.measuredValue.mpy(ConversionFactor1);
		modifieder2.measuredValue.mpy(ConversionFactor2);
		Error = new CalculatorValue(operand1);
		Error.errorDiv(modifiedop2,modifieder1, modifieder2);    // Implementation is similar to above methods
		if(Error.getErrorMessage()=="") {
			return Error.toStringerr();
		}
		else {
			return "";
		}
	}
	
	public String errorSquareRoot() {
		Error = new CalculatorValue();
		Error.errorSqrt(operand1, Error1);         // Implementation is similar to above methods
		if(Error.getErrorMessage().length()==0) {
			return Error.toStringerr();
		}
		else {
			return "";
		}
	}
}