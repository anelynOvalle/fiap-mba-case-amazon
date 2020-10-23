package com.fiap.ralfmed.ticketamazonservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ralfmed.ticketamazonservice.dto.TicketDTO;
import com.fiap.ralfmed.ticketamazonservice.entity.Ticket;

import com.fiap.ralfmed.ticketamazonservice.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createCase(@RequestBody TicketDTO ticketDTO){
        return ticketService.create(ticketDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Ticket findById(@RequestParam Long id){
        return ticketService.findById(id);
    }

    @GetMapping("/findByType")
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> findByType(@RequestParam String type){
        return ticketService.findByType(type);
    }
}
