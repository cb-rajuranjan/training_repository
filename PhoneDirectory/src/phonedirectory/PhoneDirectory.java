/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phonedirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 *
 * @author cb-raju
 */
public class PhoneDirectory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PhoneDirectoryModel phoneDirectoryModel=new PhoneDirectoryModel();
        String csv = "/home/cb-raju/NetBeansProjects/PhoneDirectory/src/phonedirectory/data.csv";
        String json = "/home/cb-raju/NetBeansProjects/PhoneDirectory/src/phonedirectory/person.json";
        String [] column = "name,address,Mobile,Home,Work".split(",");
        String name=null,phone=null,new_phone=null,phone_type=null,address=null;
        int person_id;
	int choice=0;
	do
	{
		System.out.println("1.for view by given Name");
		System.out.println("2.for view records partial match by given Name");
		System.out.println("3.for view record by given Phone");
                System.out.println("4.for store csv data into database");
                System.out.println("5.for store JSON data into database");
                System.out.println("6.for add Person");
                System.out.println("7.for update Person");
                System.out.println("8.for add Phone");
                System.out.println("9.for update Phone");
		System.out.println("0.for Exit");
		System.out.print("Enter Choice=");
		choice=Integer.parseInt(br.readLine());
		switch(choice)
		{		
			case 1: System.out.print("Enter name=");
                		name=br.readLine();
				phoneDirectoryModel.consoleOutput(phoneDirectoryModel.getByName(name));
				break;
			case 2: System.out.print("Enter name=");
				name=br.readLine();
				phoneDirectoryModel.consoleOutput(phoneDirectoryModel.getByPartialName(name));
				break;
			case 3:System.out.print("Enter phone=");
				phone=br.readLine();
				phoneDirectoryModel.consoleOutput(phoneDirectoryModel.getByPhone(phone));
				break;
                        case 4:phoneDirectoryModel.csvToDatabase(csv, column); 
                                break;
                        case 5:phoneDirectoryModel.jsonToDatabase(json, column); 
                                break;
                        case 6:System.out.print("Enter name and Address=");
                                name=br.readLine();
                                address=br.readLine();
                                phoneDirectoryModel.addPerson(name,address);
                                break;
                        case 7:System.out.print("Enter person_id=");
                                person_id=Integer.parseInt(br.readLine());
                                System.out.print("Enter new Name and address=");
                                name=br.readLine();
                                address=br.readLine();
                                phoneDirectoryModel.updatePerson(person_id,name,address);
                                break;
                        case 8:System.out.print("Enter person_id,phone_no and phone_type=");
                                person_id=Integer.parseInt(br.readLine());
                                phone=br.readLine();
                                phone_type=br.readLine();
                                phoneDirectoryModel.addPhone(person_id,phone,phone_type);
                                break;
                        case 9:System.out.print("Enter old phone_no=");
                                phone=br.readLine();
                                System.out.print("Enter new phone_no and it's type=");
                                new_phone=br.readLine();
                                phone_type=br.readLine();
                                phoneDirectoryModel.updatePhone(phone,new_phone,phone_type);
                                break;
		}
	}while(choice!=0);

    }
    
}
