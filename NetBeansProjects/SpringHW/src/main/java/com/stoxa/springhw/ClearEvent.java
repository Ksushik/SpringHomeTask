/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stoxa.springhw;

import org.springframework.context.ApplicationEvent;

/**
 *
 * @author stoxa
 */
class ClearEvent  extends ApplicationEvent {
    
    Contact deletedContact;

    public ClearEvent(Object source, Contact deletedContact) {
        super(source);
        this.deletedContact=deletedContact;
    }

    @Override
    public String toString() {
    return deletedContact.toString();
    }
}
