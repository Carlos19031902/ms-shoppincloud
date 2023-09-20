package academy.digitallap.shoppingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import academy.digitallap.shoppingservice.clientmodel.Custumer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_invoices")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_invoice")
    private String numberInvoice;
    @Column(name = "customer_id")
    private String customerId;

    private String description;

    @Column(name = "create_at")
    private Date createAt;

    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    private String state;
    
    @Transient
    private Custumer customer;
    
    

    public Invoice(){
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
        System.out.print(this.createAt.toString());
    }
}
