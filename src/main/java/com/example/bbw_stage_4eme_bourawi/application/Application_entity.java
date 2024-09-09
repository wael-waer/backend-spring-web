package com.example.bbw_stage_4eme_bourawi.application;

import com.example.bbw_stage_4eme_bourawi.job.Job_entity;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Application_entity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idApp;
    String candidateName;
    String email;
    private String cvFilePath;
    @ManyToOne
    @JoinColumn(name = "_id", referencedColumnName = "_id", nullable = false)
    @JsonIgnore
    private Job_entity job;
}
