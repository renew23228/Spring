package com.green.board7.Fileupload;

import com.green.board7.Fileupload.model.FileEntity;
import com.green.board7.Fileupload.model.FileLoadDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileuploadMapper {

    int insFile(FileEntity entity);
    FileEntity selFileById(FileLoadDto dto);
}
