package com.store.repository;

import com.store.entity.Category;
import com.store.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item , Long> {


    List<Item> findByCategory(Category category);

    List<Item> findByName(String startWith);

    List<Item> findByNameStartingWith(String startWith);

    Integer countByNameStartingWith(String startWith);


}
