package za.co.polygon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "underwriter_employees")
public class UnderwriterEmployee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@OneToMany(mappedBy="underwriterEmployee",fetch=FetchType.EAGER)
	private List<ClientPolicy> policyDetails;
	
	@Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ClientPolicy> getPolicyDetails() {
		return policyDetails;
	}

	public void setPolicyDetails(List<ClientPolicy> policyDetails) {
		this.policyDetails = policyDetails;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
}
