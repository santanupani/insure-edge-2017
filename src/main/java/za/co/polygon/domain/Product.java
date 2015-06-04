
package za.co.polygon.domain;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product {
    
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="product_id_seq", sequenceName="product_id_seq")
	@Column(name="product_id")
	private Long product_id;
	@Column(name="product_name")
	private String productname;
	@Column(name="product_desc")
	private String productDescription;
	//@Column(name="img")
	//private Blob image;

    

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /*public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }*/

    

    

    
        
        
        
        
    
}
