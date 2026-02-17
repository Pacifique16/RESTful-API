package com.auca.restfulapi.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.auca.restfulapi.ecommerce.model.Product;
import com.auca.restfulapi.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(Integer page, Integer limit) {
        if (page != null && limit != null) {
            return productRepository.findAll(PageRequest.of(page, limit)).getContent();
        }
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public List<Product> getProductsByPriceRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public List<Product> getInStockProducts() {
        return productRepository.findByStockQuantityGreaterThan(0);
    }

    public List<Product> getProductsByPriceAndBrand(Double price, String brand) {
        return productRepository.findByPriceAndBrand(price, brand);
    }

    public boolean productExists(String name) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(name, name)
                .stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long productId, Product updatedProduct) {
        if (productRepository.existsById(productId)) {
            updatedProduct.setProductId(productId);
            return Optional.of(productRepository.save(updatedProduct));
        }
        return Optional.empty();
    }

    public Optional<Product> updateStock(Long productId, int quantity) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setStockQuantity(quantity);
                    return productRepository.save(product);
                });
    }

    public boolean deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }
}
