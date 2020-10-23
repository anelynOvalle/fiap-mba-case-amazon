package com.fiap.ralfmed.ticketamazonservice.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.ralfmed.ticketamazonservice.dto.TicketDTO;
import com.fiap.ralfmed.ticketamazonservice.entity.Ticket;
import com.fiap.ralfmed.ticketamazonservice.repository.TicketRepository;

import com.fiap.ralfmed.ticketamazonservice.service.TicketService;


@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(TicketDTO ticketDTO) {
        return ticketRepository.save(Ticket.convertToTicket(ticketDTO));
    }

	@Override
	public Ticket findById(Long id) {
		// TODO Auto-generated method stub
		return (Ticket) ticketRepository.findById(id).get();
	}

	@Override
	public List<Ticket> findByType(String type) {
		// TODO Auto-generated method stub
		return ticketRepository.findByType(type);
	}


}
