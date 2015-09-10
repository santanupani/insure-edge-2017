package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "excess_structures")
public class ExcessStructure {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "excess_structure_values")
    private String excessStructureValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExcessStructureValues() {
        return excessStructureValues;
    }

    public void setExcessStructureValues(String excessStructureValues) {
        this.excessStructureValues = excessStructureValues;
    }     

}
