/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.List;
import model.MyQuestion;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public interface TeamBuilder {
    //public List<MyTeam> buildTeams(int numberOfTeams, List<MyStudent> studentList);
    public List<List<MyTeam>>buildTeamsCombinations(int numberOfTeams, List<MyStudent> studentsList, List<MyQuestion> questionsList);
    public void printResult(List<MyTeam> teams);
}
