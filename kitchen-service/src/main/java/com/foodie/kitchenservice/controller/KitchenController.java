package com.foodie.kitchenservice.controller;

import com.foodie.kitchenservice.dto.CreateMenuDto;
import com.foodie.kitchenservice.dto.CreateTicketDto;
import com.foodie.kitchenservice.entities.Menu;
import com.foodie.kitchenservice.entities.Ticket;
import com.foodie.kitchenservice.service.KitchenService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/kitchem")
public class KitchenController {

    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }




    @PostMapping("/create-menu")
    public Menu createMenu(@RequestBody() CreateMenuDto createMenuDto){
        return  this.kitchenService.createMenu(createMenuDto);
    }

    @PostMapping("/delete-menu/{menuid}")
    public void deleteMenu(@PathVariable("menuid") UUID menuId){
        this.kitchenService.deleteMenu(menuId);
    }

    @PostMapping("/get-menu-by-id/{menuid}")
    public Menu getMenuById(@PathVariable("menuid") UUID menuId){
        return this.kitchenService.getMenuById(menuId);
    }



    @PostMapping("/approve-ticket/{orderid}")
    public void approveTicket(@PathVariable("menuid") UUID orderId){
        this.kitchenService.approveTicket(orderId);
    }

    @PostMapping("/reject-ticket/{orderid}")
    public void rejectTicket(@PathVariable("menuid") UUID orderId){
        this.kitchenService.rejectTicket(orderId);
    }

}



