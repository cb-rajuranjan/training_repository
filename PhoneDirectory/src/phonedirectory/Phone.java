/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phonedirectory;

/**
 *
 * @author cb-raju
 */
public class Phone
{
	private String name;
	private String number;
        Phone()
        {}
	Phone(String name,String number)
	{
		this.name=name;
		this.number=number;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setNumber(String number)
	{
		this.number=number;
	}
	public String getName()
	{
		return this.name;
	}
	public String getNumber()
	{
		return this.number;
	}
};

