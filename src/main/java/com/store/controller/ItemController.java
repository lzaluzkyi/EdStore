package com.store.controller;

import com.store.dto.ItemDTO;
import com.store.entity.Item;
import com.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item/{itemId}" , method = RequestMethod.GET)
    private ItemDTO getById(@PathVariable("itemId") Long id, @RequestParam("withCategory") Boolean withCategory) {
        Item item = itemService.findById(id);
        if (withCategory){
            return new ItemDTO(item , withCategory);
        }
        return new ItemDTO(item);
    }


}
