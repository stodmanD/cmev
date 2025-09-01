package ru.rec.cmev.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Builder
@Data
public class SmevRequestEmptyDataDto {
    private ContactInfoTypeDto contactInfoType;

    private AttachInfoTypeDto attachInfoType;

    protected HashMap<String,  Object> anyData;
}
