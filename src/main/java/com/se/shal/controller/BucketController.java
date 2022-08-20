package com.se.shal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.se.shal.util.cloudStorageHelper.CloudStorageHelper;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class BucketController {
    @Autowired
    CloudStorageHelper cloudStorageHelper;

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException, ServletException {
        return
                ResponseEntity.ok(this.cloudStorageHelper.getImageUrl(file, "shal-f28ac.appspot.com"));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test()  {
        return
                ResponseEntity.ok("Hello world");
    }
}
