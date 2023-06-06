package com.foodie.kitchenservice.service;

import com.foodie.kitchenservice.dto.CreateMenuDto;
import com.foodie.kitchenservice.entities.Menu;
import com.foodie.kitchenservice.entities.Ticket;
import com.foodie.kitchenservice.repository.KitchenRepository;
import com.foodie.sagas.order.models.CreateTicketDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class KitchenService {
    
    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }


    public Menu createMenu(CreateMenuDto createMenuDto){
        return  this.kitchenRepository.createMenu(createMenuDto);
    }

    public void deleteMenu(UUID menuId){
        this.kitchenRepository.deleteMenu(menuId);
    }

    public Menu getMenuById(UUID menuId){
        return this.kitchenRepository.getMenuById(menuId);
    }

    public Ticket createTicket(CreateTicketDto createTicketDto){
        return this.kitchenRepository.createTicket(createTicketDto);
    }

    public void approveTicket(UUID orderId){
        this.kitchenRepository.approveTicket(orderId);
    }

    public void rejectTicket(UUID orderId){
        this.kitchenRepository.rejectTicket(orderId);
    }
}
