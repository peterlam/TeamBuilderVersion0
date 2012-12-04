/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyResponse;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class MultipleChoiceEvaluation implements ITeamsEvaluation{
    
    /**
     * Calculate standard deviation of each team combination base on the diversity
     * of all the student responses.
     * @param teamsList
     * @return 
     */
    @Override
    public double calculateTeamsStandardDeviation(List<MyTeam> teamsList, List<MyQuestion> questionsList) {
            
        //teamsList need to be populated with data
        if (teamsList.size()<1)
        {
            return 0;
        }
        
        //int teamCount;
        final int aTeam = 0;
        final int aPerson = 0;
        int numberOfQuestions = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().size();
        double standardDeviation = 0.0;
        double teamScore;
        List<Double> scores = new LinkedList<Double>();        
        
        //foreach question (assuming all MC type questions)
        for (int i = 0; i < numberOfQuestions; i++) {
            int maxResponses = 0;
            int teamCount = 0;
            teamScore = 0;
            List<Double> score = new LinkedList<Double>();
            maxResponses = getMaxResponses(teamsList, i);
            
            for (MyTeam team : teamsList) {

                List<MyResponse> responseList = buildResponseListPerQuestion(team, i);
                int responseCount = getSpecificResponseCount(responseList);
                teamCount++;

                teamScore = responseCount / (double)maxResponses; 
                                
                team.setTeamScore(teamScore, i); //store to obj, not used right now
                score.add(teamScore);
            }
            //Incorperate the diversity requirement into the calculation
            double meanScore;
            String questionID = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().get(i).getqId();
            //Is question asking for homogeneity?
            if (EvaluationHelper.isHomogenious(questionsList, questionID)) 
            {
                meanScore = EvaluationHelper.mean(score);
            } 
            else 
            {
                meanScore = 1.0 - EvaluationHelper.mean(score);
            }
            
            //double questionWeight = EvaluationHelper.getQuestionWeight(questionsList, questionID);
            //meanScore = meanScore * questionWeight;
            
            scores.add(meanScore);
        }        
        
        
        //double totalQuestionWeight = EvaluationHelper.getTotalQuestionWeight(questionsList);
        
        
        //use 1.0 minus the mean to be in sync with CAO and Scale evaluation in TeamPicker.java
        //a number close enough to zero to indicate a degree of homgeneity
        standardDeviation = (1.0 - EvaluationHelper.mean(scores));
        
        return standardDeviation;
    }
    
    /**
     * Determine if a given question should be evaluated base on similarity or dissimilarity
     * @param questionList
     * @param id
     * @return 
     */
    private boolean getDiversity(List<MyQuestion> questionList, String id)
    {
        for (int i = 0; i<questionList.size(); i++)
        {
            if(questionList.get(i).getId().equalsIgnoreCase(id))
            {
                if(questionList.get(i).getDiversity() < 0)
                {
                    return false;
                }
            }
        }        
        return true; //default to be diversified
    }
    
    /**
     * Get the number of unique response from a list of responses
     * @param responseList
     * @return 
     */
    private int getSpecificResponseCount (List<MyResponse> responseList)
    {
        HashMap map = new HashMap();
        
        for (MyResponse response: responseList){
            int responseNum = response.getAnswer(0);
            
            //Ignore Blank answer (set to -1)
            if(responseNum > 0)
            {
                if (map.containsKey(responseNum)){
                    int count = (Integer) map.get(responseNum);
                    map.put(responseNum, count + 1);
                }
                else
                {
                    map.put(responseNum, 1);
                }
            }
        }
           
        return map.size();
    }
    
    /**
     * Build a response list base on a given team and the question number
     * @param team
     * @param question
     * @return 
     */
    private List<MyResponse> buildResponseListPerQuestion(List<MyTeam> teamsList, int question) {
        List<MyResponse> responsesList = new LinkedList<MyResponse>();        

        for (MyTeam team: teamsList)
        {
            for (int i = 0; i < team.studentList.size(); i++) {
                MyResponse response = new MyResponse();
                final MyResponse studentResponse = team.studentList.get(i).getResponseList().get(question);
                response.setqId(studentResponse.getqId());
                response.setType(studentResponse.getType());
                response.setAnswers(studentResponse.getAnswers());
                responsesList.add(response);
            }
        }
        return responsesList;
    }
    
    /**
     * Build a response list base on a given team and the question number
     *
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
            responsesList.add(response);
        }

        return responsesList;
    }

    /**
     * Get the maximum number of responses per question
     * @param teamsList
     * @param questionNumber
     * @return 
     */
    private int getMaxResponses(List<MyTeam> teamsList, int questionNumber) {        
        int maxResponses = 0;
        
        List<MyResponse> responseList = buildResponseListPerQuestion(teamsList, questionNumber);
        int responseCount = getSpecificResponseCount(responseList);

        if (responseCount > maxResponses)
        {
            maxResponses = responseCount;
        }                
        
        return maxResponses;
    }
    
    
}
