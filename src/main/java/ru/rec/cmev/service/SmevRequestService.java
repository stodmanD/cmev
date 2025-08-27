package ru.rec.cmev.service;

import _class.singl.x.types._4_0.SMEVSimleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.common.jaxb.NamespaceMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.rec.cmev.dto.AttachInfoTypeDto;
import ru.rec.cmev.dto.ContactInfoTypeDto;
import ru.rec.cmev.dto.SmevRequestDto;
import ru.rec.cmev.mappers.MultipartFileMapper;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmevRequestService {

    public static final String KIF_NAMESPACE = "urn://x-singl-class-/types/4.0.0";


    public MultipartFile execute() throws DatatypeConfigurationException, JAXBException {
        ContactInfoTypeDto contactInfoType = ContactInfoTypeDto.builder()
                .email("sdfdsf@sfdsd.ru")
                .phoneNumber("5465456465456")
                .mailingAddress("Rodina")
                .build();

        AttachInfoTypeDto attachInfoType = AttachInfoTypeDto.builder()
                .uri("adsfsdfsdf")
                .description("Anithing")
                .fileType("pdf")
                .isArchive(false)
                .docName("OOOOOOOOOOOOOO!")
                .build();

        HashMap<String, SmevRequestDto.TypeAndValue> anyAttribute = new HashMap<>();

        anyAttribute.put("person", SmevRequestDto.TypeAndValue.builder()
                .type("String")
                .value("Ivanov").build());
        anyAttribute.put("surname", SmevRequestDto.TypeAndValue.builder()
                .type("String")
                .value("Petro").build());
        anyAttribute.put("Number", SmevRequestDto.TypeAndValue.builder()
                .type("int")
                .value("4564564").build());


        SmevRequestDto requestDto = SmevRequestDto.builder()
                .contactInfoType(contactInfoType)
                .attachInfoType(attachInfoType)
                .anyAttribute(anyAttribute)
                .build();

        return createDataMessage(requestDto);

    }

    private MultipartFile createDataMessage(SmevRequestDto request) throws JAXBException, DatatypeConfigurationException {

        String xmlRootName = SMEVSimleRequest.class.getSimpleName();
        log.info("Create {}", xmlRootName);
        SMEVSimleRequest requestSmev = createSMEVSimleRequest(request);
        JAXBElement<SMEVSimleRequest> jaxbElement = new JAXBElement<>(new QName(KIF_NAMESPACE, xmlRootName), SMEVSimleRequest.class, requestSmev);
        JAXBContext jaxbContext = JAXBContext.newInstance(SMEVSimleRequest.class);
        String xmlString = jaxbConvertObjectToString(jaxbContext, jaxbElement, Collections.singletonMap(KIF_NAMESPACE, "ns1"), null);
        log.info("sellTaxDataMessage document = {} ", xmlString);

        return MultipartFileMapper.convertToMultipartFile(xmlString.getBytes(), "SMEVsimple.xml");
    }

    public static String jaxbConvertObjectToString(JAXBContext jaxbContext, Object objForConvert,
                                                   Map<String, String> urisToPrefixes, Schema schema) throws JAXBException {
        java.io.StringWriter sw = new StringWriter();
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, UTF_8.name());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            if (nonNull(urisToPrefixes)) {
                marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
                    @Override
                    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
                        return null;
                    }
                });
            }
            if (nonNull(schema)) {
                marshaller.setSchema(schema);
            }
            marshaller.marshal(objForConvert, sw);
        } catch (JAXBException e) {
            log.error("Ошибка преобразования объекта", e);
            throw e;
        }
        System.out.println("------------------------------");
        System.out.println(sw);
        System.out.println("------------------------------");
        return sw.toString();
    }

    public SMEVSimleRequest createSMEVSimleRequest(SmevRequestDto request) throws DatatypeConfigurationException {
        SMEVSimleRequest requestSmev = new SMEVSimleRequest();

        SMEVSimleRequest.AnyAttribute anyAttribute = new SMEVSimleRequest.AnyAttribute();
        for (Map.Entry<String, SmevRequestDto.TypeAndValue> entry : request.getAnyAttribute().entrySet()) {

            SMEVSimleRequest.AnyAttribute.Entry value = new SMEVSimleRequest.AnyAttribute.Entry();
            value.setName(entry.getKey());
            value.setType(entry.getValue().getType());
            value.setValue(entry.getValue().getValue());
            anyAttribute.getEntry().add(value);
        }

        requestSmev.setAnyAttribute(anyAttribute);

        SMEVSimleRequest.AttachInfoType attachInfoType = new SMEVSimleRequest.AttachInfoType();
        attachInfoType.setDescription(request.getAttachInfoType().getDescription());
        attachInfoType.setFileType(request.getAttachInfoType().getFileType());
        attachInfoType.setFileName(request.getAttachInfoType().getFileName());
        attachInfoType.setURI(request.getAttachInfoType().getUri());
        attachInfoType.setFileType(request.getAttachInfoType().getFileType());
        attachInfoType.setIsArchive(request.getAttachInfoType().isArchive());
        attachInfoType.setDocCode(request.getAttachInfoType().getDocCode());
        attachInfoType.setDocName(request.getAttachInfoType().getDocName());
        requestSmev.setAttachInfoType(attachInfoType);

        SMEVSimleRequest.ContactInfoType contactInfoType = new SMEVSimleRequest.ContactInfoType();
        contactInfoType.setMailingAddress(request.getContactInfoType().getMailingAddress());
        contactInfoType.setEmail(request.getContactInfoType().getEmail());
        contactInfoType.setPhoneNumber(request.getContactInfoType().getPhoneNumber());

        requestSmev.setContactInfoType(contactInfoType);


        return requestSmev;
    }
}
