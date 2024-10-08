package simbirsoft.mgu.ozon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import simbirsoft.mgu.ozon.domain.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Category category;
    private String sellerFirstName;
    private String sellerLastName;
}
