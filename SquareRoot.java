package UnumberCalculatorWithUnits;
public class SquareRoot {


	/**
	 * <p> Title: DemoNewtonMethod. </p>
	 * 
	 * <p> Description: Mainline to demo the Newton-Raphson square root method </p>
	 * 
	 * <p> Copyright: Lynn Robert Carter © 2017 </p>
	 * 
	 * @author Lynn Robert Carter
	 * @author snehitha Beechani
	 * 
	 * @version 1.00	Initial baseline
	 * @version 1.01    Modified to integrate with CalculatorValue class
	 * 
	 */
		
		private static int numSignificantDigits = 40;
		
		/*****
		 * This private method counts how many digits are the same between two estimates
		 */
		private static int howManyDigitsMatch(UNumber newGuess, UNumber oldGuess) {
			// If the characteristics is not the same, the digits in the mantissa do not matter
			if (newGuess.getCharacteristic() != oldGuess.getCharacteristic()) return 0;
			
			// The characteristic is the same, so fetch the mantissas so we can compare them
			byte[] newG = newGuess.getMantissa();
			byte[] oldG = oldGuess.getMantissa();
			
			// Computer the shorter of the two
			int size = newGuess.length();
			//System.out.println(size);
			int otherOne = oldGuess.length();
			if (otherOne < size) 
				size = otherOne;
			
			// Loop through the digits as long as they match
			for (int ndx = 0; ndx < size; ndx++)
				if (newG[ndx] != oldG[ndx]) return ndx;	// If the don't match, ndx is the result
			
			// If the loop completes, then the size of the shorter is the length of the match
			return size;
		}
		

		/*****
		 * This is the mainline 
		 * 
		 * @param args	The program parameters are ignored
		 */
		public static UNumber squareRoot(UNumber input) {
			
					UNumber theValue =  new UNumber(input,numSignificantDigits);					
					UNumber two = new UNumber("2.0", 1, true, 40);				
					UNumber newGuess = new UNumber(theValue,40);				// Compute the estimate
					newGuess.div(two);
					UNumber oldGuess = null;										// Temporary value for determining when to terminate the loop
					int digitsMatch = 0;
					do {
						oldGuess = newGuess;								// Save the old guess						
						UNumber temp = new UNumber(theValue,numSignificantDigits);
						temp.div(oldGuess);
						temp.add(oldGuess);
						temp.div(two);
						newGuess = new UNumber(temp, 40);

						digitsMatch = howManyDigitsMatch(newGuess, oldGuess);
					} while (digitsMatch < numSignificantDigits);			// Determine if the old and the new guesses are "close enough"

					//System.out.println(newGuess);
					return newGuess;
				
			}
		}
