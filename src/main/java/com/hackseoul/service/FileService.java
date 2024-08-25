package com.hackseoul.service;
import org.springframework.util.Base64Utils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveBase64File(String base64Data, String originalFileName) throws IOException {

        String base64Prefix = "data:image/jpg;base64,";
        String substring = base64Data.substring(base64Prefix.length());

        // Base64 디코딩
        byte[] decodedBytes = Base64Utils.decodeFromString(substring);

        // 파일 확장자 추출
        String extension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            extension = originalFileName.substring(i);
        }

        // 고유한 파일 이름 생성 (UUID 사용)
        String uniqueFileName = UUID.randomUUID().toString() + extension;

        // 파일 저장 경로 설정
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        File file = path.toFile();

        // 디렉토리 생성
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // 파일에 데이터 쓰기
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }

        // 저장된 파일 경로 반환
        return path.toAbsolutePath().toString();
    }
}

