package simbirsoft.com.searchservice.repository;

import org.springframework.data.jpa.domain.Specification;
import simbirsoft.com.searchservice.domain.Category;
import simbirsoft.com.searchservice.domain.Product;

public class ProductSpecification {

    public static Specification<Product> build() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction();
    }

    public static Specification<Product> findBySeller(Long sellerId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("owner").get("id"), sellerId);
    }

    public static Specification<Product> findByCategory(Category category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Product> findByName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> findByDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }
}
