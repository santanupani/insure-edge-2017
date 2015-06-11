
package za.co.polygon.model;

import javax.persistence.Column;


public class Questionnaire{
    

    @Column(name = "id")
    private Long id;
    @Column(name = "productid")
    private Long productid;
    @Column(name = "sequencenumber")
    private Long sequenceNumber;
     @Column(name = "questionid")
    private Long questionid;
     @Column(name = "answertypeid")
    private String answertypeid;
    @Column(name = "dependson")
    private Long dependsOn;
    @Column(name = "ifanswer")
    private String ifAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }

    public String getAnswertypeid() {
        return answertypeid;
    }

    public void setAnswertypeid(String answertypeid) {
        this.answertypeid = answertypeid;
    }

    public Long getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Long dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getIfAnswer() {
        return ifAnswer;
    }

    public void setIfAnswer(String ifAnswer) {
        this.ifAnswer = ifAnswer;
    }
    
   
    
    
    
}
