Maintenance:-

package integrate1;
import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Integrate1 {
public static void main(String[] args) 
     throws IOException{
   int choice;
String searchString,loginName,passWord, userPassword="tiger";
Scanner obj = new Scanner(System.in);
System.out.println("####################################################");
System.out.println("#############COUNSELLING################");
System.out.println("##############SYSTEM#####################");
System.out.println("####################################################");
while(true)
{
System.out.print("Login name(user/quit):");
loginName=obj.next();
if(loginName.equals("quit"))return;
System.out.print("Password:");
passWord=obj.next();
if(loginName.equals("user"));
if(passWord.equals(userPassword))break;
System.out.println("Illegal LoginName or password");
}
boolean flag=true;
while(flag)
{
System.out.println("\n\n   Menu");
System.out.println("1. Management of Student information ");
System.out.println("2. Management of Faculty information");
System.out.println("3.Verification of activities");
System.out.println("4. Quit");
System.out.print("Enter your choice: ");
choice= obj.nextInt();
switch(choice){
case 1: 
MODULE 1;
 break;
 case 2:
 MODULE 2;
 break; 
  case 3:
 MODULE 3;
 break;
case 4:	
flag=false;
 break;
default:System.out.println("Wrong Choice");
}
}

