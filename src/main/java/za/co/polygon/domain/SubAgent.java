package za.co.polygon.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "sub_agents")
public class SubAgent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@OneToMany(mappedBy="subAgent",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ClientPolicy> policyMaster;
	
	@ManyToOne
	@JoinColumn(name = "underwriter_id")
    private Underwritter underwriter;
    
    @Column(name = "name")
    private Date name;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ClientPolicy> getPolicyMaster() {
		return policyMaster;
	}

	public void setPolicyMaster(List<ClientPolicy> policyMaster) {
		this.policyMaster = policyMaster;
	}

	public Underwritter getUnderwriter() {
		return underwriter;
	}

	public void setUnderwriter(Underwritter underwriter) {
		this.underwriter = underwriter;
	}

	public Date getName() {
		return name;
	}

	public void setName(Date name) {
		this.name = name;
	}
    
}
