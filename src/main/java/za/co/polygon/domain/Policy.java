package za.co.polygon.domain;

import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "underwriter_id")
    private Underwriter underwriter;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "sub_agent_id")
    private SubAgent subAgent;
    
    @OneToOne(mappedBy="policy",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PolicySchedule policySchedule;

    @Column(name = "inception_date")
    private Date inceptionDate;

    @Column(name = "renewalDate")
    private Date renewalDate;

    @Column(name = "underwriting_year")
    private int underwriting_year;

    @Column(name = "status")
    private String status;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "sasria_frequency")
    private String sasriaFrequency;

    @Column(name = "re_instatement")
    private String reInstatement;

    @Column(name = "device")
    private String device;

    @Column(name = "retro_active_date")
    private Date retroactiveDate;

    @Column(name = "collect_by_debit_order")
    private boolean collectByDebitOrder;

    @Column(name = "exclude_sasria")
    private boolean exclude_sasria;

    @Column(name = "underwriter_fee")
    private double underwriterFee;

    @Column(name = "broker_fee")
    private double brokerFee;

    @Column(name = "notes")
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Underwriter getUnderwriter() {
        return underwriter;
    }

    public void setUnderwriter(Underwriter underwriter) {
        this.underwriter = underwriter;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SubAgent getSubAgent() {
        return subAgent;
    }

    public void setSubAgent(SubAgent subAgent) {
        this.subAgent = subAgent;
    }

    public Date getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public int getUnderwriting_year() {
        return underwriting_year;
    }

    public void setUnderwriting_year(int underwriting_year) {
        this.underwriting_year = underwriting_year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSasriaFrequency() {
        return sasriaFrequency;
    }

    public void setSasriaFrequency(String sasriaFrequency) {
        this.sasriaFrequency = sasriaFrequency;
    }

    public String getReInstatement() {
        return reInstatement;
    }

    public void setReInstatement(String reInstatement) {
        this.reInstatement = reInstatement;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getRetroactiveDate() {
        return retroactiveDate;
    }

    public void setRetroactiveDate(Date retroactiveDate) {
        this.retroactiveDate = retroactiveDate;
    }

    public boolean isCollectByDebitOrder() {
        return collectByDebitOrder;
    }

    public void setCollectByDebitOrder(boolean collectByDebitOrder) {
        this.collectByDebitOrder = collectByDebitOrder;
    }

    public boolean isExclude_sasria() {
        return exclude_sasria;
    }

    public void setExclude_sasria(boolean exclude_sasria) {
        this.exclude_sasria = exclude_sasria;
    }

    public double getUnderwriterFee() {
        return underwriterFee;
    }

    public void setUnderwriterFee(double underwriterFee) {
        this.underwriterFee = underwriterFee;
    }

    public double getBrokerFee() {
        return brokerFee;
    }

    public void setBrokerFee(double brokerFee) {
        this.brokerFee = brokerFee;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PolicySchedule getPolicySchedule() {
        return policySchedule;
    }

    public void setPolicySchedule(PolicySchedule policySchedule) {
        this.policySchedule = policySchedule;
    }
    
}
