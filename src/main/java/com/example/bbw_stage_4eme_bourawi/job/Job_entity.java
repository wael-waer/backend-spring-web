package com.example.bbw_stage_4eme_bourawi.job;

import com.example.bbw_stage_4eme_bourawi.application.Application_entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Job_entity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    String title;

    String description;

    String requiredSkills;
    String location;
    String contractType;
    String status;
    int salary;
    Date applicationDeadline;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public Set<Application_entity> applicants = new HashSet<>();
}

