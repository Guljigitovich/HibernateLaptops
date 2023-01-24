package model;

import enums.OperationSystem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter

public class Laptop {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
                generator = "laptop_id_generator "
  )
     @SequenceGenerator(
             name = "laptop_id_generator",
              sequenceName ="laptop_seq",
             allocationSize =1
     )
    private Long id ;
    private String brand;
    @Column(name = "operation_system")
    @Enumerated(EnumType.STRING)
    private OperationSystem operationSystem;
    private int memory;
    private int price;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    public Laptop(String brand, OperationSystem operationSystem, int memory, int price, LocalDate dateOfIssue) {
        this.brand = brand;
        this.operationSystem = operationSystem;
        this.memory = memory;
        this.price = price;
        this.dateOfIssue = dateOfIssue;
    }
}
