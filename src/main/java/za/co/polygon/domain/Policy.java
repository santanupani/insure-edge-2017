package za.co.polygon.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "policy_inception_date")
    private Date policyInceptionDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "inception_date")
    private Date inceptionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "renewal_date")
    private Date renewalDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "anniversary_date")
    private Date anniversaryDate;
    
    @Column(name = "reference")
    private String reference;
    
    @Column(name = "product_name")
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "sub_agent_id")
    private SubAgent subAgent;
    
    @OneToMany(mappedBy="policy",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<IndemnityOption> indemnityOptions;

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

    @Column(name = "collect_by_debit_order")
    private boolean collectByDebitOrder;

    @Column(name = "exclude_sasria")
    private boolean exclude_sasria;

    @Column(name = "underwriter_fee")
    private double underwriterFee;

    @Column(name = "broker_fee")
    private double brokerFee;
    
    @Column(name = "sum_insured")
    private double sumInsured;
    
    @Column(name = "max_sum_insured")
    private double maximumSumInsured;
    
    @Column(name = "broker_commission")
    private double brokerCommission;
    
    @Column(name = "ua_commission")
    private double UACommission;
    
    @Column(name = "premium")
    private double premoium;
    
    @Column(name = "sasria_premium")
    private double sasriaPremium;
    
    @Column(name = "schedule_attaching")
    private String scheduleAttaching;
    
    @Column(name = "type_of_cover")
    private String typeOfCover;
    
    @Column(name = "subject_matter")
    private String subjectMatter;
    
   @Column(name = "excess_structure")
    private String excessSturcture;
   
   @Column(name = "special_condition")
    private String specialCondition;
   
   @Column(name = "conveyances")
    private String convenyances;

   @Column(name = "geographical_duration")
    private String geographicalDuration;

    @Column(name = "notes")
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getReference() {
		return reference;
	}
	
	public Date getPolicyInceptionDate() {
		return policyInceptionDate;
	}

	public void setPolicyInceptionDate(Date policyInceptionDate) {
		this.policyInceptionDate = policyInceptionDate;
	}

	public Date getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(Date anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

    public List<IndemnityOption> getIndemnityOptions() {
        return indemnityOptions;
    }

    public void setIndemnityOptions(List<IndemnityOption> indemnityOptions) {
        this.indemnityOptions = indemnityOptions;
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

    public double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public double getMaximumSumInsured() {
        return maximumSumInsured;
    }

    public void setMaximumSumInsured(double maximumSumInsured) {
        this.maximumSumInsured = maximumSumInsured;
    }

    public double getBrokerCommission() {
        return brokerCommission;
    }

    public void setBrokerCommission(double brokerCommission) {
        this.brokerCommission = brokerCommission;
    }

    public double getUACommission() {
        return UACommission;
    }

    public void setUACommission(double UACommission) {
        this.UACommission = UACommission;
    }

    public double getPremoium() {
        return premoium;
    }

    public void setPremoium(double premoium) {
        this.premoium = premoium;
    }

    public double getSasriaPremium() {
        return sasriaPremium;
    }

    public void setSasriaPremium(double sasriaPremium) {
        this.sasriaPremium = sasriaPremium;
    }

    public String getScheduleAttaching() {
        return scheduleAttaching;
    }

    public void setScheduleAttaching(String scheduleAttaching) {
        this.scheduleAttaching = scheduleAttaching;
    }

    public String getTypeOfCover() {
        return typeOfCover;
    }

    public void setTypeOfCover(String typeOfCover) {
        this.typeOfCover = typeOfCover;
    }

    public String getSubjectMatter() {
        return subjectMatter;
    }

    public void setSubjectMatter(String subjectMatter) {
        this.subjectMatter = subjectMatter;
    }

    public String getExcessSturcture() {
        return excessSturcture;
    }

    public void setExcessSturcture(String excessSturcture) {
        this.excessSturcture = excessSturcture;
    }

    public String getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }

    public String getConvenyances() {
        return convenyances;
    }

    public void setConvenyances(String convenyances) {
        this.convenyances = convenyances;
    }

    public String getGeographicalDuration() {
        return geographicalDuration;
    }

    public void setGeographicalDuration(String geographicalDuration) {
        this.geographicalDuration = geographicalDuration;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
