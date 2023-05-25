package it.cgmconsulting.CORDUA.service;

import it.cgmconsulting.CORDUA.entity.Film;
import it.cgmconsulting.CORDUA.entity.Inventory;
import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import it.cgmconsulting.CORDUA.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Optional<CustomerStoreResponse> getCustomer(String storeName){
        Optional<CustomerStoreResponse>  response= inventoryRepository.getCustomer(storeName);
        return  response;

    }
    public void save(Inventory v){
        inventoryRepository.save(v);
    }


   // public boolean findByFilm(Film filmId) {
      //  return inventoryRepository.findByFilm(filmId);
   // }
}
