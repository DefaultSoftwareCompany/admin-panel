package com.dsc.service;

import com.dsc.model.Assets;
import com.dsc.repository.AssetsRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AssetsService {
    @Value("${upload.folder}")
    private String uploadFolder;
    private final Hashids hashids;
    private final AssetsRepository repository;

    public AssetsService(AssetsRepository repository) {
        this.hashids = new Hashids(getClass().getName(), 8);
        this.repository = repository;
    }

    public Assets getOne(Long assetsId) {
        return repository.getAssetsByAssetsId(assetsId);
    }

    public Assets save(MultipartFile multipartFile) throws IOException {
        Assets assets = new Assets();
        assets.setFileName(multipartFile.getOriginalFilename());
        assets.setContentType(multipartFile.getContentType());
        assets.setFileSize(multipartFile.getSize());
        assets.setExtension(getExtension(multipartFile.getOriginalFilename()));
        repository.save(assets);
        assets.setHashId(hashids.encode(assets.getAssetsId()));
        String path = "/img/" + assets.getHashId() + "." + assets.getExtension();
        File file = new File(uploadFolder + path);
        assets.setUploadPath(path);
        multipartFile.transferTo(file);
        return repository.save(assets);
    }

    public Assets delete(Assets assets) {
        File file = new File(uploadFolder + assets.getUploadPath());
        if (file.delete()) {
            repository.delete(assets);
        }
        return assets;
    }

    private static String getExtension(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
