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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;

public class TestSummary {
        final public static String FILENAME_SUMMARY= "./test-summary.txt";
        public final int TEST_REPORT_FIELDS = 4;
        private int chapterQuestionsAnswered[];
        //
        private int chapterQuestionsCorrect[] = new int[8];
        // 8 
        //Uses public static final int CHAPTERS[] = {8,9,10,11,13,14,15,16};
        //9 chapters total
        private ArrayList<String> files = new ArrayList<>();
        
        
        public TestSummary() {
            
        }
        public void summarisePerformance() throws FileNotFoundException, IOException, IncorrectNumberOfFieldsException {
        /*Opens test summary file, then opens each report file
        Calculates the number of questions correct and number of questions
        answered for each chapter topics. Stores results in corresponding class 
        fields
            */
        //Variable declarations
        String line;
        String splitBy = ","; 
        int j = 0;
        int i = 0;
        int chapters = 0;
        int answered = 0;
        int correct = 0; 
        boolean validChapter = false;
        //Creates ArrayList of chapterResults
        ArrayList<ChapterResults> chapterResults = new ArrayList<>();
        ChapterResults chapter8 = new ChapterResults(8,0,0);
        ChapterResults chapter9 = new ChapterResults(9,0,0);
        ChapterResults chapter10 = new ChapterResults(10,0,0);
        ChapterResults chapter11 = new ChapterResults(11,0,0);
        ChapterResults chapter13 = new ChapterResults(13,0,0);
        ChapterResults chapter14 = new ChapterResults(14,0,0);
        ChapterResults chapter15 = new ChapterResults(15,0,0);
        ChapterResults chapter16 = new ChapterResults(16,0,0);

        
        Path path = Paths.get(FILENAME_SUMMARY);
        //Adds each line of test-summary to array list files
        try {
            List<String> summaryLines = 
            Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String l : summaryLines) {
               files.add("./" + l);
            }
        }
        //Handles possible IOException
        catch (IOException e) {
            System.out.println(e + " an error occurred during the write"
                      + " process. System exiting.");
            System.exit(0);
        }
 
        //Opens each file and calculates results
        for (String s : files) {
            try {
                FileReader fr = new FileReader(s);
                BufferedReader br = new BufferedReader(fr);
                //For each report file
                while((line = br.readLine()) != null) {
                    //0 qid, 1 chapterid, 2 correct, 3 chosen
                    System.out.println(line);
                    String[] test = line.split(splitBy);
                    if (test.length > 4) {
                        throw new IncorrectNumberOfFieldsException();
                    }
                    System.out.println(test[1]);
                    try {
                        int theChapter = Integer.valueOf(test[1]);
                        //Calculates results and adds to chapterResults object
                        if (theChapter == 8) {
                            if (test[2].equals(test[3])) {
                                chapter8.incrementCorrect();
                            }
                            chapter8.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 9) {
                            if (test[2].equals(test[3])) {
                                chapter9.incrementCorrect();
                            }
                            chapter9.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 10) {
                            if (test[2].equals(test[3])) {
                                chapter10.incrementCorrect();
                            }
                            chapter10.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 11) {
                            if (test[2].equals(test[3])) {
                                chapter11.incrementCorrect();
                            }
                            chapter11.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 13) {
                            if (test[2].equals(test[3])) {
                                chapter13.incrementCorrect();
                            }
                            chapter13.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 14 ) {
                            if (test[2].equals(test[3])) {
                                chapter14.incrementCorrect();
                            }
                            chapter14.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 15) {
                            if (test[2].equals(test[3])) {
                                chapter15.incrementCorrect();
                            }
                            chapter15.incrementAnswered();
                            validChapter = true;
                        }
                        if (theChapter == 16) {
                            if (test[2].equals(test[3])) {
                                chapter16.incrementCorrect();
                            }
                            chapter16.incrementAnswered();
                            validChapter = true;
                        }
                        if (!validChapter) {
                            throw new ChapterNotAllowedException();
                        }
                    }
                catch (NumberFormatException e) {
                            throw new ChapterNotNumericException();
                }
            }
            br.close();
            fr.close();
        }
            //Handles possible IOException
            catch (IOException e) {
                System.out.println(e + " an error occurred during the write"
                          + " process. System exiting.");
                System.exit(0);
            }
            catch (IncorrectNumberOfFieldsException | 
            ChapterNotAllowedException | ChapterNotNumericException e) {
                System.out.println(e);
                System.exit(0);
            }
        //Adds each chapter's results to chapter
        chapterResults.add(chapter8);
        chapterResults.add(chapter9);
        chapterResults.add(chapter10);
        chapterResults.add(chapter11);
        chapterResults.add(chapter13);
        chapterResults.add(chapter14);
        chapterResults.add(chapter15);
        chapterResults.add(chapter16);
        //Adds total answered questions to chapterQuestionsAnswered array
        chapterQuestionsAnswered = new int[] {chapter8.getAnswered(), 
        chapter9.getAnswered(), chapter10.getAnswered(), 
        chapter11.getAnswered(), chapter13.getAnswered(), 
        chapter14.getAnswered(), chapter15.getAnswered(), 
        chapter16.getAnswered()};
        }
        //Adds total answers correct to chapterQuestionsCorrect array
        chapterQuestionsCorrect = new int[] {chapter8.getCorrect(),
        chapter9.getCorrect(), chapter10.getCorrect(), chapter11.getCorrect(),
        chapter13.getCorrect(), chapter14.getCorrect(),chapter15.getCorrect(),
        chapter16.getCorrect()};
        
            
        } 
    public void reportPerformance() {
        /*Prints summary of all test performance to screen
        performance is on a chapter by chapter basis, plus totals at bottom
        Report columns use a fixed-width format with the system.out.printf()
        method. **MUST REPLICATE OUTPUT OF SESSION TRACE**  
            */
        double percentage = 0;
        System.out.println("\nPerformance report ... \n");
        Formatter f = new Formatter();
        f.format("%8s %8s %8s %8s \n", 
        "Chapter", "Correct", "Answered", "Percent");
        System.out.println(f);
        f.format("%8s %8s %8s %8s \n", 
        "--------", "--------", "--------", "--------");
        System.out.println(f);
        for (int i = 0; i < 8; i++) {
            if (chapterQuestionsAnswered[i] > 0) {
                percentage = (chapterQuestionsCorrect[i]*100
                /chapterQuestionsAnswered[i]);
            }
            if (chapterQuestionsAnswered[i] == 0) {
                percentage = 0;
            }
            f.format("%8s %8s %8s %8s \n", 
            QuestionBank.CHAPTERS[i], chapterQuestionsCorrect[i], 
            chapterQuestionsAnswered[i], percentage);
            System.out.println(f);
            
        }
        f.format("%8s %8s %8s %8s \n", 
        "--------", "--------", "--------", "--------");
    }
    //Custom exceptions
    public class IncorrectNumberOfFieldsException extends Exception { 
        public IncorrectNumberOfFieldsException() {
            super("Incorrect number of fields in the file.");
        }
    }
    public class ChapterNotNumericException extends Exception { 
        public ChapterNotNumericException() {
            super("Found non-numeric chapter in file.");
        }
    }
    public class ChapterNotAllowedException extends Exception { 
        public ChapterNotAllowedException() {
            super("Found illegal chapter in file. Chapter not allowed!");
        }
    }
}
