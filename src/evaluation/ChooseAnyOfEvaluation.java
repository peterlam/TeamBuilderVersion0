/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyResponse;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class ChooseAnyOfEvaluation implements ITeamsEvaluation{
    
    private int totalChoicesCount;
    
    @Override
    public double calculateTeamsStandardDeviation(List<MyTeam> teamsList, List<MyQuestion> questionsList) {
        final int aTeam = 0;
        final int aPerson = 0;
        
        //teamsList need to be populated with data
        if (teamsList.size()<1)
        {
            return 0;
        }
        
        int numberOfQuestions = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().size();
        double standardDeviation;
        double teamScore;        
        List<Double> scores = new LinkedList<Double>();
        
        //foreach question (should be all CAO type questions)
        for (int i = 0; i < numberOfQuestions; i++) {
            int teamCount = 0;
            List<Double> score = new LinkedList<Double>();
            
            for (MyTeam team : teamsList) {
                totalChoicesCount = 0; //before counting the choices per team
                List<MyResponse> responseList = buildResponseListPerQuestion(team, i);
                teamCount++;
                
                //TODO: Need to incorperate the weight into the calculation
                
                //Calculation:
                //1-(SumOfChoicesGreaterThan1^2/numberOfStudent*totalNumberOfChoicesPickedNonUnique)
                int[] choicesCount = buildChoicesCountArray(responseList);
                final double numerator = calculateNumerator(choicesCount);
                final double numberOfStudents = team.getTeamSize();
                teamScore = (double)1.0 - (numerator/(numberOfStudents*totalChoicesCount));
                
                team.setTeamScore(teamScore, i); //store to obj, not used right now
                score.add(teamScore);
            }
            
            //Incorperate the diversity requirement of the question into the calculation
            double meanScore;
            String questionID = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().get(i).getqId();
            //Is question asking for homogeneity?
            if (EvaluationHelper.isHomogenious(questionsList, questionID)) 
            {
                meanScore = 1.0 - EvaluationHelper.mean(score);
            } 
            else 
            {
                meanScore = EvaluationHelper.mean(score);
            }
            
            scores.add(meanScore);
        }        
        
        standardDeviation = EvaluationHelper.mean(scores);
        
        return standardDeviation;
    }    

    /**
     * Build ChoicesCount array and count the total choices from team
     * @param responseList
     * @return 
     */
    private int[] buildChoicesCountArray(List<MyResponse> responseList)
    {
        int[] choicesCount = new int[200];
        for (int i=0; i<responseList.size(); i++)
        {
            for (int j=0; j<responseList.get(i).getTotalChoices(); j++)
            {
                if (responseList.get(i).getAnswer(j)>0)
                {
                    choicesCount[j]++;
                    totalChoicesCount++;
                }
            }
        }
        
        return choicesCount;
    }
    
    /**
     * Calculate the numerator for the CAO score
     * (sum of choices greater than 1 ^2)
     * @param choicesCount
     * @return 
     */
    private double calculateNumerator(int[] choicesCount) {
        double numerator = 0.0;
        for (int i = 0; i < 200; i++) {
            if (choicesCount[i] > 0) {
                numerator = numerator + (choicesCount[i]*choicesCount[i]);
            }
        }
        return numerator;
    }
    
     /**
     * Build a response list base on a given team and the question number
     * @param team
     * @param question
     * @return 
     */
    private List<MyResponse> buildResponseListPerQuestion(MyTeam team, int question) {
        List<MyResponse> responsesList = new LinkedList<MyResponse>();        

        for (int i = 0; i < team.studentList.size(); i++) {
            MyResponse response = new MyResponse();
            final MyResponse studentResponse = team.studentList.get(i).getResponseList().get(question);
            response.setqId(studentResponse.getqId());
            response.setType(studentResponse.getType());
            response.setAnswers(studentResponse.getAnswers());
            response.setTotalChoices(studentResponse.getTotalChoices());
            responsesList.add(response);
        }

        return responsesList;
    }
   
}


