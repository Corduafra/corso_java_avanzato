package it.cgmconsulting.CORDUA.service;

import it.cgmconsulting.CORDUA.entity.Film;
import it.cgmconsulting.CORDUA.entity.Staff;
import it.cgmconsulting.CORDUA.payload.response.FilmMaxResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmRentResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmStoreResponse;
import it.cgmconsulting.CORDUA.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<FilmMaxResponse> getNumbers(){
        return filmRepository.getNumerTot();
    }


    public boolean existsById(long id){
        return filmRepository.existsById(id);
    }

    public Optional<Film> findById(long id){
        return filmRepository.findById(id);
    }


    public List<FilmStoreResponse> getFilm(long filmId){
        return filmRepository.getFilm(filmId);
    }


    public List<FilmResponse> getLanguageFilm(long languageId) {
        return filmRepository.getLanguageFilm(languageId);
    }

    public void save(Film f){
        filmRepository.save(f);
    }

    public List<FilmRentResponse> getFilms(long customerId){
        return filmRepository.getFilms(customerId);
    }

    public Optional<Film> cercaFilmDisponibile(long filmId,long storeId, long inventoryId){
        return filmRepository.cercaFilmDisponibile(filmId,storeId,inventoryId);
    }




}
