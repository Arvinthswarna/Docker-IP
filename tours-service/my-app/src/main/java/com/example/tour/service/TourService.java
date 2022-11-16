package com.example.tour.service;

import com.example.tour.entity.Tour;
import com.example.tour.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public Tour findUserById(Long id) { return tourRepository.findByTourId(id);}



    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }
}
