package it.cgmconsulting.CORDUA.service;

import it.cgmconsulting.CORDUA.entity.FilmStaff;
import it.cgmconsulting.CORDUA.entity.FilmStaffId;
import it.cgmconsulting.CORDUA.entity.Staff;
import it.cgmconsulting.CORDUA.payload.response.FilmResponse;
import it.cgmconsulting.CORDUA.repository.FilmStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FilmStaffService {

    @Autowired
    FilmStaffRepository filmStaffRepository;


    public List<FilmResponse> getActors(Set<Long> staffId,long size){
        return filmStaffRepository.getAttori(staffId,size);
    }




}

