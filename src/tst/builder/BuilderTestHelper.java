/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.builder;

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
public class BuilderTestHelper {
    public static List<MyQuestion> getQuestionList(int numberOfMC, int numberOfCOA, int numberOfScale, int diversity) 
    {
        List<MyQuestion> questionList = new LinkedList<MyQuestion>();
        buildQuestions("MC", numberOfMC, diversity, questionList);
        buildQuestions("Scale", numberOfMC, diversity, questionList);
        buildQuestions("CAO", numberOfMC, diversity, questionList);
        return questionList;
    }

    private static void buildQuestions(String type, int numberOfMC, int diversity, List<MyQuestion> questionList) {
        for (int i = 0; i < numberOfMC; i++) {
            MyQuestion question = new MyQuestion();
            question.setIndex(i);
            question.setTitle(type + "Question" + i);
            question.setType(type);
            question.setId(String.valueOf(i));
            question.setDiversity(diversity);
            questionList.add(question);
        }
    }

    public List<MyTeam> createTeamList (List<MyStudent> studentList, int numberOfTeam)
    {
        int teamSize = studentList.size()/numberOfTeam;
        List<MyTeam> teamsList = new LinkedList<MyTeam>();
        for(int i=0; i<numberOfTeam; i++)
        {
            List<MyStudent> subStudentList = new LinkedList<MyStudent>();
            for(int a = i*teamSize; a<i*teamSize+teamSize; a++)
            {
                MyStudent student = new MyStudent();
                student.setUsername(studentList.get(a).getUsername());
                student.setIndex(studentList.get(a).getIndex());
                student.setResponseList(studentList.get(a).getResponseList());
                subStudentList.add(student);
            }        
            MyTeam team = new MyTeam();
            team.setStudentList(subStudentList);
            team.setTeamSize(subStudentList.size());
            team.setTeamNumber(i);
            teamsList.add(team);
        }
        return teamsList;
    }
    
    /**
     * Create a list of students base on given size
     * @param numberOfStudent
     * @return
     */
    public List<MyStudent> createStudentList (int numberOfStudent, int numberOfResponse)
    {
        List<MyStudent> studentList = new LinkedList<MyStudent>();
        
        for(int i=1; i<=numberOfStudent; i++)
        {
            MyStudent student = new MyStudent();
            student.setIndex(i);
            student.setUsername("user" + i);
            List<MyResponse> responseList = createResponseList(numberOfResponse);
            student.setResponseList(responseList);
            studentList.add(student);
        }
        
        return studentList;
    }
       
    private List<MyResponse> createResponseList (int numberOfResponse)
    {
        List<MyResponse> responseList = new LinkedList<MyResponse>();        
        
        for(int i = 0; i<numberOfResponse; i++)
        {
            MyResponse response = new MyResponse();
            
            response.setqId(String.valueOf(i));
            response.setType("MC");            
            response.setAnswers(createAnswers(1));
            response.setTotalChoices(0);
            responseList.add(response);
        }        
        
        return responseList;
    }
    
    private int[] createAnswers (int numberOfAnswer)
    {
        int[] answer = new int[200];        
        for (int i = 0; i<numberOfAnswer; i++)
        {
            answer[i] = 10;
        }        
        return answer;
    }
    
}
