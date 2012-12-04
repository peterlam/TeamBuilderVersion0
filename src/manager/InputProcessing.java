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
import org.netbeans.j2ee.wsdl.teambuilder.TeamInput;

/**
 *
 * @author Lam
 */
public class InputProcessing {

    /**
     * create a list of students with associated responses
     * @param teamInput
     * @return 
     */
    public static List<MyStudent> buildStudentList(TeamInput teamInput) {
        List<MyStudent> studentList = new LinkedList<MyStudent>();
        for (int i = 0; i < teamInput.getStudent().size(); i++) {
            MyStudent studentObj = new MyStudent();
            studentObj.setIndex(i);
            List<MyResponse> respList = new LinkedList<MyResponse>();
            studentObj.setUsername(teamInput.getStudent().get(i).getUsername());

            for (int j = 0; j < teamInput.getStudent().get(i).getResponse().size(); j++) {
                MyResponse resp = new MyResponse();

                //TODO: parse the CAO answer into array
                int answerCount = teamInput.getStudent().get(i).getResponse().get(j).getValue().size();
                resp.setTotalChoices(answerCount); //for CAO type question
                for (int k = 0; k < answerCount; k++)
                {
                    if (!teamInput.getStudent().get(i).getResponse().get(j).getValue().get(k).getId().isEmpty()) 
                    {
                        if (teamInput.getStudent().get(i).getResponse().get(j).getType().equalsIgnoreCase("CAO"))
                        {
                            //CAO answer stores in Answer field
                            resp.setAnswer(Integer.parseInt(teamInput.getStudent().get(i).getResponse().get(j).getValue().get(k).getAnswer()), k);
                        }    
                        else
                        {    
                            //MC or Scale answer stores in ID field
                            resp.setAnswer(Integer.parseInt(teamInput.getStudent().get(i).getResponse().get(j).getValue().get(k).getId()), k);
                        }
                    } 
                    else 
                    {
                        resp.setAnswer(-1, k);
                    }
                }
                resp.setType(teamInput.getStudent().get(i).getResponse().get(j).getType());
                resp.setqId(teamInput.getStudent().get(i).getResponse().get(j).getQId());
                respList.add(resp);
            }

            studentObj.setResponseList(respList);
            studentList.add(studentObj);
        }
        return studentList;
    }
    
    /**
     * create a list of input questions
     * @param teamInput
     * @return 
     */
    public static List<MyQuestion> buildQuestionList(TeamInput teamInput) 
    {
        List<MyQuestion> questionList = new LinkedList<MyQuestion>();
        for (int i = 0; i < teamInput.getQuestion().size(); i++) {
            MyQuestion questionObj = new MyQuestion();
            questionObj.setIndex(i);
            questionObj.setTitle(teamInput.getQuestion().get(i).getTitle());
            questionObj.setType(teamInput.getQuestion().get(i).getType());
            questionObj.setId(teamInput.getQuestion().get(i).getId());
            questionObj.setDiversity(Integer.parseInt(teamInput.getQuestion().get(i).getWeight().get(0).getValue()));
            if (teamInput.getQuestion().get(i).getQuesitonWeight().size() > 0) //To make compatible with IPeer, this field is optional
            {
                questionObj.setQuestionWeight(Integer.parseInt(teamInput.getQuestion().get(i).getQuesitonWeight().get(0).getValue()));
            }
            questionList.add(questionObj);
        }
        
        return questionList;
    }
}
