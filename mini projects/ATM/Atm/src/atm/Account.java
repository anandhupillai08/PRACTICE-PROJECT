package atm;

public class Account {
private String accountNumber, pin;
private double balance;

public Account(String accNumber, String pin, double bal)
{ this.accountNumber=accNumber;
  this.pin=pin; 
  this.balance=bal;
}

public String getAccountNumber(){  
return this.accountNumber;
}
 public String getPin(){
return this.pin;
}
public double getBalance(){
return this.balance;
}

public void deposit(double amt){
if(amt<=0){
System.out.println("Invalid Amoutn");
}else{
balance+= amt;
}
}
public boolean withDraw(double amt){
if(this.balance <=amt || amt < 0){
return false;
}
else{
this.balance=amt;
return true;
}
}
public void getDetail(){    
System.out.println("Account Number:" + this.accountNumber);
System.out.println("Balance:" + this.balance);
}
}