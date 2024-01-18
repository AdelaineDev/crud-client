package com.estudos.crud.provider;

import com.estudos.crud.entity.Client;

public class ClientProvider {

    public static  Client mockClientResponse(){
        Client clientResponse = new Client();
        clientResponse.setId(1L);
        clientResponse.setIncome(10000.00);
        clientResponse.setName("Rihanna");
        clientResponse.setCpf("000110011001");
        clientResponse.setChildren(2);
        return clientResponse;
    }

    public static  Client mockClientRequest(){
        Client clientRequest = new Client();
        clientRequest.setIncome(10000.00);
        clientRequest.setName("Rihanna");
        clientRequest.setCpf("000110011001");
        clientRequest.setChildren(2);
        return clientRequest;
    }

    public static  Client mockClientUpdateRequest(){
        Client clientUpdate = new Client();
        clientUpdate.setIncome(300000.00);
        clientUpdate.setName("Viola Davis");
        clientUpdate.setCpf("OOO11112223");
        clientUpdate.setChildren(2);
        return clientUpdate;
    }


}
