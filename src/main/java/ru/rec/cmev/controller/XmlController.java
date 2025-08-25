package ru.rec.cmev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ru.rec.cmev.service.SmevRequestService;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

@RestController
@RequiredArgsConstructor
public class XmlController {


    private final SmevRequestService smevRequestService;

    @GetMapping
    @RequestMapping(value = "/xml")
    public MultipartFile getXml() throws DatatypeConfigurationException, JAXBException {

        return smevRequestService.execute();

    }
}
