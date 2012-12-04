/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.MyQuestion;
import model.MyTeam;
import model.MyStudent;
import org.netbeans.j2ee.wsdl.teambuilder.TeamInput;
import output.OutputResult;

/**
 *
 * @author Lam
 */
public class TeamBuildingManager {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        //TODO: get the building mode from args
        int builderMode = 1;
        
        TeamInput teamInput;
        try {
            teamInput = testParseEntireDocument();
        } catch (JAXBException ex) {
            Logger.getLogger(TeamBuildingManager.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }        
        
        //Get the list of student from the XML parser
        List<MyStudent> studentList = InputProcessing.buildStudentList(teamInput);   
        List<MyQuestion> questionList = InputProcessing.buildQuestionList(teamInput);
        
        //Find the best fit team
        int numberOfGroups = Integer.parseInt(teamInput.getNumGroups());        
        TeamPicker teamPicker = new TeamPicker(); 
        
        List<MyTeam> bestTeamsList = teamPicker.bestTeamPicker(numberOfGroups, studentList, questionList, builderMode);
           
        //TODO: output team formation base on bestTeamsList
        OutputResult output = new OutputResult();
        output.printResult(bestTeamsList, questionList);
    }
    
    /**
     * Parse XML File, and temporarily trigger team building
     * @throws JAXBException 
     */
    public static TeamInput testParseEntireDocument() throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{TeamInput.class});
        Unmarshaller um = ctx.createUnmarshaller();
        //TODO: pass in xml location from args
        TeamInput teamInput = (TeamInput) um.unmarshal(new File("SampleMConlySimilarSmallList.xml"));
        //TeamInput teamInput = (TeamInput) um.unmarshal(new File("Genetic.xml"));
        //TeamInput teamInput = (TeamInput) um.unmarshal(new File("PresentationExample.xml"));
        //TeamInput teamInput = (TeamInput) um.unmarshal(new File("CrazyLargeInputExample.xml"));
        //TeamInput teamInput = (TeamInput) um.unmarshal(new File("SampleScaleonlySimilar.xml"));
        //TeamInput teamInput = (TeamInput) um.unmarshal(new File("LargeStudentLargeQuestionExample.xml"));
        
        System.out.println("Total Students: " + teamInput.getStudent().size());
        System.out.println("Number of Groups: " + teamInput.getNumGroups());

        
        return teamInput;
    }
  
}
