package ru.rec.cmev.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Builder
@Data
public class ContactInfoTypeDto {

    protected String mailingAddress;

    protected String email;

    protected String phoneNumber;

}
