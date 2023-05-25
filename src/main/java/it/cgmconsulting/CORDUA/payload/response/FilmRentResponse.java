package it.cgmconsulting.CORDUA.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class FilmRentResponse {

    private long filmId;
    private String title;
    private String storeName;



}
