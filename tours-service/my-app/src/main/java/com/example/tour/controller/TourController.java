package com.example.tour.controller;


import com.example.tour.entity.Tour;
import com.example.tour.repository.TourRepository;
import com.example.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tour")
@CrossOrigin(origins="*")
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourRepository tourRepository;

    @PostMapping("/")
    public Tour saveTour(@RequestBody Tour tour){
        return tourService.saveTour(tour);
    }

    @GetMapping("/{id}")
    public Tour findUserById(@PathVariable("id")Long id){
        return tourService.findUserById(id);
    }

    @GetMapping("/fetch")
    public List<Tour> getAllTours(){
        List<Tour> tours = new ArrayList<>();
        return tourService.getAllTours();
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable("id") Long id, @RequestBody Tour tour){
        Optional<Tour> tourData = tourRepository.findById(id);
        if(tourData.isPresent()){
            Tour _tour = tourData.get();
            _tour.setTourName(tour.getTourName());
            _tour.setTourCost(tour.getTourCost());
            _tour.setTourDays(tour.getTourDays());

            return new ResponseEntity<>(tourRepository.save(_tour), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTour(@PathVariable("id") Long id){
        try {
            tourRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
