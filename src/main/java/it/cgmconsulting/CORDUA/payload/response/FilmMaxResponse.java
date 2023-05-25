package it.cgmconsulting.CORDUA.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class FilmMaxResponse {

    private long filmId;
    private String title;
    private long numeroTot;



}
