package ru.rec.cmev.dto;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@Builder
public class SmevRequestDto {

    private ContactInfoTypeDto contactInfoType;

    protected AttachInfoTypeDto attachInfoType;

    protected Map <String,String> anyAttribute;


}
