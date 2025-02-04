package com.shop.Ticket_Shop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketPackDto
{
    private BigDecimal Cost;
    private Integer Count;

}
