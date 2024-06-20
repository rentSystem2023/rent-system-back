package com.rentcar.back.service.implementation;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rentcar.back.service.TestService;

@Service
public class TestServiceImplementation implements TestService {

    @Value("${file.url}")
    private String fileUrl;
    @Value("${file.path}")
    private String filePath;

    @Override

    //파일 업로드
    public String upload(MultipartFile file) {

        // 빈 파일인지 검증
        if (file.isEmpty())
            return null;

        // 원본 파일 명 (originalFileName = ".jpg")
        String originalFileName = file.getOriginalFilename();
        // 원본 파일의 확장자를 구함 (extension = ".jpg")
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // UUID 형식으로 임의의 문자열 생성 (uuid="98c8c2a5-c50d-4e96-a276-60aedf8daa86")
        String uuid = UUID.randomUUID().toString();
        // 저장시 사용할 파일명 생성 ("98c8c2a5-c50d-4e96-a276-60aedf8daa86.jpg")
        String saveFileName = uuid + extension;
        // 저장할 경로 생성 (프로퍼티즈에 지정되어 있음 (savePath =
        // "D:/fileupload/98c8c2a5-c50d-4e96-a276-60aedf8daa86.jpg"))
        String savePath = filePath + saveFileName;

        try {
            // 파일 저장
            file.transferTo(new File(savePath));

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        // 파일을 불러 올 수 있는 경로 생성 (url= "http://localhost:4000/file/98c8c2a5-c50d-4e96-a276-60aedf8daa86.jpg")
        String url = fileUrl + saveFileName;
        return url;
    }

    // 파일 가져오기
    @Override
    public Resource getFile(String fileName) {

        Resource resource = null;

        try{
            resource = new UrlResource("file:"+ filePath + fileName);
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
        return resource;
    }

}
