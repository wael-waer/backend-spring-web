package com.example.bbw_stage_4eme_bourawi.application;

import com.example.bbw_stage_4eme_bourawi.application.Application_entity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IAppService {
    Application_entity applyForJob(Application_entity application, long idApp, MultipartFile cvFile) throws IOException;
    Application_entity findById(long id);

    void deleteById(long id);

    Application_entity updateApp(Application_entity app);
}
