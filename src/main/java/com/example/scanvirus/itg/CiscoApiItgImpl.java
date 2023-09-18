package com.example.scanvirus.itg;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;

public class CiscoApiItgImpl {

    public static void main(String[] args) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setBearerAuth("l1srv9saop8rspm81m99gemv1h");

            File file = new File("/home/janayna/Pictures/Screenshots/teste.png");

            MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
            ContentDisposition contentDisposition = ContentDisposition
                    .builder("form-data")
                    .name("sample")
                    .filename("sample")
                    .build();
            fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(Files.readAllBytes(file.toPath()), fileMap);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("sample", fileEntity);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            final String uriString = UriComponentsBuilder.fromHttpUrl("https://panacea.threatgrid.com/api/v2/samples").queryParam("private", true).queryParam("tags", "'bia-whatsapp'").toUriString();
            new RestTemplate().exchange(uriString, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
