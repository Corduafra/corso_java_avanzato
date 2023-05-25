package it.cgmconsulting.CORDUA.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class FilmStoreResponse {

    private long filmId;
    private String title;
    private String storeName;

    public FilmStoreResponse(long filmId, String title) {
        this.filmId = filmId;
        this.title = title;
    }
}
