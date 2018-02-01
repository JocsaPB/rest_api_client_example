package client.rest.api.service.impl;

import org.springframework.stereotype.Service;

import client.rest.api.model.Client;
import client.rest.api.repository.ClientRepository;
import client.rest.api.service.ClientService;

@Service
public class ClientServiceImplementation extends DefaultServiceImplementation<Client, ClientRepository> implements ClientService{

}
