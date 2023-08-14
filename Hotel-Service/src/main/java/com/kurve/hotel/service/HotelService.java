package com.kurve.hotel.service;

import com.kurve.hotel.dto.HotelRequest;
import com.kurve.hotel.exception.ResourceNotFoundException;
import com.kurve.hotel.model.Hotel;
import com.kurve.hotel.repo.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;

    // create
    public void createHotel(HotelRequest hotelRequest){
        String randomid = UUID.randomUUID().toString();
        Hotel hotel = Hotel.builder()
                .id(randomid)
                .name(hotelRequest.getName())
                .location(hotelRequest.getLocation())
                .about(hotelRequest.getAbout())
                .build();

        hotelRepository.save(hotel);
        log.info("Hotel Saved Successfully");
    }

    // get

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(String id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel Not Found with Id: "+id));
        return hotel;
    }

    // delete

    public void delete(String id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel Not Found with Id: "+id));
        hotelRepository.delete(hotel);
    }


    // update


}
