package com.fiap.ralfmed.ticketamazonservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fiap.ralfmed.ticketamazonservice.dto.TicketDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

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
    public Long id;
    
    public String subject;

    public String type;

    public Long productId;
    
    public Long orderId;
    
    public String description;
    
    public String status;
    
    /*@HystrixCommand(fallbackMethod  = "convertToTicketFallback",
    	commandProperties=
    	{@HystrixProperty(
    			name="execution.isolation.thread.timeoutInMilliseconds",value="12000")})*/
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
    
    /*public Ticket convertToTicketFallback(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		ticket.subject = "Erro ao cadastrar.";
		ticket.status = "Inválido";
		ticket.description = "Tente novamente mais tarde ou contate o administrador do sistema.";
		return ticket;
	}
    
    public List<Ticket> listFallbackReturn(String str){
    	List<Ticket> ticketList = null; //= new List<Ticket>();
    	
    	Ticket ticket = new Ticket();
		ticket.subject = "Erro ao cadastrar.";
		ticket.status = "Inválido";
		ticket.description = "Tente novamente mais tarde ou contate o administrador do sistema.";
    
		ticketList.add(ticket);
		return ticketList;
    }*/
    
    
    
}
