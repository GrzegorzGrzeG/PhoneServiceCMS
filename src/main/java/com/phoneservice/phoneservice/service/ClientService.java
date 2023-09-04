package com.phoneservice.phoneservice.service;

import com.phoneservice.phoneservice.entity.Client;
import com.phoneservice.phoneservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void newClient(Client client) {
        clientRepository.save(client);
    }
}
