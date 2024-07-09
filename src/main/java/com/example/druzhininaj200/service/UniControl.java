package com.example.druzhininaj200.service;

import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.entity.ClientsEntity;
import jakarta.ejb.Singleton;

import java.util.List;
import java.util.regex.Pattern;

@Singleton
public class UniControl {

        public long clientUniControl(String name, String type, List <ClientsDTO> clients) {
            for (ClientsDTO client : clients) {
                if (client.getClientName() != null || client.getTypeclient()!= null) {
                    if (client.getClientName().equals(name)&& client.getTypeclient().equals(type)) {
                        return client.getClientid();
                    }
                }
            } return 0L;
        }
    public long addressUniControl(String ip,Long clientID, List <AddressesDTO> addresses) {
        for (AddressesDTO address : addresses) {
            if (address.getIp() != null|| address.getClient()!=null) {
                if (address.getIp().equals(ip) && address.getClient()== clientID) {
                    return address.getAddressid();
                }
            }
        } return 0L;
    }
    public boolean CreatControl(String client_name,String IP, String mac) {
        boolean resultClient_name = true;
        boolean resultIP = true;
        boolean resultMac = true;
        resultClient_name = client_name.matches("[а-яёА-ЯЁ, .\\-]{1,100}") ? true : false;
        if (!resultClient_name) {
            return resultClient_name;
        }
        resultIP = IP.matches("([0-1][\\d][\\d].|2[0-4][\\d].|25[0-5].){3,3}([0-1][\\d][\\d]|2[0-4][\\d]|25[0-5])") ? true : false;
        if (!resultIP) {
            return resultIP;
        }
        resultMac = mac.matches("([\\dA-Za-z]{1,2}-){5,5}([\\dA-Za-z]{1,2})") ? true : false;
        if (!resultMac) {
            return resultMac;
        }
        return true;
    }

    }
