package com.auca.restfulapi.restaurant.controller;

import com.auca.restfulapi.restaurant.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    
    private List<MenuItem> menuItems = new ArrayList<>();
    private Long nextId = 9L;

    public MenuController() {
        menuItems.add(new MenuItem(1L, "Caesar Salad", "Fresh romaine lettuce with Caesar dressing", 8.99, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Garlic Bread", "Toasted bread with garlic butter", 5.99, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Grilled Chicken", "Marinated grilled chicken breast", 15.99, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Beef Steak", "Premium beef steak with vegetables", 24.99, "Main Course", true));
        menuItems.add(new MenuItem(5L, "Pasta Carbonara", "Creamy pasta with bacon", 12.99, "Main Course", false));
        menuItems.add(new MenuItem(6L, "Chocolate Cake", "Rich chocolate layer cake", 6.99, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Ice Cream", "Vanilla ice cream with toppings", 4.99, "Dessert", true));
        menuItems.add(new MenuItem(8L, "Fresh Juice", "Orange or apple juice", 3.99, "Beverage", true));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(@RequestParam boolean available) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.isAvailable() == available)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        menuItems.add(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        boolean removed = menuItems.removeIf(item -> item.getId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
