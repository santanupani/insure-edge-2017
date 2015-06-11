
package za.co.polygon.DTO;

import za.co.polygon.model.Questionnaire;


public class QuestionnaireDTO {
    
    private Long id;
    private Long productid;
    private Long sequenceNumber;
    private Long questionid;
    private String answertypeid;
    private Long dependsOn;
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
    
    public QuestionnaireDTO toQuestionnaire(Questionnaire questionnaire){
        
        QuestionnaireDTO questionnaireDTO = new QuestionnaireDTO();
        questionnaireDTO.setId(questionnaire.getId());
        questionnaireDTO.setProductid(questionnaire.getProductid());
        questionnaireDTO.setSequenceNumber(questionnaire.getSequenceNumber());
        questionnaireDTO.setQuestionid(questionnaire.getQuestionid());
        questionnaireDTO.setDependsOn(questionnaire.getDependsOn());
        questionnaireDTO.setIfAnswer(questionnaire.getIfAnswer());
        return questionnaireDTO;
    }
    
}
