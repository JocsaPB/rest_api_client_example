package client.rest.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import client.rest.api.model.Client;
import client.rest.api.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientController extends DefaultController<Client, ClientService>{

}
