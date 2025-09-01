package ru.rec.cmev.service;

import _class.empty.x.types._4_0.SMEVEmptyRequest;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.rec.cmev.dto.AttachInfoTypeDto;
import ru.rec.cmev.dto.ContactInfoTypeDto;

import ru.rec.cmev.dto.SmevRequestEmptyDataDto;
import ru.rec.cmev.mappers.MapToObjectMapper;
import ru.rec.cmev.mappers.MultipartFileMapper;

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
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmptySmevRequestService {
    public static final String KIF_NAMESPACE = "urn://x-empty-class-/types/4.0.0";
    private final MapToObjectMapper mapToObjectMapper;

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


        HashMap<String, Object> anyData = new HashMap<>();
        HashMap<String, Object> secondLevel = new HashMap<>();
        secondLevel.put("FIO", "Ivanov");
        HashMap<String, Object> thirdLevel = new HashMap<>();
        thirdLevel.put("Adress", "Luna");
        secondLevel.put("thirdLevel", thirdLevel);


        anyData.put("firstLevel", secondLevel);
        anyData.put("Organization", "REC");
        String data = anyData.toString();
        SmevRequestEmptyDataDto requestDto = SmevRequestEmptyDataDto.builder()
                .contactInfoType(contactInfoType)
                .attachInfoType(attachInfoType)
//                .anyData(data)
                .anyData(anyData)
                .build();

        return createEmptyDataMessage(requestDto);

    }

    private MultipartFile createEmptyDataMessage(SmevRequestEmptyDataDto request) throws JAXBException, DatatypeConfigurationException {

        String xmlRootName = SMEVEmptyRequest.class.getSimpleName();
        log.info("Create {}", xmlRootName);
        SMEVEmptyRequest requestSmev = mapToObjectMapper.toModificationInfoMap(request);

        JAXBElement<SMEVEmptyRequest> jaxbElement = new JAXBElement<>(new QName(KIF_NAMESPACE, xmlRootName), SMEVEmptyRequest.class, requestSmev);
        JAXBContext jaxbContext = JAXBContext.newInstance(_class.empty.x.types._4_0.SMEVEmptyRequest.class);
        String xmlString = jaxbConvertObjectToString(jaxbContext, jaxbElement, Collections.singletonMap(KIF_NAMESPACE, "ns1"), null);
        log.info("SmevEmptyDataMessage document = {} ", xmlString);

        return MultipartFileMapper.convertToMultipartFile(xmlString.getBytes(), "SMEVEmpty.xml");
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
        return sw.toString();
    }

}
