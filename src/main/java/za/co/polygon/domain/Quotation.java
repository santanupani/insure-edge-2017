
package za.co.polygon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

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

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
        if(quotationOptions==null) quotationOptions = new ArrayList<QuotationOption>();
        return quotationOptions;
    }

    public void setQuotationOptions(List<QuotationOption> quotationOptions) {
        this.quotationOptions = quotationOptions;
    }
}
