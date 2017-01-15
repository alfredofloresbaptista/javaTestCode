/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.factories;

import java.security.InvalidParameterException;
import java.util.Map;
import javaapplication1.model.Student;

/**
 *
 * @author alfredo
 * According to coincidence with enumerated type creates choosen object
 * This class is instantiated as a singleton
 */
public class StudentFactory {
    
    private static StudentFactory instance = new StudentFactory();
    
    private StudentFactory(){
        
    }
    
    public static StudentFactory getInstance() {
        
        return instance;
    }
    
    public Student createStudent(String type, Map<String, Object> data) throws InvalidParameterException{
        return getBuilder(type).createStudent(data);
    }

    /*
    *   Obtains right builder for the enum type
    */
    private StudentBuilder getBuilder(String studentType) throws InvalidParameterException{
        
        if(StudentType.KINDER.getName().equals(studentType)){
            return new StudentKinderBuilder();
        }
        
        if(StudentType.ELEMENTARY.getName().equals(studentType)){
            return new StudentElementaryBuilder();
        }
        
        if(StudentType.HIGH.getName().equals(studentType)){
            return new StudentHighBuilder();
        }        
        
        throw new InvalidParameterException(studentType + "is not a valid parameter"); 
                
    }

    
}
