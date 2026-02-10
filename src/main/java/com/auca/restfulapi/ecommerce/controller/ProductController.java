package com.auca.restfulapi.ecommerce.controller;

import com.auca.restfulapi.ecommerce.model.Product;
import com.auca.restfulapi.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        
        if (page != null && limit != null) {
            return ResponseEntity.ok(productRepository.findAll(PageRequest.of(page, limit)).getContent());
        }
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productRepository.findByCategoryIgnoreCase(category));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productRepository.findByBrandIgnoreCase(brand));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productRepository.findByPriceBetween(min, max));
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        return ResponseEntity.ok(productRepository.findByStockQuantityGreaterThan(0));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(product.getName(), product.getName()).stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Product already exists", "name", product.getName()));
        }
        Product saved = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        if (productRepository.existsById(productId)) {
            updatedProduct.setProductId(productId);
            Product saved = productRepository.save(updatedProduct);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setStockQuantity(quantity);
                    Product saved = productRepository.save(product);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
