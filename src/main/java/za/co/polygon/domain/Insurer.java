package za.co.polygon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "insurers")
public class Insurer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "name")
    private Date name;
    
    @Column(name = "business_description")
    private int business_description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getName() {
		return name;
	}

	public void setName(Date name) {
		this.name = name;
	}

	public int getBusiness_description() {
		return business_description;
	}

	public void setBusiness_description(int business_description) {
		this.business_description = business_description;
	}

}
