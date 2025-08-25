package ru.rec.cmev.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Builder
@Data
public class AttachInfoTypeDto {

    protected String docCode;

    protected String docName;

    protected String fileName;

    protected boolean isArchive;

    protected String uri;

    protected String fileType;

    protected String description;

}
