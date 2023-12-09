package com.store.controller;

import com.store.dto.ItemDTO;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{itemId}")
    private ItemDTO getById(@PathVariable("itemId") Long id, @RequestParam(value = "withCategory" , required = true) Boolean withCategory) {
        Item item = itemService.findById(id);
        if (withCategory){
            return new ItemDTO(item , withCategory);
        }
        return new ItemDTO(item);
    }

    @GetMapping()
    private List<ItemDTO> getAll() {
        List<Item> items = itemService.findAll();
        List<ItemDTO> itemDTOS = items.stream().map(item -> new ItemDTO(item)).collect(Collectors.toList());
        return itemDTOS;
    }


    @PostMapping
    private ResponseEntity<ItemDTO> createItem(@RequestBody() ItemDTO itemDTO){
        Item item = itemService.create(itemDTO);
        return new ResponseEntity<>(new ItemDTO(item) , HttpStatus.OK);
    }
    @PutMapping
    private ResponseEntity<ItemDTO> updateItem(@RequestBody() ItemDTO itemDTO){
        if (itemDTO.getId() == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Item item = itemService.update(itemDTO);
        return new ResponseEntity<>(new ItemDTO(item) , HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    private ResponseEntity delete(@PathVariable ("itemId") Long itemId){
        itemService.delete(itemId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
