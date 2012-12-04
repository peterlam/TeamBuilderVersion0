package tst.evaluation;

import evaluation.EvaluationHelper;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test EvaluationHelper Class public APIs
 * @author Lam
 */
public class EvaluationHelperTest {
    
    public EvaluationHelperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void sum_ReturnProperResult(){
        //Test if sum returns proper result from a list of given doubles.
        List<Double> doubleList = PopulateListOfDoubles();        
        final double expectedResult = 15.5; //1.1+2.1+3.1+4.1+5.1        
        double result = EvaluationHelper.sum(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void sum_ReturnZero(){
        //Test if sum returns zero when list is empty.        
        List<Double> doubleList = new LinkedList<Double>();
        final double expectedResult = 0.0;
        double result = EvaluationHelper.sum(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }
 
    @Test
    public void mean_ReturnProperResult(){
        //Test if mean proper result from a list of given doubles.        
        List<Double> doubleList = PopulateListOfDoubles(); 
        final double expectedResult = 3.1; //(1.1+2.1+3.1+4.1+5.1)/5
        double result = EvaluationHelper.mean(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void mean_ReturnZero(){
        //Test if mean returns zero when list is empty.
        
        List<Double> doubleList = new LinkedList<Double>();
        final double expectedResult = 0.0;
        double result = EvaluationHelper.mean(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void median_ReturnProperResult(){
        //Test if mean proper result from a list of given doubles.        
        List<Double> doubleList = PopulateListOfDoubles(); 
        final double expectedResult = 3.1; //median from 1.1, 2.1, 3.1, 4.1, 5.1
        double result = EvaluationHelper.median(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void median_ReturnZero(){
        //Test if median returns zero when list is empty.        
        List<Double> doubleList = new LinkedList<Double>();
        final double expectedResult = 0.0;
        double result = EvaluationHelper.median(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void sd_ReturnProperResult(){
        //Test if standard deviation proper result from a list of given doubles.        
        List<Double> doubleList = new LinkedList<Double>();
        for(int i = 1; i<=5; i++)
        {            
            doubleList.add((double)i);            
        } 
        final double expectedResult = 1.58; //sd from 1.1, 2.1, 3.1, 4.1, 5.1
        double result = EvaluationHelper.sd(doubleList);
               
        Assert.assertEquals(expectedResult, roundTwoDecimals(result));
    }
    
    @Test
    public void sd_ReturnZero(){
        //Test if standard deviation returns zero when list is empty.        
        List<Double> doubleList = new LinkedList<Double>();
        final double expectedResult = 0.0;
        double result = EvaluationHelper.sd(doubleList);
               
        Assert.assertEquals(expectedResult, result);
    }    
    
    /**
     * Create some fake data in this case a list of doubles
     * @return 
     */
    private List<Double> PopulateListOfDoubles() 
    {
        List<Double> doubleList = new LinkedList<Double>();
        for(int i = 1; i<=5; i++)
        {            
            doubleList.add((double)i + 0.1);            
        }
        return doubleList;
    }
    
    /**
     * Round decimals to 2 decimal places
     * @param d
     * @return 
     */
    private double roundTwoDecimals(double d) 
    {            
        DecimalFormat twoDForm = new DecimalFormat("#.##");        
        return Double.valueOf(twoDForm.format(d));
    }
}
