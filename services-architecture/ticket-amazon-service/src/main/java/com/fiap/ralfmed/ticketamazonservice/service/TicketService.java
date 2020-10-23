package com.fiap.ralfmed.ticketamazonservice.service;

import java.util.List;

import com.fiap.ralfmed.ticketamazonservice.dto.TicketDTO;
import com.fiap.ralfmed.ticketamazonservice.entity.Ticket;

public interface TicketService {

    Ticket create(TicketDTO ticketDTO);

    Ticket findById(Long id);

    List<Ticket> findByType(String type);
}
