package simbirsoft.mgu.ozon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import simbirsoft.mgu.ozon.domain.Category;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequestDto {
    private String name;
    private String description;
    private Integer price;
    private Category category;
    private List<String> fileIds;
}
