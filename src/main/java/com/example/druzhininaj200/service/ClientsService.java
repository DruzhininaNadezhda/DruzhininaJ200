package com.example.druzhininaj200.service;
import com.example.druzhininaj200.dto.AddressesDTO;
import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.entity.AddressesEntity;
import com.example.druzhininaj200.entity.ClientsEntity;
import com.example.druzhininaj200.repository.AddressRepository;
import com.example.druzhininaj200.repository.ClientRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ClientsService {
    @EJB
    private ClientRepository clientRepository;

    @EJB
    private AddressRepository addressRepository;

    public ClientsDTO findClientsDtoById(long clientID) {
        ClientsEntity clients = clientRepository.findClientById(clientID);
        if (clients != null && clients.getClientid() != null) {
            return ClientsDTO.builder()
                    .clientid(clients.getClientid())
                    .datecreatclient(clients.getDatecreatclient())
                    .clientName(clients.getClientName())
                    .typeclient(clients.getTypeclient())
                    .build();
        }
        return null;
    }
    public List<ClientsDTO> findAll() {
        List<ClientsDTO> clients = new LinkedList<>();
        clientRepository.findAll().forEach(entity -> {
                    Set<AddressesEntity> addresses = entity.getAddressEntities();
                    if (addresses == null|| addresses.isEmpty()) {
                            clients.add(ClientsDTO.builder()
                                    .clientName(entity.getClientName())
                                    .clientid(entity.getClientid())
                                    .typeclient(entity.getTypeclient())
                                    .datecreatclient(entity.getDatecreatclient())
                                    .build());}
                    else {addresses.forEach(address ->clients.add(ClientsDTO.builder()
                            .addressid(address.getAddressid())
                            .clientName(entity.getClientName())
                            .clientid(entity.getClientid())
                            .typeclient(entity.getTypeclient())
                            .datecreatclient(entity.getDatecreatclient())
                            .ip(address.getIp())
                            .mac(address.getMac())
                            .model(address.getModel())
                            .address(address.getAddress())
                            .build()));}
    }
        );
        return clients.stream().sorted(Comparator.comparingLong(ClientsDTO::getClientid)).collect(Collectors.toList());
}

    public void create(ClientsDTO dto){
        ClientsEntity entity = new ClientsEntity();
        entity.setClientName(dto.getClientName());
        entity.setTypeclient(dto.getTypeclient());
        entity.setDatecreatclient(dto.getDatecreatclient());
        clientRepository.create(entity);
    }
    public void update(ClientsDTO dto){
        ClientsEntity clients = new ClientsEntity();
        clients.setClientid(dto.getClientid());
        clients.setClientName(dto.getClientName());
        clients.setTypeclient(dto.getTypeclient());
        clients.setDatecreatclient(dto.getDatecreatclient());
        ClientsEntity clientsBd = clientRepository.findClientById(clients.getClientid());
        if (clientsBd.getAddressEntities() != null || !clientsBd.getAddressEntities().isEmpty()) {
            clientRepository.update(clients);

        }

    }
    public void remove(ClientsDTO dto) {
        ClientsEntity clients = new ClientsEntity();
        clients.setClientid(dto.getClientid());
        ClientsEntity clientsBd = clientRepository.findClientById(clients.getClientid());
        if (clientsBd.getAddressEntities() != null || !clientsBd.getAddressEntities().isEmpty()) {
            for (AddressesEntity addresses : clientsBd.getAddressEntities()) {
                addressRepository.remove(addresses);
            }
        }
        clientRepository.remove(clientsBd);
    }
}
