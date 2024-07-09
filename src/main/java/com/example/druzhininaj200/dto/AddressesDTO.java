package com.example.druzhininaj200.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AddressesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long client;
    private String ip;
    private Long addressid;
    private String mac;
    private String model;
    private String address;

}
