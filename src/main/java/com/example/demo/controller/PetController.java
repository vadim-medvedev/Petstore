package com.example.demo.controller;

import com.example.demo.model.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping
    public void put(@Valid @RequestBody Pet pet){
        petService.save(pet);
    }

    @PostMapping("/category")
    public void put(@Valid String category){
        petService.addCategory(category);
    }

    @PutMapping
    public void update(@Valid @RequestBody Pet pet){
        petService.update(pet);
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> getByStatus(@RequestBody String[] status){
        List<Pet> petList = petService.listByStatus(status);
        return new ResponseEntity<>(petList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getById(@PathVariable int petId){
        Pet byId = petService.getById(petId);
        if (byId != null){
            return new ResponseEntity<>(byId, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{petId}")
    public void update(@PathVariable int petId, String name, String status){
        petService.update(petId,name,status);
    }
    
    @DeleteMapping("/{petId}")
    public void delete(@PathVariable int petId){
        petService.delete(petId);
    }
}
