package it.cgmconsulting.CORDUA.service;

import it.cgmconsulting.CORDUA.entity.Rental;
import it.cgmconsulting.CORDUA.entity.RentalId;
import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import it.cgmconsulting.CORDUA.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;



    public Optional<CustomerStoreResponse> getCustomers(String storeName){
        return rentalRepository.getCustomers(storeName);
    }

    public long getNumber(long storeId, String start, String end){
        return rentalRepository.getNumber(storeId,start,end);
    }


    public boolean existsById(RentalId r){
        return  rentalRepository.existsById(r);
    }


    public Optional<Rental> findById(RentalId rentalId) {
        return rentalRepository.findById(rentalId);
    }

    public void save(Rental rental) {
    }
}
