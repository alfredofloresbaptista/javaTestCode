/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.factories;

import java.util.Map;
import javaapplication1.model.Student;
import javaapplication1.model.StudentKinder;

/**
 *
 * @author alfredo
 */
public class StudentKinderBuilder implements StudentBuilder{
    
    @Override
    public Student createStudent(Map<String, Object> row) {
        
        String type = (String) row.get("type");
        String name = (String) row.get("name");
        String gender = (String) row.get("gender");
        String lastUpdate = (String) row.get("lastUpdate");
                
        
        StudentKinder newInstance = new StudentKinder(type, name, gender, lastUpdate);
        
        return newInstance;
    }
}
