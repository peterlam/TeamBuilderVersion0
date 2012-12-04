/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.builder;

import model.MyTeam;
import junit.framework.Assert;
import builder.AllCombinationTeamsBuilder;
import java.util.LinkedList;
import model.MyStudent;
import java.util.List;
import model.MyQuestion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lam
 */
public class AllCombinationTeamsBuilderTest {
    
    private TestableAllCombinationTeamsBuilder _testableAllCombinationTeamsBuilder 
            = new TestableAllCombinationTeamsBuilder();
    private BuilderTestHelper _builderTestHelper;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableAllCombinationTeamsBuilder = new TestableAllCombinationTeamsBuilder();
        _builderTestHelper = new BuilderTestHelper();
    }
    
    @After
    public void tearDown() {
        _testableAllCombinationTeamsBuilder = null;
        _builderTestHelper = null;
    }
    
    @Test
    public void buildTeams_TestTeamsListFilled(){
        //Test that teamsList contains teams
        final int numOfTeamCombinations = 720;
        final int numOfTeams = 2;
        final int numOfStudentPerTeam = 3;
        final int numOfMC = 3;
        
        List<MyStudent> studentList = _builderTestHelper.createStudentList (6, numOfMC);
        List<MyQuestion> questionList = BuilderTestHelper.getQuestionList(numOfMC, 0, 0, -5);
        List<List<MyTeam>> teamsList = _testableAllCombinationTeamsBuilder.buildTeamsCombinations(numOfTeams, studentList, questionList);

        Assert.assertFalse(teamsList.isEmpty());
        Assert.assertEquals(numOfTeamCombinations, teamsList.size());
        Assert.assertEquals(numOfTeams, teamsList.get(0).size());
        for (int i = 0; i<numOfTeamCombinations; i++)
        {Assert.assertEquals(numOfStudentPerTeam, teamsList.get(i).get(0).studentList.size());}
    }
    
    /**
     * Create a list of students base on given size
     * @param numberOfStudent
     * @return 
     */
    private List<MyStudent> createStudentList(int numberOfStudent) {
        List<MyStudent> studentList = new LinkedList<MyStudent>();

        for (int i = 1; i <= numberOfStudent; i++) {
            MyStudent student = new MyStudent();
            student.setIndex(i);
            student.setUsername("user" + i);
            studentList.add(student);
        }

        return studentList;
    }
    
    public class TestableAllCombinationTeamsBuilder extends AllCombinationTeamsBuilder
    {      
        
    }
}
