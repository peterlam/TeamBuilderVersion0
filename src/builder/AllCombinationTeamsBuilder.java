/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class AllCombinationTeamsBuilder extends CommonTeamBuilder {

    /**
     * Build all combination team lists from a given list of students
     *
     * @param studentList
     * @param numberOfTeams
     * @return
     */
    @Override
    public List<List<MyTeam>> buildTeamsCombinations(int numberOfTeams, List<MyStudent> studentList, List<MyQuestion> questionsList) {
        List<List<MyTeam>> teamsListCombination = new LinkedList<List<MyTeam>>();
        //teamsListCombination = getTeamCombinations(studentList);
        AllCombinationTeamsHelper helper = new AllCombinationTeamsHelper();
        List<int[]> permutationList = helper.buildPermutationList(studentList.size());

        for(int i = 0; i < permutationList.size(); i++)
        {
            teamsListCombination.add(buildTeams(numberOfTeams, studentList, permutationList.get(i)));
        }

        return teamsListCombination;
    }

    //TODO: build one team combination    
    public List<MyTeam> buildTeams(int numberOfTeams, List<MyStudent> listOfStudents, int[] permutation) {

        int evenTeamSize = listOfStudents.size() / numberOfTeams;
        int remainderSize = listOfStudents.size() % evenTeamSize;
        int evenTeamMemberSize = listOfStudents.size() - remainderSize;        

        List<MyTeam> teamList = new LinkedList<MyTeam>();
        MyTeam team = new MyTeam();
        List<MyStudent> studentsListPerTeam = new LinkedList<MyStudent>();
        for (int i=0; i<evenTeamMemberSize; i++)
        {
            //time to assign student to a new team
            if (i%evenTeamSize == 0 && i>0)
            {
                team = new MyTeam();
                studentsListPerTeam = new LinkedList<MyStudent>();
            }
            
            //permuation list is not zero based (start with one)
            studentsListPerTeam.add(listOfStudents.get(permutation[i]-1));

            //last member of a team added, add team to list
            if ((i%evenTeamSize == evenTeamSize-1) && i>0)
            {
                team.setStudentList(studentsListPerTeam);
                teamList.add(team);                
            }
        }
        
        //assign remainder students to a team
        int permutationIndex = evenTeamMemberSize - 1;
        for (int j=0; j<remainderSize; j++)
        {
            teamList.get(j).studentList.add(listOfStudents.get(permutation[permutationIndex]-1));            
        }

        return teamList;
    }
}
