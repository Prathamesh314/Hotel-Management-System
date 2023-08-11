package com.kurve.hotel.controller;

import com.kurve.hotel.dto.HotelRequest;
import com.kurve.hotel.model.Hotel;
import com.kurve.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody HotelRequest hotelRequest){
        hotelService.createHotel(hotelRequest);
        return "Hotel Created Successfully";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getAll(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hotel getById(@PathVariable String id){
        return hotelService.getHotelById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String id){
        hotelService.delete(id);
        return "Hotel Deleted Successfully";
    }

}
