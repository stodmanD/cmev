package ru.rec.cmev.service;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.rec.templateengine.dto.smev.SmevRequestSimpleDto;
import ru.rec.templateengine.mapper.smev.MultipartFileMapper;
import ru.rec.templateengine.service.smev.AnyTypeDataConverterService;
import ru.rec.templateengine.service.smev.SmevRequestGeneratorService;
import xsd.dynamic.SMEVSimpleRequest;
import xsd.singl.SMEVSimleRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnotherDynamicXmlService {

//    public static final String KIF_NAMESPACE = "urn://any-attribute";
//
//    private final SmevRequestGeneratorService requestGeneratorService;
//
//    private final AnyTypeDataConverterService anyTypeDataConverterService;
//
//    public MultipartFile execute() throws DatatypeConfigurationException, JAXBException {
//        return createDataMessage(requestGeneratorService.generateAnotherXmlRequest());
//    }
//
//    private MultipartFile createDataMessage(SmevRequestSimpleDto request) throws JAXBException, DatatypeConfigurationException {
//
//        String xmlRootName = SMEVSimleRequest.class.getSimpleName();
//        log.info("Create {}", xmlRootName);
//        SMEVSimpleRequest requestSmev = createSMEVSimleRequest(request);
//        JAXBElement<SMEVSimpleRequest> jaxbElement = new JAXBElement<>(new QName(KIF_NAMESPACE, xmlRootName), SMEVSimpleRequest.class, requestSmev);
//        JAXBContext jaxbContext = JAXBContext.newInstance(SMEVSimpleRequest.class);
//        String xmlString = jaxbConvertObjectToString(jaxbContext, jaxbElement, Collections.singletonMap(KIF_NAMESPACE, "ns1"), null);
//        log.info("AnotherChangeRequest document = {} ", xmlString);
//
//        return MultipartFileMapper.convertToMultipartFile(xmlString.getBytes(), "SMEVAnotherChange.xml");
//    }
//
//    public static String jaxbConvertObjectToString(JAXBContext jaxbContext, Object objForConvert,
//                                                   Map<String, String> urisToPrefixes, Schema schema) throws JAXBException {
//        StringWriter sw = new StringWriter();
//        try {
//            Marshaller marshaller = jaxbContext.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_ENCODING, UTF_8.name());
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            if (nonNull(urisToPrefixes)) {
//                marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
//                    @Override
//                    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
//                        return null;
//                    }
//                });
//            }
//            if (nonNull(schema)) {
//                marshaller.setSchema(schema);
//            }
//            marshaller.marshal(objForConvert, sw);
//        } catch (JAXBException e) {
//            log.error("Ошибка преобразования объекта", e);
//            throw e;
//        }
//        return sw.toString();
//    }
//
//    public SMEVSimpleRequest createSMEVSimleRequest(SmevRequestSimpleDto request) throws DatatypeConfigurationException {
//
//        SMEVSimpleRequest requestSmev = new SMEVSimpleRequest();
//        requestSmev.setAnyTypeData(
//                anyTypeDataConverterService.convertToAnyAttribute(requestGeneratorService.generateXmlElement())
//        );
//
//        SMEVSimpleRequest.AttachInfoType attachInfoType = new SMEVSimpleRequest.AttachInfoType();
//        attachInfoType.setDescription(request.getAttachInfoType().getDescription());
//        attachInfoType.setFileType(request.getAttachInfoType().getFileType());
//        attachInfoType.setFileName(request.getAttachInfoType().getFileName());
//        attachInfoType.setURI(request.getAttachInfoType().getUri());
//        attachInfoType.setFileType(request.getAttachInfoType().getFileType());
//        attachInfoType.setIsArchive(request.getAttachInfoType().isArchive());
//        attachInfoType.setDocCode(request.getAttachInfoType().getDocCode());
//        attachInfoType.setDocName(request.getAttachInfoType().getDocName());
//        requestSmev.setAttachInfoType(attachInfoType);
//
//        SMEVSimpleRequest.ContactInfoType contactInfoType = new SMEVSimpleRequest.ContactInfoType();
//        contactInfoType.setMailingAddress(request.getContactInfoType().getMailingAddress());
//        contactInfoType.setEmail(request.getContactInfoType().getEmail());
//        contactInfoType.setPhoneNumber(request.getContactInfoType().getPhoneNumber());
//
//        requestSmev.setContactInfoType(contactInfoType);
//
//
//        return requestSmev;
//    }
}
