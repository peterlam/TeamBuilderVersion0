/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.util.List;
import model.MyQuestion;
import model.MyResponse;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class OutputResult {

    public void printResult(List<MyTeam> teams, List<MyQuestion> questions) {        
        
        System.out.println("Begin Result");
        outputQuestionsInfo(questions);
        
        int teamCounter = 0;
        for (MyTeam team : teams) 
        {
            teamCounter++;
            System.out.println( "---- Team " + teamCounter + " ----");            
            
            //Consider showing evaluation scores
            //team.getTeamScore(i) 
            
            for (MyStudent student : team.studentList)
            {
                System.out.println("UserName: " + student.getUsername());
                List<MyResponse> list = student.getResponseList();
                for (int i=0; i<list.size(); i++)
                {   
                    if (list.get(i).getType().equalsIgnoreCase("CAO"))
                    {
                        String answers = "";
                        for(int j=0; j<200; j++)
                        {
                            if (list.get(i).getAnswer(j) == 1)
                            {
                                answers += ", " + j;
                            }
                        }
                        System.out.println("Question(" + i + "), Type(" + list.get(i).getType() + ")" + answers);
                    }
                    else
                    {
                        System.out.println("Question(" + i + "), Type(" + list.get(i).getType() + "), " + list.get(i).getAnswer(0));
                    }
                }                
            }
        }
        
        System.out.println("End Result");
    }

    /**
     * Show questions information
     * @param questions 
     */
    private void outputQuestionsInfo(List<MyQuestion> questions) {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question (" + i + "), " 
                    + questions.get(i).getTitle() + ", " 
                    + questions.get(i).getType() + ", "
                    + getDiversityMeaning(questions.get(i).getDiversity()) + ", weight = "
                    + questions.get(i).getQuestionWeight());
        }
    }
    
    private String getDiversityMeaning(int diversity)
    {
        if (diversity > 0)
        {
            return "not similar";
        }
        else
        {
            return "similar";
        }
    }
}
