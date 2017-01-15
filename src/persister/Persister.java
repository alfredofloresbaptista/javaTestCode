/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persister;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import javaapplication1.JavaApplication1;
import javaapplication1.config.Constants;
import javaapplication1.factories.StudentFactory;
import javaapplication1.model.Student;
import javaapplication1.search.Searcher;

/**
 *
 * @author alfredo
 * 
 * Allows to execute some persistence features
 */
public class Persister {

    public void createStudent(String type, Map <String, Object> data){
        Student newStudent;
        newStudent = StudentFactory.getInstance().createStudent(type, data);
        insertIntoTreeSet(newStudent);
    }
    
    public void deleteStudent(Student studentToDelete){
        
        Map data;
        data = new HashMap<String, Object>();
        data.put("type", studentToDelete.getType());
            data.put("name", studentToDelete.getName());
            data.put("gender", studentToDelete.getGender());
            data.put("lastUpdate", studentToDelete.getLastUpdate());

        Student student = StudentFactory.getInstance()
                    .createStudent(studentToDelete.getType(), data);
        
        try {
            if (JavaApplication1.data.remove((Object) student)){
                System.out.println("Student "+student.getName()+" deleted");
            }else{
                System.out.println("Student not found");
            }
        }catch (ClassCastException ex ){
                System.out.println(" ClassCastException ");
        }
    }

    public void deleteStudentByName(String name){
        
        TreeSet<Student> studentsToDelete = getStudentByName(name);
        
        for(Student toDelete:studentsToDelete){
                                
            deleteStudent(toDelete);
        }
    }
    
    public void getStudentById(String id){
        Searcher searcher = new Searcher();
        Map criteria = new HashMap();
        criteria.put("id", id);
        TreeSet<Student> searchResult = searcher.doSearch(Constants.ORDER_BY_NAME, criteria);
    }    

    
    public TreeSet<Student> getStudentByName(String name){
        Searcher searcher = new Searcher();
        Map criteria = new HashMap();
        criteria.put("name", name);
        TreeSet<Student> searchResult = searcher.doSearch(Constants.ORDER_BY_NAME, criteria);
        return searchResult;
    }    
    
    
    public static void insertIntoTreeSet(Object object){
            JavaApplication1.data.add(object);
    }
    
}
