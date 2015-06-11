package za.co.polygon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "answervalues")
public class AnswerValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "question")
    private String question;
    @Column(name = "answervalue")
    private String answervalue;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "answervalue")
    private List<Questionnaire> questionnaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswervalue() {
        return answervalue;
    }

    public void setAnswervalue(String answervalue) {
        this.answervalue = answervalue;
    }

    public List<Questionnaire> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<Questionnaire> questionnaire) {
        this.questionnaire = questionnaire;
    }
    
    
}
