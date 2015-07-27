package za.co.polygon.domain;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "policy_requests")
public class PolicyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "policyRequest", fetch = FetchType.EAGER)
    private List<QuotationOption> quotationOptions;

    @JoinColumn(name = "quotation_id")
    private Quotation quotation;

    @Column(name = "company_reg_number")
    private String companyRegNumber;

    @Column(name = "vat_reg_number")
    private String vatRegNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "designation")
    private String designation;

    @Column(name = "buisness_desc")
    private String buisnessDesc;

    @Column(name = "representive")
    private String representive;

    @Column(name = "account_holder")
    private String accountHolder;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "acc_type")
    private String accType;

    @Column(name = "debit_order_date")
    private Date debitOrderDate;

    @Column(name = "bank_statement")
    private Blob bankStatement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public String getCompanyRegNumber() {
        return companyRegNumber;
    }

    public void setCompanyRegNumber(String companyRegNumber) {
        this.companyRegNumber = companyRegNumber;
    }

    public String getVatRegNumber() {
        return vatRegNumber;
    }

    public void setVatRegNumber(String vatRegNumber) {
        this.vatRegNumber = vatRegNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBuisnessDesc() {
        return buisnessDesc;
    }

    public void setBuisnessDesc(String buisnessDesc) {
        this.buisnessDesc = buisnessDesc;
    }

    public String getRepresentive() {
        return representive;
    }

    public void setRepresentive(String representive) {
        this.representive = representive;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Date getDebitOrderDate() {
        return debitOrderDate;
    }

    public void setDebitOrderDate(Date debitOrderDate) {
        this.debitOrderDate = debitOrderDate;
    }

    public Blob getBankStatement() {
        return bankStatement;
    }

    public void setBankStatement(Blob bankStatement) {
        this.bankStatement = bankStatement;
    }

    public List<QuotationOption> getQuotationOptions() {
        return quotationOptions;
    }

    public void setQuotationOptions(List<QuotationOption> quotationOptions) {
        this.quotationOptions = quotationOptions;
    }

}
