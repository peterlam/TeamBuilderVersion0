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
 * Scale type question is predefined currently as between 0 - 100
 * @author Lam
 */
public class ScaleEvaluation implements ITeamsEvaluation{

    @Override
    public double calculateTeamsStandardDeviation(List<MyTeam> teamsList, List<MyQuestion> questionsList) {

        //teamsList need to be populated with data
        if (teamsList.size()<1)
        {
            return 0;
        }
        
        final int aTeam = 0; //any team, so pick the first team
        final int aPerson = 0; //any person, so pick the first person
        int numberOfQuestions = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().size();
        double teamScore;
        List<Double> teamCombinationSD = new LinkedList<Double>();        
        
        //foreach question (assuming all Scale type questions)
        for (int i = 0; i < numberOfQuestions; i++) {
            List<Double> teamSD = new LinkedList<Double>(); 
            int teamCount = 0;
            
            for (MyTeam team : teamsList) {
                List<Double> score = buildResponseListPerQuestion(team, i);
                teamCount++;
                teamScore = EvaluationHelper.sd(score);
                teamSD.add(teamScore);
                team.setTeamScore(teamScore, i); //store to obj, not used right now
            }
            //Incorperate the diversity requirement of the question into the calculation
            double meanScore;
            String questionID = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().get(i).getqId();
            //Is question asking for homogeneity?
            if (EvaluationHelper.isHomogenious(questionsList, questionID)) 
            {
                meanScore = 1.0 - EvaluationHelper.mean(teamSD);
            } 
            else 
            {
                meanScore = EvaluationHelper.mean(teamSD);
            }
            teamCombinationSD.add(meanScore);            
        }  
        
        double standardDeviation = 0.0;
        if (teamCombinationSD.size() >= 0)
        {
            standardDeviation = EvaluationHelper.mean(teamCombinationSD);
        }
        return standardDeviation;
    }        
        
    /**
     * Build a list scales base on a given team and the question number
     *
     * @param team
     * @param question
     * @return
     */
    private List<Double> buildResponseListPerQuestion(MyTeam team, int question) {
        List<Double> scaleList = new LinkedList<Double>();
        for (int i = 0; i < team.studentList.size(); i++) {
            final MyResponse studentResponse = team.studentList.get(i).getResponseList().get(question);
            if (studentResponse.getAnswer(0) > 0)
            {
                //normalized by dividing 100
                scaleList.add((double)studentResponse.getAnswer(0)/100.0);                 
            }
        }
        return scaleList;
    }
}
