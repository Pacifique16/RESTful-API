package com.auca.restfulapi.restaurant.repository;

import com.auca.restfulapi.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategoryIgnoreCase(String category);
    List<MenuItem> findByAvailable(boolean available);
    List<MenuItem> findByNameContainingIgnoreCase(String name);
}
