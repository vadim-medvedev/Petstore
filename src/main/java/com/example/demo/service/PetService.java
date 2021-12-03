package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Pet;
import com.example.demo.model.PetStatus;
import com.example.demo.model.Tag;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;

    public void save(Pet pet) {
        if (petRepository.existsById(pet.getId())){
            throw new RuntimeException();
        }else {
            fillPet(pet);
        }
    }

    public void addCategory(String category){
        if (!categoryRepository.existsByCategory(category)){
            Category c = new Category();
            c.setCategory(category);
            categoryRepository.save(c);
        }
    }

    public void update(Pet pet) {
        if (petRepository.existsById(pet.getId())){
            fillPet(pet);
        }else {
            throw new RuntimeException();
        }
    }

    private void fillPet(Pet pet) {
        Category byId = categoryRepository.getById(pet.getCategory().getId());
        pet.setCategory(byId);
        for (Tag tag : pet.getTags()) {
            if (!tagRepository.existsById(tag.getId())){
                tagRepository.save(tag);
            }
        }
        petRepository.save(pet);
    }

    public List<Pet> listByStatus(String[] status){

        for (String s : status) {
            if (!PetStatus.isExist(s)){
                throw new RuntimeException();
            }
        }

        List<Pet> result = Arrays
                .stream(status)
                .flatMap(s -> petRepository.getByStatus(s).stream())
                .collect(Collectors.toList());
        return result;
    }

    public Pet getById(int petId){
        Optional<Pet> byId = petRepository.getById(petId);
        return byId.orElse(null);
    }

    public void update(int petId, String name, String status) {
        if (petRepository.existsById(petId) && PetStatus.isExist(status)){
            Pet pet = petRepository.getById(petId).get();
            pet.setName(name);
            pet.setStatus(PetStatus.valueOf(status.toUpperCase()));

            petRepository.save(pet);
        }
    }

    public void delete(int petId) {
        if (petRepository.existsById(petId)){
            Pet pet = petRepository.getById(petId).get();
            petRepository.delete(pet);
        }
    }
}
