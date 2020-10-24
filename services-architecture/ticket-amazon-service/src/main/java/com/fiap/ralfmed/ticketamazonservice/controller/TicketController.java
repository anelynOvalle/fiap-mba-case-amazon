package com.fiap.ralfmed.ticketamazonservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ralfmed.ticketamazonservice.dto.TicketDTO;
import com.fiap.ralfmed.ticketamazonservice.entity.Ticket;

import com.fiap.ralfmed.ticketamazonservice.service.TicketService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @HystrixCommand(fallbackMethod  = "singleTicketFallback",
		commandProperties=
		{@HystrixProperty(
				name="execution.isolation.thread.timeoutInMilliseconds",value="2000")})
    public Ticket createCase(@RequestBody TicketDTO ticketDTO){
        return ticketService.create(ticketDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod  = "singleTicketFallback")
    public Ticket findById(@RequestParam Long id){
        return ticketService.findById(id);
    }

    @GetMapping("/findByType/{type}")
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod  = "listFallbackReturn")
    public List<Ticket> findByType(@PathVariable(name = "type") String type){
    	
    	/*try {
			Thread.sleep(50000);
			return ticketService.findByType(type);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;*/
    	
        return ticketService.findByType(type);
    }
    
    public Ticket singleTicketFallback(TicketDTO ticketDTO) {
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
    }
    
}
