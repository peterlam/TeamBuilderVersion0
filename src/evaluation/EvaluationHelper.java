/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;
import java.util.*;
import model.MyQuestion;
/**
 *
 * @author Lam
 */
public class EvaluationHelper {

    public static double sum(List<Double> a) {
        if (a.size() > 0) {
            double sum = 0;

            for (double i : a) {
                sum += i;
            }
            return sum;
        }
        return 0.0;
    }

    public static double mean(List<Double> a) {
        double sum = sum(a);
        double mean = 0;
        if(a.size() > 0)
        {
            mean = sum / (a.size() * 1.0);
        }
        return mean;
    }

    public static double weightedAverage(List<Double> a, double totalWeight) {
        double sum = sum(a);
        double mean = 0;
        if(a.size() > 0)
        {
            mean = sum / (totalWeight * 1.0);
        }
        return mean;
    }
    
    public static double median(List<Double> a) {        
        if (a.size() > 0)
        {
            int middle = a.size() / 2;

            if (a.size() % 2 == 1) {
                return a.get(middle);
            } else {
                return (a.get(middle - 1) + a.get(middle)) / 2.0;
            }
        }
        return 0;
    }

    public static double sd(List<Double> a) 
    {
        double sum = 0;
        double mean = mean(a);
        
        if (mean > 0)
        {
            for (double i : a) {
                sum += Math.pow((i - mean), 2);
            }
            return Math.sqrt(sum / (a.size() - 1));
        }
        return 0;       
    }
    
    /**
     * Determine if given question is looking for homogeneous combination or not
     * @param myQuestionList
     * @param questionId
     * @return 
     */
    public static boolean isHomogenious(List<MyQuestion> myQuestionList, String questionId)
    {
        for(MyQuestion question: myQuestionList)
        {
            if (question.getId().equalsIgnoreCase(questionId))
            {
                if (question.getDiversity() < 0)
                {
                    return true;
                }
                else
                {
                    break;
                }
            }
        }
        
        return false;
    }
    
    public static double getTotalQuestionWeight(List<MyQuestion> questionsList)            
    {
        double totalQuestionWeight = 0.0;
        for(MyQuestion question: questionsList)
        {
            if (question.getQuestionWeight()==0)
            {
                totalQuestionWeight += 5.0;
            }
            else
            {
                totalQuestionWeight += question.getQuestionWeight();
            }    
        }
        return totalQuestionWeight;
    }
    
    public static double getQuestionWeight(List<MyQuestion> questionList, String questionId)
    {
        for(MyQuestion question: questionList)
        {
            if (question.getId().equalsIgnoreCase(questionId))
            {
                if (question.getQuestionWeight()==0)
                {
                    return 5.0;
                }
                else
                {
                    return (double)question.getQuestionWeight();
                }
            }
        }
        return 1.0; //shouldn't be here, since the question id should exist in the list
    }
}
