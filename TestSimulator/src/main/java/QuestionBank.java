/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Callum Cameron
 * @date 27/03/2020
 * @version 1.0
 * @description The QuestionBank class loads all questions from the data files 
 * into memory. This allows them to be selected as part of a test later.
 */
public class QuestionBank implements QuestionFinder {   
    //Variables
    public static final int CHAPTERS[] = {8,9,10,11,13,14,15,16};
    //Edit these paths to change respective file path
    private final String TRUE_FALSE_FILE = 
            "./true-false-questions.csv";
    private final String MULTIPLE_CHOICE_FILE = 
            "./multiple-choice-questions.csv";
    //Edit these values to change respective column count
    private final int MULTIPLE_CHOICE_FIELDS = 8;
    private final int TRUE_FALSE_FIELDS = 4;
    private int length;
    ArrayList<Question> questions = new ArrayList<>();
    Question question;
    boolean contains;
    int index;
    //Error check variables
    boolean errorId = false;
    boolean realChapter = false;
    boolean realAnswer = false;
    boolean validTf = false;
    boolean validMc = false;
    boolean errorTf = false;
    boolean errorMc = false;
    boolean correctAnswerTf = false;
    char correctAnswerMc;
    ArrayList<String> identicals = new ArrayList<>();
    String questionType;
    //File variables
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    //Multiple choice variables
    String questionId;
    int chapterNumber;
    String questionText;
    String answer1; 
    String answer2; 
    String answer3;
    String answer4;
    String correctAnswerString;

    public QuestionBank() throws FileNotFoundException,
            IncorrectNumberOfFieldsException, ChapterNotNumericException,
            ChapterNotAllowedException, NotTrueFalseException, 
            DuplicateIdException
            {
    //Class method 
        //Calls local methods to load questions
        loadMultipleChoiceQuestions();         
        loadTrueFalseQuestions();           
        //Displays count of questions to user
        System.out.println("Loaded all "
                + questions.size()
                + " questions from the question bank.");

    }

