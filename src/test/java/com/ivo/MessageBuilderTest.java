/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author ivankayongo
 */
public class MessageBuilderTest {
    
    @Test
    public void testNameIvan() {

        MessageBuilder obj = new MessageBuilder();
        assertEquals("Hello Ivan", obj.getMessage("Ivan"));

    }

    @Test
    public void testNameEmpty() {

        MessageBuilder obj = new MessageBuilder();
        assertEquals("Please provide a name!", obj.getMessage(" "));

    }

    @Test
    public void testNameNull() {

        MessageBuilder obj = new MessageBuilder();
        assertEquals("Please provide a name!", obj.getMessage(null));

    }
    
}
