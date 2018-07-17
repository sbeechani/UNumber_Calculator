package UnumberCalculatorWithUnits;

import java.io.IOException;
import java.util.*;

/**
 * <p> Title: CalculatorUnit Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs unit conversions </p>
 * 
 * @author Snehitha Beechani
 * 
 * @version 1.00	2018-03-09 Unit conversion implementations by means of ReadExcel class
 *  
 */

public class CalculatorUnit {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	int unitSpecifier = -1;
	int quantitySpecifier = -1;
	String unit = "";
	String UnitFile = "resultantUnit.xls"; // file holding resultant units for various operations
	String ConversionFile = "ConversionFactor.xls";// file holding conversion factors between the units
	Hashtable<String, String[]> unitDimension; // HashTable to hold the Dimensional representation of units
	
	/*This is the link to ReadExcel class */
	ReadExcel readValue = new ReadExcel();
	
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	
	/*****
	 * This is the default constructor, which sets the unit specifier to -1 (invalid value)
	 */
	public CalculatorUnit() {
		unitSpecifier = -1;
	}

	/*****
	 * This constructor creates a calculator unit based on the unit name, unit specifier and quantity specifier.  
	 */
	public CalculatorUnit(String unit, int uni, int qty) {
		unitSpecifier = uni;
		quantitySpecifier = qty;
		this.unit = unit;
	}
	
	
	
	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/

	/*****
	 * This method returns the integer value of the unit specifier  
	 */
	public int getUnitSpecifier() {
		return unitSpecifier;
	}

	/*****
	 * This method sets the value of the specifier for CalculatorUnit object
	 */
	void setUnitSpecifier(int uni) {
		unitSpecifier = uni;
	}
	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	
	/*******************************************************************************************************
	 * The following methods implement computation on the calculator units.  These routines compute by taking specifier values 
	 * into consideration.  These methods understand read the values from the data in excel sheets by means of ReadExcel class,
	 * hiding those details from the User Interface modules.
	 * 
	 * 
	 */
	
	/*****
	 * This method builds the hash table with dimensional representation for all the unit values. 
	 */
	
	public void buildDimensionTable()
	{
	unitDimension = new Hashtable<String,String[]>();
	unitDimension.put("0","0 0 0".split(" "));
	unitDimension.put("1","0 1 0".split(" "));
	unitDimension.put("2","0 1 0".split(" "));
	unitDimension.put("3","0 1 0".split(" "));
	unitDimension.put("4","0 1 0".split(" "));
	unitDimension.put("5","1 0 0".split(" "));
	unitDimension.put("6","1 0 0".split(" "));
	unitDimension.put("7","1 0 0".split(" "));
	unitDimension.put("8","0 0 1".split(" "));
	unitDimension.put("9","0 0 1".split(" "));
	unitDimension.put("10","0 0 1".split(" "));
	unitDimension.put("11","0 0 1".split(" "));
	unitDimension.put("12","1 0 -1".split(" "));
	unitDimension.put("13","1 0 -1".split(" "));
	unitDimension.put("14","1 0 -1".split(" "));
	unitDimension.put("15","1 0 -1".split(" "));
	unitDimension.put("16","1 0 -2".split(" "));
	unitDimension.put("17","1 0 -2".split(" "));
	unitDimension.put("18","1 0 -2".split(" "));
	unitDimension.put("19","1 0 -2".split(" "));
	unitDimension.put("20","0 3 -2".split(" "));
	unitDimension.put("21","-1 3 -2".split(" "));
	unitDimension.put("22","0 2 0".split(" "));
	unitDimension.put("23","0 2 0".split(" "));
	unitDimension.put("24","0 2 0".split(" "));
	unitDimension.put("25","0 2 0".split(" "));
	unitDimension.put("26","0 3 0".split(" "));
	unitDimension.put("27","0 3 0".split(" "));
	unitDimension.put("28","0 3 0".split(" "));
	unitDimension.put("29","0 3 0".split(" "));
	unitDimension.put("30","0 0 2".split(" "));
	unitDimension.put("31","0 1 -1".split(" "));
	}
	
