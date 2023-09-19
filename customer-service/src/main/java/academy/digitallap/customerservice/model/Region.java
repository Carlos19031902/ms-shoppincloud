package academy.digitallap.customerservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_region")
@Data
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
