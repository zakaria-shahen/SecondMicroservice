package com.mycompany.secondmicroservice.event.listener.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LicenseMessageEventModel {

    private final String type = LicenseMessageEventModel.class.getTypeName();
    private final String action;
    private final String licenseId;
}
