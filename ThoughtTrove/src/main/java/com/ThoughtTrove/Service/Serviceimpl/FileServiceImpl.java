package com.ThoughtTrove.Service.Serviceimpl;

import com.ThoughtTrove.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();

        String rendomId = UUID.randomUUID().toString();
        String filename1 = rendomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path+ File.separator+filename1;
        File f = new File(path);
        if ((!f.exists())){
            f.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream() , Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+ File.separator +fileName;
        InputStream is = new FileInputStream(fullPath);
        //db logic to return input stream;
        return is;
    }
}
