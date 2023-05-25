package it.cgmconsulting.CORDUA.entity;

import it.cgmconsulting.CORDUA.payload.response.CustomerStoreResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

@NamedNativeQuery(
        name="Inventory.getCustomer",
        query =" SELECT s.store_name, COUNT(r.customer_id) "+
       " FROM inventory i , rental r , store s  " +
        " WHERE i.store_id=s.store_id" +
        " AND i.inventory_id=r.inventory_id" +
        "  AND s.store_name =  :storeName " +
        " AND r.rental_return IS NOT null ",


      /*  query="SELECT c.codice_cane, c.nome, ROUND(AVG(voto),2) AS average, " +
                "(ABS((c.peso - r.peso_standard)/(r.peso_standard)) + ABS((c.altezza - r.altezza_standard)/(r.altezza_standard))) as scostamento " +
                "FROM voti v " +
                "INNER JOIN cane c ON c.codice_cane=v.codice_cane " +
                "INNER JOIN razza r ON r.codice_razza=c.codice_razza " +
                "INNER JOIN mostra_canina m ON (m.nome_mostra=v.nome_mostra AND m.anno=v.anno) " +
                "WHERE m.anno=:anno AND m.nome_mostra=:nomeMostra " +
                "GROUP BY c.codice_cane, c.nome " +
                "ORDER BY average DESC, scostamento ASC;",

       */
        resultSetMapping="miaQuery"
)
@SqlResultSetMapping(
        name="miaQuery",
        classes = @ConstructorResult(
                targetClass= CustomerStoreResponse.class,
                columns = {
                        @ColumnResult(name="store_name", type=String.class),
                        @ColumnResult(name="number_customer", type= Short.class),

                }
        )
)




public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id",nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id",nullable = false)
    private Film film;

    public Inventory(Store store, Film film) {
        this.store = store;
        this.film = film;
    }

    public Inventory(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return inventoryId == inventory.inventoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId);
    }


}
