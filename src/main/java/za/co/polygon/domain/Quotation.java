
package za.co.polygon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quotations")
public class Quotation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "quotation_request_id")
    private QuotationRequest quotationRequest;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "quotation")
    private List<QuotationOption> quotationOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuotationRequest getQuotationRequest() {
        return quotationRequest;
    }

    public void setQuotationRequest(QuotationRequest quotationRequest) {
        this.quotationRequest = quotationRequest;
    }

    public List<QuotationOption> getQuotationOptions() {
        return quotationOptions;
    }

    public void setQuotationOptions(List<QuotationOption> quotationOptions) {
        this.quotationOptions = quotationOptions;
    }

    

    
    
}
