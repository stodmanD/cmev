package ru.rec.cmev.service;

import _class.empty.x.types._4_0.SMEVEmptyRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.rec.cmev.dto.AttachInfoTypeDto;
import ru.rec.cmev.dto.ContactInfoTypeDto;

import ru.rec.cmev.dto.SmevRequestEmptyDataDto;
import ru.rec.cmev.mappers.MapToObjectMapper;
import ru.rec.cmev.mappers.MultipartFileMapper;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private MultipartFile createEmptyDataMessage(SmevRequestEmptyDataDto request) throws JAXBException {

        String xmlRootName = SMEVEmptyRequest.class.getSimpleName();
        log.info("Create {}", xmlRootName);
        SMEVEmptyRequest requestSmev = mapToObjectMapper.toModificationInfoMap(request);

        JAXBElement<SMEVEmptyRequest> jaxbElement = new JAXBElement<>(new QName(KIF_NAMESPACE, xmlRootName), SMEVEmptyRequest.class, requestSmev);

        JAXBContext jaxbContext = JAXBContext.newInstance(_class.empty.x.types._4_0.SMEVEmptyRequest.class, LinkedHashMap.class);

        String xmlString = jaxbConvertObjectToString(jaxbContext, jaxbElement, Collections.singletonMap(KIF_NAMESPACE, "ns1"), null);
//testing
//        Document document = convertStringToDocument(xmlString);
//        System.out.println("------------");
//
//        System.out.println( document.getDocumentElement());
//        System.out.println("---------------------");
//        XPathFactory xPathFactory = XPathFactory.newInstance();
//            XPath xpath = xPathFactory.newXPath();
//            String expression = "//*/ContactInfoType";
//        try {
//            NodeList nodes = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
//            System.out.println(nodes.getLength());
//        } catch (XPathExpressionException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        Element element = document.createElement("ChildElement");
//        element.setAttribute("entry", request.getAnyData().toString());
//        document.appendChild(element);
//        try {
//            StringWriter sw = new StringWriter();
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//
//            transformer.transform(new DOMSource(document), new StreamResult(sw));
//            System.out.println("++++++++++++++++++++");
//            System.out.println(sw.toString());
//            System.out.println("++++++++++++++++++++");
//        } catch (
//                TransformerConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (TransformerException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            XPathFactory xPathFactory = XPathFactory.newInstance();
//            XPath xpath = xPathFactory.newXPath();
//            String expression = "//*/SMEVEmptyRequest";
//
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = dbf.newDocumentBuilder();
//            Document document = builder.parse(new InputSource(new StringReader(xmlString)));
//            Node node = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
//            Document input = builder.parse(xmlString);
//
//            NodeList nodes = (NodeList) xpath.evaluate(expression, input, XPathConstants.NODESET);
//
//            Document documentq = builder.newDocument();
//            Element element = documentq.createElement("ChildElement");
//            element.setAttribute("ChildElement",request.getAnyData().toString());
//            document.appendChild(element);
//
//                StringWriter sw = new StringWriter();
//                TransformerFactory tf = TransformerFactory.newInstance();
//                Transformer transformer = tf.newTransformer();
//                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//
//                transformer.transform(new DOMSource(document), new StreamResult(sw));
//
//
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (TransformerConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (TransformerException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        } catch (XPathExpressionException e) {
//            throw new RuntimeException(e);
//        }

//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//


//
//
//        } catch (ParserConfigurationException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (SAXException e) {
//            throw new RuntimeException(e);
//        } catch (XPathExpressionException e) {
//            throw new RuntimeException(e);
//        }

//testing
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

    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        String gg ="<SMEVEmptyRequest xmlns=\"urn://x-empty-class-/types/4.0.0\">\n" +
                "        \n" +
                "    <ContactInfoType>\n" +
                "                \n" +
                "        <MailingAddress>Rodina</MailingAddress>\n" +
                "                \n" +
                "        <Email>sdfdsf@sfdsd.ru</Email>\n" +
                "                \n" +
                "        <PhoneNumber>5465456465456</PhoneNumber>\n" +
                "            \n" +
                "    </ContactInfoType>\n" +
                "  </SMEVEmptyRequest>";
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(gg)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
