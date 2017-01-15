/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.config.Constants;

/**
 *
 * @author alfredo
 * 
 * First abstract class, implementations are Kinder, Elementary and High
 */
public abstract class Student implements Comparable<Student>{

    private String id;

    private String type;
    private String name;
    private String gender;
    private String lastUpdate; //<year><month><day><hour><minute><second>
    private String orderBy;

    /**
     *
     * @param type
     * @param name
     * @param gender
     * @param lastUpdate
     */
    public Student(String type, String name, String gender, String lastUpdate){
        this.type = type;
        this.name = name;
        this.gender = gender;
        this.lastUpdate = lastUpdate;
        this.id = UUID.randomUUID().toString();
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        StringBuffer result = new StringBuffer();
        StringBuffer date = new StringBuffer();
        try {
            date = date.append(dateStringToFormatedString(this.lastUpdate));
        } catch (ParseException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = result.append("Type: ").append(this.type)
                .append(" Name: ").append(this.name)
                .append(" Gender: ").append(this.gender)
                .append(" Last Update: ").append(date);
        return result.toString();
    }
    
    public String dateStringToFormatedString(String rawDate) throws ParseException{
        String result ;
        
        try{
            StringBuffer ndate = new StringBuffer();
        
            ndate = ndate
                    .append(rawDate.substring(0, 4)).append("-")
                    .append(rawDate.substring(4, 6)).append("-")
                    .append(rawDate.substring(6, 8)).append(" ")
                    .append(rawDate.substring(8, 10)).append(":")
                    .append(rawDate.substring(10, 12)).append(":")
                    .append(rawDate.substring(12, 14));


            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
            // el que formatea
            SimpleDateFormat formateador = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
            //Date date = parseador.parse("31-03-2016");
            Date date = parseador.parse(ndate.toString());
            result = formateador.format(date);
            
        } catch(StringIndexOutOfBoundsException Ex) {
            System.out.println("String "+rawDate+" can not be parsed to a valid date");
            return null;
        }
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the lastUpdate
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }    

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
   
    
    /**
     *
     * @param object
     * @return
     * 
     * method overrided via delegate pattern.
     * compareTo is used for inserting at TreeSet
     * Uses Interface StudentByAttributeDelegate 
     * for accessing StudentByDateDelegate or StudentByNameDelegate
     * according to enum type
     */
    @Override
    public int compareTo(Student otherStudent){
        
        StudentByAttributeDelegate delegate = new StudentByNameDelegate();
        
        if(Constants.ORDER_BY_DATE.equals(orderBy)) {
            delegate = new StudentByDateDelegate();
        }
        
        return delegate.compareTo(otherStudent, this);
    }       
            
}
