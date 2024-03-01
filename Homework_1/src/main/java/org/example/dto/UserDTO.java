package org.example.dto;

import lombok.*;

import java.sql.Date;


@Builder
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
}
