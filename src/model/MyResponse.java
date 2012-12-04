/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lam
 */
public class MyResponse implements Cloneable
{
    protected String qId;
    protected String type;
    protected int[] answer;
    protected int totalChoicesCount; //for CAO

    public MyResponse()
    {
        //Note: each CAO type question should not have more than 200 choices        
        answer = new int[200];
    }
    
    public MyResponse(String id, String type, int[] theAnswer )
    {
        qId = id;
        this.type = type;
        answer = theAnswer;
    }
    
    @Override
    public MyResponse clone() {
        try {
            return (MyResponse) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("MyResponse Clone Error");
        }
    }    
    
    public String getqId() {
        return qId;
    }

    public void setqId(String value) {
        this.qId = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public int getAnswer(int index) {
        return answer[index];
    }

    public int[] getAnswers() {
        return answer;
    }
    
    public void setAnswer(int value, int index) {
        this.answer[index] = value;
    }   
    
    public void setAnswers(int[] value) {
        this.answer = value.clone();        
    }
    
    public int getTotalChoices() {
        return totalChoicesCount;
    }
    
    public void setTotalChoices(int count) {
        this.totalChoicesCount = count;
    }
}
