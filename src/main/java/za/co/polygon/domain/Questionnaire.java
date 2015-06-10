
package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaires")
public class Questionnaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "sequence_number")
    private Long sequenceNumber;
    @Column (name="answer_type_id")
    private Long answerTypeId;
    @Column (name="depends_on")
    private Long dependsOn;
    @Column (name="if_answer")
    private String ifAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Long getAnswerTypeId() {
        return answerTypeId;
    }

    public void setAnswerTypeId(Long answerTypeId) {
        this.answerTypeId = answerTypeId;
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
