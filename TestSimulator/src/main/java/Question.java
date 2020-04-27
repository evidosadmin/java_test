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
 * @description The Question class is an abstract class that represents one 
 * question for a test. It contains details about the question, 
 * but not much about the answers. This is because there are different 
 * types of answers (i.e.: multiple-choice versus true/false) 
 * and those details are implemented elsewhere.
 */
public abstract class Question {
    private String questionId;
    private int chapterNumber;
    private String questionText;
    boolean isCorrect;
    boolean isTrueFalse;
    char chosenAnswer;
    
    public Question (String questionId, int chapterNumber, String questionText) 
    {
        this.questionId = questionId;
        this.chapterNumber = chapterNumber;
        this.questionText = questionText;
    }
    
    //Abstract methods
    public abstract boolean isAnswerCorrect();
    public abstract boolean isTrueFalse();
    public abstract String getAnswers();
    
    //Getters
    public String getQuestionId() {
        return questionId;
    }     
    public int getChapterNumber() {
        return chapterNumber;
    } 
    public String getQuestionText() {
        return questionText;
    }

   
    
}
