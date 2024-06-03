package com.example.demo.service;

import com.example.demo.entity.Events;
import com.example.demo.repository.EventsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsService {

    private EventsJpaRepository eventsJpaRepository;

    @Autowired
    public void setEventsJpaRepository(EventsJpaRepository eventsJpaRepository) {
        this.eventsJpaRepository = eventsJpaRepository;
    }

    public void saveEvents(Events events) {
        eventsJpaRepository.save(events);
    }

    public void editEvents(Events events) {
        eventsJpaRepository.save(events);
    }

    public void deleteEvents(Events events) {
        eventsJpaRepository.delete(events);
    }

    public Events getEvent(long id) {
        return eventsJpaRepository.findById(id).get();
    }

    public List<Events> getAllEvents() {
        return eventsJpaRepository.findAll();
    }
}
