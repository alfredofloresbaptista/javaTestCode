/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1.view;

import java.text.ParseException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.model.Student;

/**
 *
 * @author alfredo
 */
public class StudentView {

    public void showAllTreeSet(TreeSet data){
        
        for (Object studentObj : data){
            Student student = (Student) studentObj;
            try {
                System.out.println("Type: "+student.getType()+
                        " Name: "+student.getName()+
                        " Gender: "+student.getGender()+
                        " Last Update: "+student.dateStringToFormatedString(student.getLastUpdate()) ) ;
            } catch (ParseException ex) {
                Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }        
    }

    public void showObject(Object object){
        Student student = (Student) object;
        try {
            System.out.println("Type: "+student.getType()+
                    " Name: "+student.getName()+
                    " Gender: "+student.getGender()+
                    " Last Update: "+student.dateStringToFormatedString(student.getLastUpdate()) ) ;
        } catch (ParseException ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    
}
