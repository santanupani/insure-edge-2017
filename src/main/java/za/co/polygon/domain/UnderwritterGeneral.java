package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "underwritter_generals")
public class UnderwritterGeneral {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_attaching_id")
    private ScheduleAttaching scheduleAttaching;
    
    @ManyToOne
    @JoinColumn(name = "type_of_cover_id")
    private TypeOfCover typeOfCover;
    
    @ManyToOne
    @JoinColumn(name = "subject_matter_id")
    private SubjectMatter subjectMatter;
    
    @ManyToOne
    @JoinColumn(name = "excess_structure_id")
    private ExcessStructure excessStructure;
    
    @ManyToOne
    @JoinColumn(name = "special_policy_condition_id")
    private SpecialPolicyCondition specialPolicyCondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScheduleAttaching getScheduleAttaching() {
        return scheduleAttaching;
    }

    public void setScheduleAttaching(ScheduleAttaching scheduleAttaching) {
        this.scheduleAttaching = scheduleAttaching;
    }

    public TypeOfCover getTypeOfCover() {
        return typeOfCover;
    }

    public void setTypeOfCover(TypeOfCover typeOfCover) {
        this.typeOfCover = typeOfCover;
    }

    public SubjectMatter getSubjectMatter() {
        return subjectMatter;
    }

    public void setSubjectMatter(SubjectMatter subjectMatter) {
        this.subjectMatter = subjectMatter;
    }

    public ExcessStructure getExcessStructure() {
        return excessStructure;
    }

    public void setExcessStructure(ExcessStructure excessStructure) {
        this.excessStructure = excessStructure;
    }

    public SpecialPolicyCondition getSpecialPolicyCondition() {
        return specialPolicyCondition;
    }

    public void setSpecialPolicyCondition(SpecialPolicyCondition specialPolicyCondition) {
        this.specialPolicyCondition = specialPolicyCondition;
    }
   
}
