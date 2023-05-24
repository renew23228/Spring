package com.green.board7.Fileupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileupload")
public class FileuploadController {
    private final Logger LOGGER;
    private FileuploadService service;

    @Autowired
    public FileuploadController(FileuploadService service) {
        LOGGER = LoggerFactory.getLogger(FileuploadController.class);
        this.service = service;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void fileupload(@RequestPart MultipartFile img) { //json형식으로 받을거기 때문에 @RequestPart 쓰고 @PostMapping에 더 적어야함
        LOGGER.info("imgFileName: "+img.getOriginalFilename());
    }
}
