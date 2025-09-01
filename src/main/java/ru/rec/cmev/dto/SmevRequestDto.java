package ru.rec.cmev.dto;


import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class SmevRequestDto {

    private ContactInfoTypeDto contactInfoType;

    private AttachInfoTypeDto attachInfoType;

    protected HashMap<String, TypeAndValue> anyData;

    @Data
    @Builder
    public static class TypeAndValue {
        private String type;
        private String value;
    }
}
