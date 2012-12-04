/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.evaluation;

import evaluation.MultipleChoiceEvaluation;
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
import static org.junit.Assert.*;

/**
 *
 * @author Lam
 */
public class MultipleChoiceEvaluationTest {
    
    private TestableMultipleChoiceEvaluation _testableMCEvaluation;
    
    public MultipleChoiceEvaluationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableMCEvaluation = new TestableMultipleChoiceEvaluation();
    }
    
    @After
    public void tearDown() {
        _testableMCEvaluation = null;
    }

    @Test
    public void calculateTeamsStandardDeviation_returnZero() 
    {
        //Test if teamsList is empty, if it will return zero
        List<MyTeam> teamsList = new LinkedList<MyTeam>();
        List<MyQuestion> questionsList = new LinkedList<MyQuestion>();
        double result = _testableMCEvaluation.calculateTeamsStandardDeviation(teamsList, questionsList);
        Assert.assertEquals(0, result, 0.001);
    }
    
        public class TestableMultipleChoiceEvaluation extends MultipleChoiceEvaluation
    {      
        
    }
}
