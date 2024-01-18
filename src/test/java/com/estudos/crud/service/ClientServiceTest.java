package com.estudos.crud.service;

import com.estudos.crud.entity.Client;
import com.estudos.crud.provider.ClientProvider;
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
        when(clientRepository.findAll()).thenReturn(List.of(ClientProvider.mockClientResponse()));

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

        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientProvider.mockClientResponse()));

        var currentResult =  clientService.findClientById(1L);

        Assertions.assertEquals(10000.00, currentResult.getIncome());
        Assertions.assertEquals("Rihanna",currentResult.getName());
        Assertions.assertEquals("000110011001", currentResult.getCpf());
        Assertions.assertEquals(2, currentResult.getChildren());

    }

    @Test
    public void shouldSaveClient(){
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(ClientProvider.mockClientResponse());

        var resultCurrent = clientService.insertClient(ClientProvider.mockClientRequest());

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
        when(clientRepository.findById(1L)).thenReturn(Optional.of(ClientProvider.mockClientResponse()));
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(ClientProvider.mockClientUpdateRequest());

        var resultCurrent = clientService.updateClient(1L, ClientProvider.mockClientUpdateRequest());
        Assertions.assertEquals(1L, resultCurrent.getId());
        Assertions.assertEquals("Viola Davis", resultCurrent.getName());

    }

}
