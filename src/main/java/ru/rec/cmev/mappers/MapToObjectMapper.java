package ru.rec.cmev.mappers;

import _class.empty.x.types._4_0.SMEVEmptyRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;
import ru.rec.cmev.dto.SmevRequestEmptyDataDto;


import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapToObjectMapper {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    @Mappings({
            @Mapping(target = "anyData", qualifiedByName = "mapToXml")
    })
    SMEVEmptyRequest toModificationInfoMap(SmevRequestEmptyDataDto request);

    @Named("mapToXml")
    default Object mapToXml(Map<String,Object> req) {
        return objectMapper.convertValue(req, Object.class);
    }
}
