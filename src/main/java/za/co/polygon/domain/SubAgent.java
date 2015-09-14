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
	private List<PolicyDetail> policyMaster;
	
	@ManyToOne
	@JoinColumn(name = "underwriter_id")
    private Underwritter underwriter;
    
    @Column(name = "name")
    private Date name;
    
    @Column(name = "title")
    private int title;
    
    @Column(name = "job_description")
    private String job_description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PolicyDetail> getPolicyMaster() {
		return policyMaster;
	}

	public void setPolicyMaster(List<PolicyDetail> policyMaster) {
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

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
    
}
