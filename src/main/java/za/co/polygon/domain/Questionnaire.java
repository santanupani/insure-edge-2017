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

}
