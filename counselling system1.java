Management of  Faculty information:-


package project;
import java.util.Scanner;
import java.io.*;

class StudentRec
{
int reg_no;
String name;
String subject;
String  branch;
String  liesure;

void displayRecord()
{
System.out.print("RegNo.: " + reg_no);
System.out.print("\n Name: " + name);
System.out.println(" SUBJECT: "+subject);
System.out.println(" BRANCH: "+	branch);
System.out.println(" LIESURE ON: "+liesure);
}
 

}

public class Project2 {

   
    public static void main(String[] args)
    throws IOException{
        int i,num;
int choice=0;
int search_regNo=0,update_regno;
String str="";
boolean flag=true;
Scanner input=new Scanner(System.in);

StudentRec  record = new StudentRec(); 

while(flag)
{
System.out.println("\n   Menu");
System.out.println("1. Write File");
System.out.println("2. Search");
System.out.println("3. Display File");
System.out.println("4. exit");
System.out.print("Enter your choice: ");
choice= input.nextInt();

switch(choice){
case 1: 
	// Create a file.
	FileWriter fout = new FileWriter("test.txt");
	
	//Read data from Keyboard
	System.out.print("How many records? ");
	num= input.nextInt();

	for(i=0;i<num;i++)
	{
	System.out.println("enter Faculty id:");
	record.reg_no= input.nextInt();
	
        System.out.println("Enter Faculty name:");
	record.name=input.next();
	 System.out.println(" SUBJECT: ");
          record.subject=input.next();
          System.out.println(" BRANCH: ");
          record.branch=input.next();
          System.out.println(" LIESURE ON: ");
          record.liesure=input.next();
      
        // Write to file.	
	fout.write(record.reg_no+"\n");
	fout.write(record.name+"\n");
        fout.write(record.subject+"\n");
        fout.write(record.branch+"\n");
        fout.write(record.liesure+"\n");
      }
	fout.write("EOF");
	fout.close(); 
	System.out.println("File is created");  
	break;
case 2:	
	System.out.print("enter search Id: ");
	search_regNo = input.nextInt();
case 3:
	FileReader fin = new FileReader("test.txt");
	Scanner sc = new Scanner(fin);
	while(sc.hasNextInt()) 
	{
	//Read data from file
	record.reg_no = sc.nextInt();
	record.name = sc.next();	
	record.subject= sc.next();
       record.branch= sc.next();
        record.liesure= sc.next();

	if(choice==3)
	record.displayRecord();
	if(choice==2)
            if(record.reg_no==search_regNo)
	record.displayRecord();
	}
	str= sc.next();
	if(str.equals("EOF"))
	System.out.println("End of file");
	else System.out.println("File format error.");
	fin.close(); 
	break;

case 4:	flag=false;
	break;

                           
default:System.out.println("Wrong Choice!!");
  }
 }
System.out.println("Program is over");    
     
    }
    
}









