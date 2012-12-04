package tst.builder;

import builder.GeneticTeamsBuilder;
import java.util.LinkedList;
import java.util.List;
import model.MyQuestion;
import model.MyResponse;
import model.MyStudent;
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
public class GeneticTeamsBuilderTest{
    
    private GeneticTeamsBuilderTest.TestableGeneticTeamsBuilder _testableGeneticTeamsBuilder;
    private BuilderTestHelper _builderTestHelper;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableGeneticTeamsBuilder = new GeneticTeamsBuilderTest.TestableGeneticTeamsBuilder();
        _builderTestHelper = new BuilderTestHelper();
    }
    
    @After
    public void tearDown() {
        _testableGeneticTeamsBuilder = null;
        _builderTestHelper = null;
    }
    
    @Test
    public void buildTeams_TestTeamsListFilled(){
        //Test that teamsList contains teams
        final int numOfTeamCombinations = 1;
        final int numOfTeams = 2;
        final int numOfStudentPerTeam = 3;
        final int numOfMC = 3;
        
        List<MyStudent> studentList = _builderTestHelper.createStudentList (6, numOfMC);
        List<MyQuestion> questionList = BuilderTestHelper.getQuestionList(numOfMC, 0, 0, -5);
        List<List<MyTeam>> teamsList = _testableGeneticTeamsBuilder.buildTeamsCombinations(numOfTeams, studentList, questionList);

        Assert.assertFalse(teamsList.isEmpty());
        Assert.assertEquals(numOfTeamCombinations, teamsList.size());
        Assert.assertEquals(numOfTeams, teamsList.get(0).size());
        Assert.assertEquals(numOfStudentPerTeam, teamsList.get(0).get(0).studentList.size());
        Assert.assertEquals(numOfStudentPerTeam, teamsList.get(0).get(1).studentList.size());
    }   
    
    public class TestableGeneticTeamsBuilder extends GeneticTeamsBuilder
    {      
        
    }
}