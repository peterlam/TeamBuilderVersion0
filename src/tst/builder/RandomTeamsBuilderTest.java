/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.builder;

import builder.RandomTeamsBuilder;
import java.util.LinkedList;
import java.util.List;
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
public class RandomTeamsBuilderTest{
    
    private TestableRandomTeamsBuilder _testableRandomTeamsBuilder;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableRandomTeamsBuilder = new TestableRandomTeamsBuilder();
    }
    
    @After
    public void tearDown() {
        _testableRandomTeamsBuilder = null;
    }
    
    @Test
    public void buildTeams_TestTeamsListFilled(){
        //Test that teamsList contains teams
        final int numOfTeams = 2;
        
        List<MyStudent> studentList = createStudentList (6);
        List<MyTeam> teamsList = _testableRandomTeamsBuilder.buildTeams(numOfTeams, studentList);
        Assert.assertFalse(teamsList.isEmpty());
        Assert.assertEquals(numOfTeams, teamsList.size());
        Assert.assertEquals(6/numOfTeams, teamsList.get(0).studentList.size());
        Assert.assertEquals(6/numOfTeams, teamsList.get(1).studentList.size());
    }
    
    /**
     * Create a list of students base on given size
     * @param numberOfStudent
     * @return 
     */
    private List<MyStudent> createStudentList (int numberOfStudent)
    {
        List<MyStudent> studentList = new LinkedList<MyStudent>();
        
        for(int i=1; i<=numberOfStudent; i++)
        {
            MyStudent student = new MyStudent();
            student.setIndex(i);
            student.setUsername("user" + i);
            studentList.add(student);
        }
        
        return studentList;
    }
       
    public class TestableRandomTeamsBuilder extends RandomTeamsBuilder
    {      
        
    }
}
