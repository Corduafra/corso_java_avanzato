package it.cgmconsulting.CORDUA.service;

import it.cgmconsulting.CORDUA.entity.Store;
import it.cgmconsulting.CORDUA.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;


    public boolean existsById(long storeId){
        return storeRepository.existsById(storeId);
    }

    public Optional<Store> findById(long storeId){return storeRepository.findById(storeId);}
}
