package academy.digitallap.shoppingservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_invoice_item")
@Data
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "El stock debe ser mayor a cero")
    private Double quantity;

    private Double price;
    @Column(name = "product_id")
    private Long ProductId;

    @Transient
    private double subTotal;
    public Double getSubtotal(){
        if(this.price>0 && this.quantity>0){
            return this.quantity*this.price;
        }
        else {
            return (double)0;
        }
    }

    public  InvoiceItem(){
        this.quantity=(double)0;
        this.price=(double)0;
    }


}
