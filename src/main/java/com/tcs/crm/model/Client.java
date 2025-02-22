package com.tcs.crm.model;

import com.tcs.crm.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Client extends Person {
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;


}
