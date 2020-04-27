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
 * @description The Test class creates a test using a random subset of questions
 * from the QuestionBank. It also includes functionality to run the test,
 * show a summary of results, and save the results to file. 
 */
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.nio.file.*;

public class Test implements QuestionFinder {
    //Miscellanous
    Random rand = new Random();
    //Ints
    int length;
    int numQuestions;
    int actualNumQuestions;
    int Questionbank;
    int totalScore;
    //Chars
    char inputChar;
    //Booleans
    boolean results;
    boolean contains;
    boolean hasQuestion;
    boolean exceedsQuestions;
    boolean inputValid;
    boolean inputBoolean;
    boolean correct;
    boolean hasRun;
    //Strings
    String inputString;
    String convertedQuestion;
    String[] convertedQuestions;
    String feedback;
    String correctAnswer;
    //ArrayLists
    ArrayList<Question> selectedQuestions = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();
    Set<Question> selectedQuestionsSet = new HashSet<>();
    ArrayList<Integer> scores = new ArrayList<>();
    ArrayList<String> tokensArrayList = new ArrayList<>();
    //Arrays
    int[] primitiveScores;
    
    //Class method
    public Test(int numQuestions, QuestionBank questionBank) {
        /*Calls the selectQuestions(int numQuestions) helper method. 
        The program will continue and print the following messages if the 
        QuestionBank has fewer questions than requested: The question bank does 
        not have M questions. The test will have N questions instead.
        */
        //If numQuestions is longer than total array set to numQuestions
        if (numQuestions > questionBank.getLength()) {
            System.out.println("The question bank does not have " + numQuestions
                    + " questions."
                    + " The test will have " + questionBank.getLength() 
                    + " questions instead.");
            System.out.println("You may hit 'q' to quit the test at any time,"
                    + "but progress of results will not be saved.");
            System.out.println("Starting your test now ...");
            numQuestions = questionBank.getLength();
        }
        length = numQuestions;
        selectQuestions(numQuestions, questionBank);   
    }

    
    //Functional methods
    public boolean containsQuestion(String questionID, 
        ArrayList<Question> questions[]) {
        return contains;
    }
    private void selectQuestions(int numQuestions, QuestionBank questionBank) {
        /*Randomly selects the appropriate number of questions from the question
        bank to be the test. Avoids duplicates. */
        
        while (selectedQuestionsSet.size() < numQuestions) {
            //Add random questions to selectedQuestions while < numQuestions
            int n = (int)(Math.random() * questionBank.getLength());
            selectedQuestionsSet.add(questionBank.getQuestion(n));
        }    
    }
    public boolean runTest() {
    /* Runs the test in an interactive fashion one question at a time. 
    Reprompts for invalid answers. 
    Allows quitting any time with ‘q’. 
    Progress and results will not be saved.
    Gives instantaneous feedback after each question. */
        Scanner sc = new Scanner(System.in);
        //Declare int z and y for iteration
        int z = 0;
        int y = 0;
        String letter = "";
        //Iterate through each question in selectQuestionsSet 
        for(Question q : selectedQuestionsSet){
            z++;
            //Print question number and text
            System.out.println("Question " + (z) + ":");
            //Tokenizes questionText and formats, splitting by spaces
            int t = 0;
            String[] tokens = q.getQuestionText().split(" ");
            List<String> tokenList = Arrays.asList(tokens);
            for(String token : tokenList) {
                t++;
                System.out.print(token + " ");
                if (t>12) {
                    System.out.println("");
                    t = 0;
                }
            }
            System.out.println("");
            //System.out.println(q.getQuestionText());
            //If true false question print correspoding answers
            if (q.isTrueFalse()) {
                System.out.println("(a)  True");
                System.out.println("(b)  False");
            }
            //If multiple choice question print corresponding answers
            if (!q.isTrueFalse()) {
                convertedQuestions = q.getAnswers().split(",");
                for(String a : convertedQuestions) {
                    y++;
                    if (y == 1) {
                        letter = ("(a)  ");
                    }
                    if (y == 2) {
                        letter = ("(b)  ");
                    }
                    if (y == 3) {
                        letter = ("(c)  ");
                    }
                    if (y == 4) {
                        letter = ("(d)  ");
                    }
                    System.out.println(letter + a);
                }
            }
            //Ask for input until valid input is received
            do {
                System.out.println("Enter your answer: ");
                String userInput = sc.next();
                if (userInput.equals("q")) {
                    System.exit(0);
                }
                //Ensure input is valid and stores as inputChar for mc questions
                if (!q.isTrueFalse())
                {   inputValid = false;
                    if (userInput.equals("a") || userInput.equals("A")
                        || userInput.equals("(a)") || userInput.equals("(A)")) {
                        inputChar = 'a';
                        inputValid = true;
                    }
                    if (userInput.equals("b") || userInput.equals("B")
                        || userInput.equals("(a)") || userInput.equals("(B)")) {
                        inputChar = 'b';
                        inputValid = true;
                    }
                    if (userInput.equals("c") || userInput.equals("C")
                        || userInput.equals("(c)") || userInput.equals("(C)")) {
                        inputChar = 'c';
                        inputValid = true;
                    }
                    if (userInput.equals("d") || userInput.equals("D") 
                        || userInput.equals("(d)") || userInput.equals("(D)")) {
                        inputChar = 'd';
                        inputValid = true;
                    }
                    if (!inputValid) {
                        System.out.println("Please enter a valid answer.");
                    }
                }
                //Ensure input is valid and stores as inputChar for tf questions
                if (q.isTrueFalse())
                {
                    inputValid = false;
                    if (userInput.equals("a") || userInput.equals("A")
                        || userInput.equals("(a)") || userInput.equals("(A)")) {
                        inputChar = 'a';
                        inputValid = true;
                    }
                    if (userInput.equals("b") || userInput.equals("B")
                        || userInput.equals("(a)") || userInput.equals("(B)")) {
                        inputChar = 'b';
                        inputValid = true;
                    }
                    if (!inputValid) {
                        System.out.println("Please enter a valid answer.");
                    }
                }
            }
            while(!inputValid);

            //Check answer and add score to scores for TrueFalseQuestion
            if (q.isTrueFalse()) {
                //Convert inputChar to boolean inputBoolean
                if (inputChar == 'a') {
                    inputBoolean = true;
                }
                if (inputChar == 'b') {
                    inputBoolean = false;
                }
                ((TrueFalseQuestion)q).setChosenAnswer(inputBoolean);
                if(((TrueFalseQuestion)q).isAnswerCorrect()) {
                    scores.add(1);
                    feedback = "Correct!" ;
                }
                if(!((TrueFalseQuestion)q).isAnswerCorrect()) {
                    scores.add(0);
                    feedback = "Incorrect. The correct answer is: " 
                           +((TrueFalseQuestion)q).getCorrectAnswer();
                }
            }
            //Check answer and add score to scores for MultipleChoiceQuestion
            if (!q.isTrueFalse()) {
                ((MultipleChoiceQuestion)q).setChosenAnswer(inputChar);
                if (((MultipleChoiceQuestion)q).isAnswerCorrect()) {
                    scores.add(1);
                    feedback = "Correct!";
                }
                if (!((MultipleChoiceQuestion)q).isAnswerCorrect()) {
                    scores.add(0);
                    feedback = "Incorrect. The correct answer is: "
                            +((MultipleChoiceQuestion)q).getCorrectAnswer();
                }
            }
            System.out.println("");
            System.out.println("Feedback: " + feedback);
            System.out.println("");
            inputValid = false;
            //Reset iterator at the end of each question cycle
            y = 0;
            //End of iteration cycle for question
        }
        //Creates array of ints from ArrayList<Integer> scores
        primitiveScores = scores.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        //For each score, add to totalScore
        for (int score : primitiveScores){
            totalScore = score + totalScore;
        }
    return hasRun;
    }
    public void showTestSummary() {
        /*Prints a short summary of performance for the current test in the 
        following format: You answered M questions correctly out of N. 
        Your score was XX.XX%.*/
        float percentage;
        //Displays total score out of x questions
        System.out.println("You answered " + totalScore + ""
                + " questions correctly out of " + getLength());
        //Calculates total score as percentage 
        percentage = (totalScore*100/getLength());
        //Displays percentile score
        System.out.println("Your score was " + percentage + "%.");
    }
    public void saveTestResult() throws IOException {
        /* Saves results in two files:  
        ▪ Test result file: test-yymmdd-hhmmss.txt 
        (where yymmdd-hhmmss is the current date and time). 
        ▪ Test summary file: test-summary.txt 
        The test result file uses the following format (one question per line):
        questionID,chapterNumber,correctAnswer,chosenAnswer 
        The test summary file stores the names of all previous result files.  
        File errors must be caught, and the program must be terminated with an 
        appropriate error message. All files are saved at the top level of your 
        NetBeans project. This is the default location. 
        Prints the following message on success (example provided): 
        Test result saved to test-180612-110754.txt. 
        Test record added to test-summary.txt */
        String resultFileName = ("test-" + generateDate() + ".txt");
        String resultFileNameF = (resultFileName + "\n");
        String summaryFileName = ("test-summary.txt");
        String answer = "";
        String correctAnswer = "";
        String summaryPathString = "./test-summary.txt";
        Path summaryPath = Paths.get(summaryPathString);
        File summaryFile;
        //If there is summary file - append results file path then new line
        if (Files.exists(summaryPath)) {
            System.out.println("Test");
           try {
               Files.write(Paths.get(summaryPathString), 
               resultFileNameF.getBytes(), StandardOpenOption.APPEND);
           }
           catch (IOException e) {
            System.out.println(e + " an error occurred during the write"
                        + " process. System exiting.");
                System.exit(0);
           }          
        }
        //If there is no summary file - creates summary file
        if (!Files.exists(summaryPath)) {
            summaryFile = new File(summaryPathString);
             try (Writer writerSummary = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(summaryFile), "utf-8"))) {
                    writerSummary.write(resultFileName);
                    writerSummary.close();
                }
                catch (IOException e) {
                    System.out.println(e + " an error occurred during the write"
                        + " process. System exiting.");
                    System.exit(0);
                    }
        }
    
        //Handles results file
        try {
            //Creates result file
            File resultFile = new File("./"+resultFileName);
            if (resultFile.createNewFile())
            {           
            //Writes to result file
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(resultFile), "utf-8"))) {
                for (Question q: selectedQuestionsSet) {
                    //Sets chosen & correct answer for true false
                    if (q.isTrueFalse()){
                        if (((TrueFalseQuestion)q).getCorrectAnswer())
                        {
                            correctAnswer = "true";
                        }
                        if (!((TrueFalseQuestion)q).getCorrectAnswer())
                        {
                            correctAnswer = "false";
                        }
                        if (((TrueFalseQuestion)q).getChosenAnswer())
                        {
                            answer = "true";
                        }
                        if (!((TrueFalseQuestion)q).getChosenAnswer())
                        {
                            answer = "false";
                        }
                    }
                    //Sets chosen & correct answer for multiple choice
                    if (!q.isTrueFalse()){
                        correctAnswer = Character.toString
                        (((MultipleChoiceQuestion)q).getCorrectAnswer());
                        answer = Character.toString
                        (((MultipleChoiceQuestion)q).getChosenAnswer());
                    
                    }
                //Writes answers to file on one line seperated by ,
                writer.write(q.getQuestionId() + ",");
                writer.write(q.getChapterNumber() + ",");
                writer.write(correctAnswer + ",");
                writer.write(answer + ",");
                writer.write("\n");
                }
                writer.close();
            }
            catch(IOException e) {
                System.out.println(e + " an error occurred during the write"
                        + " process. System exiting.");
                System.exit(0);
            }
            System.out.println("Test result saved to " + resultFileName);
            System.out.println("Test record added to " + summaryFileName);
            } else {
                System.out.println("File already exists.");
                }
            }
        //Handles IOexceptions and exits
        catch(IOException e) {
            System.out.println(e + " an error occurred during the save process."
                    + " System exiting.");
            System.exit(0);
        }     
    }

    public String generateDate() {
        //Generates current date time, formats and returns as String
        String nowFormatted;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyMMdd-HHmmss");
        nowFormatted = dateTimeFormatter.format(currentDateTime);
        return nowFormatted;
    }
    //Getters
    private int getLength() {
        return length;
    }
}
