package simbirsoft.com.searchservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simbirsoft.com.searchservice.domain.Category;
import simbirsoft.com.searchservice.domain.Product;
import simbirsoft.com.searchservice.service.ProductService;

import java.util.List;


@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductByFilter(
            @RequestParam(value = "seller", required = false) Long sellerId,
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description) {
        return ResponseEntity.ok(
                productService.getProductByFilter(sellerId, category, name, description)
        );
    }
}
