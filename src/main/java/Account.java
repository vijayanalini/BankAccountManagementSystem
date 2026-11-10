import java.util.Scanner;

public class Account
{
    private String account_Number;
    private String account_Name;
    private long balance;
    public Account(String account_Number,String account_Name,long balance)
    {
        this.account_Number=account_Number;
        this.account_Name=account_Name;
        this.balance=balance;
    }
    public void setAccount_Number(String account_Number)
    {
        this.account_Number=account_Number;
    }
    public void setAccount_Name(String account_Name)
    {
        this.account_Name=account_Name;
    }
    public String getAccount_Number()
    {
        return account_Number;
    }
    public String getAccount_Name()
    {
        return account_Name;
    }
    public long getBalance()
    {
        return balance;
    }
    public void deposit(long deposit)
    {
        if(deposit<=0)
        {
            System.out.println("Enter a valid positive amount");
            return;
        }
        balance+=deposit;
        System.out.println("Amount is succesfully deposited");
    }
    public void withdraw(long withdraw)
    {
        if(withdraw<=0)
        {
            System.out.println("Enter a valid positive amount");
            return;
        }
        if(withdraw>balance)
        {
            System.out.println("Insufficient balance");
        }
        else
        {
            balance-=withdraw;
            System.out.println("Amount is sucessfully withdrawn");
        }
    }
    public void displayDetails()
    {
        System.out.println("Account Name: "+getAccount_Name());
        System.out.println("Account Number: "+getAccount_Number());
        System.out.println("Remaining Balance is: "+getBalance());
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Account Number:");
        String accNo = sc.next();
        sc.nextLine();
        System.out.println("Enter Account Holder Name:");
        String name = sc.nextLine();

        System.out.println("Enter Initial Balance:");
        long initialBalance = sc.nextLong();

        Account a = new Account(accNo, name, initialBalance);

        System.out.println("\n1. Deposit");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Display Details");
        System.out.println("Enter Your Input: ");
        int ch=sc.nextInt();
        switch (ch)
        {
            case 1:System.out.println("Enter Deposit Amount:");
                    a.deposit(sc.nextLong());
                    a.displayDetails();
                    break;
            case 2:System.out.println("Enter Withdrawal Amount:");
                    a.withdraw(sc.nextLong());
                    a.displayDetails();
                    break;
            case 3:a.displayDetails();
                    break;
            default:System.out.println("Invalid Input");
                    break;
        }
    }
}