package com.estudos.crud.service;

import com.estudos.crud.entity.Client;
import com.estudos.crud.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void shouldFindAllClient(){
        List<Client> mockListClient = List.of(mockClient());
        when(clientRepository.findAll()).thenReturn(mockListClient);

        var clients =  clientService.findAllClient();

        Assertions.assertNotNull(clients);
        Assertions.assertEquals(1, clients.get(0).getId());
        Assertions.assertEquals(10000.00, clients.get(0).getIncome());
        Assertions.assertEquals("Rihanna", clients.get(0).getName());
        Assertions.assertEquals("000110011001", clients.get(0).getCpf());
        Assertions.assertEquals(2, clients.get(0).getChildren());

    }

    @Test
    public void shouldFindClientById(){

        when(clientRepository.findById(1L)).thenReturn(Optional.of(mockClient()));

        var currentResult =  clientService.findClientById(1L);

        Assertions.assertEquals(10000.00, currentResult.getIncome());
        Assertions.assertEquals("Rihanna",currentResult.getName());
        Assertions.assertEquals("000110011001", currentResult.getCpf());
        Assertions.assertEquals(2, currentResult.getChildren());

    }

    @Test
    public void shouldSaveClient(){
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(mockClient());

        var resultCurrent = clientService.insertClient(mockClient());

        Assertions.assertEquals(1L, resultCurrent.getId());
        Assertions.assertEquals(10000.00, resultCurrent.getIncome());
        Assertions.assertEquals("Rihanna", resultCurrent.getName());
        Assertions.assertEquals("000110011001", resultCurrent.getCpf());
        Assertions.assertEquals(2, resultCurrent.getChildren());

    }

    @Test
    public void shouldDeleteClient(){
        doNothing().when(clientRepository).deleteById(1L);
        clientService.deleteClient(1L);

        verify(clientRepository).deleteById(1L);

    }
    @Test
    public void shouldUpdateClient(){
        Client clientUpdate = new Client();
        clientUpdate.setId(1L);
        clientUpdate.setIncome(10000.00);
        clientUpdate.setName("Vanessa");
        clientUpdate.setCpf("000110011001");
        clientUpdate.setChildren(2);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(mockClient()));
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(clientUpdate);

        var resultCurrent = clientService.updateClient(1L, clientUpdate);
        Assertions.assertEquals(1L, resultCurrent.getId());
        Assertions.assertEquals("Vanessa", resultCurrent.getName());

    }

    private Client mockClient(){
        Client clientMock = new Client();
        clientMock.setId(1L);
        clientMock.setIncome(10000.00);
        clientMock.setName("Rihanna");
        clientMock.setCpf("000110011001");
        clientMock.setChildren(2);
       return clientMock;
    }
}
