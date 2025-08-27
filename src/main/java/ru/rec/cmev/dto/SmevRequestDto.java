package ru.rec.cmev.dto;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class SmevRequestDto {

    private ContactInfoTypeDto contactInfoType;

    private AttachInfoTypeDto attachInfoType;

    protected HashMap<String, TypeAndValue> anyAttribute;

    @Data
    @Builder
    public static class TypeAndValue {
        private String type;
        private String value;
    }
}
