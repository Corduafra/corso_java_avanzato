package it.cgmconsulting.CORDUA.repository;

import it.cgmconsulting.CORDUA.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository  extends JpaRepository<Store,Long> {





}
