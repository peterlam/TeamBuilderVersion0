/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lam
 */
public class MyTeam implements Cloneable{
    public List<MyStudent> studentList;
    private int teamSize;
    protected int teamNumber;

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getTeamNumber() {
        return teamNumber;
    }
    protected double[] evaluationScore;
    
    //Constructor
    public MyTeam()
    {
        studentList = null;
        teamSize = 0;
        teamNumber = 0;
        evaluationScore = new double[100];//can only have 100 survey question       
    }
    
    
    public MyTeam(List<MyStudent> theStudentList, int size, int teamId)
    {
        studentList = theStudentList;
        teamSize = size;
        teamNumber = teamId;
        evaluationScore = new double[100];       
    }
            
    @Override
    public MyTeam clone() {
        try {
            return (MyTeam) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("MyTeam Clone Error");
        }
    } 
    
    public void setTeamScore(double score, int index)
    {
        //TODO: call some fitness algorithm to get a score 
        //using the student list
        this.evaluationScore[index] = score;
    }
    
    public double getTeamScore(int index)
    {
        return evaluationScore[index];
    }

    /**
     * @return the teamSize
     */
    public int getTeamSize() {
        return teamSize;
    }

    /**
     * @param teamSize the teamSize to set
     */
    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
    
    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(List<MyStudent> studentList) {
        this.studentList = new LinkedList<MyStudent>();
        for(int i=0; i<studentList.size(); i++)
        {
            MyStudent student = new MyStudent();
            student.setIndex(studentList.get(i).getIndex());
            student.setResponseList(studentList.get(i).getResponseList());
            student.setUsername(studentList.get(i).getUsername());
            this.studentList.add(student);
        }               
    }

    /**
     * setter for student in team with specific index
     * @param studentIndex
     * @param student 
     */
    public void set(int studentIndex, MyStudent student) {
        this.studentList.get(studentIndex).setIndex(student.getIndex());
        this.studentList.get(studentIndex).setResponseList(student.getResponseList());
        this.studentList.get(studentIndex).setUsername(student.getUsername());
    }
}
