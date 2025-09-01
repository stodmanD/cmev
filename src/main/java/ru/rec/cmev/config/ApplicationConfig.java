package ru.rec.cmev.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();
    }
    @Bean
    public Jaxb2Marshaller smevEmpty_Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        String[] packagesToScan= {"_class.empty.x.types._4_0"};
        marshaller.setPackagesToScan(packagesToScan);
        return marshaller;
    }
    @Bean
    public Unmarshaller SMEVEmpty_Unmarshaller() {
        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance(_class.empty.x.types._4_0.SMEVEmptyRequest.class).createUnmarshaller();
        } catch (JAXBException exception) {
            throw new RuntimeException(exception);
        }
        return unmarshaller;
    }

}
