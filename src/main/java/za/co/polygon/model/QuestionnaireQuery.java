
package za.co.polygon.model;

import java.util.List;



public class QuestionnaireQuery {
    
    private Long id;
    private Long sequenceNumber;
    private String question;
    private String answerType;
    private Long dependsOn;
    private String onAnswer;
    private List<String> answerValues ;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public Long getDependsOn() {
		return dependsOn;
	}
	public void setDependsOn(Long dependsOn) {
		this.dependsOn = dependsOn;
	}
	
	public String getOnAnswer() {
		return onAnswer;
	}
	public void setOnAnswer(String onAnswer) {
		this.onAnswer = onAnswer;
	}
	public List<String> getAnswerValues() {
		return answerValues;
	}
	public void setAnswerValues(List<String> answerValues) {
		this.answerValues = answerValues;
	}
    
    

        
}
