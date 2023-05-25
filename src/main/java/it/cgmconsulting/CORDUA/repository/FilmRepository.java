package it.cgmconsulting.CORDUA.repository;

import it.cgmconsulting.CORDUA.entity.Film;
import it.cgmconsulting.CORDUA.entity.Staff;
import it.cgmconsulting.CORDUA.payload.response.FilmMaxResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmRentResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmResponse;
import it.cgmconsulting.CORDUA.payload.response.FilmStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FilmRepository  extends JpaRepository<Film,Long> {

@Query(value = " SELECT DISTINCT NEW it.cgmconsulting.CORDUA.payload.response.FilmStoreResponse( " +
        "f.filmId, " +
        "f.title,  " +
       "s.storeName) " +
        "FROM Inventory i " +
        "INNER JOIN Film f on f.filmId=i.film.filmId " +
        "INNER JOIN Store s ON s.storeId = i.store.storeId " +
        "WHERE f.filmId= :filmId ")
    List<FilmStoreResponse> getFilm(@Param("filmId") long filmId);



@Query(value = "SELECT DISTINCT NEW it.cgmconsulting.CORDUA.payload.response.FilmRentResponse(" +
        "f.filmId, " +
        "f.title, " +
        "s.storeName) " +
        "FROM Film f, Inventory i " +
        " INNER JOIN Film f ON i.film.filmId = f.filmId " +
        "INNER JOIN Store s ON s.storeId = i.store.storeId " +
        "INNER JOIN Rental r ON r.rentalId.inventory.inventoryId= i.inventoryId  " +
        "WHERE r.rentalId.customer.customerId = :customerId")
    List<FilmRentResponse> getFilms(@Param("customerId")long customerId);



@Query(value = "SELECT Distinct NEW it.cgmconsulting.CORDUA.payload.response.FilmMaxResponse(" +
        "f.filmId, " +
        "f.title, " +
        "(SELECT COUNT(*) " +
        "From Rental r " +
        "INNER join Inventory i ON i.inventoryId= r.rentalId.inventory.inventoryId " +
        "where f.filmId= i.film.filmId   ) as numeroTot ) " +
        " FROM Film f, Inventory i " +
        " INNER JOIN Rental r ON r.rentalId.inventory.inventoryId= i.inventoryId " +
        "WHERE f.filmId= i.film.filmId " +

        "ORDER BY numeroTot DESC")
    List<FilmMaxResponse> getNumerTot();
/*
@Query(value = "SELECT distinct  NEW it.cgmconsulting.CORDUA.payload.response.FilmResponse(" +
        "f.filmId, " +
        "f.title, " +
        "f.description, " +
        "f.releaseYear " +
        ") " +
        "FROM FilmStaff fs " +
        "INNER JOIN Film f ON f.filmId= fs.filmStaffId.film.filmId " +
        "INNER JOIN Staff s ON s.staffId = fs.filmStaffId.staff.staffId " +
        "INNER JOIN Role r ON r.roleId = fs.filmStaffId.role.roleId " +
        "where r.roleId=1 " +
        "AND fs.filmStaffId.staff.staffId IN :staffId " +
        "ORDER BY f.title " +
        "")
    List<FilmResponse> getAttori(@Param("staffId") Set<Long> staffId );

*/


    @Query(value = " SELECT distinct f.filmId " +
            "FROM Film f " +
            "WHERE f.filmId not in " +
            "( SELECT f.filmId " +
            " FROM Rental r " +
            "INNER JOIN Inventory i ON r.rentalId.inventory.inventoryId = i.inventoryId " +
            "INNER JOIN Film f ON i.film.filmId = f.filmId " +
            "WHERE f.filmId = :filmId " +
            "AND i.store.storeId = :storeId " +
            "AND i.inventoryId = :inventoryId )")
    Optional<Film> cercaFilmDisponibile(@Param("filmId") long filmId, @Param("storeId") long storeId, @Param("inventoryId") long inventoryId);



@Query(value = " SELECT  NEW it.cgmconsulting.CORDUA.payload.response.FilmResponse( " +
        "f.filmId, " +
        "f.title, " +
        "f.description, " +
        "f.releaseYear, " +
        "l.languageName" +
        ") " +
        "FROM  Language l " +
        "INNER JOIN Film f ON f.language.languageId=l.languageId " +
        "WHERE l.languageId = :languageId ")
List<FilmResponse> getLanguageFilm(@Param("languageId") long languageId);
}
