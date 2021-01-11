package com.dsc.service;

import com.dsc.model.Image;
import com.dsc.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImageService {
    @Value("${image.url}")
    private String imageUrl;

    private final ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Image getOne(Long assetsId) throws Exception {
        Optional<Image> image = repository.findById(assetsId);
        if (image.isPresent()) return image.get();
        else throw new Exception("There is no image with such an id");
    }

    public Image save(MultipartFile multipartFile) throws Exception {
        if (!multipartFile.isEmpty()) {
            String extension = getExtension(multipartFile.getOriginalFilename());
            if (extension.equals("jpg") || extension.equals("png")) {
                Image image = new Image();
                image.setName(multipartFile.getOriginalFilename());
                image.setContentType(multipartFile.getContentType());
                image.setImageSize(multipartFile.getSize());
                image.setExtension(extension);
                ByteArrayResource imageFile = new ByteArrayResource(multipartFile.getBytes()) {
                    @Override
                    public String getFilename() {
                        return multipartFile.getOriginalFilename();
                    }
                };
                MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
                requestBody.add("image", imageFile);
                requestBody.add("password", "Sunnat5427#");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestBody, headers);
                RestTemplate restTemplate = new RestTemplate();
                Long imageId = restTemplate.exchange(imageUrl + "/save", HttpMethod.POST, request, Long.class).getBody();
                image.setUrl(imageId);
                repository.save(image);
                return repository.save(image);
            } else throw new Exception("The image must be in jpg or png format");
        } else throw new Exception("Select an image");
    }

    public void delete(Long imageId) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        Image image = repository.getOne(imageId);
        restTemplate.exchange(imageUrl + "/delete/" + image.getUrl(), HttpMethod.DELETE, request, Void.class);
        repository.deleteById(imageId);
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
