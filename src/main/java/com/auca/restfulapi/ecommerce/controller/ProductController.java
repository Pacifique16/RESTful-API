package com.auca.restfulapi.ecommerce.controller;

import com.auca.restfulapi.ecommerce.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private List<Product> products = new ArrayList<>();
    private Long nextId = 11L;

    public ProductController() {
        products.add(new Product(1L, "iPhone 14", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        products.add(new Product(2L, "Samsung Galaxy S23", "Android flagship phone", 899.99, "Electronics", 30, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "Professional laptop", 2499.99, "Electronics", 20, "Apple"));
        products.add(new Product(4L, "Nike Air Max", "Running shoes", 129.99, "Footwear", 100, "Nike"));
        products.add(new Product(5L, "Adidas Ultraboost", "Comfortable running shoes", 149.99, "Footwear", 80, "Adidas"));
        products.add(new Product(6L, "Sony Headphones", "Noise cancelling headphones", 299.99, "Electronics", 0, "Sony"));
        products.add(new Product(7L, "Levi's Jeans", "Classic denim jeans", 79.99, "Clothing", 150, "Levi's"));
        products.add(new Product(8L, "Canon Camera", "DSLR camera", 1299.99, "Electronics", 15, "Canon"));
        products.add(new Product(9L, "Puma Sneakers", "Casual sneakers", 89.99, "Footwear", 60, "Puma"));
        products.add(new Product(10L, "Dell Monitor", "27-inch 4K monitor", 399.99, "Electronics", 40, "Dell"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, products.size());
            if (start >= products.size()) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            return ResponseEntity.ok(products.subList(start, end));
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = products.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        product.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Product> result = products.stream()
                .filter(product -> product.getPrice() >= min && product.getPrice() <= max)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = products.stream()
                .filter(product -> product.getStockQuantity() > 0)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                products.set(i, updatedProduct);
                return ResponseEntity.ok(updatedProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(product -> product.getProductId().equals(productId));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
