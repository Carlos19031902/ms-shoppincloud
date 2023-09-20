package academy.digitallap.customerservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_customers")
@Data

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 8,max = 8,message = "La longitud debe ser de 8")
    @Column(name="number_id", unique = true,length = 8,nullable = false)
    private String numberID;
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name="first_name", nullable = false)
    private String firstName;
    @NotEmpty(message = "El apellido no puede ser vacio")
    @Column(name="last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "El email no puede ser vacio")
    @Email(message = "El emmail no es valido")
    @Column(unique = true,nullable = false)
    private String email;
    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "la region no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;
    private String state;

}
