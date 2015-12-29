/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author cb-rajuranjankumar
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
    
        
    public ArrayList<Person> getByName(String name)
    {
            persons=new ArrayList<Person>();
            try 
            {   
                Class.forName("com.mysql.jdbc.Driver");
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
                Class.forName("com.mysql.jdbc.Driver");
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
                Class.forName("com.mysql.jdbc.Driver");
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
    public void addPerson(String name,String address)
    {
            try 
            {   
                Class.forName("com.mysql.jdbc.Driver");
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
    
    public String getJSONString_ByName(String name)
    {
         
        return this.getJSONString(this.getByName(name));
    }
    public String getJSONString_ByPartialName(String name)
    {
         
        return this.getJSONString(this.getByPartialName(name));
    }
    
    public String getJSONString_ByPhone(String name)
    {
         return this.getJSONString(this.getByPhone(name)); 
    }
    public String getJSONString(ArrayList<Person> al)
    {
        
        Iterator it=al.iterator();
        Iterator it1;
        
       JSONObject jSONObject=new JSONObject();
       JSONArray persons=new JSONArray();
	while(it.hasNext())
	{
            JSONObject jPerson=new JSONObject();
            Person person=(Person)it.next();
            jPerson.put("Name",person.getName());
            jPerson.put("Address",person.getAddress());
            JSONArray phones=new JSONArray();
            it1=person.getPhone().iterator();
            while(it1.hasNext())
            {
               Phone phone=(Phone)it1.next();
               JSONObject jPhone=new JSONObject();
               jPhone.put(phone.getName(),phone.getNumber());
               phones.add(jPhone);
            }
            jPerson.put("Phone",phones);
            persons.add(jPerson);
	}
        jSONObject.put("Person", persons);
        return jSONObject.toJSONString();
       
    }
    public void updatePerson(String name,String address,String uName,String uAddress)
    {
            try 
            {   
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                rs=stmt1.executeQuery("select * from person where name='"+name+"' and address='"+address+"'");
                int person_id=0;
              
                if(rs.next())
                {
                    person_id=rs.getInt("person_id");
                    stmt1.executeUpdate("update person set name='"+uName+"' where person_id="+person_id);
                    stmt1.executeUpdate("update person set address='"+uAddress+"' where person_id="+person_id);
                    stmt1.close();
                }
                con.close();
            } 
            catch (Exception e) 
            {   
                    e.printStackTrace();   
            }   
        
    }
    public void updatePhone(String old_phone,String new_phone)
    {
            try 
            {   
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);
                stmt1=con.createStatement();
                rs=stmt1.executeQuery("select * from phone where phone_no="+old_phone);
                if(rs.next())
                {
                   
                    System.out.print(old_phone+" "+new_phone);
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

