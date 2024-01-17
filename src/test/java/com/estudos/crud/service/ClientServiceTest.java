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

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;



    @Test
    public void shouldFindAllClient(){
        List<Client> mockListClient = List.of(mockClient());
        Mockito.when(clientRepository.findAll()).thenReturn(mockListClient);

        var clients =  clientService.findAllClient();

        Assertions.assertNotNull(clients);
        Assertions.assertEquals(1, mockListClient.get(0).getId());
        Assertions.assertEquals(10000.00, mockListClient.get(0).getIncome());
        Assertions.assertEquals("Rihanna", mockListClient.get(0).getName());
        Assertions.assertEquals("000110011001", mockListClient.get(0).getCpf());
        Assertions.assertEquals(2, mockListClient.get(0).getChildren());

    }


    @Test
    public void shouldFindClientById(){

        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(mockClient()));

       clientService.findClientById(1L);

        Assertions.assertEquals(10000.00, mockClient().getIncome());
        Assertions.assertEquals("Rihanna",mockClient().getName());
        Assertions.assertEquals("000110011001", mockClient().getCpf());
        Assertions.assertEquals(2, mockClient().getChildren());

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
