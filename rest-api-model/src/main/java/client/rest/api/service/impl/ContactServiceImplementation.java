package client.rest.api.service.impl;

import org.springframework.stereotype.Service;

import client.rest.api.model.Contact;
import client.rest.api.repository.ContactRepository;
import client.rest.api.service.ContactService;

@Service
public class ContactServiceImplementation extends DefaultServiceImplementation<Contact, ContactRepository> implements ContactService{

}
