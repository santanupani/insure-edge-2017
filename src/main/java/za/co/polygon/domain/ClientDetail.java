package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_details")
public class ClientDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_no")
    private String clientNumber;
    
    @Column(name = "policy_number")
    private String PolicyNumber;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "ap_code")
    private String apCode;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "code")
    private String code;
    
    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "work_tel_number")
    private String workTelNumber;

    @Column(name = "home_tel_number")
    private String homeTelNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "cell_number")
    private String cellNumber;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "branch")
    private String branchCode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "reg_no")
    private String regNumber;

    @Column(name = "income_tax_number")
    private String incomeTaxNumber;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_person")
    private String contactPerson;
    
    @Column(name = "pref_comm")
    private String prefComm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getApCode() {
        return apCode;
    }

    public void setApCode(String apCode) {
        this.apCode = apCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getWorkTelNumber() {
        return workTelNumber;
    }

    public void setWorkTelNumber(String workTelNumber) {
        this.workTelNumber = workTelNumber;
    }

    public String getHomeTelNumber() {
        return homeTelNumber;
    }

    public void setHomeTelNumber(String homeTelNumber) {
        this.homeTelNumber = homeTelNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getIncomeTaxNumber() {
        return incomeTaxNumber;
    }

    public void setIncomeTaxNumber(String incomeTaxNumber) {
        this.incomeTaxNumber = incomeTaxNumber;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_person() {
        return contactPerson;
    }

    public void setContact_person(String contact_person) {
        this.contactPerson = contact_person;
    }

    public String getPrefComm() {
        return prefComm;
    }

    public void setPrefComm(String prefComm) {
        this.prefComm = prefComm;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String PolicyNumber) {
        this.PolicyNumber = PolicyNumber;
    }
    
  

}
