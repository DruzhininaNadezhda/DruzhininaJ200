package com.example.druzhininaj200.service;


import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.entity.AddressesEntity;
import com.example.druzhininaj200.entity.ClientsEntity;
import com.example.druzhininaj200.repository.AddressRepository;
import com.example.druzhininaj200.repository.ClientRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;

import java.util.List;
import java.util.stream.Collectors;
@Singleton
public class AddressesService {
    @EJB
    private ClientRepository personRepository;
    @EJB
    private AddressRepository addressRepository;
    public AddressesDTO findAddressesDtoById(long addressId) {
        AddressesEntity entity = addressRepository.findAddressById(addressId);
        if (entity != null && entity.getClient() != null) {
            ClientsEntity client = entity.getClient();
            return AddressesDTO.builder()
                    .client(client != null ? client.getClientid() : null)
                    .ip(entity.getIp())
                    .mac(entity.getMac())
                    .model(entity.getModel())
                    .address(entity.getAddress())
                    .addressid(entity.getAddressid())
                    .build();
        }
        return null;
    }
    public List<AddressesDTO> findAll() {
        return addressRepository.findAll().stream().map(entity -> {
            //new ModelMapper().map(entity, AddressDTO.class)
                            ClientsEntity client = entity.getClient();
                            return AddressesDTO.builder()
                                    .client(client != null ? client.getClientid() : null)
                                    .addressid(entity.getAddressid())
                                    .ip(entity.getIp())
                                    .mac(entity.getMac())
                                    .model(entity.getModel())
                                    .address(entity.getAddress())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    public void create(AddressesDTO dto) {
        ClientsEntity client = personRepository.findClientById(dto.getClient());
        AddressesEntity entity = new AddressesEntity();
        entity.setClient(client);
        entity.setIp(dto.getIp());
        entity.setMac(dto.getMac());
        entity.setModel(dto.getModel());
        entity.setAddress(dto.getAddress());
        addressRepository.create(entity);
    }

    public void update(AddressesDTO dto) {
        ClientsEntity client = personRepository.findClientById(dto.getClient());
        AddressesEntity addresses = new AddressesEntity();
        addresses.setAddressid(dto.getAddressid());
        addresses.setClient(client);
        addresses.setIp(dto.getIp());
        addresses.setMac(dto.getMac());
        addresses.setModel(dto.getModel());
        addresses.setAddress(dto.getAddress());
        AddressesEntity addressBd = addressRepository.findAddressById(addresses.getAddressid());
        if(!addressBd.equals(addresses)){
            addressRepository.update(addresses);
        }
        }

    public void remove(AddressesDTO dto) {
        AddressesEntity addresses = new AddressesEntity();
        addresses.setAddressid(dto.getAddressid());
        AddressesEntity addressBd = addressRepository.findAddressById(addresses.getAddressid());
            addressRepository.remove(addressBd);
    }
}

