/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.model;

/**
 *
 * @author alfredo
 * Interface for access compareTo in order to modify it
 */
public interface StudentByAttributeDelegate {
    
    public int compareTo(Student otherStudent, Student currentStudent);
}
