package za.co.polygon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "type_of_covers")
public class TypeOfCover {
    
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "ype_of_cover_values")
    private String typeOfCoverValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfCoverValues() {
        return typeOfCoverValues;
    }

    public void setTypeOfCoverValues(String typeOfCoverValues) {
        this.typeOfCoverValues = typeOfCoverValues;
    }

}
