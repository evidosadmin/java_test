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
 * @description The QuestionFinder interface provides the containsQuestion() 
 * method used in the QuestionBank and Test classes. 
 */
import java.util.ArrayList;

public interface QuestionFinder {
    
    public default boolean containsQuestion(String questionID, 
            ArrayList<Question> questions)
    //Checks for a duplicate questionID, returns true if a duplicate is found
        { 
        int j = 0;
        boolean contains = false;
        //For each question in questions, check for matching ID then j++
        for (Question q: questions) {
            if (q.getQuestionId().equals(questionID)) {
                j++;
            }
        }
        //If more than one ID set contains to true
        if (j>0) {
            contains = true;
        }
    return contains;
    } 
}



