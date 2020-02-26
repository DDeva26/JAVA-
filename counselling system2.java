Verification Of Activities:-

package trail1;
import java.util.Scanner;
import java.io.*;

class StudentRec
{
int reg_no;
String name;
int internal_marks;
float attend_percentage;
String call;
String marklist;
String  attend_info;
void displayRecord()
{
System.out.print("RegNo.: " + reg_no);
System.out.print("\n Name: " + name);
if(internal_marks <41){
System.out.println("\n Internal marks: "+internal_marks);
}
else
{
  System.out.println("\nPLEASE ENTER VALID INTERNAL MARKS");  
}
  if(attend_percentage<101)
  {
System.out.println(" \nattendece %: "+attend_percentage	);
  }
  else
  {
      System.out.println("\n1PLEASE ENTER VALID ATTENDENCE % ");
  }
  System.out.println(" called or not: "+	call);
System.out.println(" mark list sent or not: "+	marklist);
System.out.println(" attendence information sent or not: "+attend_info);
}
}
public class Trail1 {
 public static void main(String[] args)
    throws IOException{
    int i,num;
int choice=0;
int search_regNo=0;
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
System.out.println("4. Quit");
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
	System.out.println("enter student id:");
	record.reg_no= input.nextInt();
	System.out.println("Enter student name:");
	record.name=input.next();
	
        System.out.println("enter Internalmarks Between 0 - 40:");
        record.internal_marks =input.nextInt();
      
        System.out.println("enter attendence % between 0 - 100:");
        record.attend_percentage =input.nextFloat();
        System.out.println("enter whether parents are called or not:");
        record.call=input.next();
        System.out.println("enter whether marklist has been sent or not:");
        record.marklist=input.next();
        System.out.println("enter whether attendence information is sent or not:");
        record.attend_info=input.next();
	// Write to file.	
	fout.write(record.reg_no+"\n");
	fout.write(record.name+"\n");
	fout.write(record.internal_marks+"\n");
        fout.write(record.attend_percentage+"\n");
        fout.write(record.call+"\n");
        fout.write(record.marklist+"\n");
        fout.write(record.attend_info+"\n");
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
	record.internal_marks= sc.nextInt();
        record.attend_percentage=sc.nextFloat();
        record.call=sc.next();
        record.marklist=sc.next();
        record.attend_info=sc.next();
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
