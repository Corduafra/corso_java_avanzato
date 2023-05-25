package it.cgmconsulting.CORDUA.controller;

import it.cgmconsulting.CORDUA.entity.*;
import it.cgmconsulting.CORDUA.payload.request.FilmRequest;
import it.cgmconsulting.CORDUA.payload.request.RentalRequest;
import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmMaxResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmStoreResponse;
import it.cgmconsulting.CORDUA.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


@RestController
public class MainController {

    @Autowired
    StaffService staffService;

    @Autowired
    FilmStaffService filmStaffService;

    @Autowired
    FilmService filmService;

    @Autowired
    StoreService storeService;

    @Autowired
    RentalService rentalService;

    @Autowired
    InventoryService inventoryService;


    @PatchMapping("/update-film/{filmId}") // 1 ok
    @Transactional
    public ResponseEntity<?> getUpdate(@Valid  @RequestBody  FilmRequest request, @PathVariable long filmId){
        Optional<Film> film= filmService.findById(filmId);
        if(film.isEmpty())
            return new ResponseEntity<>("film not found",HttpStatus.NOT_FOUND);


        film.get().setDescription(request.getDescription());
        film.get().setTitle(request.getTitle());
        film.get().setReleaseYear(request.getReleaseYear());
        Genre g = new Genre(request.getGenreId());
        film.get().setGenre(g);
        Language l=new Language(request.getLanguageId());
        film.get().setLanguage(l);

        return new ResponseEntity<>("update",HttpStatus.OK);

    }

    @GetMapping(("/find-film-in-store/{filmId}")) // 2 ok
    public ResponseEntity<?> getFind(@PathVariable long filmId){

        List<FilmStoreResponse> responses = filmService.getFilm(filmId);

        if(responses.isEmpty())
            return new ResponseEntity<>("questo film non esiste",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(responses,HttpStatus.OK);

    }

    @GetMapping("/find-films-by-language/{languageId}") // 3 ok
    public ResponseEntity<?> getLanguageFilm(  @PathVariable@NotBlank @Min(1) long languageId){

        List<FilmResponse> responses=filmService.getLanguageFilm(languageId);

        if(responses.isEmpty())
            return new ResponseEntity<>("language not found",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(responses,HttpStatus.OK);

    }

    @PutMapping("/add-film-to-store/{storeId}/{filmId}")
    @Transactional  // 4  fatto
    public ResponseEntity<?> addFilm(@PathVariable @NotBlank @Min(1) long storeId, @PathVariable @NotBlank @Min(1) long filmId){

        if(!filmService.existsById(filmId) && storeService.existsById(storeId)) //CONTROLLO SE ESISTONO LO STORE E IL FIM
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Optional<Film> f= filmService.findById(filmId);
        Optional<Store> s= storeService.findById(storeId);

       Inventory inventory= new Inventory(s.get(),f.get());

       inventoryService.save(inventory);


        return new ResponseEntity<>("film is added ",HttpStatus.OK);
    }

    @PutMapping("/add-update-rental") // 6 parte ma non funziona appieno
    @Transactional
    public ResponseEntity<?> addOrUpdate(@RequestBody RentalRequest request ){
        Customer c= new Customer(request.getCustomer());
        Film f = new Film(request.getFilm());
        Store s= new Store(request.getStore());
        Inventory i = new Inventory(request.getInventory(),s,f);
        RentalId r = new RentalId(c,i,request.getRentalDate());;
        Optional<Rental> rental=rentalService.findById(r);

        if(rentalService.existsById(r) && rental.get().getRentalReturn()==(null) ){

            rental.get().setRentalReturn(Date.from(Instant.now()));
            rentalService.save(rental.get());
            return new ResponseEntity<>("update ",HttpStatus.OK);
        }
        else {
         //   Optional<Film> film= filmService.cercaFilmDisponibile(request.getFilm(),request.getInventory(),request.getStore());
           if(/*film.isPresent()*/ true){

               Rental rental1=new Rental(new RentalId(c,
                       i,
                       Date.from(Instant.now())));
               inventoryService.save(i);
               rentalService.save(rental1);
               return new ResponseEntity<>(rental1,HttpStatus.CREATED);
           }

           else
               return new ResponseEntity<>(HttpStatus.FORBIDDEN);


        }



    }



    @GetMapping("/find-film-with-max-number-of-rent") // es 9 fatto
    public ResponseEntity<?> numberOfRent(){
        List<FilmMaxResponse> responses=filmService.getNumbers();
        List<FilmMaxResponse> max =new ArrayList<>();

        long massimo=responses.get(0).getNumeroTot();
        int f=0;

        for (int i = 0; i < responses.size() ; i++) {

            if(responses.get(i).getNumeroTot()==massimo)
                f++;
            else if(massimo > responses.get(i).getNumeroTot()){
                break;
            }
        }
        for (int i=0; i<f; i++){   // aggiungo alla nuova lista che riporterò quelli con numeroTot uguale
            max.add(i,responses.get(i));
       }
        return new ResponseEntity<>(max,HttpStatus.OK);
    }

    @GetMapping("/find-films-by-actors") // es 10 FATTO
    public ResponseEntity<?> findActors(@RequestParam Set<Long> staffId){



     //  List<Staff> staffs=staffService.findById(staffId);

        List<FilmResponse> responses=filmStaffService.getActors(staffId,staffId.size());

        if(responses.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

















}
