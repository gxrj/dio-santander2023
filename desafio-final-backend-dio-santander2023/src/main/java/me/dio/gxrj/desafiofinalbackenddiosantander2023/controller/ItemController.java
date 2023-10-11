package me.dio.gxrj.desafiofinalbackenddiosantander2023.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.service.ItemService;

@RestController
@RequestMapping( "/item" )
public class ItemController {
    
    private final ItemService service;

    public ItemController( ItemService service ) {
        this.service = service;
    }
}
