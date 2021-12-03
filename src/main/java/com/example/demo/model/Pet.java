package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @Valid
    private com.example.demo.model.Category category;
    @NotNull
    private String name;
    @ManyToMany
    @Valid
    private List<com.example.demo.model.Tag> tags;
    private com.example.demo.model.PetStatus status;
}
