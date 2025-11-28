package ru.rec.cmev.service;


import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.rec.templateengine.dto.smev.AttachInfoTypeDto;
import ru.rec.templateengine.dto.smev.ContactInfoTypeDto;

import ru.rec.templateengine.dto.smev.SmevRequestDto;
import ru.rec.templateengine.mapper.smev.MultipartFileMapper;
import xsd.empty.SMEVEmptyRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmptyXmlService {
//    public static final String KIF_NAMESPACE = "urn://empty-xsd";
//
//    private final SmevRequestGeneratorService requestGeneratorService;
//
//    public MultipartFile execute() {
//
//        HashMap<String, String> anyData = new HashMap<>();
//        anyData.put("FIO", "Ivanov");
//        anyData.put("Adress", "Luna");
//        anyData.put("Organization", "REC");
//        List<String> list = new ArrayList<>();
//        list.add("first");
//        list.add("second");
//        list.add("third");
//        anyData.put("Perechen", list.toString());
//
//        return createEmptyDataMessage(requestGeneratorService.generateEmptyXmlRequest(), anyData);
//    }
//
//    private MultipartFile createEmptyDataMessage(SmevRequestDto request, Map<String, String> anyData) {
//        JAXBContext jaxbContext;
//        String xmlString;
//        String xmlRootName = SMEVEmptyRequest.class.getSimpleName();
//        log.info("Create {}", xmlRootName);
//        SMEVEmptyRequest requestSmev = createSMEVRequest(request);
//
//        JAXBElement<SMEVEmptyRequest> jaxbElement = new JAXBElement<>(new QName(KIF_NAMESPACE, xmlRootName), SMEVEmptyRequest.class, requestSmev);
//        try {
//            jaxbContext = JAXBContext.newInstance(xsd.empty.SMEVEmptyRequest.class, LinkedHashMap.class);
//
//            xmlString = jaxbConvertObjectToString(jaxbContext, jaxbElement, Collections.singletonMap(KIF_NAMESPACE, "ns1"), null, anyData);
//
//            log.info("SmevEmptyDataMessage document = {} ", xmlString);
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//
//        return MultipartFileMapper.convertToMultipartFile(xmlString.getBytes(), "SMEVEmpty.xml");
//    }
//
//    public static String jaxbConvertObjectToString(JAXBContext jaxbContext, Object objForConvert,
//                                                   Map<String, String> urisToPrefixes, Schema schema, Map<String, String> anyData) {
//        StringWriter sw = new StringWriter();
//        StringWriter ss = new StringWriter();
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        dbf.setNamespaceAware(true);
//        dbf.setXIncludeAware(true);
//        try {
//
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
//
//            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document document = db.parse(new InputSource(new StringReader(sw.toString())));
//            Element root = document.getDocumentElement();
//            Element element = document.createElement("AnyData");
//            for (Map.Entry<String, String> entry : anyData.entrySet()) {
//                Element child = document.createElement(entry.getKey());
//                child.appendChild(document.createTextNode(entry.getValue()));
//                element.appendChild(child);
//                root.appendChild(element);
//            }
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//
//            transformer.transform(new DOMSource(document), new StreamResult(ss));
//
//        } catch (JAXBException e) {
//            log.error("Ошибка преобразования объекта", e);
//            throw new RuntimeException(e);
//        } catch (TransformerException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        }
//
//        return ss.toString();
//    }
//
//    public SMEVEmptyRequest createSMEVRequest(SmevRequestDto request) {
//        SMEVEmptyRequest requestSmev = new SMEVEmptyRequest();
//
//        SMEVEmptyRequest.AttachInfoType attachInfoType = new SMEVEmptyRequest.AttachInfoType();
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
//        SMEVEmptyRequest.ContactInfoType contactInfoType = new SMEVEmptyRequest.ContactInfoType();
//        contactInfoType.setMailingAddress(request.getContactInfoType().getMailingAddress());
//        contactInfoType.setEmail(request.getContactInfoType().getEmail());
//        contactInfoType.setPhoneNumber(request.getContactInfoType().getPhoneNumber());
//
//        requestSmev.setContactInfoType(contactInfoType);
//
//        return requestSmev;
//    }
}
