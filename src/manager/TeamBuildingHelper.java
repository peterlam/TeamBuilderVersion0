/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyResponse;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class TeamBuildingHelper {
    
    /**
     * Reconstruct sub-list of teams list that contains only one question type
     *
     * @param teamsList
     * @param mcTeamsList
     * @param caoTeamsList
     * @param scaleTeamsList
     */
    public static void buildTeamsListBaseOnType(List<MyTeam> teamsList, List<MyTeam> mcTeamsList, List<MyTeam> caoTeamsList, List<MyTeam> scaleTeamsList) {
        final int aTeam = 0; //any team, so pick the first team
        final int aPerson = 0; //any person, so pick the first person
        final int numberOfQuestions = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().size();

        for (int i = 0; i < teamsList.size(); i++) 
        {                      
            List<MyStudent> mcStudentList = new LinkedList<MyStudent>();
            List<MyStudent> caoStudentList = new LinkedList<MyStudent>();
            List<MyStudent> scaleStudentList = new LinkedList<MyStudent>();
            for (int j = 0; j < teamsList.get(i).studentList.size(); j++) 
            {                
                List<MyResponse> mcResponseList = new LinkedList<MyResponse>();
                List<MyResponse> caoResponseList = new LinkedList<MyResponse>();
                List<MyResponse> scaleResponseList = new LinkedList<MyResponse>();                
                
                //foreach question (assuming all Scale type questions)
                for (int k = 0; k < numberOfQuestions; k++) 
                {
                    MyResponse response = new MyResponse();
                    response = teamsList.get(i).studentList.get(j).getResponseList().get(k);
                    String type = teamsList.get(i).studentList.get(j).getResponseList().get(k).getType();
                    if (type.equalsIgnoreCase("MC")) 
                    {
                        mcResponseList.add(response);
                    } 
                    else if (type.equalsIgnoreCase("CAO")) 
                    {
                        caoResponseList.add(response);
                    } 
                    else //"SCALE"
                    {                        
                        scaleResponseList.add(response);
                    }
                }
                
                MyStudent mcStudent = new MyStudent();
                mcStudent.setResponseList(mcResponseList);
                mcStudent.setUsername(teamsList.get(i).studentList.get(j).getUsername());
                mcStudent.setIndex(teamsList.get(i).studentList.get(j).getIndex());
                mcStudentList.add(mcStudent);
                
                MyStudent caoStudent = new MyStudent();
                caoStudent.setResponseList(caoResponseList);
                caoStudent.setUsername(teamsList.get(i).studentList.get(j).getUsername());
                caoStudent.setIndex(teamsList.get(i).studentList.get(j).getIndex());
                caoStudentList.add(caoStudent);
                
                MyStudent scaleStudent = new MyStudent();
                scaleStudent.setResponseList(scaleResponseList);
                scaleStudent.setUsername(teamsList.get(i).studentList.get(j).getUsername());
                scaleStudent.setIndex(teamsList.get(i).studentList.get(j).getIndex());
                scaleStudentList.add(scaleStudent);
            }
            
            MyTeam mcTeam = new MyTeam();
            mcTeam.setTeamSize(teamsList.get(i).getTeamSize());
            mcTeam.setStudentList(mcStudentList);
            mcTeamsList.add(mcTeam);

            MyTeam caoTeam = new MyTeam();
            caoTeam.setTeamSize(teamsList.get(i).getTeamSize());
            caoTeam.setStudentList(caoStudentList);
            caoTeamsList.add(caoTeam);

            MyTeam scaleTeam = new MyTeam();
            scaleTeam.setTeamSize(teamsList.get(i).getTeamSize());
            scaleTeam.setStudentList(scaleStudentList);
            scaleTeamsList.add(scaleTeam);
        }        
    }
    
    /**
     * Reconstruct sub-list of questions list that contains only one question type
     * @param questionsList
     * @param mcQuestionsList
     * @param caoQuestionsList
     * @param scaleQuestionsList 
     */
    public static void buildQuestionsListBaseOnType(List<MyQuestion> questionsList, List<MyQuestion> mcQuestionsList, List<MyQuestion> caoQuestionsList, List<MyQuestion> scaleQuestionsList) 
    {        
        for (int i = 0; i < questionsList.size(); i++) 
        {
            String type = questionsList.get(i).getType();
            if (type.equalsIgnoreCase("MC")) 
            {
                mcQuestionsList.add(questionsList.get(i));
            } 
            else if (type.equalsIgnoreCase("CAO")) 
            {
                caoQuestionsList.add(questionsList.get(i));
            } 
            else //"SCALE"
            {                        
                scaleQuestionsList.add(questionsList.get(i));
            }
        }
    }
}
