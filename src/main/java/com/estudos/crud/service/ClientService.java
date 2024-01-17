package com.estudos.crud.service;

import com.estudos.crud.entity.Client;
import com.estudos.crud.repository.ClientRepository;
import com.estudos.crud.service.exception.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client insertClient(Client client){
        clientRepository.save(client);
        return client;
    }
    public List<Client> findAllClient(){
        return clientRepository.findAll();
    }

    public Client findClientById(Long id){
        Optional<Client> client = clientRepository.
                findById(id);
        return  client.orElseThrow(() -> new ResourceNotFoundException(""));
    }

    public Client updateClient(Long id, Client client) {
            Client clientUpdate= findClientById(id);
            clientUpdate.setName(client.getName());
            clientUpdate.setChildren(client.getChildren());
            clientUpdate.setCpf(client.getCpf());
            clientUpdate.setBirthDate(client.getBirthDate());
            clientUpdate.setIncome(client.getIncome());
            clientRepository.save(clientUpdate);
            return clientUpdate;
    }

    public void deleteClient(Long id){
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }
}
