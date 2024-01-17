package com.estudos.crud.controller;

import com.estudos.crud.entity.Client;
import com.estudos.crud.service.ClientService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClienteController {
    private final ClientService clientService;
    public ClienteController(ClientService clientService){
        this.clientService = clientService;
    }
    @PostMapping
    public ResponseEntity<Client> insertClient(@RequestBody Client client){
        client = clientService.insertClient(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").build().toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @GetMapping
    private List<Client> findAllClient(){
        return clientService.findAllClient();
    }
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id)  {
        return clientService.findClientById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client){
        client = clientService.updateClient(id, client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").build().toUri();
        return ResponseEntity.created(uri).body(client);
    }
}
