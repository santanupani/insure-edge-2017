package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaires")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sequencenumber")
    private Long sequenceNumber;
    @Column(name = "dependson")
    private Long dependsOn;
    @Column(name = "ifanswer")
    private String ifAnswer;
    @ManyToOne
    @JoinColumn(name = "productid")
    Product product;
    @ManyToOne
    @JoinColumn(name = "answertypeid")
    AnswerType answertype;
    @ManyToOne
    @JoinColumn(name = "questionid")
    AnswerValue answervalue;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AnswerType getAnswertype() {
        return answertype;
    }

    public void setAnswertype(AnswerType answertype) {
        this.answertype = answertype;
    }

    public AnswerValue getAnswervalue() {
        return answervalue;
    }

    public void setAnswervalue(AnswerValue answervalue) {
        this.answervalue = answervalue;
    }
    
    

}
