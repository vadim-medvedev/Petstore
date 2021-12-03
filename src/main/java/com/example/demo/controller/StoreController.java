package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/inventory")
    public ResponseEntity<String> inventory(){
        return null;
    }

    @PostMapping("/order")
    public ResponseEntity<Order> order(@Valid @RequestBody Order order){
        Optional<Order> o = storeService.save(order);
        if (o.isPresent()){
           return new ResponseEntity<>(o.get(),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable int orderId){
        Optional<Order> order = storeService.getOrder(orderId);
        if (order.isPresent()){
            return new ResponseEntity<>(order.get(),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/order/{orderId}")
    public void delete(@PathVariable int orderId){
        storeService.delete(orderId);
    }

}
