
package za.co.polygon.service.command;


/**
 *
 * @author User
 */
public class ProductQuery {
    
        private Long product_id;
	private String productname;
	private String productDescription;
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
