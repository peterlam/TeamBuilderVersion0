package model;

/**
 *
 * @author Lam
 */
public class MyQuestion {

    protected String id;
    protected String type;
    protected String title;
    protected int index;
    protected int diversity; //negative means heterogeneous, positive means homogeneous
    protected int questionWeight;
    
    public MyQuestion()
    {
        this.id = "";
        this.type = "";
        this.title = "";
        this.index = 0;
        this.diversity = 0;
        this.questionWeight = 0;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDiversity(int diversity) {
        this.diversity = diversity;
    }

    public void setQuestionWeight(int questionWeight) {
        this.questionWeight = questionWeight;
    }
    
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getDiversity() {
        return diversity;
    }

    public int getQuestionWeight() {
        return questionWeight;
    }

}
