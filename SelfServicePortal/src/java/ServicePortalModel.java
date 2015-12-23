
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cb-raju
 */
public class ServicePortalModel 
{
    
    private final String url = "jdbc:mysql://localhost:3306/portal";   
    private final String user = "root";   
    private final String password = "root";   
    private PreparedStatement preparedStatement;
    private Statement statement,statement1,statement2;
    private ResultSet rs;
    public void newRegister(Register register) 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            preparedStatement=con.prepareStatement("insert into register(firstname,lastname,email,password) values(?,?,?,?)");
            preparedStatement.setString(1,register.getFirstName());
            preparedStatement.setString(2,register.getLastName());
            preparedStatement.setString(3,register.getEmail());
            preparedStatement.setString(4,SHAPassword.hashValueOfPassword(register.getPassword()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Register getRegister(String email,String pass)
    {
        Register register=new Register();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            System.out.println(email+" "+pass);
            rs=statement.executeQuery("select * from register where email='"+email+"' and password='"+SHAPassword.hashValueOfPassword(pass)+"'");
            if(rs!=null && rs.first())
            {
                register.setFirstName(rs.getString(2));
                register.setLastName(rs.getString(3));
                register.setEmail(rs.getString(4));
                register.setPassword(rs.getString(5));
                //System.out.println(register.toString());
                return register;
            }
            else
                return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteRegister(String email) 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            int result=statement.executeUpdate("delete from register where email='"+email+"'");
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void insertAddress(Address address,String email) 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            int id=0,result=0;
            rs=statement.executeQuery("select id from register where email='"+email+"'");
            
            if(rs!=null && rs.first())
            id=rs.getInt("id");
            
            statement1=con.createStatement();
            result=statement1.executeUpdate("delete from address where id="+id);
            
            //statement2=con.createStatement();
            //result=statement2.executeUpdate("insert into address values('"+address.getAddressLine1()+"','"+address.getAddressLine2()+"','"+address.getCity()+"','"+address.getState()+"','"+address.getZIP()+"','"+address.getCountry()+"',"+id+")");
            
            preparedStatement=con.prepareStatement("insert into address values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,address.getAddressLine1());
            preparedStatement.setString(2,address.getAddressLine2());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getState());
            preparedStatement.setString(5, address.getZIP());
            preparedStatement.setString(6, address.getCountry());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean checkEmailExistance(String email)
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            rs=statement.executeQuery("select * from register where email='"+email+"'");
            if(rs==null || !rs.first())
                return true;
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public void updateRegisterDetails(String newFirstName,String newLastName,String newEmail,String oldEmail)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            String query="update register set firstname='"+newFirstName+"', lastname='"+newLastName+"',email='"+newEmail+"' where email='"+oldEmail+"'";
            int result=statement.executeUpdate(query);
            System.out.println(result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean validateEmailPassword(String email,String pass)
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            System.out.println(email+" "+pass+" "+SHAPassword.hashValueOfPassword(pass));
            rs=statement.executeQuery("select * from register where email='"+email+"' and password='"+SHAPassword.hashValueOfPassword(pass)+"'");
            if(rs!=null && rs.first())
                return true;
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public Address getAddress(String email)
    {
        Address address=new Address();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            int id=0,result=0;
            rs=statement.executeQuery("select id from register where email='"+email+"'");
            
            if(rs!=null && rs.first())
            id=rs.getInt("id");
            rs.close();
            statement.close();
            
            statement=con.createStatement();
            rs=statement.executeQuery("select * from address where id="+id);
            if(rs!=null && rs.first())
            {
                address.setAddressLine1(rs.getString(1));
                address.setAddressLine2(rs.getString(2));
                address.setCity(rs.getString(3));
                address.setState(rs.getString(4));
                address.setZIP(rs.getString(5));
                address.setCountry(rs.getString(6));
                System.out.println(address.toString());
                return address;
            }
            else
                return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
