package za.co.polygon.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "policy_schedules")
public class PolicySchedule {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   
    @OneToMany(mappedBy="policySchedule",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<IndemnityOption> indemnityOptions;
    
    @OneToOne
    @JoinColumn(name="policy_id")
    private Policy policy;
    
    @Column(name = "sum_insured")
    private double sumInsured;
    
    @Column(name = "maximum_sum_insured")
    private double maximumSumInsured;
    
    @Column(name = "broker_commission")
    private double brokerCommission;
    
    @Column(name = "UA_commission")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IndemnityOption> getIndemnityOptions() {
        return indemnityOptions;
    }

    public void setIndemnityOptions(List<IndemnityOption> indemnityOptions) {
        this.indemnityOptions = indemnityOptions;
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

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
   
   
  
}