package com.auca.restfulapi.ecommerce.controller;

import com.auca.restfulapi.ecommerce.model.Product;
import com.auca.restfulapi.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(productService.getAllProducts(page, limit));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.getProductsByBrand(brand));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        return ResponseEntity.ok(productService.getInStockProducts());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getProductsByPriceAndBrand(
            @RequestParam(required = false) Double price, 
            @RequestParam(required = false) String brand) {
        if (price == null || brand == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.getProductsByPriceAndBrand(price, brand));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (productService.productExists(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Product already exists", "name", product.getName()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        return productService.updateStock(productId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (productService.deleteProduct(productId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
