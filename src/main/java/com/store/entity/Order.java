package com.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "our_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "item_our_order" ,
//    joinColumns = @JoinColumn(name = "our_order_id"),
//    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @ManyToOne
    private User user;

    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
