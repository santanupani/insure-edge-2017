package za.co.polygon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "underwriters")
public class Underwritter {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	
	@OneToOne
	@JoinColumn(name="insurer_id")
	private Insurer insurer;
	
	@Column(name = "name")
    private Date name;
    
    @Column(name = "commission_rate")
    private int commission_rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Insurer getInsurer() {
		return insurer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public Date getName() {
		return name;
	}

	public void setName(Date name) {
		this.name = name;
	}

	public int getCommission_rate() {
		return commission_rate;
	}

	public void setCommission_rate(int commission_rate) {
		this.commission_rate = commission_rate;
	}
}
