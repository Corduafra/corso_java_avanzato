package it.cgmconsulting.CORDUA.controller;

import it.cgmconsulting.CORDUA.payload.response.FilmRentResponse;
import it.cgmconsulting.CORDUA.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class CustomerController {

    @Autowired
    FilmService filmService;




    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}") //es 8 ok
    public ResponseEntity<?> getFilm(@PathVariable long customerId){

        List<FilmRentResponse> responses=filmService.getFilms(customerId);

        if (responses.isEmpty())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(responses,HttpStatus.OK);

    }
}
