package com.green.board7.Fileupload;

import com.green.board7.Fileupload.model.FileLoadDto;
import com.green.board7.Fileupload.model.FileuploadInsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileupload")
public class FileuploadController {
    private final Logger LOGGER;
    private final FileuploadService service;

    @Autowired
    public FileuploadController(FileuploadService service) {
        LOGGER = LoggerFactory.getLogger(FileuploadController.class);
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Resource> download(FileLoadDto dto) {
        Resource file = service.fileLoad(dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }


    // 모든 파일 다 받을 수 있음 . 폼데이터로 보낸다면 @PoastMapping 옆 부분 없어도됨Cm
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void fileupload(@RequestPart FileuploadInsDto dto,
                           @RequestPart MultipartFile img) { //json형식으로 받을거기 때문에 @RequestPart 쓰고 @PostMapping에 더 적어야함
        LOGGER.info("dto : " + dto);
        LOGGER.info("imgFileName: "+img.getOriginalFilename());
        service.fileUpload(dto, img);
    }

}
