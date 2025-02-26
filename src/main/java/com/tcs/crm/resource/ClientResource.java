package com.tcs.crm.resource;

import com.tcs.crm.dto.ClientDto;
import com.tcs.crm.model.Client;
import com.tcs.crm.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor

public class ClientResource {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDto clientDto) {
        Client newClient = this.clientService.createClient(clientDto);
        return ResponseEntity.ok(newClient);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody ClientDto clientDto) {
        Client updatedClient =
                this.clientService.updateClient(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}/ci")
    public ResponseEntity<String> getClientCi(@PathVariable Long id) {
        String clientCi = clientService.getClientCi(id);
        return ResponseEntity.ok(clientCi);
    }

    @GetMapping("/name/{ci}")
    public ResponseEntity<String> getClientName(@PathVariable String ci) {
        String clientName = clientService.getClientName(ci);
        return ResponseEntity.ok(clientName);
    }

    @PatchMapping("/{id}/update-password")
    public ResponseEntity<Client> updatePassword(@PathVariable String id, @RequestBody ClientDto clientDto) {
        Client updatedClient = clientService.updatePassword(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }
}
