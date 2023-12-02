package com.store.dto;

import com.store.entity.Category;
import com.store.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private Boolean creditAvailable;
    private String description;
    private List<Long> characteristics;
    private BigDecimal price;
    private Integer stockCount;
    private CategoryDTO category;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.creditAvailable = item.getCreditAvailable();
        this.description = item.getDescription();
        this.characteristics = item.getCharacteristics().stream()
                .map(characteristics1 -> characteristics1.getId())
                .collect(Collectors.toList());
        this.price = item.getPrice();
        this.stockCount = item.getStockCount();
        Category category = item.getCategory();
        this.category = new CategoryDTO(item.getId());
    }

    public ItemDTO(Item item , boolean withCategory) {
        this.id = item.getId();
        this.name = item.getName();
        this.creditAvailable = item.getCreditAvailable();
        this.description = item.getDescription();
        this.characteristics = item.getCharacteristics().stream()
                .map(characteristics1 -> characteristics1.getId())
                .collect(Collectors.toList());
        this.price = item.getPrice();
        this.stockCount = item.getStockCount();
        Category category = item.getCategory();
        this.category = new CategoryDTO(item.getId() , category.getName());
    }
}
