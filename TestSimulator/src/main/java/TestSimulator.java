

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Callum Cameron
 * @date 27/03/2020
 * @version 1.0
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSimulator {
      /**
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws QuestionBank.ChapterNotAllowedException
     * @throws QuestionBank.ChapterNotNumericException
     * @throws QuestionBank.NotTrueFalseException
     * @throws QuestionBank.DuplicateIdException
     * @throws QuestionBank.IncorrectNumberOfFieldsException
     * @throws TestSummary.IncorrectNumberOfFieldsException
     */
    
public static int selection;
public static String selectionString;
public static boolean notValid;
public static final int EXIT = 3;
  
    
    public static void main(String[] args) 
        throws IOException,FileNotFoundException,
        QuestionBank.IncorrectNumberOfFieldsException, 
        QuestionBank.ChapterNotNumericException,
        QuestionBank.ChapterNotAllowedException, 
        QuestionBank.DuplicateIdException, 
        QuestionBank.NotTrueFalseException, 
        TestSummary.IncorrectNumberOfFieldsException {
        //Runs menu, branches to functions based on input 1/2/3
        do {
            System.out.println(new File("").getAbsolutePath());
            //Calls getSelection to get valid input 1/2/3
            getSelection();
            //Gets questions, runs test, shows results then returns to menu
            if (selection == 1) {
                System.out.println("You selected (1) New test.\n");
                try {
                    //Gets list of questions from QuestionBank
                    QuestionBank questionBank = new QuestionBank();
                    //Creates test. Int in constructor is number of questions.
                    Test test = new Test(5, questionBank);
                    //Calls the runTest function to commence test
                    test.runTest();
                    //Displays summary of test
                    test.showTestSummary();
                    test.saveTestResult();
                }
                //Catches possible exceptions
                catch (QuestionBank.DuplicateIdException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                catch (IOException e) {
                    System.out.println(e + " an error occurred during the write"
                        + " process. System exiting.");
                    System.exit(0);
                }
            } 
            if (selection == 2) {
                System.out.println("You selected (2) Test summary.\n");
                TestSummary testSummary = new TestSummary();
                testSummary.summarisePerformance();
                testSummary.reportPerformance();;
            } 
        }   
        while(selection == 1 || selection == 2);
           
    }
    
    public static int getSelection() {
        //Displays menu, validates selection, loops if invalid, returns 1/2/3
        do {
            //Displays menu options
            System.out.println("Welcome to the test TestSimulator program menu."
                    + "\n" +
                    "Select from one of the following options.\n" +
                    "(1) New test.\n" + "(2) Test summary.\n" + "(3) Exit.\n" +
                    "Enter your selection: ");
            //Creates Scanner object 
            Scanner userInput = new Scanner(System.in);
            //Declares users next input as String selectionString
            selectionString = userInput.next();
            //Declares notValid as false if input 1/2/3
            if (selectionString.equals("1")) {
                selection = 1;
                notValid = false;
            }
            if (selectionString.equals("2")) {
                selection = 2;
                notValid = false;
            }
             if (selectionString.equals("3")) {
                selection = 2;
                notValid = false;
            }
            //Declares notValid as true if input not 1/2/3
            if (!selectionString.equals("1") && !selectionString.equals("2") &&
                    !selectionString.equals("3"))
            {
                System.out.println("Error! Please select option 1, 2 or 3");
                notValid = true;
            }
        }
        //Loops while notValid is true to get valid input
        while (notValid);
        //Parses selectionString to int
        selection = Integer.parseInt(selectionString);
        //Returns 1/2/3
        return selection;
    
}
}


