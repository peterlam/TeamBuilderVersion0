/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import builder.RandomTeamsBuilder;
import builder.AllCombinationTeamsBuilder;
import builder.GeneticTeamsBuilder;
import builder.TeamBuilder;
import evaluation.ChooseAnyOfEvaluation;
import evaluation.ITeamsEvaluation;
import evaluation.MultipleChoiceEvaluation;
import evaluation.ScaleEvaluation;
import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class TeamPicker {
    
    /**
     * Pick the best team combination base on given requirement
     * @param numberOfGroups
     * @param studentsList
     * @param builderMode
     * @return 
     */
    public List<MyTeam> bestTeamPicker (int numberOfGroups, List<MyStudent> studentsList, List<MyQuestion> questionsList, int builderMode)
    {       
        //Loop a number time and get the best sd as the team result
        List<MyTeam> bestTeamsList = new LinkedList<MyTeam>();

        double bestSd = -1.0;

        TeamBuilder teamBuilder;
        teamBuilder = getTeamBuilder(builderMode);
        
        List<List<MyTeam>> teamsCombinationsList = teamBuilder.buildTeamsCombinations(numberOfGroups, studentsList, questionsList);            
        
        for(List<MyTeam> teamsList: teamsCombinationsList)
        {                        
            //MyTeams teamsList = (MyTeams) teamBuilder.buildTeams(teamInput.getNumGroups(), studentsList);
            //Calculate sd of the diversity level of the team combination            
   
            double totalNumberOfQuestions = 0;
            double sd = 0.0;
            double mcSd = 0.0;
            double caoSd = 0.0;
            double scaleSd = 0.0;
            
            List<MyTeam> mcTeamsList = new LinkedList<MyTeam>();
            List<MyTeam> caoTeamsList = new LinkedList<MyTeam>();
            List<MyTeam> scaleTeamsList = new LinkedList<MyTeam>();
            
            List<MyQuestion> mcQuestionsList = new LinkedList<MyQuestion>();
            List<MyQuestion> caoQuestionsList = new LinkedList<MyQuestion>();
            List<MyQuestion> scaleQuestionsList = new LinkedList<MyQuestion>();
            
            //build sublist of one type of question only  
            TeamBuildingHelper.buildTeamsListBaseOnType(teamsList, mcTeamsList, caoTeamsList, scaleTeamsList);      
            
            TeamBuildingHelper.buildQuestionsListBaseOnType(questionsList, mcQuestionsList, caoQuestionsList, scaleQuestionsList); 
            
            final int mcQuestionCount = mcTeamsList.get(0).studentList.get(0).getResponseList().size();
            final int caoQuestionCount = caoTeamsList.get(0).studentList.get(0).getResponseList().size();
            final int scaleQuestionCount = scaleTeamsList.get(0).studentList.get(0).getResponseList().size();
            
            if (mcQuestionCount > 0)
            {
                ITeamsEvaluation mcTeamsEval = new MultipleChoiceEvaluation(); 
                mcSd = mcTeamsEval.calculateTeamsStandardDeviation(mcTeamsList, mcQuestionsList);
                mcSd = mcSd * mcQuestionCount;
                totalNumberOfQuestions = totalNumberOfQuestions + mcQuestionCount;
            }

            if (caoQuestionCount > 0)
            {
                ITeamsEvaluation caoTeamsEval = new ChooseAnyOfEvaluation(); 
                caoSd = caoTeamsEval.calculateTeamsStandardDeviation(caoTeamsList, caoQuestionsList);
                caoSd = caoSd * caoQuestionCount;
                totalNumberOfQuestions = totalNumberOfQuestions + caoQuestionCount;
            }
            
            if (scaleQuestionCount > 0)
            {
                ITeamsEvaluation scaleTeamsEval = new ScaleEvaluation(); 
                scaleSd = scaleTeamsEval.calculateTeamsStandardDeviation(scaleTeamsList, scaleQuestionsList);
                scaleSd = scaleSd * scaleQuestionCount;
                totalNumberOfQuestions = totalNumberOfQuestions + scaleQuestionCount;
            }
            
            if (totalNumberOfQuestions > 0)
            {
                //calculate weighted average
                //Example: 1 MC, 2 CAO, 99 scale
                sd = (mcSd + scaleSd + caoSd)/totalNumberOfQuestions;
            }
            
            if (sd > bestSd)
            {
                bestSd = sd;
                bestTeamsList = teamsList;
            }
                         
        }
        return bestTeamsList;
    }

    /**
     * Provide TeamBuilder object base on choice of team building algorithm
     * @param builderMode
     * @return 
     */
    private TeamBuilder getTeamBuilder(int builderMode) {
        TeamBuilder teamBuilder;
        if (builderMode == 1)
        {
            teamBuilder = new RandomTeamsBuilder();
        }
        else if (builderMode == 2)
        {
            teamBuilder = new GeneticTeamsBuilder();
        }
        else
        {
            teamBuilder = new AllCombinationTeamsBuilder();
        }
        return teamBuilder;
    }
}
