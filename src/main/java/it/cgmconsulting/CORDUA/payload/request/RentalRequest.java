package it.cgmconsulting.CORDUA.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RentalRequest {

    @Min(1) @NotBlank
    private long customer;

    @NotBlank @Min(1)
    private  long film;

    @NotBlank
    private Date rentalDate;

    private Date rentalReturn;

    @Min(1) @NotBlank
    private long store;

    @Min(1) @NotBlank
    private long inventory;

    public RentalRequest(long customer, Date rentalDate, long inventory) {
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.inventory = inventory;
    }

}
