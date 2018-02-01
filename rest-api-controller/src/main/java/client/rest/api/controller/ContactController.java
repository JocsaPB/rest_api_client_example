package client.rest.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import client.rest.api.model.Contact;
import client.rest.api.service.ContactService;

@RestController
@RequestMapping("contact")
public class ContactController extends DefaultController<Contact, ContactService> {

}
