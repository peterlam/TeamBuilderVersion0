/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.List;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public abstract class CommonTeamBuilder implements TeamBuilder{
    
    /**
     * Currently not used. It is here in case there is common printing mechanism 
     */
    @Override
    public void printResult(List<MyTeam> teams)
    {
        System.out.println("Common Print");
    }
}
