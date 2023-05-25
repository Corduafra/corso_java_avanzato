package it.cgmconsulting.CORDUA.repository;

import it.cgmconsulting.CORDUA.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StaffRepository  extends JpaRepository<Staff,Long> {

    List<Staff> findByStaffIdIn(Set<Long> staffId);
}
