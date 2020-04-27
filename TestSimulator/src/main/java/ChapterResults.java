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
 * @description Object to hold chapterResults for summary files
  */
public class ChapterResults {
    private int chapter;
    private int answered;
    private int correct;
    public ChapterResults(int chapter, int answered, int correct) {
        this.chapter = chapter;
        this.answered = answered;
        this.correct = correct;
    }
    //Incrementer
    public void incrementAnswered() {
    //Increments the amount of questions answered 
        answered++;
    }
    public void incrementCorrect() {
    //Increments the amount of questions answered
        correct++;
    }
    //Getters
    public int getAnswered() {
        return answered;
    }
    public int getCorrect() {
        return correct;
    }
    public int getChapter() {
        return chapter;
    }
    //Setters
    public void setAnswered(int answered) {
        this.answered = answered;        
    } 
    public void setCorrect(int correct) {
        this.correct = correct;
    }
    public void setChapter(int chapter) {
        this.chapter = chapter;
    }
}
