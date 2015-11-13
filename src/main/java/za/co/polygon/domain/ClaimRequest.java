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
@Table(name = "claim_requests")
public class ClaimRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "policy_id")
	private Policy policy;

	@ManyToOne
	@JoinColumn(name = "claim_type_id")
	private ClaimType claimType;

	@OneToMany(mappedBy = "claimRequest", fetch = FetchType.EAGER)
	private List<ClaimAnswer> claimAnswers;


	@Column(name = "claim_number")
	private String claimNumber;

	@Column(name = "status")
	private String status;

	@Column(name = "investigation_report")
	private byte[] investigationReport;

	@Column(name = "confirmation_amount")
	private byte[] comfirmationAmount;

	@Column(name = "proof_of_pickup")
	private byte[] proofOfPickup;

	@Column(name = "case_number")
	private byte[] caseNumber;

	@Column(name = "amount_banked")
	private byte[] amountBanked;

	@Column(name = "trans_track_document")
	private byte[] transTrackDocument;

	@Column(name = "quote")
	private byte[] quote;

	@Column(name = "report")
	private byte[] report;

	@Column(name = "affidavit")
	private byte[] affidavit;

	@Column(name = "photo1")
	private byte[] photo1;

	@Column(name = "photo2")
	private byte[] photo2;

	@Column(name = "photo3")
	private byte[] photo3;

	@Column(name = "photo4")
	private byte[] photo4;

	@Column(name = "photo5")
	private byte[] photo5;

	@Column(name = "create_date")
	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public byte[] getInvestigationReport() {
		return investigationReport;
	}

	public void setInvestigationReport(byte[] investigationReport) {
		this.investigationReport = investigationReport;
	}

	public byte[] getComfirmationAmount() {
		return comfirmationAmount;
	}

	public void setComfirmationAmount(byte[] comfirmationAmount) {
		this.comfirmationAmount = comfirmationAmount;
	}

	public byte[] getProofOfPickup() {
		return proofOfPickup;
	}

	public void setProofOfPickup(byte[] proofOfPickup) {
		this.proofOfPickup = proofOfPickup;
	}

	public byte[] getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(byte[] caseNumber) {
		this.caseNumber = caseNumber;
	}

	public byte[] getAmountBanked() {
		return amountBanked;
	}

	public void setAmountBanked(byte[] amountBanked) {
		this.amountBanked = amountBanked;
	}

	public byte[] getTransTrackDocument() {
		return transTrackDocument;
	}

	public void setTransTrackDocument(byte[] transTrackDocument) {
		this.transTrackDocument = transTrackDocument;
	}

	public byte[] getQuote() {
		return quote;
	}

	public void setQuote(byte[] quote) {
		this.quote = quote;
	}

	public byte[] getReport() {
		return report;
	}

	public void setReport(byte[] report) {
		this.report = report;
	}

	public byte[] getAffidavit() {
		return affidavit;
	}

	public void setAffidavit(byte[] affidavit) {
		this.affidavit = affidavit;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}

	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}

	public byte[] getPhoto3() {
		return photo3;
	}

	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}

	public byte[] getPhoto4() {
		return photo4;
	}

	public void setPhoto4(byte[] photo4) {
		this.photo4 = photo4;
	}

	public byte[] getPhoto5() {
		return photo5;
	}

	public void setPhoto5(byte[] photo5) {
		this.photo5 = photo5;
	}



	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
	}


	public List<ClaimAnswer> getClaimAnswer() {
		if(claimAnswers == null)
			return new ArrayList<ClaimAnswer>();
		else
			return claimAnswers;
	}



	public void setClaimAnswer(List<ClaimAnswer> claimRequestQuestionnaire) {
		this.claimAnswers = claimRequestQuestionnaire;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}








}
