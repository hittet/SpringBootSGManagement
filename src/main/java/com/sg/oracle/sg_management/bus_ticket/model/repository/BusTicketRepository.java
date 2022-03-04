package com.sg.oracle.sg_management.bus_ticket.model.repository;

import com.sg.oracle.sg_management.bus_ticket.model.entity.BusTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTicketRepository extends JpaRepository<BusTicket, Integer>
{

}
