/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import evaluation.ChooseAnyOfEvaluation;
import evaluation.ITeamsEvaluation;
import evaluation.MultipleChoiceEvaluation;
import evaluation.ScaleEvaluation;
import java.util.LinkedList;
import java.util.List;
import manager.TeamBuildingHelper;
import model.MyQuestion;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class GeneticTeamsBuilder extends CommonTeamBuilder{
    /**
     * Return a list of Team Combinations
     */
    @Override
    public List<List<MyTeam>> buildTeamsCombinations(int numberOfTeams, List<MyStudent> studentsList, List<MyQuestion> questionsList) {

        List<List<MyTeam>> teamsListCombination = new LinkedList<List<MyTeam>>();
        RandomTeamsBuilder randomTeamsBuilder = new RandomTeamsBuilder();
        List<MyTeam> teamsList = randomTeamsBuilder.buildTeams(numberOfTeams, studentsList);
        List<MyTeam> bestTeamsList = new LinkedList<MyTeam>();
        //double bestSd = evaluateTeamCombination(teamsList, questionsList);
        double bestSd = -1.0;
        double tempSd;

        int evenMemberCount = studentsList.size() / numberOfTeams;
        int swapCount;
        int totalSwap = 0;
        int goodSwap = 1;
        int badSwap = 0;
        
        int count = 0;
        
        while (count<50) {
            if (goodSwap == 0) {
                break;
            }
            count++;
            goodSwap = 0;
            for (int i = 0; i < numberOfTeams; i++) //current team
            {
                swapCount = 0;

                for (int m = i+1; i + m < numberOfTeams; m++) //swap target team
                {
                    for (int j = 0; j < evenMemberCount && i+ 1 < numberOfTeams; j++) //current member
                    {
                        for (int k = 0; k < evenMemberCount; k++) //swap member
                        {

                            List<MyTeam> tempTeamsList = swapMember(teamsList, i, i + m, j, k);
                            tempSd = evaluateTeamCombination(tempTeamsList, questionsList);

                            swapCount++;

                            if (tempSd > bestSd) {
                                
                                //Copy good team combination to bestTeamsList and the original teamsList
                                bestTeamsList = new LinkedList<MyTeam>();         
                                teamsList = new LinkedList<MyTeam>();
                                recordBestTeamsList(tempTeamsList, bestTeamsList, teamsList);  
                                
                                bestSd = tempSd;
                                goodSwap++;
                                totalSwap++;
                                //break; //optional to improve performance
                            } else {
                                badSwap++;
                            }
                        }
                    }
                }
            }
        }
        
        teamsListCombination.add(bestTeamsList);
        return teamsListCombination;
    }
    
    /**
     * Swap two team member between two teams
     *
     * @param teamList
     * @param teamOneIndex
     * @param teamTwoIndex
     * @param teamOneStudentIndex
     * @param teamTwoStudentIndex
     * @return
     */
    private List<MyTeam> swapMember(List<MyTeam> teamList, int teamOneIndex, int teamTwoIndex, int teamOneStudentIndex, int teamTwoStudentIndex) {        
        MyStudent student1 = copyStudentFromTeamList(teamList, teamOneIndex, teamOneStudentIndex);
        MyStudent student2 = copyStudentFromTeamList(teamList, teamTwoIndex, teamTwoStudentIndex);
        
        List<MyTeam> swappedTeamList = new LinkedList<MyTeam>();         
        
        for(int i = 0; i<teamList.size(); i++)
        {
            MyTeam team = new MyTeam();
            team.setStudentList(teamList.get(i).studentList);
            team.setTeamSize(teamList.get(i).getTeamSize());
            team.setTeamNumber(teamList.get(i).getTeamNumber());
            team.setTeamScore(teamList.get(i).getTeamScore(0), 0);
            swappedTeamList.add(team);
        }  
        
        swappedTeamList.get(teamOneIndex).set(teamOneStudentIndex, student2);
        swappedTeamList.get(teamTwoIndex).set(teamTwoStudentIndex, student1);

        return swappedTeamList;
    }
    
    /**
     * Evaluate Team Combination 
     * TODO: Eliminate duplicate code from TeamBuildingHelper class
     * @param teamsList
     * @return 
     */
    private double evaluateTeamCombination(List<MyTeam> teamsList, List<MyQuestion> questionsList)
    {
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
        
        //build sublist of one type of question only  
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
            //calculate weighted average base on number of questions per catagory
            sd = (mcSd + scaleSd + caoSd)/totalNumberOfQuestions;
        }            

        return sd;
    }

    /**
     * Copy student from team list
     * @param teamList
     * @param teamOneIndex
     * @param teamOneStudentIndex
     * @return 
     */
    private MyStudent copyStudentFromTeamList(List<MyTeam> teamList, int teamIndex, int studentIndex) {
        MyStudent student = new MyStudent();
        student.setResponseList(teamList.get(teamIndex).studentList.get(studentIndex).getResponseList());
        student.setUsername(teamList.get(teamIndex).studentList.get(studentIndex).getUsername());
        student.setIndex(teamList.get(teamIndex).studentList.get(studentIndex).getIndex());
        return student;
    }

    private void recordBestTeamsList(List<MyTeam> tempTeamsList, List<MyTeam> bestTeamsList, List<MyTeam> teamsList) {
        for(int a = 0; a<tempTeamsList.size(); a++)
        {
            MyTeam team = new MyTeam();
            team.setStudentList(tempTeamsList.get(a).studentList);
            team.setTeamSize(tempTeamsList.get(a).getTeamSize());
            team.setTeamNumber(tempTeamsList.get(a).getTeamNumber());
            team.setTeamScore(tempTeamsList.get(a).getTeamScore(0), 0);
            bestTeamsList.add(team);
            teamsList.add(team);
        }
    }
}
