/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phonedirectory;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author cb-raju
 */
public class PhoneDirectoryModel 
{
    String url = "jdbc:mysql://localhost:3306/phone";   
    String user = "root";   
    String password = "root";   
    PreparedStatement stmt;
    Statement stmt1,stmt2;
    ResultSet rs,rs1;
    ArrayList<Person> persons;
    
    CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');	
    
    public ArrayList<Person> getByName(String name)
    {
            persons=new ArrayList<Person>();
            try 
            {   
                
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                stmt2=con.createStatement();
                rs=stmt1.executeQuery("select * from person where name='"+name+"'");
                    while(rs.next())
                    {
                        Person person=new Person();
                        person.setName(rs.getString(2));
                        person.setAddress(rs.getString(3));
                        rs1=stmt2.executeQuery("select * from phone where person_id="+rs.getInt(1));
                        while(rs1.next())
                        {
                            Phone phone=new Phone();
                            phone.setNumber(rs1.getString(1));
                            phone.setName(rs1.getString(2));
                            person.addPhone(phone);
                        }
                        persons.add(person);
                        rs1.close();
                    }
                    rs.close();
                con.close();
            }    
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }
            return persons;
    }
    public ArrayList<Person> getByPartialName(String name)
    {
                persons=new ArrayList<Person>();
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                stmt2=con.createStatement();
                rs=stmt1.executeQuery("select * from person where name like'"+name+"%'");
                    while(rs.next())
                    {
                        Person person=new Person();
                        person.setName(rs.getString(2));
                        person.setAddress(rs.getString(3));
                        rs1=stmt2.executeQuery("select * from phone where person_id="+rs.getInt(1));
                        while(rs1.next())
                        {
                            Phone phone=new Phone();
                            phone.setNumber(rs1.getString(1));
                            phone.setName(rs1.getString(2));
                            person.addPhone(phone);
                        }
                        persons.add(person);
                        rs1.close();
                    }
                    rs.close();
                con.close();
            }    
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }
            return persons;
    }
    public ArrayList<Person> getByPhone(String number)
    {
            persons=new ArrayList<Person>();
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                stmt2=con.createStatement();
                rs=stmt1.executeQuery("select person_id from phone where phone_no='"+number+"'");
                if(rs.next())
                {    
                  
                    int person_id=rs.getInt("person_id");
                    rs.close();
                    rs=stmt1.executeQuery("select * from person where person_id="+person_id);
                    if(rs.next())
                    {
                        Person person=new Person();
                        person.setName(rs.getString(2));
                        person.setAddress(rs.getString(3));
                        rs1=stmt2.executeQuery("select * from phone where person_id="+rs.getInt(1));
                        while(rs1.next())
                        {
                            Phone phone=new Phone();
                            phone.setNumber(rs1.getString(1));
                            phone.setName(rs1.getString(2));
                            person.addPhone(phone);
                        }
                        persons.add(person);
                        rs1.close();
                    }
                    rs.close();
                }    
                con.close();
            }    
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }
            return persons;
    }
    public static void consoleOutput(ArrayList<Person> al)
    {
	Iterator it=al.iterator();
        Iterator it1;
	while(it.hasNext())
	{
            Person person=(Person)it.next();
            System.out.println("Name="+person.getName());
            System.out.println("Address="+person.getAddress());
            it1=person.getPhone().iterator();
            while(it1.hasNext())
            {
		Phone phone=(Phone)it1.next();
		System.out.println(phone.getName()+" ::"+phone.getNumber());
            }
            System.out.println();
	}		
	
    }
    public void csvToDatabase(String csv,String column[])
    {
            
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                int i;
                CSVParser parser = new CSVParser(new FileReader(csv), format);
		for(CSVRecord record : parser)
                {         
                        stmt=con.prepareStatement("insert into person(name,address) values(?,?)");
                        stmt.setString(1,record.get(0));
                        stmt.setString(2, record.get(1));
                        stmt.executeUpdate();
                        stmt.close();
                        
                        rs=stmt1.executeQuery("select person_id from person");
                        rs.last();
                        int person_id=rs.getInt(1);
                        
                        
                        stmt=con.prepareStatement("insert into phone values(?,?,?)");
                        for(i=2;i<record.size();i++)
                        {   
                            stmt.setString(1, record.get(i));
                            stmt.setString(2,column[i]);
                            stmt.setInt(3,person_id);
                            stmt.executeUpdate();
                            
                        }
                        stmt.close();
                        
                }
		parser.close();
                con.close();
            } 
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }   
    
    }
    
    public void jsonToDatabase(String json,String column[])
    {
            
        JSONParser parser = new JSONParser();        
        try 
        {       
            Connection con = DriverManager.getConnection(url, user, password);
            stmt1=con.createStatement();
            Object obj = parser.parse(new FileReader(new File(json)));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray persons=(JSONArray)jsonObject.get("Person");
            Iterator it=persons.iterator();
            while(it.hasNext())
            {
       
                JSONObject jsonObject1=(JSONObject) it.next();
                stmt=con.prepareStatement("insert into person(name,address) values(?,?)");
                stmt.setString(1,jsonObject1.get("Name").toString());
                stmt.setString(2, jsonObject1.get("Address").toString());
                stmt.executeUpdate();
                stmt.close();
                        
                JSONArray phones=(JSONArray)jsonObject1.get("Phone");
                Iterator it1=phones.iterator();
                int i=2;
                
                rs=stmt1.executeQuery("select person_id from person");
                rs.last();
                int person_id=rs.getInt(1);
                        
                stmt=con.prepareStatement("insert into phone values(?,?,?)");
                while(it1.hasNext())
                {
                    JSONObject o=((JSONObject)it1.next());
                    stmt.setString(1, o.get(column[i]).toString());
                    stmt.setString(2,column[i]);
                    stmt.setInt(3,person_id);
                    stmt.executeUpdate();
                    i++;
                }
                stmt.close();
            } 
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addPerson(String name,String address)
    {
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt=con.prepareStatement("insert into person(name,address) values(?,?)");
                stmt.setString(1,name);
                stmt.setString(2,address);
                stmt.executeUpdate();
                stmt.close();
                con.close();
            } 
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }   
    
    }
    public void updatePerson(int person_id,String name,String address)
    {
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                rs=stmt1.executeQuery("select * from person where person_id="+person_id);
                if(rs.next())
                {
                   
                    stmt1.executeUpdate("update person set name='"+name+"' where person_id="+person_id);
                    stmt1.executeUpdate("update person set address='"+address+"' where person_id="+person_id);
                    stmt1.close();
                }
                con.close();
            } 
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }   
        
    }
    public void addPhone(int person_id,String phone,String phone_type)
    {
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt=con.prepareStatement("insert into phone values(?,?,?)");

                stmt.setString(1,phone);
                stmt.setString(2, phone_type);
                stmt.setInt(3,person_id);
                stmt.executeUpdate();
                stmt.close();
                con.close();
            } 
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }
    }
    public void updatePhone(String old_phone,String new_phone,String phone_type)
    {
            try 
            {   
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                rs=stmt1.executeQuery("select * from phone where phone_no="+old_phone);
                if(rs.next())
                {
                   
                    System.out.print(old_phone+" "+new_phone+" "+phone_type);
                    stmt1.executeUpdate("update phone set phone_type='"+phone_type+"' where phone_no="+old_phone);
                    stmt1.executeUpdate("update phone set phone_no='"+new_phone+"' where phone_no="+old_phone);
                    stmt1.close();
                }
                con.close();
            } 
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }
    }
    
}

