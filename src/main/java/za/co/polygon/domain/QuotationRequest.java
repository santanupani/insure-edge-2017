
package za.co.polygon.domain;


import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quotation_requests")
public class QuotationRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "referrence")
    private Long referrence;
    
    @Column(name = "applicant_name")
    private String applicantName;
    
    @Column(name = "applicant_email")
    private String applicantEmail;
    
    @Column(name = "broker_id")
    private Long brokerId;
    
    @Column(name = "created_on")
    private Date date;
    
    @Column(name = "status")
    private String status;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "quotationRequest")
    private List<QuotationRequestQuestionnaires> quotationRequestQuestionnaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReferrence() {
        return referrence;
    }

    public void setReferrence(Long referrence) {
        this.referrence = referrence;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<QuotationRequestQuestionnaires> getQuotationRequestQuestionnaire() {
        return quotationRequestQuestionnaire;
    }

    public void setQuotationRequestQuestionnaire(List<QuotationRequestQuestionnaires> quotationRequestQuestionnaire) {
        this.quotationRequestQuestionnaire = quotationRequestQuestionnaire;
    }

    
      
}
