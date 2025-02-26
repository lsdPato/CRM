package com.tcs.crm.service;
import com.tcs.crm.dao.ClientRepository;
import com.tcs.crm.dto.ClientDto;
import com.tcs.crm.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor

public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEcryptor passwordEcryptor;

    public Client createClient(ClientDto clientDto){
        Client client = (Client) Client.builder()
                .id(clientDto.getClientId())
                .clientStatus(clientDto.getClientStatus())
                .password(clientDto.getPassword())
                .name(clientDto.getName())
                .age(clientDto.getAge())
                .ci(clientDto.getCi())
                .gender(clientDto.getGender())
                .address(clientDto.getAddress())
                .phone(clientDto.getPhone())
                .build();
        client.setPassword(passwordEcryptor.encrypt(client.getPassword()));

        return clientRepository.save(client);

    }

    public Client updateClient(String clientId, ClientDto clientDto){
        Client client = clientRepository.findClientByCi(clientId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            client.setName(clientDto.getName());
            client.setGender(clientDto.getGender());
            client.setCi(clientDto.getCi());
            client.setAge(clientDto.getAge());
            client.setAddress(clientDto.getAddress());
            client.setPhone(clientDto.getPhone());
            client.setClientStatus(clientDto.getClientStatus());
            return clientRepository.save(client);

    }
    public Client updatePassword(String clientId, ClientDto clientDto){
        Client client = clientRepository.findClientByCi(clientId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        client.setPassword(clientDto.getPassword());
        return clientRepository.save(client);

    }
    public void deleteClient(String clientId){
        Client client = clientRepository.findClientByCi(clientId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clientRepository.delete(client);

    }
    public String getClientCi(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        return clientOptional.map(Client::getCi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clientId));
    }
    public String getClientName(String ci) {
        Optional<Client> clientOptional = clientRepository.findClientByCi(ci);
        return clientOptional.map(Client::getName)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + ci));
    }

}
