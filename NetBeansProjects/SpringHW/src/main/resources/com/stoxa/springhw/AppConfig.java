/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springhw;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Ksu
 */
@Configuration
@ImportResource("classpath:config.xml")
@PropertySource("classpath:ContactBookMaximumSize.properties")
public class AppConfig {

    @Autowired
    Contact contact1, contact2, contact3, contact4, contact5;

    @Value("${maxSize}")
    private int maxContactBookSize;

    @Bean
    public ContactDAO dao() {
        final ContactSimpleDAO dao = new ContactSimpleDAO();
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        dao.setContacts(contacts);
        return dao;
    }

    @Bean(initMethod = "init")
    public ContactService contactService() {
        ContactManager contactService = new ContactManager();
        contactService.setDao(dao());
        contactService.setMaxContactBookSize(maxContactBookSize);
        return contactService;
    }

    @Bean
    public ApplicationEventPublisherAware applicationEventPublisherAware() {
        return new ContactManager();
    }

    @Bean
    ApplicationListener<ClearEvent> applicationListener() {
        return new DeleteContactListener();
    }
}
