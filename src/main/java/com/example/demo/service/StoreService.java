package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.Pet;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private PetRepository petRepository;

    public Optional<Order> save(Order order) {
        if (!storeRepository.existsById(order.getId()) && petRepository.existsById(order.getPet().getId())){
            Pet byId = petRepository.getById(order.getPet().getId());
            order.setPet(byId);
            storeRepository.save(order);
            return Optional.of(order);
        }else {
            throw new RuntimeException();
        }
    }

    public Optional<Order> getOrder(int orderId) {
        return storeRepository.getById(orderId);
    }

    public void delete(int orderId) {
        Optional<Order> byId = storeRepository.getById(orderId);
        if (byId.isPresent()) {
            storeRepository.delete(byId.get());
        }
    }
}
