package com.example.bbw_stage_4eme_bourawi.job;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Job_Controller {
    private final IJobService jobService ;

    @GetMapping("/getalljobs")
    public List<Job_entity> getAll(){
        return jobService.findAll();


    }

    @PostMapping("/addjob")
    public Job_entity addjob (@RequestBody Job_entity j){
        return this.jobService.addjob(j);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Job_entity> updateJob(@PathVariable("id") long idJob, @RequestBody Job_entity updatedJob) {
        // Chercher le job par ID pour vérifier son existence
        Job_entity existingJob = jobService.findById(idJob);
        if (existingJob != null) {
            // Mettre à jour les informations du job
            updatedJob.set_id(idJob); // Assurez-vous que l'ID du job est défini dans l'entité
            Job_entity updated = jobService.updateJob(updatedJob);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/Delete/{id}")
    public void deleteF(@PathVariable("id") long idF){
        this.jobService.deleteById(idF);
    }

    @GetMapping("/getjob/{id}")
    public Job_entity getjob(@PathVariable("id")long idFoyer){
        return this.jobService.findById(idFoyer);
    }


}
