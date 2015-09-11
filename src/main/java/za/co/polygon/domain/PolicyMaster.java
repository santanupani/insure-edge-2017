package za.co.polygon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "policy_masters")
public class PolicyMaster {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "reference_no")
    private Long reference_no;
	
	@Column(name = "int_policy_no")
    private String int_policy_no;
	
    @OneToOne
    @JoinColumn(name = "policy_request_id")
    private PolicyRequest policyRequest;
    
    @OneToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;
    
    @OneToOne
    @JoinColumn(name = "client_master_id")
    private ClientDetail clientMasterData;
    
    @ManyToOne
    @JoinColumn(name = "sub_agent_id")
    private SubAgent subAgent;
    
    @ManyToOne
    @JoinColumn(name = "insurer_id")
    private Insurer insurer;
    
    @Column(name = "policy_inception_date")
    private Date policy_inception_date;
    
    @Column(name = "inception_date")
    private Date inception_date;
    
    @Column(name = "renewal_date")
    private Date renewal_date;
    
    @Column(name = "underwriting_year")
    private int underwriting_year;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "frequency")
    private String frequency;
    
    @Column(name = "sasria_frequency")
    private String sasria_frequency;
    
    @Column(name = "device")
    private Long device;
    
    @Column(name = "retroactive_date")
    private Date retroactive_date;
    
    @Column(name = "approved")
    private boolean approved;
    
    @Column(name = "collect_by_debit_order")
    private boolean collect_by_debit_order;
    
    @Column(name = "exclude_sasria")
    private boolean exclude_sasria;
    
    @Column(name = "underwriter_policy_fee")
    private double underwriter_policy_fee;
    
    @Column(name = "broker_policy_fee")
    private double broker_policy_fee;
    
    @Column(name = "notes")
    private String notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReference_no() {
		return reference_no;
	}

	public void setReference_no(Long reference_no) {
		this.reference_no = reference_no;
	}

	public String getInt_policy_no() {
		return int_policy_no;
	}

	public void setInt_policy_no(String int_policy_no) {
		this.int_policy_no = int_policy_no;
	}

	public PolicyRequest getPolicyRequest() {
		return policyRequest;
	}

	public void setPolicyRequest(PolicyRequest policyRequest) {
		this.policyRequest = policyRequest;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public SubAgent getSubAgent() {
		return subAgent;
	}

	public void setSubAgent(SubAgent subAgent) {
		this.subAgent = subAgent;
	}

	public Insurer getInsurer() {
		return insurer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public Date getPolicy_inception_date() {
		return policy_inception_date;
	}

	public void setPolicy_inception_date(Date policy_inception_date) {
		this.policy_inception_date = policy_inception_date;
	}

	public Date getInception_date() {
		return inception_date;
	}

	public void setInception_date(Date inception_date) {
		this.inception_date = inception_date;
	}

	public Date getRenewal_date() {
		return renewal_date;
	}

	public void setRenewal_date(Date renewal_date) {
		this.renewal_date = renewal_date;
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

	public String getSasria_frequency() {
		return sasria_frequency;
	}

	public void setSasria_frequency(String sasria_frequency) {
		this.sasria_frequency = sasria_frequency;
	}

	public Long getDevice() {
		return device;
	}

	public void setDevice(Long device) {
		this.device = device;
	}

	public Date getRetroactive_date() {
		return retroactive_date;
	}

	public void setRetroactive_date(Date retroactive_date) {
		this.retroactive_date = retroactive_date;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isCollect_by_debit_order() {
		return collect_by_debit_order;
	}

	public void setCollect_by_debit_order(boolean collect_by_debit_order) {
		this.collect_by_debit_order = collect_by_debit_order;
	}

	public boolean isExclude_sasria() {
		return exclude_sasria;
	}

	public void setExclude_sasria(boolean exclude_sasria) {
		this.exclude_sasria = exclude_sasria;
	}

	public double getUnderwriter_policy_fee() {
		return underwriter_policy_fee;
	}

	public void setUnderwriter_policy_fee(double underwriter_policy_fee) {
		this.underwriter_policy_fee = underwriter_policy_fee;
	}

	public double getBroker_policy_fee() {
		return broker_policy_fee;
	}

	public void setBroker_policy_fee(double broker_policy_fee) {
		this.broker_policy_fee = broker_policy_fee;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
