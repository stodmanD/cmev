package ru.rec.cmev.service;

import org.springframework.stereotype.Component;
import ru.rec.templateengine.dto.xmlElement.XmlElement;
import xsd.dynamic.SMEVSimpleRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class AnyTypeDataConverterService {
//
//    public SMEVSimpleRequest.AnyTypeData convertToAnyAttribute(List<XmlElement> xmlElementList) {
//        SMEVSimpleRequest.AnyTypeData attribute = new SMEVSimpleRequest.AnyTypeData();
//
//        for(XmlElement xmlElement : xmlElementList) {
//            convertToAnyAttribute(xmlElement, attribute);
//        }
//
//        attribute.getComplexAttribute().add()
//        return attribute;
//    }

//    private void convertToAnyAttribute(XmlElement xmlElement, AnyAttribute attribute) {
//        switch (xmlElement.getType()) {
//            case "Object" : {
//                convertToObject((XmlObjectElement) xmlElement, attribute);
//                break;
//            }
//            case "Array" : {
//                convertToArray((XmlArrayElement) xmlElement, attribute);
//                break;
//            }
//            default: {
//                convertToSimpleType(xmlElement, attribute);
//                break;
//            }
//        }
//    }
//
//    private void convertToObject(
//            XmlObjectElement xmlObjectElement,
//            AnyAttribute attribute
//    ) {
//        AnyAttribute.ObjectValue attributeObject = new AnyAttribute.ObjectValue();
//        attributeObject.setName(xmlObjectElement.name());
//
//        AnyAttribute objectFields = new AnyAttribute();
//        for(XmlElement e : xmlObjectElement.value()) {
//            convertToAnyAttribute(e, objectFields);
//        }
//
////        attributeObject.getValue().add(objectFields); todo
//        attribute.getObjectValue().add(attributeObject);
//    }
//
//    private void convertToArray(XmlArrayElement o, AnyAttribute attribute) {
//        convertToArray(o.getElementsType(), o.name(), o.value(), attribute);
//    }
//
//    private void convertToArray(
//        String arrayType,
//        String arrayName,
//        List<XmlElement> arrayValues,
//        AnyAttribute attribute
//    ) {
//        AnyAttribute.ArrayValue attributeArray = new AnyAttribute.ArrayValue();
//        attributeArray.setType(arrayType);
//        attributeArray.setName(arrayName);
//        for (XmlElement value : arrayValues) {
//            AnyAttribute arrayAttribute = new AnyAttribute();
//            convertToAnyAttribute(value, arrayAttribute);
////            attributeArray.getValue().add(arrayAttribute);
//        }
//        attribute.getArrayValue().add(attributeArray);
//    }
//
//    private void convertToSimpleType(
//            XmlElement xmlElement,
//            AnyAttribute attribute
//    ) {
//        if (xmlElement.getType().equals("String")) {
//            attribute.getStringValue().add(convertToStringType(xmlElement.name(), xmlElement.value()));
//            return;
//        }
//        if (xmlElement.getType().equals("Integer")) {
//            attribute.getIntegerValue().add(convertToIntegerType(xmlElement.name(), xmlElement.value()));
//            return;
//        }
//        if (xmlElement.getType().equals("Boolean")) {
//            attribute.getBoolValue().add(convertToBoolType(xmlElement.name(), xmlElement.value()));
//            return;
//        }
//        if (xmlElement.getType().equals("Decimal")) {
//            attribute.getDecimalValue().add(convertToDecimalType(xmlElement.name(), xmlElement.value()));
//            return;
//        }
//        if (xmlElement.getType().equals("Date")) {
//            attribute.getDateValue().add(convertToDateType(xmlElement.name(), xmlElement.value()));
//            return;
//        }
//        throw new IllegalArgumentException(String.format("Тип данных %s не поддерживается", xmlElement.getType()));
//    }
//
//    private AnyAttribute.StringValue convertToStringType(String name, Object value) {
//        AnyAttribute.StringValue stringValue = new attribute.any.AnyAttribute.StringValue();
//        stringValue.setValue((String) value);
//        stringValue.setName(name);
//        return stringValue;
//    }
//
//    private AnyAttribute.IntegerValue convertToIntegerType(String name, Object value) {
//        attribute.any.AnyAttribute.IntegerValue integerValue = new attribute.any.AnyAttribute.IntegerValue();
//        integerValue.setValue(new BigInteger(Integer.toString((Integer) value))); //todo нужна нормальная обработка
//        integerValue.setName(name);
//        return integerValue;
//    }
//
//    private AnyAttribute.BoolValue convertToBoolType(String name, Object value) {
//        attribute.any.AnyAttribute.BoolValue boolValue = new attribute.any.AnyAttribute.BoolValue();
//        boolValue.setValue((Boolean) value);
//        boolValue.setName(name);
//        return boolValue;
//    }
//
//    private AnyAttribute.DecimalValue convertToDecimalType(String name, Object value) {
//        AnyAttribute.DecimalValue decimalValue = new attribute.any.AnyAttribute.DecimalValue();
//        decimalValue.setValue((BigDecimal) value);
//        decimalValue.setName(name);
//        return decimalValue;
//    }
//
//    private AnyAttribute.DateValue convertToDateType(String name, Object value)  {
//        //todo нужна конвертация входящей даты в формат XMLGregorianCalendar (какой формат входящей даты?)
//
//        Date date = (Date) value;
//
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        gregorianCalendar.setTime(date);
//
//        AnyAttribute.DateValue dateValue = new attribute.any.AnyAttribute.DateValue();
//
//        try {
//            dateValue.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
//        }
//        catch (DatatypeConfigurationException e) {
//            dateValue.setValue(null);
//        }
//
//        dateValue.setName(name);
//        return dateValue;
//    }


}
