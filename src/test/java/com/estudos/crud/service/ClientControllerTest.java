package com.estudos.crud.service;

import com.estudos.crud.controller.ClienteController;
import com.estudos.crud.entity.Client;
import com.estudos.crud.provider.ClientProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ClienteController.class)
public class ClientControllerTest {

    @MockBean
    private ClientService clientService;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnCreatedWhenInsertClient() throws Exception {
    when(clientService.insertClient(any(Client.class))).thenReturn(ClientProvider.mockClientResponse());

    ResultActions responseCurrent = mockMvc.perform(post("/client").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(ClientProvider.mockClientResponse())));

    responseCurrent.andExpect(MockMvcResultMatchers.status().is(201));

    }
    @Test
    public void shouldReturnCreatedWhenUpdateClient() throws Exception {
        when(clientService.updateClient(anyLong(), any(Client.class))).thenReturn(ClientProvider.mockClientUpdateRequest());

        ResultActions responseCurrent = mockMvc.perform(put("/client/1").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(ClientProvider.mockClientResponse())));

        responseCurrent.andExpect(MockMvcResultMatchers.status().is(201));

    }
    @Test
    public void shouldReturnSucessWhenGetClientById() throws Exception {
        when(clientService.findClientById(1L)).thenReturn(ClientProvider.mockClientResponse());

        ResultActions responseCurrent = mockMvc.perform(get("/client/1").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(ClientProvider.mockClientResponse())));

        responseCurrent.andExpect(MockMvcResultMatchers.status().is(200));

    }
    @Test
    public void shouldReturnSucessWhenGetAllClient() throws Exception {
        when(clientService.findAllClient()).thenReturn(List.of(ClientProvider.mockClientResponse()));

        ResultActions responseCurrent = mockMvc.perform(get("/client").
                contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(ClientProvider.mockClientResponse())));

        responseCurrent.andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldReturnNoContentWhenDeleteClient() throws Exception {
        doNothing().when(clientService).deleteClient(anyLong());

        ResultActions responseCurrent = mockMvc.perform(delete("/client/1"));

        responseCurrent.andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}
