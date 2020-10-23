package com.fiap.ralfmed.ticketamazonservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private String subject;

    private String type;

    private Long productId;
    
    private Long orderId;
    
    private String description;
    
    private String status;
}
