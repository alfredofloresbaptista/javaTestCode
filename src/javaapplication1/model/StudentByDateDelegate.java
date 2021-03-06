/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.model;

/**
 *
 * @author alfredo
 * Modifies compareTo without inheriting all
 */
public class StudentByDateDelegate implements StudentByAttributeDelegate{
    
    @Override
    public int compareTo(Student otherStudent, Student currentStudent) {
        
        if(null == otherStudent && null == currentStudent) {
            return 0;
        }
        
        if(null == otherStudent){
            return 1;
        }
        
        if(null == currentStudent) {
            return -1;
        }
        
        int test = otherStudent.getLastUpdate()
                    .compareTo(currentStudent.getLastUpdate());
        if (test==0){
            return 1;
        }
        return test;
    }
}
