package it.cgmconsulting.CORDUA.repository;

import it.cgmconsulting.CORDUA.entity.FilmStaff;
import it.cgmconsulting.CORDUA.entity.FilmStaffId;
import it.cgmconsulting.CORDUA.entity.Staff;
import it.cgmconsulting.CORDUA.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmStaffRepository  extends JpaRepository<FilmStaff, FilmStaffId> {


   // Set<FilmStaffId> findByFilmStaffIdStaffIn(Set<Long> staffId);



    @Query(value = "SELECT distinct  NEW it.cgmconsulting.CORDUA.payload.response.FilmResponse(" +
            "f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "fs.filmStaffId.film.language.languageName" +
            ") " +
            "FROM FilmStaff fs " +
            "INNER JOIN Film f ON f.filmId= fs.filmStaffId.film.filmId " +
            "INNER JOIN Staff s ON s.staffId = fs.filmStaffId.staff.staffId " +
            "INNER JOIN Role r ON r.roleId = fs.filmStaffId.role.roleId " +
            "where r.roleId=1 " +
            "AND fs.filmStaffId.staff.staffId IN :staffId " +
            "group by f.title " +
            "having count(fs.filmStaffId.staff.staffId )=:size " +
            "ORDER BY f.title " +
            "")
    List<FilmResponse> getAttori(@Param("staffId") Set<Long> staffId, long size);





}
