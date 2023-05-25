package it.cgmconsulting.CORDUA.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Rental {

    @EmbeddedId
    RentalId rentalId;

    @Column(nullable = true)
    private Date rentalReturn;

    public Rental(RentalId rentalId) {
        this.rentalId = rentalId;
    }

    public Rental(Date rentalReturn) {
        this.rentalReturn = rentalReturn;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(rentalId, rental.rentalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId);
    }
}
