package it.cgmconsulting.CORDUA.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class FilmRequest {


    @NotBlank @Size(max = 100)
    private String title;

    @NotBlank @Size(min=100,max = 65535)
    private String description ;



    private short releaseYear;

    @Min(1)
    private long genreId;

    @Min(1)
    private long languageId;






}
