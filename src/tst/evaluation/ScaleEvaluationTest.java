/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.evaluation;

import evaluation.ScaleEvaluation;
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
public class ScaleEvaluationTest {
    
    private TestableScaleEvaluation _testableScaleEvaluation;
    
    public ScaleEvaluationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableScaleEvaluation = new TestableScaleEvaluation();
    }
    
    @After
    public void tearDown() {
        _testableScaleEvaluation = null;
    }

    @Test
    public void calculateTeamsStandardDeviation_returnZero() 
    {
        //Test if teamsList is empty, if it will return zero
        List<MyTeam> teamsList = new LinkedList<MyTeam>();
        List<MyQuestion> questionsList = new LinkedList<MyQuestion>();
        double result = _testableScaleEvaluation.calculateTeamsStandardDeviation(teamsList, questionsList);
        Assert.assertEquals(0, result, 0.001);
    }
    
    public class TestableScaleEvaluation extends ScaleEvaluation
    {      
        
    }
}
