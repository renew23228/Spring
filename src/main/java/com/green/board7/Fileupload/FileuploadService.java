package com.green.board7.Fileupload;

import com.green.board7.Fileupload.model.FileEntity;
import com.green.board7.Fileupload.model.FileLoadDto;
import com.green.board7.Fileupload.model.FileuploadInsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
public class FileuploadService {

    @Value("${file.dir}") // yaml파일에 설정한 내용을 들고 올 수 있게 해줌. D:\download 이 내용을 fileDir에 주입시켜줌
    private String fileDir;

    private final FileuploadMapper mapper;

    @Autowired
    public FileuploadService(FileuploadMapper mapper) {
        this.mapper = mapper;
    }

    public Resource fileLoad(FileLoadDto dto) {
        FileEntity entity = mapper.selFileById(dto);
        try {
            File file = new File(fileDir + entity.getPath());
            Resource resource = new UrlResource(file.toURI());
            if(resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void fileUpload(FileuploadInsDto dto, MultipartFile img) {
        System.out.println("fileDir : "+ fileDir);

        //원래 파일 이름 추출 : 밑에서 확장자를 빼내기 위해서
        String originFileName = img.getOriginalFilename();

        //파일 이름으로 사용할 uuid 생성
        String uuid = UUID.randomUUID().toString();

        int dotIdx = originFileName.lastIndexOf("."); // 파일이름에서 확장자 추출
        String ext = originFileName.substring(dotIdx); // 추출한 확장자

        // 나중에  savedFileName, savedFilePath 둘 중 하나를 db에 저장할지 선택해야함
        String savedFileName = uuid + ext;
        String savedFilePath = fileDir + savedFileName;

        File file = new File(savedFilePath);
        // file.exists(); // 파일이 있는지 없는지 확인해줌. 있으면 true/없으면 false
        try {
            img.transferTo(file); //file 경로로 메모리에 있는걸 파일로 만들어줌
            // builder 패턴 : 생성자를 이용해서 원하는 값만 가진 객체를 만듬
            FileEntity entity = FileEntity.builder()
                    .path(savedFileName)
                    .uploader(dto.getUploader())
                    .levelValue(dto.getLevelValue())
                    .build();

            mapper.insFile(entity);

        } catch (IOException e) {
            e.printStackTrace(); // 원래는 로그 찍는 부분
        }

    }


}
