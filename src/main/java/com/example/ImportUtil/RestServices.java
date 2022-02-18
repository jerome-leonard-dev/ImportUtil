package com.example.ImportUtil;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestServices {
    
    private static final Logger logger = LoggerFactory.getLogger(RestServices.class);
    
    @GetMapping(value = "/ping")
    public ResponseEntity<String> pong() 
    {
        logger.info("Démarrage des services OK .....");
        return new ResponseEntity<String>("Réponse du serveur: "+HttpStatus.OK.name(), HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        try {
            File uploadsPath = new File(UPLOAD_PATH);
            if (!uploadsPath.exists()) {
                //create upload folder if not exist.
                uploadsPath.mkdir();
            }
            logger.info("uploaded file " + file.getOriginalFilename());
            file.transferTo(new File(UPLOAD_PATH+file.getOriginalFilename()));
        } catch (Exception e) {
            System.err.println("Error while uploading files!");
            e.printStackTrace();
        }
    }

     
    /**
     * Location to save uploaded files on server
     */
    private static final String UPLOAD_PATH = "C:\\temp\\";
     

    @PostMapping("/UploadServlet")
    protected void doPost(@RequestParam("file") MultipartFile file) {
        try {
            File uploadsPath = new File(UPLOAD_PATH);
            if (!uploadsPath.exists()) {
                //create upload folder if not exist.
                uploadsPath.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}