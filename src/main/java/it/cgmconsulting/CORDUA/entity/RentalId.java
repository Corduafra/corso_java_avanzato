package it.cgmconsulting.CORDUA.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RentalId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(nullable = false)
    private Date rentalDate;

    public RentalId(Customer customer, Inventory inventory) {
        this.customer = customer;
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalId rentalId = (RentalId) o;
        return Objects.equals(customer, rentalId.customer) && Objects.equals(rentalDate, rentalId.rentalDate) && Objects.equals(inventory, rentalId.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, rentalDate, inventory);
    }
}
