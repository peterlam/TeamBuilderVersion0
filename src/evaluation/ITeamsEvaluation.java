/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.util.List;
import model.MyQuestion;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public interface ITeamsEvaluation {
    public double calculateTeamsStandardDeviation(List<MyTeam> teamsList, List<MyQuestion> questionsList);
}
