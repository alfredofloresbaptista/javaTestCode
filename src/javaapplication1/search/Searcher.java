/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.search;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import javaapplication1.JavaApplication1;
import javaapplication1.model.Student;

/**
 *
 * @author alfredo
 */
public class Searcher extends Thread{
    
    private TreeSet results;      
    
    public Searcher() {   
        
        results = new TreeSet();
               
    }
    
    public TreeSet doSearch(String orderBy, Map<String, String> query) {
        Iterator data = JavaApplication1.data.iterator();        
        String dataValue;
        
        while(data.hasNext()){

            Student student = (Student) data.next();

            if(false == search(query, student)){
                continue;
            }
            
            student.setOrderBy(orderBy);
            results.add(student);
        }
        
        return results;
    }
    
    private Boolean search(Map<String, String> query, Student item){

        for(Map.Entry<String, String> cond : query.entrySet()){

            if(false == search(cond.getKey(), cond.getValue(), item)){
                    return false;
            }
        }
        
        return true;
    }
    
    private Boolean search(String field, String value, Student item){        
            
        switch(field){
            case "id":
                return value.equalsIgnoreCase(item.getId());
            case "name":
                return value.equalsIgnoreCase(item.getName());
            case "type":
                return value.equalsIgnoreCase(item.getType());
            case "gender":
                return value.equalsIgnoreCase(item.getGender());
            case "lastUpdate":
                return value.equalsIgnoreCase(item.getLastUpdate());
            default:
                return false;
        }                        
    }
   
}
