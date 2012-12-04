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
public class MyStudent implements Cloneable
{

    private String username;
    private int index;
    private List<MyResponse> responseList;
    
    @Override
    public MyStudent clone() {
        try {
            return (MyStudent) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("MyStudent Clone Error");
        }
    } 
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the responseList
     */
    public List<MyResponse> getResponseList() {
        return responseList;
    }

    /**
     * @param responseList the responseList to set
     */
    public void setResponseList(List<MyResponse> responseList) {
        this.responseList = new LinkedList<MyResponse>();
        
        for(int i = 0; i<responseList.size(); i++)
        {
            MyResponse response = new MyResponse();
            
            response.setqId(responseList.get(i).getqId());
            response.setType(responseList.get(i).getType());
            response.setAnswers(responseList.get(i).getAnswers());
            response.setTotalChoices(responseList.get(i).getTotalChoices());
            this.responseList.add(response);
        }        
    }

           
}
