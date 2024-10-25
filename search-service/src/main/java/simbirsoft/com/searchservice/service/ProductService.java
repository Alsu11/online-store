package simbirsoft.com.searchservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import simbirsoft.com.searchservice.domain.Category;
import simbirsoft.com.searchservice.domain.Product;
import simbirsoft.com.searchservice.repository.ProductRepository;
import simbirsoft.com.searchservice.repository.ProductSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductByFilter(Long sellerId, Category category, String name, String description) {
        Specification<Product> productSpecification = ProductSpecification.build();

        if (sellerId != null) {
            productSpecification = productSpecification.and(ProductSpecification.findBySeller(sellerId));
        }

        if (category != null) {
            productSpecification = productSpecification.and(ProductSpecification.findByCategory(category));
        }

        if (name != null) {
            productSpecification = productSpecification.and(ProductSpecification.findByName(name));
        }

        if (description != null) {
            productSpecification = productSpecification.and(ProductSpecification.findByDescription(description));
        }

        return productRepository.findAll(productSpecification);
    }
}