    //Functional methods
    private void loadMultipleChoiceQuestions() throws FileNotFoundException,
            IncorrectNumberOfFieldsException, ChapterNotAllowedException, 
            ChapterNotNumericException, NotTrueFalseException, 
            DuplicateIdException {
    /*Loads the multiple-choice questions from the data file into the 
    “questions” member. Handles errors. */ 
        questionType = "multiple choice question";
        try {
            //Load file
            br = new BufferedReader(new FileReader(MULTIPLE_CHOICE_FILE));
            //While there is a line do for each line
            while ((line = br.readLine()) != null) {
            //Use comma as seperator and store as String[]
                String[] rawMcQuestion = line.split(csvSplitBy);
                int itemCount = rawMcQuestion.length;
                //Handle exception for > 8 fields
                try {
                    if (itemCount > MULTIPLE_CHOICE_FIELDS) {
                        throw new IncorrectNumberOfFieldsException();
                    }
                }
                catch (IncorrectNumberOfFieldsException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                questionId = rawMcQuestion[0];
                //Handle exception for duplicate IDs
                try {
                    errorId = containsQuestion(questionId, questions);
                    if (errorId) {
                        throw new DuplicateIdException();
                    }
                }
                catch (DuplicateIdException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                //Handle exception for chapter not allowed
                try {
                    try {
                        realChapter = false;
                        chapterNumber = Integer.parseInt(rawMcQuestion[1]);
                        //Check chapterNumber against array CHAPTERS
                        if (chapterNumber == CHAPTERS[0]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[1]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[2]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[3]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[4]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[5]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[6]) {
                            realChapter = true;
                        }         
                        if (chapterNumber == CHAPTERS[7]) {
                            realChapter = true;
                        }            
                        if (!realChapter) {
                            throw new ChapterNotAllowedException();
                        }
                    }
                    catch (ChapterNotAllowedException e) {
                        System.out.println(e);
                        System.exit(0);
                    }    
                //Handle non numeric chapter error
                    catch (NumberFormatException e) {
                        throw new ChapterNotNumericException();
                    }
                }
                catch (ChapterNotNumericException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                //Declare questionText at 2 in array
                questionText = rawMcQuestion[2];
                //Declare answers 1 to 4 at 3 to 6 in array
                answer1 = rawMcQuestion[3];
                answer2 = rawMcQuestion[4];
                answer3 = rawMcQuestion[5];
                answer4 = rawMcQuestion[6];
                //Declare correctAnswerMc at 7 in array to lowercase
                correctAnswerMc = Character.toLowerCase
                (rawMcQuestion[7].charAt(0));
                //Handle exception for not a, b, c or d
                validMc = false;
                try {
                    if (correctAnswerMc == 'a') {
                        validMc = true;
                    }
                    if (correctAnswerMc == 'b') {
                        validMc = true;
                    }
                    if (correctAnswerMc == 'c') {
                        validMc = true;
                    }
                    if (correctAnswerMc == 'd') {
                        validMc = true;
                    }
                    if (!validMc) {
                        throw new NotAToDException();
                    }
                }
                catch (NotAToDException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                //Add question to questions array
                questions.add(new MultipleChoiceQuestion(questionId, 
                chapterNumber, questionText, answer1, answer2, answer3, 
                        answer4, correctAnswerMc));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Error! File not found or"
                    + " could not be opened");
            System.exit(0);

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error! A miscellanous error has occurred.");
            System.exit(0);
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                    System.out.println("Error! A miscellanous error "
                            + "has occurred.");
                    System.exit(0);
                }
            }
        }
    }


    private void loadTrueFalseQuestions() throws 
            IncorrectNumberOfFieldsException, ChapterNotNumericException, 
            ChapterNotAllowedException, NotTrueFalseException {
    /*Loads the true/false questions from the data file into the 
    “questions” member. Handles errors. */ 
        questionType = "true false question";
         try {
            //Load file
            br = new BufferedReader(new FileReader(TRUE_FALSE_FILE));
            //While there is a line do for each line
            while ((line = br.readLine()) != null) {
            //Use comma as seperator and store as String[]
                String[] rawTfQuestion = line.split(csvSplitBy);
                //Handle exception for > 8 fields
                int itemCount = rawTfQuestion.length;
                try {
                    if (itemCount > TRUE_FALSE_FIELDS) {
                        throw new IncorrectNumberOfFieldsException();
                    }
                }
                catch (IncorrectNumberOfFieldsException e) {
                    System.out.println(e);
                    System.exit(0);
                    }
                //Declare questionID as 0 in array
                questionId = rawTfQuestion[0];
                //Handle exception for duplicate IDs
                try {
                    errorId = containsQuestion(questionId, questions);
                    if (errorId) {
                        throw new DuplicateIdException();
                    }
                }
                catch (DuplicateIdException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                //Handle exception for chapter not allowed
                try {
                    try {
                        realChapter = false;
                        //Declare chapterNumber as int at 1 in array
                        chapterNumber = Integer.parseInt(rawTfQuestion[1]);
                        //Check chapterNumber against array CHAPTERS
                        if (chapterNumber == CHAPTERS[0]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[1]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[2]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[3]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[4]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[5]) {
                            realChapter = true;
                        }
                        if (chapterNumber == CHAPTERS[6]) {
                            realChapter = true;
                        }         
                        if (chapterNumber == CHAPTERS[7]) {
                            realChapter = true;
                        }            
                        if (!realChapter) {
                            throw new ChapterNotAllowedException();
                        }
                    }
                    catch (ChapterNotAllowedException e) {
                        System.out.println(e);
                        System.exit(0);
                    }
                      //Handle non numeric chapter error
                    catch (NumberFormatException e) {
                        throw new ChapterNotNumericException();
                    }
                }
                catch (ChapterNotNumericException e) {
                    System.out.println(e);
                    System.exit(0);
                }
                //Declare questionText as 2 in array
                questionText = rawTfQuestion[2];
                //Handle exception for not true or false
                validTf = false;
                //Send correct answer field to lowercase
                correctAnswerString = 
                rawTfQuestion[3].toLowerCase();
                try {
                    if (correctAnswerString.equals("true")) {
                        correctAnswerTf = true;
                        validTf = true;
                    }
                    if (correctAnswerString.equals("false")) {
                        correctAnswerTf = false;
                        validTf = true;
                    }
                    if (!validTf) {
                        throw new NotTrueFalseException();
                    }
                }
                catch (NotTrueFalseException e){
                    System.out.println(e);
                    System.exit(0);
                }
                //Add the question to array questions
                questions.add(new TrueFalseQuestion(questionId, 
                chapterNumber, questionText,
                correctAnswerTf));
            }
        //Catch other errors that may occur
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Error! File not found or"
                    + " could not be opened");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error! A miscellanous error has occurred.");
            System.exit(0);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                    System.out.println("Error! A miscellanous error "
                            + "has occurred.");
                    System.exit(0);
                }
            }
        }
    }
    //Getters
    public int getLength() {
    //Returns the length of the “questions” member
        length = questions.size();
        return length;
    }
    public Question getQuestion(int index) {
    //Returns a Question at the given index
        question = questions.get(index);
        return question;
        }
    //Custom exceptions
    public class IncorrectNumberOfFieldsException extends Exception { 
        public IncorrectNumberOfFieldsException() {
            super("Error at " + questionType + " " + questionId + "! " 
                    + "Incorrect number of fields in this question.");
        }
    }
    public class ChapterNotNumericException extends Exception { 
        public ChapterNotNumericException() {
            super("Error at " + questionType + " " + questionId + "! "
                    + "Chapter number is not numeric.");
        }
    }
    public class ChapterNotAllowedException extends Exception { 
        public ChapterNotAllowedException() {
            super("Error at " + questionType + " " + questionId 
                    + "! Chapter " + chapterNumber + " not allowed!");
        }
    }
    public class NotAToDException extends Exception { 
        public NotAToDException() {
            super("Error at " + questionType + " " + questionId
                    + "! Correct answer is not a, b, c or d.");
        }
    }
    public class NotTrueFalseException extends Exception { 
        public NotTrueFalseException() {
            super("Error at " + questionType + " " + questionId
                    + "! Correct answer is not true or false.");
        }
    }
    public class DuplicateIdException extends Exception { 
        public DuplicateIdException() {
            super("Error in " + questionType + "s! "
                    + "Duplicate question IDs found for " + questionId);
        }
    }
}
