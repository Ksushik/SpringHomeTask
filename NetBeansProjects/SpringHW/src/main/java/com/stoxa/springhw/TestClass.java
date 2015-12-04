/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springhw;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ksu
 */
public class TestClass {
   public static void main(String[] args) {
         ApplicationContext context = 
      new AnnotationConfigApplicationContext(AppConfig.class);
        Contact c1 = (Contact) context.getBean("contact1");
        Contact c2 = (Contact) context.getBean("contact2");
        Contact c3 = (Contact) context.getBean("contact3");
        Contact c4 = (Contact) context.getBean("contact4");
        Contact c5 = (Contact) context.getBean("contact5");
        ContactService contactService = (ContactService) context.getBean("contactService");
 
        System.out.println("ADD CONTACT ==============");
        contactService.addContact(c1);
        contactService.addContact(c2);
        contactService.addContact(c3);
        contactService.addContact(c4);
        contactService.addContact(c5);
        List<Contact> result1 = contactService.getAllContacts();
        for(Contact c : result1) {
            System.out.println(c);
        }
        System.out.println("******************************************");
        
        System.out.println("UPDATE CONTACT ==============");
        Contact change = new Contact ("Алексей", "Соколов", "+380934567894", "sokolov@yandex.ru");
        contactService.updateContact(change);
        List<Contact> result2 = contactService.getAllContacts();
        for(Contact c : result2) {
            System.out.println(c);
        }
        System.out.println("******************************************");
        
        System.out.println("DELETE CONTACT ==============");
        contactService.deleteContact(c3);
        List<Contact> result3 = contactService.getAllContacts();
        for(Contact c : result3) {
            System.out.println(c);
        }
        System.out.println("******************************************");        
 
        System.out.println("GET CONTACT ==============");
        Contact contact = contactService.getContact("+380674560901");
        System.out.println(contact);
        
        System.out.println("******************************************");        
        
        System.out.println("CLEAR ALL CONTACTS ==============");
        contactService.clearAll();
        List<Contact> result4 = contactService.getAllContacts();
        for(Contact c : result4) {
            System.out.println(c);
        }
    }
   }

