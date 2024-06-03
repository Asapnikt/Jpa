package com.example.demo.service;

import com.example.demo.repository.ServiceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {

    private ServiceJpaRepository serviceJpaRepository;

    @Autowired
    public void setServiceJpaRepository(ServiceJpaRepository serviceJpaRepository) {
        this.serviceJpaRepository = serviceJpaRepository;
    }

    public void save(com.example.demo.entity.Service serviceEntity) {
        serviceJpaRepository.save(serviceEntity);
    }

    public com.example.demo.entity.Service getService(long id) {
        return serviceJpaRepository.findById(id).orElseThrow();
    }

    public int getCount() {
        return serviceJpaRepository.findAll().size();
    }

    public int getMinService() {
        var service = serviceJpaRepository.findAll().stream()
                .findFirst()
                .get();
        return (int) service.getId();
    }
}
