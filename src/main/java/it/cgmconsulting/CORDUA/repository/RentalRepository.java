package it.cgmconsulting.CORDUA.repository;

import it.cgmconsulting.CORDUA.entity.Rental;
import it.cgmconsulting.CORDUA.entity.RentalId;
import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository  extends JpaRepository<Rental, RentalId> {





    @Query(value = "SELECT Distinct NEW it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse(" +
            "s.storeName, " +
            "COUNT(r.rentalId.customer.customerId) as  totalCustomer) " +
            "FROM Inventory i  " +
            "INNER JOIN  Store s ON s.storeId = i.store.storeId " +
            "INNER JOIN Rental r ON r.rentalId.inventory.inventoryId= i.inventoryId  " +
            "WHERE  s.storeName =  :storeName  " +
            "AND r.rentalReturn IS NOT NULL ")
   Optional< CustomerStoreResponse> getCustomers(@Param("storeName")String storeName);



/*
QUERY NATIVA PER PRENDERE SPUNTO

    SELECT s.store_name, COUNT(r.customer_id)
    FROM inventory i , rental r , store s
    WHERE i.store_id=s.store_id
    AND i.inventory_id=r.inventory_id
    AND s.store_name = 'ENJOY VIDEO STORE'
    AND r.rental_return IS NOT null

    */




    @Query(value = "SELECT  COUNT(*)\n" +
            "FROM inventory i , rental r , store s, customer c \n" +
            "WHERE i.store_id=s.store_id \n" +
            "AND i.inventory_id=r.inventory_id\n" +
            "AND s.store_id = :storeId " +
            "AND c.customer_id=r.customer_id\n" +
            "AND r.rental_date BETWEEN :start  AND  :end ",nativeQuery = true)
    long getNumber(@Param("storeId")long storeId , @Param("start") String start, @Param("end") String end);




}
