package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pecial_policy_conditions")
public class SpecialPolicyCondition {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "special_policy_condition_values")
    private String specialPolicyConditionValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialPolicyConditionValues() {
        return specialPolicyConditionValues;
    }

    public void setSpecialPolicyConditionValues(String specialPolicyConditionValues) {
        this.specialPolicyConditionValues = specialPolicyConditionValues;
    }
       
}
