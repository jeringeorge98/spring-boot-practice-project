package db;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_table", indexes = {
        @Index(name = "idx_productjpaentity", columnList = "productName")
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductJPAEntity {
    @Id
    private String productId;
    @NotNull
    private String productName;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal price;

}
