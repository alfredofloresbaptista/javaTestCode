/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javaapplication1.model.Student;
import javaapplication1.factories.StudentFactory;
import java.util.TreeSet;
import java.io.FileReader;
import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import javaapplication1.config.Constants;
import javaapplication1.search.Searcher;
import javaapplication1.view.StudentView;
import persister.Persister;


/**
 *
 * @author alfredo
 */
public class JavaApplication1 {
    public static TreeSet data = new TreeSet();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length==0){
            System.out.println("No filename specified, please include one csv file.");
            return;
        }
        StudentView view = new StudentView();
                        
        Searcher searcher = new Searcher();
        Map <String,String> criteria = new HashMap();
        TreeSet<Student> searchResult = searcher.doSearch(Constants.ORDER_BY_NAME, criteria);
            
            
        /* ***********************
         * ***********************
         * Perform test requests
         * ***********************
         * ***********************
         */

        //Store the students in the system
        //read and load into TreeSet data
            
        try {
            
            loadIntoTreeSet(args[0]);
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println("Show all inserted: "+data.size());

        try {
            view.showAllTreeSet(data);
        } catch (StringIndexOutOfBoundsException ex){
            System.out.println("Exception "+ex.getMessage());
        }


        
        //Create new students
        Map newdata;
        newdata = new HashMap<String, Object>();
        newdata.put("type","Kinder");
        newdata.put("name","AAStudent");
        newdata.put("gender","F");
        newdata.put("lastUpdate","19810101111012");

        Persister persister = new Persister();
        persister.createStudent("Kinder", newdata);
        newdata.clear();
        
        System.out.println("");
        System.out.println("");
        System.out.println("Show all inserted (+AA_Student): "+data.size());
        view.showAllTreeSet(data);


                
        //Delete a specific student
        System.out.println("");
        System.out.println("");
        persister.deleteStudentByName("Anakin");
        System.out.println("Show all (-Anakin): "+data.size());
        view.showAllTreeSet(data);

                
        
        //Search for students
        
            //By name, sorted alphabetically
        criteria.put("name","Darth");
        searchResult = searcher.doSearch(Constants.ORDER_BY_NAME, criteria);
        System.out.println("");
        System.out.println("");
        System.out.println("Search by name Darth, sorted alphabetically: "+searchResult.size());
        view.showAllTreeSet(searchResult);
        criteria.clear();
        searchResult.clear();

        
        
            //By student type(Kinder, Elementary, High) sorting by date, most recent to leas recent
        criteria.put("type","Elementary");
        searchResult = searcher.doSearch(Constants.ORDER_BY_DATE, criteria);
        
        System.out.println("");
        System.out.println("");
        System.out.println("Search by type elementary sorted by date: "+searchResult.size());
        view.showAllTreeSet(searchResult);
        criteria.clear();
        searchResult.clear();


        
            //By gender and type (female elementary) sorting by date most recent to least recent
        criteria.put("gender","F");
        criteria.put("type","Elementary");
        searchResult = searcher.doSearch(Constants.ORDER_BY_DATE, criteria);
        
        System.out.println("");
        System.out.println("");
        System.out.println("Search by gender F type elementary sorted by date: "+searchResult.size());
        view.showAllTreeSet(searchResult);
        criteria.clear();
        searchResult.clear();

    }


    private static void loadIntoTreeSet(String fileName) throws FileNotFoundException, IOException{

        CSVReader reader;
        reader = new CSVReader(new FileReader(fileName), ',', '"', 0);
        
        //Read all rows at once
        List<String[]> allRows = reader.readAll();


        //Read CSV line by line and use the string array as you want
        
        for(String[] line : allRows){
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("type", line[0]);
            data.put("name", line[1]);
            data.put("gender", line[2]);
            data.put("lastUpdate", line[3]);
            
            Student student = StudentFactory.getInstance()
                    .createStudent(line[0], data);
            student.setOrderBy(Constants.ORDER_BY_NAME);
            
            Persister persister =new Persister();
            persister.insertIntoTreeSet(student);
        }
        
    }
        
}
