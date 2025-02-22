package com.tcs.crm.dto;

import com.tcs.crm.enums.ClientStatus;
import com.tcs.crm.enums.Gender;
import lombok.*;

@Getter
@Setter//
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long clientId;
    private String name;
    private Gender gender;
    private String ci;
    private Integer age;
    private String address;
    private String phone;
    private String password;
    private ClientStatus clientStatus;

}
