package com.auca.restfulapi.restaurant.controller;

import com.auca.restfulapi.restaurant.model.MenuItem;
import com.auca.restfulapi.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    
    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(menuItemRepository.findByCategoryIgnoreCase(category));
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(@RequestParam boolean available) {
        return ResponseEntity.ok(menuItemRepository.findByAvailable(available));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        return ResponseEntity.ok(menuItemRepository.findByNameContainingIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<?> addMenuItem(@RequestBody MenuItem menuItem) {
        if (menuItemRepository.findByNameContainingIgnoreCase(menuItem.getName()).stream()
                .anyMatch(m -> m.getName().equalsIgnoreCase(menuItem.getName()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Menu item already exists", "name", menuItem.getName()));
        }
        MenuItem saved = menuItemRepository.save(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .map(item -> {
                    item.setAvailable(!item.isAvailable());
                    MenuItem saved = menuItemRepository.save(item);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
