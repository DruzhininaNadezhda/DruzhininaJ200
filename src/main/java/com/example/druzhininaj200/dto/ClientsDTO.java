package com.example.druzhininaj200.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
@Builder
@Data
public class ClientsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long clientid;
    private Long addressid;
    private String clientName;
    private String typeclient;
    private Date datecreatclient;
    private Long client;
    private String ip;
    private String mac;
    private String model;
    private String address;

}
