/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author cb-rajuranjankumar
 */
public class Person
{
	private String name;
	private String address;
	private ArrayList<Phone> phone;
	Person()
	{
		phone=new ArrayList<Phone>();
	}
	Person(String name,String address,Phone phone)
	{
		this.name=name;
		this.address=address;
		this.phone=new ArrayList<Phone>();
		this.phone.add(phone);
	}

    public void addPhone(Phone phone)
    {
    this.phone.add(phone);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Phone> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<Phone> phone) {
        this.phone = phone;
    }
        
};
