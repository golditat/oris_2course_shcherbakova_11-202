package org.example.dto;


import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
public class OrderDTO {
    private Long id;

    private int userId;
    private int goodId;
    private String createDate;
}
