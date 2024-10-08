package simbirsoft.mgu.ozon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simbirsoft.mgu.ozon.domain.Product;
import simbirsoft.mgu.ozon.domain.User;
import simbirsoft.mgu.ozon.dto.ProductRequestDto;
import simbirsoft.mgu.ozon.dto.ProductResponseDto;
import simbirsoft.mgu.ozon.repository.ProductRepository;
import simbirsoft.mgu.ozon.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductResponseDto create(Integer userId, ProductRequestDto productRequestDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .owner(user)
                .build();

        product = productRepository.save(product);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .sellerFirstName(user.getFirstName())
                .sellerLastName(user.getLastName())
                .build();

        return productResponseDto;
    }

    public List<ProductResponseDto> getAllProduct(Integer userId) {
        List<ProductResponseDto> result = new ArrayList<>();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        List<Product> products = productRepository.getProductsByOwner(user);

        for (Product product : products) {
            ProductResponseDto productResponseDto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .category(product.getCategory())
                    .sellerFirstName(user.getFirstName())
                    .sellerLastName(user.getLastName())
                    .build();
            result.add(productResponseDto);
        }
        return result;
    }

    public ProductResponseDto getOneProduct(Integer userId, Integer productId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty() || productOptional.get().getOwner().getId() != user.getId()) {
            throw new IllegalArgumentException("Product with id: " + productId + " not found");
        }
        Product product = productOptional.get();

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .sellerFirstName(user.getFirstName())
                .sellerLastName(user.getLastName())
                .build();

        return productResponseDto;
    }

    public ProductResponseDto updateProduct(Integer userId, Integer productId, ProductRequestDto productRequestDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty() || productOptional.get().getOwner().getId() != user.getId()) {
            throw new IllegalArgumentException("Product with id: " + productId + " not found or owner not equals");
        }
        Product product = productOptional.get();

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setCategory(productRequestDto.getCategory());
        product.setPrice(productRequestDto.getPrice());

        product = productRepository.save(product);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .sellerFirstName(user.getFirstName())
                .sellerLastName(user.getLastName())
                .build();

        return productResponseDto;
    }

    public String deleteProduct(Integer userId, Integer productId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id: " + userId + " not found");
        }
        User user = userOptional.get();

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty() || productOptional.get().getOwner().getId() != user.getId()) {
            throw new IllegalArgumentException("Product with id: " + productId + " not found or owner not equals");
        }
        Product product = productOptional.get();

        productRepository.delete(product);

        return "Product with id: " + productId + " deleted";
    }
}
