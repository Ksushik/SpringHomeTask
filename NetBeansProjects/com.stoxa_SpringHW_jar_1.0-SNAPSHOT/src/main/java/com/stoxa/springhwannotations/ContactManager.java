/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springhwannotations;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author ksu
 */
@PropertySource("classpath:ContactBookMaximumSize.properties")
@Service
public class ContactManager implements ContactService {
    
    @Autowired
    private Environment env;
    int maxContactBookSize;
    private int contactsNumber;
    @Autowired
    private ContactDAO dao;
    private String smaxContactBookSize;

    public ContactManager() {
        
    }
    
    
    
    
    @PostConstruct
    public void init() {
        this.smaxContactBookSize=env.getProperty ("maxSize");
        System.err.println(smaxContactBookSize);
        this.maxContactBookSize = Integer.parseInt(smaxContactBookSize);
        this.contactsNumber=dao.getAllContacts().size();
        System.err.println(smaxContactBookSize);
        if (contactsNumber>=maxContactBookSize) {
        clear();    
        }
    }
    

    @Override
    public void addContact(Contact contact) {
        dao.addContact(contact);
    }
    
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }
 
    @Override
    public void deleteContact(Contact contact) {
        dao.deleteContact(contact);
    }
 
    public Contact getContact(String phone) {
        return dao.getContact(phone);
    }
    
    @Override
    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    @Override
    public void clearAll() {
        dao.clearAll();
    }
    
    public void clear() {
        dao.getAllContacts().remove(contactsNumber-1);
    }

    /**
     * @return the dao
     */
    public ContactDAO dao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ContactDAO dao) {
        this.dao = dao;
    }

    /**
     * @param maxContactBookSize the maxContactBookSize to set
     */
    public void setMaxContactBookSize(int maxContactBookSize) {
        this.maxContactBookSize = maxContactBookSize;
    }
    
    public void setSMaxContactBookSize(String smaxContactBookSize) {
        this.smaxContactBookSize = smaxContactBookSize;
    }
}
    
