package com.auca.restfulapi.user.repository;

import com.auca.restfulapi.user.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsernameIgnoreCase(String username);
    List<UserProfile> findByCountryIgnoreCase(String country);
    List<UserProfile> findByAgeBetween(int min, int max);
    List<UserProfile> findByActive(boolean active);
}
