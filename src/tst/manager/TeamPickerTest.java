/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.manager;

import java.util.List;
import manager.TeamPicker;
import model.MyQuestion;
import model.MyStudent;
import model.MyTeam;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tst.builder.BuilderTestHelper;

/**
 *
 * @author Lam
 */
public class TeamPickerTest {
    private TestableTeamPicker _testableTeamPicker;
    private BuilderTestHelper _builderTestHelper;
    
    public TeamPickerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableTeamPicker = new TestableTeamPicker();
        _builderTestHelper = new BuilderTestHelper();
    }
    
    @After
    public void tearDown() {
        _testableTeamPicker = null;
        _builderTestHelper = null;
    }

    @Test
    public void bestTeamPicker_returnATeamCombination() 
    {
        //Test if teamsList is not empty
        final int numOfMC = 3;
        final int numOfTeams = 2;
        final int numOfStudents = 6;
        
        List<MyStudent> studentList = _builderTestHelper.createStudentList (numOfStudents, numOfMC);
        List<MyQuestion> questionsList = BuilderTestHelper.getQuestionList(numOfMC, 0, 0, -5);
        List<MyTeam> result = _testableTeamPicker.bestTeamPicker(numOfTeams, studentList, questionsList, 1);
        Assert.assertNotNull(result);
        Assert.assertEquals(numOfTeams, result.size()); //two teams
        Assert.assertEquals(numOfStudents/numOfTeams, result.get(0).studentList.size()); //3 students per team
    }
    
    public class TestableTeamPicker extends TeamPicker
    {      
        
    }    
}
