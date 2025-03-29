package atm;

import java.util.Scanner;

public class AtmMenu{
private Services services;
private Scanner sc;

public AtmMenu(){
 services = new Services();
sc = new Scanner(System.in);
}
public void displayMenu(){
boolean authenticated = false;

while(!authenticated){
System.out.println("Welcome to Nandus ATM");
authenticated = services.authenticateUser();
if(!authenticated) {
System.out.println("Invalid details");
}
}
while(true){
System.out.println("\nATM Main Menu");
System.out.println("1. View Balance"); 
System.out.println("2. Deposit Money");
System.out.println("3. withdraw Money"); 
System.out.println("4. Exit");
System.out.print("Enter your choice: ");
int choice=sc.nextInt();
sc.nextLine(); 

switch (choice) {
case 1:
services.getBalance();
break;
case 2:
services.depositMoney();
break; 
case 3:
services.withDraw(); 
break;
case 4:
System.out.println("Thank you for using Nandus ATM. Come Again!");
 System.exit(0);
break;
default:
System.out.println("Invalid choice. Please try again.");
}
}
}
}