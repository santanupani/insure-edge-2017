package za.co.polygon.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quotation_requests")
public class QuotationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "applicant_email")
    private String applicantEmail;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<LocationOption> locationOptions;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<History> history;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<Quotation> quotation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createdDate) {
        this.createDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Answer> getAnswers() {
        if (answers == null) {
            return new ArrayList<Answer>();
        } else {
            return answers;
        }
    }

    public void setAnswers(List<Answer> quotationRequestQuestionnaire) {
        this.answers = quotationRequestQuestionnaire;
    }

    public List<Quotation> getQuotation() {
        return quotation;
    }

    public void setQuotation(List<Quotation> quotation) {
        this.quotation = quotation;
    }

    public List<LocationOption> getLocationOptions() {
        return locationOptions;
    }

    public void setLocationOptions(List<LocationOption> locationOptions) {
        this.locationOptions = locationOptions;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
    

}
