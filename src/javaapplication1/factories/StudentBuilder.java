/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.factories;

import java.util.Map;
import javaapplication1.model.Student;

/**
 *
 * @author alfredo
 * Interface for createStudent
 */
public interface StudentBuilder {
    
    public Student createStudent(Map<String, Object> row);
    
}
