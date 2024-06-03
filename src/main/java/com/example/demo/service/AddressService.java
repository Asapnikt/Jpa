package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.repository.AddressJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressJpaRepository addressJpaRepository;

    @Autowired
    public void setAddressJpaRepository(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    public void saveAddress(Address address) {
        addressJpaRepository.save(address);
    }

    public void editAddress(Address address) {
        addressJpaRepository.save(address);
    }

    public void deleteAddress(Address address) {
        addressJpaRepository.delete(address);
    }

    public Address getAddress(long id) {
        return addressJpaRepository.findById(id).get();
    }

    public List<Address> getAllAddress() {
        return addressJpaRepository.findAll();
    }

    public Page<Address> getAddressWithPagination(Pageable pageParam) {
        return addressJpaRepository.findAll(pageParam);
    }
}
