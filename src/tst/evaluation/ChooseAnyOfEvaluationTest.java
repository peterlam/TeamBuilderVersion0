/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.evaluation;

import evaluation.ChooseAnyOfEvaluation;
import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyTeam;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lam
 */
public class ChooseAnyOfEvaluationTest {
    private TestableCAOEvaluation _testableCAOEvaluation;    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableCAOEvaluation = new TestableCAOEvaluation();
    }
    
    @After
    public void tearDown() {
        _testableCAOEvaluation = null;
    }

    @Test
    public void calculateTeamsStandardDeviation_returnZero() 
    {
        //Test if teamsList is empty, if it will return zero
        List<MyTeam> teamsList = new LinkedList<MyTeam>();
        List<MyQuestion> questionsList = new LinkedList<MyQuestion>();
        double result = _testableCAOEvaluation.calculateTeamsStandardDeviation(teamsList, questionsList);
        Assert.assertEquals(0, result, 0.001);
    }
    
    public class TestableCAOEvaluation extends ChooseAnyOfEvaluation
    {      
        
    }
}
