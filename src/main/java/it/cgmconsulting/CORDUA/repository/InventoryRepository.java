package it.cgmconsulting.CORDUA.repository;

import it.cgmconsulting.CORDUA.entity.Film;
import it.cgmconsulting.CORDUA.entity.Inventory;
import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository  extends JpaRepository<Inventory,Long>
{
    @Query(nativeQuery=true)
    Optional<CustomerStoreResponse> getCustomer(@Param("storeName") String storeName);


    boolean findByFilm(Film filmId);
}
