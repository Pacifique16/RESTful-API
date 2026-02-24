package com.auca.restfulapi.academic.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity representing a hierarchical location structure
 * Supports: Province -> District -> Sector -> Cell -> Village
 */
@Entity
@Table(name="location")
public class Location {
    

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ELocationType type;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties("parent")
    private Location parent;


    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ELocationType getType() {
        return this.type;
    }

    public void setType(ELocationType type) {
        this.type = type;
    }

    public Location getParent() {
        return this.parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

}
