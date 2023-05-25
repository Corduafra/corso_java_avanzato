package it.cgmconsulting.CORDUA.controller;

import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import it.cgmconsulting.CORDUA.service.InventoryService;
import it.cgmconsulting.CORDUA.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RestController
@Validated
public class RentalController {
    @Autowired
    RentalService rentalService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/count-customers-by-store/{storeName}") //5 es numero alto
    public ResponseEntity<?> getCustomers(@PathVariable @NotBlank String storeName){
        Optional<CustomerStoreResponse> c= rentalService.getCustomers(storeName);

        if(c.isEmpty())
            return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(c,HttpStatus.OK);

    }
    @GetMapping("/count-rentals-in-date-range-by-store/{storeId}") // 7 FATTO
    public ResponseEntity<?>  countRental(@RequestParam String start, @RequestParam String end, @PathVariable @Valid  long storeId){
        long numero=rentalService.getNumber(storeId,start,end);


        return new ResponseEntity<>(numero,HttpStatus.OK);

    }




}
