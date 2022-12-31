
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.*;
 @SuppressWarnings("serial")
class Employee implements Serializable{
	
	int id;
	String firstName;
	String lastName;
	String dob;
	String title;
	String startDate;
	String endDate;
	String email_id;  
	String kebID;
	
	        
	
	public Employee(int id, String firstName, String lastName,String dob, String title,String startDate, String endDate, String email_id,String kebID)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName=lastName;
		this.dob = dob;
		this.title = title;
		this.startDate=startDate;
		this.endDate=endDate;
		this.email_id = email_id;
		this.kebID=kebID;
	}
	
	//this will help in deleting time.to mark end date of employee as current date
	    public void setendDate(String d1) {
	        this.endDate = d1;
	    }
	    
	public String toString()
	{
		return 
		"\nEmployee Details :" +
	    "\nID               : " + this.id + 
	    "\nFirstName        : " + this.firstName + 
	    "\nDOB              : " + this.dob + 
	    "\nTitle            : " + this.title +
	    "\nStart Date       : "+this.startDate +
	    "\nEnd Date         : "+ this.endDate+ 
	    "\nEmail-id         : " + this.email_id + 
	    " \nKeberoes Id     : " + this.kebID;
	}
	
}

public class EmployeeManagement
{	
	
	static void display(ArrayList<Employee> al)
	{
		System.out.println("\n--------------EMPLOYEE LIST--------------\n");
		
		for(Employee e : al)
		{
			
		System.out.println("Employee ID :         "+e.id);
	    System.out.println("First Name :          "+e.firstName);
		System.out.println("Last Name :           "+e.lastName);
		System.out.println("Title:                "+e.title);
		System.out.println("Date of Birth :       "+e.dob);
     	System.out.println("Start Date            "+e.startDate);
		System.out.println("End Date :            "+e.endDate);
		System.out.println("Email ID :            "+e.email_id);
		System.out.println("The Keberoe I'D:      "+ e.kebID);
		System.out.println("=========================================");
		
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		
		int id;
		String firstName;
		String lastName;
		String dob;
		String title;
		String startDate;
		String endDate;
		String email_id;
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Employee> al = new ArrayList<Employee>();
		HashMap<String,ArrayList<String>> hmap=new HashMap<>();
		//hmap will help in filter operation. specially to check number of employee with same title
		
		
		File f = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos =null;
		try{
			
			f = new File("N:/Java Work Space/Eclipse Programs/Employee Management Tool/src/EmployeeDataList1.txt");
			if(f.exists())
			{
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				al = (ArrayList<Employee>)ois.readObject();
			}
			
		}
		catch(Exception exp){
			System.out.println(exp);
		}
		do
		{
			System.out.println("\n****************  Welcome to the Employee Management System  ****************\n");
			System.out.println( "1). Add Employee to the DataBase\n" +
								"2). Search for Employee\n" +
								"3). Delete Employee Details\n" +
								"4). Filter Employee\n" +
								"5). Display all Employee Details\n"+
								"6). Edit Employee details\n"+
                                "7). Exit\n"
								
								
								);
			System.out.println("Enter your choice in number(1-6) : ");
			int ch = sc.nextInt();
			
			switch(ch)
			{
			case 1:System.out.println("\nEnter the following details to ADD Employee to the  list:\n");
				System.out.println("Enter employee ID (in digts):");
				id = sc.nextInt();
				System.out.println("Enter First Name :");
				firstName = sc.next();
				System.out.println("Enter Last Name :");
				lastName = sc.next();
				System.out.println("Enter Date of Birth :");
				dob = sc.next();
		        System.out.println("Enter Email-ID :");
				email_id = sc.next();
				System.out.println("Enter Start Date :");
				startDate = sc.next();
				System.out.println("Enter End Date (Enter Present ,if currently Working) :");
				endDate = sc.next();
				
				
				//TO GENERATE KERBEROES ID-->
                
			    String s1=firstName.substring(0);
				String s2=lastName.substring(0);
				String kebID=s2+s1;
				
				System.out.println("Enter Title(Analyst/Associate/VP/MD) :");
				title = sc.next();
				if(hmap.containsKey(title))
				{
					ArrayList<String> list=hmap.get(title);
					list.add(kebID);
					hmap.put(title, list);
					
				}
				else {
					ArrayList<String> list=new ArrayList<>();
					list.add(kebID);
					hmap.put(title, list);
					
				}
				//now adding all data of employee into the arraylist
				al.add(new Employee(id, firstName, lastName, dob,title,startDate,endDate, email_id,kebID));
				display(al);
				break;
				
			case 2: System.out.println("Enter the kerberos ID to search :");
					String kerberos_id = sc.next();
					int i=0;
					for(Employee e: al)
					{
						if((kerberos_id).equals(e.kebID))
						{
							System.out.println(e +"\n");
							i++;
						}
					}
					if(i == 0)
					{
						System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
					}
					break;
			

			case 3: System.out.println("\nEnter kerberos ID to DELETE from the Databse :");
					 String input_kebid = sc.next();
					System.out.println("\nEnter todays Date :");
					 String todayDate = sc.next();
					
					int k=0;
					try{
					for(Employee e: al)
					{
						if((input_kebid).equals(e.kebID))
						{
							 
						  e.setendDate(todayDate);
							display(al);
								k++;
						}
					}
					if(k == 0)
					{
						System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
					}
					}
					catch(Exception ex){
						System.out.println(ex);
					}
					break;
					
			case 4:   System.out.println("\nEnter Title of employee if you want to see active employees on title basis :");
				      String isactiveTitle = sc.next();
				      
				      //if no employee is yet added in the list. then display to user that for now no one added. plz. 1st add employees
				      if(hmap.size()==0)
				      {
				    	  System.out.println("For now No employee in the list. Please first add employee in the list!");
				      
				      }
				      else {
				      
				     for( Map.Entry<String,ArrayList<String>> w: hmap.entrySet())
				    	 
				     {
				    	
				    	 String key=w.getKey();
				    	 
				    	 if(isactiveTitle.equals(key))
				    	 {
				    		 ArrayList<String> a=w.getValue();
				    		 System.out.println("The Kerberos ID of active employees on the basis of Title is:");
				    		 for(int count=0;count<a.size();count++)
				    		 {
				    			 
				    		 System.out.println(a.get(count));
				    		 }
				    	 }
				    	 
				     } 
				     }
				      
				      
			
			case 5: try {
				al = (ArrayList<Employee>)ois.readObject();
				
			} catch (ClassNotFoundException e2) {
				
				System.out.println(e2);
			} catch (Exception e2) {
				
				System.out.println(e2);
			}
				display(al);
				break;
					
			case 6: System.out.println("\nEnter the kerberos ID to EDIT the details");
			String keb_id = sc.next();
			int j=0;
			for(Employee e: al)
			{
				if((keb_id).equals(e.kebID))
				{	
					j++;
				do{
					int ch1 =0;
					System.out.println("\nEDIT Employee Details :\n" +
										"1). Employee ID\n" +
										"2). FirstName\n" +
										
										"3). Date of Birth\n" +
										"4). Title.\n" +
										"5). Start Date\n" +
										"6). End Date\n" +
										"7). Email-ID\n" +
										"8). GO BACK\n");
					System.out.println("Enter your choice : ");
					ch1 = sc.nextInt();
					switch(ch1)
					{
					case 1: System.out.println("\nEnter Employee updated ID:");
							e.id =sc.nextInt();
							System.out.println(e +"\n");
							break;
					
					case 2: System.out.println("Enter Employee updated First Name:");
							e.firstName =sc.next();
							System.out.println(e+ "\n");
							break;
							
							
					case 3: System.out.println("Enter Employee  updated  Date of Birth:");
							e.dob =sc.next();
							System.out.println(e+"\n");
							break;
							
					case 4: System.out.println("Enter Employee  updated Title :");
							e.title =sc.next();
							System.out.println(e+"\n");
							break;
							
					case 5: System.out.println("Enter Employee  updated  Start Date :");
					        e.startDate =sc.next();
					        System.out.println(e+"\n");
					        break;	
					
					case 6: System.out.println("Enter  Employee updated  End Date :");
					        e.endDate =sc.next();
					          System.out.println(e+"\n");
					          break;
					
							
					        
					case 7: System.out.println("Enter Employee  updated Email-ID :");
							e.email_id =sc.next();
							System.out.println(e+"\n");
				
							break;
					
					
			default : System.out.println("\nEnter a correct choice from the List :");
						break;
			
			}
			}
		while(j==1);
		}
	}
	if(j == 0)
	{
		System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
	}

	break;
			
      case 7: System.out.println("\nYou have chosen EXIT !! Saving Files and closing the tool.");
             break;
              
		
		default : System.out.println("\nEnter a correct choice from the List :");
					break;
		
		}
	}
	while(true);
}
}
