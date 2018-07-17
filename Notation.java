
package UnumberCalculatorWithUnits;

/**
 * <p> Title: Notation. </p>
 * 
 * <p> Description: A component of the Calculator application </p>
 * 
 * @author Snehitha Beechani
 * 
 * 
 * @version 1.00	2017-03-06	Modifies the representation of error term and resultant value
 * 
 * 
 */

import java.math.BigDecimal;

public class Notation {
	
	/**********
	 * This class is used to modify the representation of resultant values. The methods in this class
	 * return the string representation based on the value. Extreme values are represented through scientific notation
	 * and normal values are represented by means of normal decimal notation. Result value is modified based on error value.	 * 
	 */
	
	/*********************************************************************************************/
	
	/**********
	 * NotationError method is used to modify the string representation of the resultant error value.
	 * Any value in between the range of 0.001 and 9000 are represented using normal decimal representation and
	 * any value beyond the mentioned range is represented by means of scientific notation.
	 * 
	 * @param Expected	The String object of the expected value
	 * @param Actual	The String object of the actual value
	 */

	String NotationError(String errorStr) {
		BigDecimal errorUpperLimit = new BigDecimal(9E3); // upper limit for the decimal notation
		BigDecimal errorLowerLimit = new BigDecimal(1E-3); // lower limit for the decimal notation
		BigDecimal error = new BigDecimal(errorStr); 
		// If the value is in the mentioned range, we use big decimal's toPlainString method to get decimal notation
		if (error.compareTo(errorUpperLimit) < 0 && error.compareTo(errorLowerLimit) > 0) {
			return (error.toPlainString());
		} 
		// If the value is beyond the mentioned range, we use Unumber's toString method to get the scientific notation
		else {
			String StringNotation = error.toPlainString();
			String ModifiedString = StringNotation.replace(".", "");
			return new UNumber(ModifiedString,StringNotation.split("\\.")[0].length(),true,40).toString();
		}

	}
	
	/**********
	 * NotationResult method is used to modify the string representation of the resultant measured value.
	 * Any value in between the range of 0.00001 and 10000000 are represented using normal decimal representation and
	 * any value beyond the mentioned range is represented by means of scientific notation. The measured value is rounded 
	 * based on the position of the significant digit in error value before modifying the string representation.
	 * 
	 * @param Expected	The String object of the expected value
	 * @param Actual	The String object of the actual value
	 */

	String NotationResult(String measureString, String errorStr) {
		BigDecimal one = new BigDecimal(1.0);
		BigDecimal zero = new BigDecimal(0);
		BigDecimal error = new BigDecimal(errorStr);
		BigDecimal measuredValue = new BigDecimal(measureString);
		if(measuredValue.compareTo(zero)==0) {return (new UNumber("0", 1, true, 40)).toDecimalString();}
		boolean measureSign = measuredValue.compareTo(zero) >= 0;
		measuredValue = measuredValue.abs();
		if(error.compareTo(measuredValue)>0) {return (new UNumber("0", 1, true, 40)).toDecimalString();}
		if (error.compareTo(zero) != 0) {
			String errorString = error.toPlainString();
			BigDecimal multiplier = new BigDecimal(10);// This stores the value to be multiplied to round the measured resultant value
			if (error.compareTo(one) < 0) {
				String Character = errorString.split("\\.")[1];
				int lngt = Character.length();
				multiplier = multiplier.pow(lngt);
				measuredValue = measuredValue.multiply(multiplier);
				measuredValue = new BigDecimal(measuredValue.toPlainString().split("\\.")[0]);
				measuredValue = measuredValue.divide(multiplier);
			} else {
				String Character = errorString.split("\\.")[0];
				int lngt = Character.length() - 1;
				multiplier = multiplier.pow(lngt);
				measuredValue = measuredValue.divide(multiplier);
				measuredValue = new BigDecimal(measuredValue.toPlainString().split("\\.")[0]);
				measuredValue = measuredValue.multiply(multiplier);
			}
		}
		BigDecimal measureUpperLimit = new BigDecimal(1e7);
		BigDecimal measureLowerLimit = new BigDecimal(1e-5);
		
		// If the value is in the mentioned range, we use big decimal's toPlainString method to get decimal notation
		if (measuredValue.compareTo(measureUpperLimit) < 0 && measuredValue.compareTo(measureLowerLimit) > 0) {
			if (!measureSign) {
				measuredValue = measuredValue.negate();
			}
			
			return (measuredValue.toPlainString());
			// If the value is beyond the mentioned range, we use Unumber's toString method to get the scientific notation
		} else {
			String StringNotation = measuredValue.toPlainString();
			String ModifiedString = StringNotation.replace(".", "");
			return new UNumber(ModifiedString,StringNotation.split("\\.")[0].length(),measureSign,40).toString();
	}
	}
}