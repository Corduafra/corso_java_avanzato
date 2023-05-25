package it.cgmconsulting.CORDUA.service;

import it.cgmconsulting.CORDUA.entity.Staff;
import it.cgmconsulting.CORDUA.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;


    public List<Staff> findById(Set<Long> staffId){
        return staffRepository.findByStaffIdIn(staffId);
    }



}