	/*****
	 * This method is called after checking for the validity between the operands, it returns the conversion factor
	 * to be multiplied with the unit1 operand value to be compatible with unit2 operand
	 * 
	 * @param operation stating unit conversion for reading values from file
	 * @param CalculatorUnit operand2Unit
	 */
	
	public String getConversionFactor(int operation, CalculatorUnit operand2Unit)throws IOException
	{
		readValue.setInputFile(ConversionFile);
		return readValue.ReadCell(operation, this.getUnitSpecifier() + 1, operand2Unit.getUnitSpecifier() + 1);
		
	}
	
	/*****
	 * This method is called after checking for the validity between the operands, it returns the resultant unit after performing 
	 * computation between the operands based on the operation being performed, by means of unit specifiers.
	 * 
	 * @param operation it represents the operation which is being performed
	 * @param CalculatorUnit operand2Unit
	 */
	
	public String getResultantUnit (int operation, CalculatorUnit operand2Unit)throws IOException
	{
		readValue.setInputFile(UnitFile);
		if(operation!=2) // checks if the operation is division
		{
			return readValue.ReadCell(operation, this.getUnitSpecifier() + 1, operand2Unit.getUnitSpecifier() + 1);
		}
		else // for division operation
		{  
			buildDimensionTable();
			String check = readValue.ReadCell(operation, this.getUnitSpecifier() + 1, operand2Unit.getUnitSpecifier() + 1);
			if(check.equals(""))// checks the resultant unit, for null values we just return the resultant dimensional representation
			{
				String dimension1 = (Integer.parseInt(unitDimension.get(this.getUnitSpecifier()+"")[0]))-(Integer.parseInt(unitDimension.get(operand2Unit.getUnitSpecifier()+"")[0]))+"";
				String dimension2 = (Integer.parseInt(unitDimension.get(this.getUnitSpecifier()+"")[1]))-(Integer.parseInt(unitDimension.get(operand2Unit.getUnitSpecifier()+"")[1]))+"";
				String dimension3 = (Integer.parseInt(unitDimension.get(this.getUnitSpecifier()+"")[2]))-(Integer.parseInt(unitDimension.get(operand2Unit.getUnitSpecifier()+"")[2]))+"";
				return "new unit: M^"+dimension1+" L^"+dimension2+" T^"+dimension3;
			}
			return check;// for valid results, we return the existing value
		}
		
	}

	/*****
	 * default tostring method, returns the string with object parameters in suitable format.
	 */
	public String toString() {
		return unit + unitSpecifier + "";
	}

	/**********************************************************************************************

	The Validation methods
	
	**********************************************************************************************/
	
	/*******************************************************************************************************
	 * The following methods checks for the compatibility between the operating units, for all possible operations
	 * they consider the specifier values to determine the validity.
	 * 
	 * @param CalculatorUnit operand2Unit
	 * 
	 */
	
	// for addition
	public boolean checkIfValidForAddition(CalculatorUnit operand2Unit) {
		if (this.quantitySpecifier == operand2Unit.quantitySpecifier)
			return true;
		return false;
	}

	// for subtraction
	public boolean checkIfValidForSubtraction(CalculatorUnit operand2Unit) {
		if (this.quantitySpecifier == operand2Unit.quantitySpecifier)
			return true;
		return false;
	}

	// for multiplication
	public boolean checkIfValidForMultiplication(CalculatorUnit operand1Unit, CalculatorUnit operand2Unit) {
		return true;
	}

	// for division
	public boolean checkIfValidForDivision(CalculatorUnit operand2Unit) {
		return true;
	}

	// for squareroot
	public boolean checkIfValidForSquareRoot() {
		buildDimensionTable();
		int power1 = Integer.parseInt(unitDimension.get(this.getUnitSpecifier()+"")[0]);
		int power2 = Integer.parseInt(unitDimension.get(this.getUnitSpecifier()+"")[1]);
		int power3 = Integer.parseInt(unitDimension.get(this.getUnitSpecifier()+"")[2]);
		if(power1%2==0 && power2%2==0 && power3%2==0)
		{
			return true;
		}
		return false;
	}	

	
}
