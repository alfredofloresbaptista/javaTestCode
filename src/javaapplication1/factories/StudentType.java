/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.factories;

/**
 *
 * @author alfredo
 * Enumerates different kind of types
 */
public enum StudentType {
    
    KINDER("Kinder"),
    ELEMENTARY("Elementary"),
    HIGH("High");
    
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    StudentType(String name){
        this.name = name;
    }

    
    
}
