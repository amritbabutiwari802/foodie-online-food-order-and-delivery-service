package com.foodie.kitchenservice.repository;

import com.foodie.kitchenservice.dao.MenuDao;
import com.foodie.kitchenservice.dao.TicketDao;
import com.foodie.kitchenservice.dto.CreateMenuDto;
import com.foodie.kitchenservice.entities.Menu;
import com.foodie.kitchenservice.entities.Ticket;
import com.foodie.sagas.order.models.CreateTicketDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class KitchenRepository {

    private final MenuDao menuDao;
    private final TicketDao ticketDao;

    public KitchenRepository(MenuDao menuDao, TicketDao ticketDao) {
        this.menuDao = menuDao;
        this.ticketDao = ticketDao;
    }

    public Menu createMenu(CreateMenuDto createMenuDto){
        Menu menu = new Menu();
        menu.setName(createMenuDto.getName());
        menu.setPrice(createMenuDto.getPrice());
        menu.setDiscountPercentage(createMenuDto.getDiscountPercentage());
        menu.setRestaurantId(createMenuDto.getRestaurantId());
        menu.setBranchId(createMenuDto.getBranchId());
        menu.setDescription(createMenuDto.getDescription());
        return  this.menuDao.save(menu);
    }

  public void deleteMenu(UUID menuId){
        this.menuDao.deleteById(menuId);
  }

  public Menu getMenuById(UUID menuId){
        return this.menuDao.findById(menuId).get();
  }

  public Ticket createTicket(CreateTicketDto createTicketDto){
    Ticket ticket = new Ticket();
    ticket.setMenuId(createTicketDto.getMenuId());
    ticket.setOrderId(createTicketDto.getOrderId());
    ticket.setQuantity(createTicketDto.getQuantity());
    ticket.setStatus(Ticket.Status.CREATED);
    return this.ticketDao.save(ticket);
  }

  public void approveTicket(UUID orderId){
      List<Ticket> ticketList = this.ticketDao.findByOrderId(orderId);
      Ticket ticket = ticketList.get(0);
      ticket.setStatus(Ticket.Status.APPROVED);
      this.ticketDao.save(ticket);
  }

  public void rejectTicket(UUID orderId){
        List<Ticket> ticketList = this.ticketDao.findByOrderId(orderId);
        Ticket ticket = ticketList.get(0);
        ticket.setStatus(Ticket.Status.REJECTED);
        this.ticketDao.save(ticket);
  }



}
