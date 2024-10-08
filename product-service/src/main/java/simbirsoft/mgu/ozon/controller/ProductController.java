package simbirsoft.mgu.ozon.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirsoft.mgu.ozon.dto.ProductRequestDto;
import simbirsoft.mgu.ozon.dto.ProductResponseDto;
import simbirsoft.mgu.ozon.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product/{userId}")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@PathVariable("userId") Integer userId,
            @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.create(userId, productRequestDto));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProduct(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(productService.getAllProduct(userId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getOneProduct(@PathVariable("userId") Integer userId,
                                                            @PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(productService.getOneProduct(userId, productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("userId") Integer userId,
                                                            @PathVariable("productId") Integer productId,
                                                            @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(userId, productId, productRequestDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("userId") Integer userId,
                                                @PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(productService.deleteProduct(userId, productId));
    }
}
