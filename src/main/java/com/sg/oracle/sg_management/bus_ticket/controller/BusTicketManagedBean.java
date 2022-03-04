package com.sg.oracle.sg_management.bus_ticket.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.sg.oracle.sg_management.bus_ticket.model.entity.BusTicket;
import com.sg.oracle.sg_management.bus_ticket.model.repository.BusTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
 * Eger bean’in sadece sayfa boyunca yasamasini istersek bu durumda ViewScoped kullanabiliriz.
 * 
 *  @Inject instead of Spring’s @Autowired to inject a bean.
	@Named instead of Spring’s @Component to declare a bean.
 * 
 *  https://stackoverflow.com/questions/5415261/what-is-javax-inject-named-annotation-supposed-to-be-used-for
 */
@Setter
@Getter
@Named
@ViewScoped
public class BusTicketManagedBean implements Serializable
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Controller'da getter ve setter'i olan bu varligin ilgili alanina atama
	 */
	private BusTicket busTicket = new BusTicket();
    private List<BusTicket> busTicketList = new ArrayList<>();

    @Autowired
    private BusTicketRepository busTicketRepository;

    public void fetchAll() 
    {
    	busTicketList = busTicketRepository.findAll();
    }

    public void save() 
    {
    
    	busTicketRepository.save(busTicket); // yapilandirilan bileti kaydet
        
    	busTicket = new BusTicket(); // yapilandirilmak uzere yeni bilet olustur
        
    	busTicketList = busTicketRepository.findAll(); // listeyi guncelle
    }

    public void edit(BusTicket busTicket) 
    {
        this.busTicket = busTicket;
    }

    public void refresh() 
    {
    	busTicket = new BusTicket(); // yapilandirilmak uzere yeni bilet olustur
    }
    
}