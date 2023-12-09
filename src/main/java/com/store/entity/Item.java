package com.store.entity;

import javax.persistence.*;

import com.store.dto.ItemDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity()
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "credit_available")
    private Boolean creditAvailable;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY)
    private List<Characteristics> characteristics;

    private BigDecimal price;

    @Column(name = "stock_count")
    private Integer stockCount;

    @ManyToOne()
    private Category category;
    @ManyToMany(mappedBy = "items")
    private List<Order> ourOrders;


    public Item(Long id, Boolean creditAvailable) {
        this.id = id;
        this.creditAvailable = creditAvailable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creditAvailable=" + creditAvailable +
                ", description='" + description + '\'' +
                ", characteristics=" + characteristics +
                ", price=" + price +
                ", stockCount=" + stockCount +
                ", category=" + category +
                '}';
    }
}
