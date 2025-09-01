package ru.rec.cmev.controller;

import _class.singl.x.types._4_0.SMEVSimleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ru.rec.cmev.service.EmptySmevRequestService;
import ru.rec.cmev.service.SmevRequestService;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class XmlController {


    private final SmevRequestService smevRequestService;
    private final EmptySmevRequestService emptySmevRequestService;

    @GetMapping
    @RequestMapping(value = "/xml")
    public ResponseEntity<byte[]> getXml() throws DatatypeConfigurationException, JAXBException, IOException {
        MultipartFile file = smevRequestService.execute();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(String.format("%s.%s", file.getName(), file.getContentType()))
                .build();
        header.setContentDisposition(contentDisposition);
        header.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        return new ResponseEntity<>(file.getBytes(), header, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(value = "/emptyXml")
    public ResponseEntity<byte[]> getEmptyXml() throws DatatypeConfigurationException, JAXBException, IOException {
        MultipartFile file = emptySmevRequestService.execute();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(String.format("%s.%s", file.getName(), file.getContentType()))
                .build();
        header.setContentDisposition(contentDisposition);
        header.setAccessControlExposeHeaders(List.of("Content-Disposition"));
        return new ResponseEntity<>(file.getBytes(), header, HttpStatus.OK);
    }
}
