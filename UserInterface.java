
package UnumberCalculatorWithUnits;

import java.io.IOException;

import UnumberCalculatorWithUnits.BusinessLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @Modified Snehitha Beechani
 * 
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * 
 * @version 4.01    2018-02-16 Implemented SingleOperandIssues function, sqrtOperand for double
 * 					calculator and dimensions were adjusted.
 * 
 * @version 4.02    2018-02-18 Added new text fields and label fields for error terms, modified all functions
 * 					to operate on both operand values and error values
 * 
 * @version 4.03    2018-03-06 Integrated with the Notation class, to modify the representation of resultant values
 * 					based on their value
 * 
 * @version 4.04    2018-03-07 Implemented accordion based select lists to facilitate unit selection by creating new buttons
 * 					and by means of stage and scene set-up
 * 
 * @version 4.05    2018-03-08 Integrated with CalculatorUnit class to allow conversions between various physical quantities
 * 
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_DecimalCalculator = new Label("UNumber Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_Error1 = new Label("Error1 operand");
	private TextField text_Error1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Error2 = new Label("Error2 operand");
	private TextField text_Error2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Label label_Error = new Label("Error in result");
	private TextField text_Error = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221A");
	private Button button_selectunit1 = new Button ("Select Units");
	private Button button_selectunit2 = new Button ("Select Units");
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errError1 = new Label("");
	private Label label_errError2 = new Label("");
	private Label label_errResult = new Label("");
	private Label Label_resultUnit = new Label("Resultant Unit");
	private TextField text_resultUnit = new TextField();
	private Label Label_UnitOperand1 = new Label("Operand1 Unit");
	private Label Label_UnitOperand2 = new Label("Operand2 Unit");
	private String Operand1qty = "";
	private String Operand2qty = "";
	private String Operand1unitindex = "";
	private String Operand2unitindex = "";
	private String Operand1unit = "";
	private String Operand2unit = "";
	public  String unit1cf;
	public  String unit2cf;
	public boolean accordianflag = true;

		
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	/*This is the link to the Notation*/
	public Notation modify = new Notation();

	/*This is the link to the calculator unit*/
	public CalculatorUnit unit1;
	public CalculatorUnit unit2;
	
	
//For the accordion display
		Group rootPopUp1;
		Scene scenePopUp1;
		Group rootPopUp2;
		Scene scenePopUp2;
		
		static Stage stagePopUp1;
		static Stage stagePopUp2;

		TitledPane t11, t12, t13, t14, t15, t16, t17, t18, t19, t110; //panes for accordion1
		
		// Arrays for accordion1 segregating units by physical quantity
		ObservableList<Button> arrayA1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayB1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayC1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayD1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayE1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayF1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayG1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayH1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayI1 = FXCollections.observableArrayList();
		ObservableList<Button> arrayJ1 = FXCollections.observableArrayList();
		
		TitledPane t21, t22, t23, t24, t25, t26, t27, t28, t29, t210; //panes for accordion2
		
		// Arrays for accordion2 segregating units by physical quantity
		ObservableList<Button> arrayA2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayB2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayC2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayD2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayE2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayF2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayG2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayH2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayI2 = FXCollections.observableArrayList();
		ObservableList<Button> arrayJ2 = FXCollections.observableArrayList();
		
	//Buttons for accordion1 displaying various units	
	Button btnWord00 = createButton1 ("- no unit - ","0", "0");
	
	//for length
	Button btnWord01 = createButton1 ("meter (m)", "1", "1");
	Button btnWord02 = createButton1 ("kilometer (km)", "1", "2");
	Button btnWord03 = createButton1 ("mile (mi)", "1", "3");
	Button btnWord04 = createButton1 ("foot (ft)", "1", "4");
	
	//for Mass
	Button btnWord05 = createButton1 ("kilogram (kg)", "2", "5");
	Button btnWord06 = createButton1 ("gram (g)", "2", "6");
	Button btnWord07 = createButton1 ("pound (lb)", "2", "7");
	
	//For time
	Button btnWord08 = createButton1 ("second", "3", "8");
	Button btnWord09 = createButton1 ("minute", "3", "9");
	Button btnWord010 =createButton1 ("hour", "3", "10");
	Button btnWord011 =createButton1 ("day", "3", "11");
	
	//For Speed
	Button btnWord012 = createButton1 ("kilometer / hour (kmph)", "4", "12");
	Button btnWord013 = createButton1 ("mile / hour (miph)", "4", "13");
	Button btnWord014 = createButton1 ("meter / second (m/s)", "4", "14");
	Button btnWord015 = createButton1 ("foot / second (ft/s)", "4", "15");
	Button btnWord031 = createButton1 ("kilometer / second (km/s)", "4", "31");
	
	//For Acceleration
	Button btnWord016 = createButton1 ("meter/second\u00b2", "5", "16");
	Button btnWord017 = createButton1 ("kilometer/hour\u00b2", "5", "17");
	Button btnWord018 = createButton1 ("mile/hour\u00b2", "5", "18");
	Button btnWord019 = createButton1 ("foot/second\u00b2", "5", "19");
	
	//For Gravitational parameters
	Button btnWord020 = createButton1 ("GM (km\u00b3/s\u00b2)", "6", "20");
	Button btnWord021 = createButton1 ("Gravitational constant (meter\u00b3 /kg s\u00b2)", "6", "21");
	
	//For Area
	Button btnWord022 = createButton1 ("meter\u00b2", "7", "22");
	Button btnWord023 = createButton1 ("kilometer\u00b2", "7", "23");
	Button btnWord024 = createButton1 ("mile\u00b2", "7", "24");
	Button btnWord025 = createButton1 ("foot\u00b2", "7", "25");
	
	//For Volume
	Button btnWord026 = createButton1 ("meter\u00b3", "8", "26");
	Button btnWord027 = createButton1 ("kilometer\u00b3", "8", "27");
	Button btnWord028 = createButton1 ("mile\u00b3", "8", "28");
	Button btnWord029 = createButton1 ("foot\u00b3", "8", "29");
	
	//For Other
	Button btnWord030 = createButton1 ("second\u00b2", "9", "30");
	
	//Buttons for accordion2 displaying various units
    Button btnWord10 = createButton2 ("- no unit - ", "0", "0");
	
	//for length
	Button btnWord11 = createButton2 ("meter (m)", "1", "1");
	Button btnWord12 = createButton2 ("kilometer (km)", "1", "2");
	Button btnWord13 = createButton2 ("mile (mi)", "1", "3");
	Button btnWord14 = createButton2 ("foot (ft)", "1", "4");
	
	//for Mass
	Button btnWord15 = createButton2 ("kilogram (kg)", "2", "5");
	Button btnWord16 = createButton2 ("gram (g)", "2", "6");
	Button btnWord17 = createButton2 ("pound (lb)", "2", "7");
	
	//For time
	Button btnWord18 = createButton2 ("second", "3", "8");
	Button btnWord19 = createButton2 ("minute", "3", "9");
	Button btnWord110 =createButton2 ("hour", "3", "10");
	Button btnWord111 =createButton2 ("day", "3", "11");
	
	//For Speed
	Button btnWord112 = createButton2 ("kilometer / hour (kmph)", "4", "12");
	Button btnWord113 = createButton2 ("mile / hour (mih)", "4", "13");
	Button btnWord114 = createButton2 ("meter / second (m/s)", "4", "14");
	Button btnWord115 = createButton2 ("foot / second (ft/s)", "4", "15");
	Button btnWord131 = createButton2 ("kilometer / second (km/s)", "4", "31");
	
	//For Acceleration
	Button btnWord116 = createButton2 ("meter/second\u00b2", "5", "16");
	Button btnWord117 = createButton2 ("kilometer/hour\u00b2", "5", "17");
	Button btnWord118 = createButton2 ("mile/hour\u00b2", "5", "18");
	Button btnWord119 = createButton2 ("foot/second\u00b2", "5", "19");
	
	//For Gravitational parameters	
	Button btnWord120 = createButton2 ("GM (km\u00b3/s\u00b2)", "6", "20");
	Button btnWord121 = createButton2 ("Gravitational constant (meter\u00b3/kg s\u00b2)", "6", "21");
	
	//For Area
	Button btnWord122 = createButton2 ("meter\u00b2", "7", "22");
	Button btnWord123 = createButton2 ("kilometer\u00b2", "7", "23");
	Button btnWord124 = createButton2 ("mile\u00b2", "7", "24");
	Button btnWord125 = createButton2 ("foot\u00b2", "7", "25");
	
	//For Volume
	Button btnWord126 = createButton2 ("meter\u00b3", "8", "26");
	Button btnWord127 = createButton2 ("kilometer\u00b3", "8", "27");
	Button btnWord128 = createButton2 ("mile\u00b3", "8", "28");
	Button btnWord129 = createButton2 ("foot\u00b3", "8", "29");
	
	//For Other
	Button btnWord130 = createButton2 ("second\u00b2", "9", "30");
		

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are six gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_DecimalCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 80);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 14, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 100, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the first error term when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Error1.requestFocus(); });
		
		// Establish an error message for the first operand just below it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 130);
		label_errOperand1.setTextFill(Color.RED);
		
		// Label the first error term just below the first operand error message, left aligned
		setupLabelUI(label_Error1, "Arial", 14, Calculator.WINDOW_WIDTH-100, Pos.BASELINE_LEFT, 10, 165);
				
		// Establish the label for error message in first error term.
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Error1, "Arial", 14, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 190, true);
		text_Error1.textProperty().addListener((observable, oldValue, newValue) -> {setError1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		text_Error1.setOnAction((event) -> { text_Operand2.requestFocus(); });
				
		// Establish an error message for the first error term just below it, left aligned
		setupLabelUI(label_errError1, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 220);
		label_errError1.setTextFill(Color.RED);		
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 270);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 14, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 290, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Error2.requestFocus(); });
		
		// Establish an error message for the second operand just below it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 320);
		label_errOperand2.setTextFill(Color.RED);
		
		// Label the second error term just below the error message for the second operand, left aligned
		setupLabelUI(label_Error2, "Arial", 14, Calculator.WINDOW_WIDTH-100, Pos.BASELINE_LEFT, 10, 355);
				
		// Establish the result text input error field and when anything changes in second error term,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Error2, "Arial", 14, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 385, true);
		text_Error2.textProperty().addListener((observable, oldValue, newValue) -> {setError2(); });
		// Move focus to the second operand when the user presses the enter (return) key
		text_Error2.setOnAction((event) -> { text_Result.requestFocus(); });
				
		// Establish an error message for the error operand just below it with, left aligned
		setupLabelUI(label_errError2, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 425);
		label_errError2.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 475);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 14, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 495, false);
		
		// Establish an error message for the result just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 250, 475);
		label_errResult.setTextFill(Color.RED);
		
		// Label the result error operand just below it, left aligned
		setupLabelUI(label_Error, "Arial", 14, Calculator.WINDOW_WIDTH-100, Pos.BASELINE_LEFT, 10, 555);
				
		// Establish the error output field. It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Error, "Arial", 14, Calculator.WINDOW_WIDTH-250, Pos.BASELINE_LEFT, 10, 575, false);
		
		//Establish label  and text field to display the resultant unit
		setupLabelUI(Label_resultUnit, "Arial", 14, Calculator.WINDOW_WIDTH-100, Pos.BASELINE_LEFT, 600, 475);
		setupTextUI (text_resultUnit, "Arial", 14, 180, Pos.BASELINE_LEFT, 600, 495, false);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		//Below functions might throw exception as they are integrated with readExcel class through Calculator unit class, 
		//which includes reading from an excel file
		setupButtonUI(button_Add, "Symbol", 22, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 625);
		button_Add.setOnAction((event) -> { try {addOperands();	} catch (IOException e) {e.printStackTrace();} });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 22, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 625);
		button_Sub.setOnAction((event) -> { try {subOperands();	} catch (IOException e) {e.printStackTrace();} });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 22, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 625);
		button_Mpy.setOnAction((event) -> { try {mpyOperands();	} catch (IOException e) {e.printStackTrace();} });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 22, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 625);
		button_Div.setOnAction((event) -> { try {divOperands();	} catch (IOException e) {e.printStackTrace();} });
		
		// Establish the SQRT "\u221A" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 22, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 625);
		button_Sqrt.setOnAction((event) -> { try {sqrtOperands();	} catch (IOException e) {e.printStackTrace();} });
		
		// Establish the labels and buttons to select units, position it and link it to methods to accomplish its work		
		setupLabelUI(Label_UnitOperand1, "Arial", 14, 120, Pos.BASELINE_LEFT, 600, 80);
		setupLabelUI(Label_UnitOperand2, "Arial", 14, 120, Pos.BASELINE_LEFT, 600, 270);
		
		//Linked the below buttons with stagePopUp to display the units window, the methods throws exception on multiple clicks
		setupButtonUI(button_selectunit1, "Symbol", 14, 120, Pos.BASELINE_LEFT, 600 , 100);
		button_selectunit1.setOnAction((event) -> {try {stagePopUp1.showAndWait();} catch (Exception e) {} });		
		setupButtonUI(button_selectunit2, "Symbol", 14, 120, Pos.BASELINE_LEFT, 600 , 290);
		button_selectunit2.setOnAction((event) -> {try {stagePopUp2.showAndWait();} catch (Exception e) {} });

		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DecimalCalculator, label_Operand1, text_Operand1, label_Error1, text_Error1, label_errOperand1, 
				label_errError1, label_Operand2, text_Operand2, label_Error2, text_Error2, label_errOperand2, label_errError2, label_Result,
				text_Result, label_errResult, label_Error,text_Error, button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt,button_selectunit1,
				button_selectunit2,Label_resultUnit,text_resultUnit,Label_UnitOperand1,Label_UnitOperand2);
		
		//Physical quantity arrays for different titled panes to hold unit buttons in accordion1
		arrayA1 = FXCollections.observableArrayList(btnWord00);
		arrayB1 = FXCollections.observableArrayList(btnWord01, btnWord02, btnWord03,btnWord04);
		arrayC1 = FXCollections.observableArrayList(btnWord05, btnWord06, btnWord07);
		arrayD1 = FXCollections.observableArrayList(btnWord08, btnWord09, btnWord010, btnWord011);
		arrayE1 = FXCollections.observableArrayList(btnWord012, btnWord013, btnWord014, btnWord015, btnWord031);
		arrayF1 = FXCollections.observableArrayList(btnWord016, btnWord017, btnWord018, btnWord019);
		arrayG1 = FXCollections.observableArrayList(btnWord020, btnWord021);
		arrayH1 = FXCollections.observableArrayList(btnWord022, btnWord023, btnWord024, btnWord025);
		arrayI1 = FXCollections.observableArrayList(btnWord026, btnWord027, btnWord028, btnWord029);
		arrayJ1 = FXCollections.observableArrayList(btnWord030);

		//Physical quantity arrays for different titled panes to hold unit buttons in accordion2
		arrayA2 = FXCollections.observableArrayList(btnWord10);
		arrayB2 = FXCollections.observableArrayList(btnWord11, btnWord12, btnWord13,btnWord14);
		arrayC2 = FXCollections.observableArrayList(btnWord15, btnWord16, btnWord17);
		arrayD2 = FXCollections.observableArrayList(btnWord18, btnWord19, btnWord110, btnWord111);
		arrayE2 = FXCollections.observableArrayList(btnWord112, btnWord113, btnWord114, btnWord115, btnWord131);
		arrayF2 = FXCollections.observableArrayList(btnWord116, btnWord117, btnWord118, btnWord119);
		arrayG2 = FXCollections.observableArrayList(btnWord120, btnWord121);
		arrayH2 = FXCollections.observableArrayList(btnWord122, btnWord123, btnWord124, btnWord125);
		arrayI2 = FXCollections.observableArrayList(btnWord126, btnWord127, btnWord128, btnWord129);
		arrayJ2 = FXCollections.observableArrayList(btnWord130);
		
		
		// Create a group to hold the window UI elements
		rootPopUp1 = new Group();
		rootPopUp2 = new Group();
		
		//Panes displaying physical quantities for accordion1
		t11 = new TitledPane("Scalar", new ListView<>(arrayA1));
		t12 = new TitledPane("Length", new ListView<>(arrayB1));
		t13 = new TitledPane("Mass", new ListView<>(arrayC1));
		t14 = new TitledPane("Time", new ListView<>(arrayD1));
		t15 = new TitledPane("Speed", new ListView<>(arrayE1));
		t16 = new TitledPane("Acceleration", new ListView<>(arrayF1));
		t17 = new TitledPane("Gravitational paramters", new ListView<>(arrayG1));
		t18 = new TitledPane("Area", new ListView<>(arrayH1));
		t19 = new TitledPane("Volume", new ListView<>(arrayI1));
		t110 = new TitledPane("Others", new ListView<>(arrayJ1));


		//setting up the window for first select operand unit selection
		Accordion accordion1 = new Accordion();
		accordion1.getPanes().addAll(t11, t12, t13, t14, t15, t16, t17, t18, t19, t110);
		accordion1.setMinWidth(300);
		accordion1.setMaxHeight(400);
		rootPopUp1.getChildren().addAll(accordion1);
		scenePopUp1 = new Scene(rootPopUp1, 300, 400);
		stagePopUp1 = new Stage();
		stagePopUp1.setScene(scenePopUp1);
		stagePopUp1.setTitle("Select a physical quantity for first operand");
		
		//Panes displaying physical quantities for accordion2
		t21 = new TitledPane("Scalar", new ListView<>(arrayA2));
		t22 = new TitledPane("Length", new ListView<>(arrayB2));
		t23 = new TitledPane("Mass", new ListView<>(arrayC2));
		t24 = new TitledPane("Time", new ListView<>(arrayD2));
		t25 = new TitledPane("Speed", new ListView<>(arrayE2));
		t26 = new TitledPane("Acceleration", new ListView<>(arrayF2));
		t27 = new TitledPane("Gravitational paramters", new ListView<>(arrayG2));
		t28 = new TitledPane("Area", new ListView<>(arrayH2));
		t29 = new TitledPane("Volume", new ListView<>(arrayI2));
		t210 = new TitledPane("Others", new ListView<>(arrayJ2));

		
		//setting up the window for second select operand unit selection
		Accordion accordion2 = new Accordion();
		accordion2.getPanes().addAll(t21, t22, t23, t24, t25, t26, t27, t28, t29, t210);
		accordion2.setMinWidth(300);
		accordion2.setMaxHeight(400);
		rootPopUp2.getChildren().addAll(accordion2);
		scenePopUp2 = new Scene(rootPopUp2, 300, 400);
		stagePopUp2 = new Stage();
		stagePopUp2.setScene(scenePopUp2);
		stagePopUp2.setTitle("Select a physical quantity for second operand");

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	/**********
	 * Create a new button, set up its word, and set up the value 
	 * 
	 * @param buttonText	The text that defines the word
	 * @param txtIndex		The text that defines the unit's index
	 * @param qtyIndex      The text that defines the physical quantity's index
	 * @return
	 */
	private Button createButton1 (String buttonText, String qtyIndex, String unitIndex) {
		Button button = new Button(buttonText);
		button.setOnAction(eve->{button_selectunit1.setText(button.getText());
		stagePopUp1.close(); Operand1qty = qtyIndex; Operand1unitindex = unitIndex;Operand1unit=buttonText;});
		return button;
	}
	
	private Button createButton2 (String buttonText, String qtyIndex, String unitIndex) {
		Button button = new Button(buttonText);
		button.setOnAction(eve->{button_selectunit2.setText(button.getText());
		stagePopUp2.close();Operand2qty = qtyIndex; Operand2unitindex = unitIndex; Operand2unit=buttonText;});
		return button;
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Error1.getText().length() == 0)
				label_errError1.setText("");
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
			if (text_Error2.getText().length() == 0)
				label_errError2.setText("");

			
		}
		else 												// If there's a problem with the operand, display
			label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
	}
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
			if (text_Error1.getText().length() == 0)
				label_errError1.setText("");
			if (text_Error2.getText().length() == 0)
				label_errError2.setText("");
		}
		else
			label_errOperand2.setText(perform.getOperand2ErrorMessage());
	}
	
	/**********
	 * Private local method to set the value of the first error given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setError1() {
		//text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		text_Error.setText("");
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setError1(text_Error1.getText())) {
			label_errError1.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
			if (text_Operand2.getText().length() == 0)
				label_errOperand2.setText("");
			if (text_Error2.getText().length() == 0)
				label_errError2.setText("");
		}
		else
			label_errError1.setText(perform.getError1ErrorMessage());
	}
	
	/**********
	 * Private local method to set the value of the second error given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setError2() {
		//text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		text_Error.setText("");
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setError2(text_Error2.getText())) {
			label_errError2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
			if (text_Operand2.getText().length() == 0)
				label_errOperand2.setText("");
			if (text_Error1.getText().length() == 0)
				label_errError1.setText("");
		}
		else
			label_errError2.setText(perform.getError2ErrorMessage());
	}
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		String errorMessage3 = perform.getError1ErrorMessage();
		String errorMessage4 = perform.getError2ErrorMessage();

		// check if any of the four operands generated a error message, if yes return true
		boolean flag = false;
		if(errorMessage1.length()>0)
		{
			label_errOperand1.setText(errorMessage1);	
			flag = true;
		}
		
		if(errorMessage2.length()>0)
		{
			label_errOperand2.setText(errorMessage2);
			flag = true;
		}
		
		if(errorMessage3.length()>0)
		{
			label_errError1.setText(errorMessage3);
			flag = true;
		}
		
		if(errorMessage4.length()>0)
		{
			label_errError2.setText(errorMessage4);
			flag = true;
		}
		
		if(Operand1qty.equals("")||Operand2qty.equals(""))
		{
			label_errResult.setText("Please select the units");
			flag=true;
		}
		
		if(flag) {return true;}
		
		
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	/**********
	 * This method is called when an Single operation button has been pressed. It assesses if there are issues 
	 * with the operand or it is not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean singleOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getError1ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			return true;						// Signal there are issues
		}
		if(errorMessage2.length()>0)
		{
			label_errError1.setText(errorMessage2);
			return true;
		}
		
		if(Operand1qty.equals(""))                            //check to see if any of the unit is selected
		{
			label_errResult.setText("Please select the units");
			return true;
		}
		// If the code reaches here, the operand has no an error condition. The following code
		// check to see if the operand is defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			return true;
		} 										// Signal there are issues
		
		return false;											// Signal there are no issues with the operands
	}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, divide and squareroot) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands() throws IOException{
		text_Result.setText("");
		text_Error.setText("");
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		// initialize unit objects based on the parameters of the unit selected
		unit1= new CalculatorUnit(Operand1unit,Integer.parseInt(Operand1unitindex),Integer.parseInt(Operand1qty));
		unit2= new CalculatorUnit(Operand2unit,Integer.parseInt(Operand2unitindex),Integer.parseInt(Operand2qty));
		if(unit1.checkIfValidForAddition(unit2)) //check if addition can be performed on selected units
		{
			unit1cf = unit1.getConversionFactor(0,unit2);
			unit2cf = unit2.getConversionFactor(0,unit1);
			String theAnswer = perform.addition(unit1cf, unit2cf);						// Call the business logic add method
			String theError = perform.errorAddition(unit1cf, unit2cf);					//call errorAddition method, to perform error term addition
			label_errResult.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
	            theError = modify.NotationError(theError);            //Round down the error value to one significant digit and modify the representation
				theAnswer = modify.NotationResult(theAnswer, theError); // Modify the answer depending on the tolerance value (error term)
				text_Result.setText(theAnswer);							// If okay, display it in the result field and
				label_Result.setText("Sum");								// change the title of the field to "Sum"
				if(theError.length() > 0 && perform.getErrorFlag())
				{
					text_Error.setText(theError);
				}
				text_resultUnit.setText(unit1.getResultantUnit(0, unit2));
							
			}
			else {														// Some error occurred while doing the addition.
				text_Result.setText("");									// Do not display a result if there is an error.				
				label_Result.setText("Result");							// Reset the result label if there is an error.
				label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				text_Error.setText("");
			}
		}
		// If the units are not compatible display the error message.
		else
		{
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText("Can't perform addition on specified units");
		}
		
		
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands() throws IOException{
		text_Result.setText("");
		text_Error.setText("");
		if (binaryOperandIssues())
			return;
		unit1= new CalculatorUnit(Operand1unit,Integer.parseInt(Operand1unitindex),Integer.parseInt(Operand1qty));
		unit2= new CalculatorUnit(Operand2unit,Integer.parseInt(Operand2unitindex),Integer.parseInt(Operand2qty));
		if(unit1.checkIfValidForSubtraction(unit2))
		{
			unit1cf = unit1.getConversionFactor(0,unit2);
			unit2cf = unit2.getConversionFactor(0,unit1);
			String theAnswer = perform.subtraction(unit1cf,unit2cf);
			String theError = perform.errorSubtraction(unit1cf, unit2cf);
			label_errResult.setText("");                                // Implementation is similar to addition
			if (theAnswer.length() > 0 ) {
				//System.out.println("hi");
				theError = modify.NotationError(theError);            //Round down the error value to one significant digit and modify the representation
				theAnswer = modify.NotationResult(theAnswer, theError); // Modify the answer depending on the tolerance value (error term)
				text_Result.setText(theAnswer);
				label_Result.setText("Difference");
				if(theError.length() > 0 && perform.getErrorFlag())
				{
					text_Error.setText(theError);
				}
				text_resultUnit.setText(unit1.getResultantUnit(0, unit2));
			}
			else {
				text_Result.setText("");
				label_Result.setText("Result");
				label_errResult.setText(perform.getResultErrorMessage());
				text_Error.setText("");
			}
		}
		else
		{
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText("Can't perform Subtraction on specified units");
		}
		
		
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands()throws IOException{
		text_Result.setText("");
		text_Error.setText("");
		if (binaryOperandIssues())
			return;
		unit1= new CalculatorUnit(Operand1unit,Integer.parseInt(Operand1unitindex),Integer.parseInt(Operand1qty));
		unit2= new CalculatorUnit(Operand2unit,Integer.parseInt(Operand2unitindex),Integer.parseInt(Operand2qty));
		unit1cf = unit1.getConversionFactor(0,unit2);
		unit2cf = unit2.getConversionFactor(0,unit1);
		String theAnswer = perform.multiplication(unit1cf, unit2cf);				// Implementation is similar to addition
		String theError = perform.errorMultiplication(unit1cf, unit2cf);
		theError = modify.NotationError(theError);            //Round down the error value to one significant digit and modify the representation
		theAnswer = modify.NotationResult(theAnswer, theError); // Modify the answer depending on the tolerance value (error term)
		label_errResult.setText("");
		if (theAnswer.length() > 0) {
			text_Result.setText(theAnswer);
			label_Result.setText("Product");
			if(theError.length() > 0 && perform.getErrorFlag())
			{
				text_Error.setText(theError);
			}
			text_resultUnit.setText(unit1.getResultantUnit(1, unit2));
		}
		else {
			text_Result.setText("");
			label_Result.setText("Result");
			label_errResult.setText(perform.getResultErrorMessage());
			text_Error.setText("");
		}
		
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands()throws IOException{
		text_Result.setText("");
		text_Error.setText("");
		if (binaryOperandIssues())
			return;
		unit1= new CalculatorUnit(Operand1unit,Integer.parseInt(Operand1unitindex),Integer.parseInt(Operand1qty));
		unit2= new CalculatorUnit(Operand2unit,Integer.parseInt(Operand2unitindex),Integer.parseInt(Operand2qty));
		if(unit1.checkIfValidForDivision(unit2))
				{
					unit1cf = unit1.getConversionFactor(0,unit2);
					unit2cf = unit2.getConversionFactor(0,unit1);
					String theAnswer = perform.division(unit1cf, unit2cf);					// Implementation is similar to addition
					String theError = perform.errorDivision(unit1cf, unit2cf);
					label_errResult.setText("");
					if (theAnswer.length() > 0) {
						theError = modify.NotationError(theError);            //Round down the error value to one significant digit and modify the representation
						theAnswer = modify.NotationResult(theAnswer, theError); // Modify the answer depending on the tolerance value (error term)
						text_Result.setText(theAnswer);
						label_Result.setText("Division");
						if(theError.length() > 0 && perform.getErrorFlag())
						{
							text_Error.setText(theError);
						}
						text_resultUnit.setText(unit1.getResultantUnit(2, unit2));
					}
					else {
						text_Result.setText("");
						label_Result.setText("Result");
						label_errResult.setText(perform.getResultErrorMessage());
						text_Error.setText("");
					}
				}
		else
		{
			{
				text_Result.setText("");									// Do not display a result if there is an error.				
				label_Result.setText("Result");							// Reset the result label if there is an error.
				label_errResult.setText("Can't perform Division on specified units");
			}
		}
		
	}
	
	/*********
	 * This square root routine
	 * If the input is a negative value, the operand is declared invalid
	 */
	private void sqrtOperands() throws IOException{
		text_Result.setText("");
		text_Error.setText("");
		label_errOperand2.setText("");
		label_errError2.setText("");
		// check to see if the operand is defined and valid
		if (singleOperandIssues())
			return;
		unit1= new CalculatorUnit(Operand1unit,Integer.parseInt(Operand1unitindex),Integer.parseInt(Operand1qty));
		if(unit1.checkIfValidForSquareRoot())
		{
			String theAnswer = perform.squareRoot();						
			String theError = perform.errorSquareRoot();
			label_errResult.setText("");
			label_errOperand2.setText("");
			label_errError2.setText("");
			if (theAnswer.length() > 0) {
				//System.out.println(theAnswer);
				theError = modify.NotationError(theError);            //Round down the error value to one significant digit and modify the representation
				theAnswer = modify.NotationResult(theAnswer, theError); // Modify the answer depending on the tolerance value (error term)
				text_Operand2.setText("");				   //reset operand2 text as we are dealing with only one operand
				text_Error2.setText("");						//Implementation is similar to addition, but operate only on one operand
				text_Result.setText(theAnswer);
				label_Result.setText("Root");
				if(theError.length() > 0 && perform.getErrorFlag())
				{
					text_Error.setText(theError);
				}
				unit2 = new CalculatorUnit("no-unit",0,0); // passing default unit as second oprands unit
				text_resultUnit.setText(unit1.getResultantUnit(3, unit2));
			}
			else {
				text_Result.setText("");
				label_Result.setText("Result");
				label_errResult.setText(perform.getResultErrorMessage());
				text_Error.setText("");
			}
			
		}
		else
		{
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText("Can't perform Squareroot on specified units");
		}
		
		}
		
}