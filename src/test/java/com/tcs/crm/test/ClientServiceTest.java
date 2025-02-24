package com.tcs.crm.test;

import com.tcs.crm.dao.ClientRepository;
import com.tcs.crm.dto.ClientDto;
import com.tcs.crm.model.Client;
import com.tcs.crm.service.ClientService;
import com.tcs.crm.service.PasswordEcryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PasswordEcryptor passwordEcryptor;
    @InjectMocks
    private ClientService clientService;

    private ClientDto clientDto;
    private Client client;


    @Test
    void createClientTest() {
        when(passwordEcryptor.encrypt(clientDto.getPassword())).thenReturn("password");
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client client = clientService.createClient(clientDto);


        assert(client.getPassword().equals(clientDto.getPassword()));
        assert(client.getName().equals(clientDto.getName()));
        verify(clientRepository).save(client);

    }
    @Test
    void updatePasswordTest() {
        when(clientRepository.findClientByCi(clientDto.getCi())).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client updateClient = clientService.updatePassword(clientDto.getCi().toString(), clientDto);

        assert(updateClient.getPassword().equals(clientDto.getPassword()));
        verify(clientRepository.save(client));

    }
    @Test
    void deleteClientTest() {
        when(clientRepository.findClientByCi(clientDto.getCi())).thenReturn(Optional.of(client));
        clientService.deleteClient(clientDto.getCi());
        verify(clientRepository).delete(client);
    }
    @Test
    void getClientCiTest() {
        when(clientRepository.findClientByCi(clientDto.getCi())).thenReturn(Optional.of(client));
        String ci = clientService.getClientCi(123456789L);
        assert(ci.equals(clientDto.getCi()));

    }
    @Test
    void getClientNameTest() {
        when(clientRepository.findClientByCi("100200300")).thenReturn(Optional.of(client));

        String name = clientService.getClientName("100200300");

        assert(name.equals(clientDto.getName()));


    }


}
