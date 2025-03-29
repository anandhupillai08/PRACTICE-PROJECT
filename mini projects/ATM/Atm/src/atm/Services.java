package atm;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Services {
private List <Account>accounts;
private Scanner sc;
private Account loggedAccount;

public Services(){
     this.accounts= new ArrayList<>();
     this.sc = new Scanner(System.in);
}

private void createdummyAccount(){
accounts.add(new Account("3017111036","1457", 3000)); 
accounts.add(new Account("123456789", "1234", 1000));
}

public boolean authenticateUser(){
    System.out.println("Enter Account Number:");
    String accNumbers=sc.nextLine();
    System.out.println("Enter PIN:"); 
    String pin= sc.nextLine();

this.loggedAccount = authenticate(accNumbers,pin);
return loggedAccount != null;
}
private Account authenticate(String accNumber, String pin){
     for (Account account : accounts){
          if( account.getAccountNumber().equals(accNumber)&& account.getPin().equals(pin));
     }
return null;
    }
public void depositMoney(){
    if (this.loggedAccount != null); {
 System.out.println ("Enter the amount you want to deposit");
 double amt = sc.nextDouble();
 this.loggedAccount.deposit(amt);
System.out.println("Amount deposited successfully");
    }
}
public void getBalance(){
    if (this.loggedAccount != null); {
    System.out.println("your total available balance is "+this.loggedAccount.getBalance());
    }
}

public void withDraw(){
    if (this.loggedAccount != null); {
System.out.println("Enter the assuet you want to withdraw");
double amt = sc.nextDouble();
boolean didWithdraw = loggedAccount.withDraw(amt);
if (didWithdraw) {
     System.out.println("Amount withdraw");
    }  else{
System.out.println("insufficient Amounts:Transaction cancelled");
    }
    }
}
}


