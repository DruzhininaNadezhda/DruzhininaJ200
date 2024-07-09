package com.example.druzhininaj200.service;

import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.entity.ClientsEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filter {
        public List<ClientsDTO> filterType(String type, List <ClientsDTO> clients) {
            System.out.println(type);
            if (type == null || type.isEmpty()) {
                return clients;
            } else {
                return clients.stream().filter(client -> client.getTypeclient().equals(type)).collect(Collectors.toList());
            }
        }

        public List<ClientsDTO> filterName(String name, List<ClientsDTO> clients) {
            if (name == null || name.isEmpty()) {
                return clients;
            } else {
                Stream<ClientsDTO> result = clients.stream().filter(client -> client.getClientName().toLowerCase().contains(name.toLowerCase()));
                Stream<ClientsDTO> result2 = clients.stream()
                        .filter(client -> client.getAddress()!=null)
                        .filter(client -> client.getAddress().toLowerCase().contains(name.toLowerCase()));
                return Stream.concat(result,result2).collect(Collectors.toList());
            }
        }
    }
