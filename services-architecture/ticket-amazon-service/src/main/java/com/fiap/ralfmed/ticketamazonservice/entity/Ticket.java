package com.fiap.ralfmed.ticketamazonservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fiap.ralfmed.ticketamazonservice.dto.TicketDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String subject;

    private String type;

    private Long productId;
    
    private Long orderId;
    
    private String description;
    
    private String status;

    public static Ticket convertToTicket(TicketDTO ticketDTO){
    	
    	Ticket ticket = new Ticket();
    	ticket.setSubject(ticketDTO.getSubject());
    	ticket.setType(ticketDTO.getType());
    	ticket.setProductId(ticketDTO.getProductId());
    	ticket.setOrderId(ticketDTO.getOrderId());
    	ticket.setStatus(ticketDTO.getStatus());
    	ticket.setDescription(ticketDTO.getDescription());
    	
    	return ticket;
    	
    }
    
    
    
}
