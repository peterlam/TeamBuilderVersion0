/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.manager;

import java.util.LinkedList;
import java.util.List;
import manager.TeamBuildingHelper;
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
public class TeamBuildingHelperTest {
    
    private BuilderTestHelper _builderTestHelper;
    
    public TeamBuildingHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _builderTestHelper = new BuilderTestHelper();
    }
    
    @After
    public void tearDown() {
        _builderTestHelper = null;
    }

    @Test
    public void buildTeamsListBaseOnType_returnPopulatedTeamsSubList() 
    {
        //Test if teams sub-lists get populated accordingly
        List<MyStudent> studentList = _builderTestHelper.createStudentList (6, 2);
        List<MyTeam> teamsList = _builderTestHelper.createTeamList(studentList, 2);
        List<MyTeam> mcTeamsList = new LinkedList<MyTeam>();
        List<MyTeam> caoTeamsList = new LinkedList<MyTeam>();
        List<MyTeam> scaleTeamsList = new LinkedList<MyTeam>();
        TestableTeamBuildingHelper.buildTeamsListBaseOnType(teamsList, mcTeamsList, caoTeamsList, scaleTeamsList);
        Assert.assertNotNull(mcTeamsList);
        Assert.assertEquals(2, mcTeamsList.size());
        Assert.assertEquals(2, caoTeamsList.size());
        Assert.assertEquals(2, scaleTeamsList.size());
        Assert.assertEquals(2, mcTeamsList.get(0).studentList.get(0).getResponseList().size());
        Assert.assertEquals(0, caoTeamsList.get(0).studentList.get(0).getResponseList().size());
        Assert.assertEquals(0, scaleTeamsList.get(0).studentList.get(0).getResponseList().size());
    }
    
        @Test
    public void buildQuestionsListBaseOnType_returnPopulatedQuestionsSubList() 
    {
        //Test if questions sub-lists get populated accordingly
        List<MyQuestion> questionsList = BuilderTestHelper.getQuestionList(1, 1, 1, 5);
        List<MyQuestion> mcQuestionsList = new LinkedList<MyQuestion>();
        List<MyQuestion> caoQuestionsList = new LinkedList<MyQuestion>();
        List<MyQuestion> scaleQuestionsList = new LinkedList<MyQuestion>();
        TestableTeamBuildingHelper.buildQuestionsListBaseOnType(questionsList, mcQuestionsList, caoQuestionsList, scaleQuestionsList);
        Assert.assertEquals(1, mcQuestionsList.size());
        Assert.assertEquals(1, caoQuestionsList.size());
        Assert.assertEquals(1, scaleQuestionsList.size());
    }
    
    public class TestableTeamBuildingHelper extends TeamBuildingHelper
    {      
        
    }
    
}
