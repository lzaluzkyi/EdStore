package com.store.entity;

import lombok.Data;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;
    

    @NotNull
    @ManyToOne()
    private Item item;


    public Characteristics() {

    }
}
