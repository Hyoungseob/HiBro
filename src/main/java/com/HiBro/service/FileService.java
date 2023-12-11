package com.HiBro.service;


import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Log
public class FileService {

    //파일 업로드 처리 메서드
    public String uploadImgFile(String uploadPath, String originalFileName, byte[] fileData){

        UUID uuid = UUID.randomUUID();

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String saveFileName = uuid.toString() + extension;

        String fileUploadFUllUrl = uploadPath + "/" + saveFileName;

        try {
            FileOutputStream fos = new FileOutputStream(fileUploadFUllUrl);
            fos.write(fileData);
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return saveFileName;
    }

    //파일 삭제 처리 메서드
    public void deleteFile(String filePath){

        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
