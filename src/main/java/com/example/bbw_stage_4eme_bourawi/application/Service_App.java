package com.example.bbw_stage_4eme_bourawi.application;

import com.example.bbw_stage_4eme_bourawi.job.Job_entity;
import com.example.bbw_stage_4eme_bourawi.job.Jobrepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


    @Service
    public class Service_App implements IAppService {
        @Autowired
        private Apprepository apprepository;

        @Autowired
        private Jobrepository jobrepository;
        @Value("${cv.upload-dir}")
        private String uploadDir;


        @PostConstruct
        public void init() throws IOException {
            Files.createDirectories(Paths.get(uploadDir));
        }

        @Transactional
        public Application_entity applyForJob(Application_entity application, long _id, MultipartFile cvFile) throws IOException {
            Job_entity job = jobrepository.findById(_id)
                    .orElseThrow(() -> new RuntimeException("Job not found"));

            application.setJob(job);

            // Sauvegarder le fichier CV
            String fileName = saveCvFile(cvFile);
            application.setCvFilePath(fileName);

            return apprepository.save(application);
        }
        private String saveCvFile(MultipartFile cvFile) throws IOException {
            Path path = Paths.get(uploadDir + UUID.randomUUID().toString() + "_" + cvFile.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, cvFile.getBytes());
            return path.toString();
        }
        @Transactional
        public Application_entity findById(long id) {
            return apprepository.findById(id).orElse(null);
        }
        @Transactional
        public void deleteById(long id) {
            apprepository.deleteById(id);
        }

        public Application_entity updateApp(Application_entity app){
            if (apprepository.existsById(app.getIdApp())) {
                return apprepository.save(app);
            } else {
                throw new RuntimeException("Job not found with id " + app.getIdApp());
            }
        }

    }


