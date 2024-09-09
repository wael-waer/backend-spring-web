package com.example.bbw_stage_4eme_bourawi.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/applications")
public class App_Controller {

    @Autowired
    private IAppService applicationService;

    @PostMapping("/apply/{_id}")
    public ResponseEntity<Application_entity> applyForJob(
            @PathVariable long _id,
            @RequestParam("candidateName") String candidateName,
            @RequestParam("email") String email,
            @RequestParam("cvFile") MultipartFile cvFile) {

        try {
            Application_entity application = new Application_entity();
            application.setCandidateName(candidateName);
            application.setEmail(email);

            Application_entity savedApplication = applicationService.applyForJob(application,_id, cvFile);
            return ResponseEntity.ok(savedApplication);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application_entity> findById(@PathVariable long id) {
        Application_entity application = applicationService.findById(id);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(application);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        applicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Application_entity> updateApp(@RequestBody Application_entity app) {
        Application_entity updatedApp = applicationService.updateApp(app);
        return ResponseEntity.ok(updatedApp);
    }
}
