import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountDAO
{
    void addAccount(Account a)
    {
        if (a.getBalance() <= 0)
        {
            System.out.println("Invalid Initial Balance");
            return;
        }
        try(Connection con = DBConnection.getConnection())
        {
            if (con == null)
            {
                System.out.println("Database Connection Failed!");
                return;
            }
            else
                {
                String sql = "insert into accounts(account_number,account_holder,balance)values(?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, a.getAccount_Number());
                    ps.setString(2, a.getAccount_Name());
                    ps.setLong(3, a.getBalance());
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Account Created Successfully");
                    } else {
                        System.out.println("Account Creation Failed!");
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    boolean accountExists(String account_Number)
    {
        try(Connection con = DBConnection.getConnection())
        {
            if(con == null)
                throw new SQLException("Database Connection Failed!");

            String sql = "select account_number from accounts where account_number=?";

            try(PreparedStatement ps = con.prepareStatement(sql))
            {
                ps.setString(1, account_Number);

                try(ResultSet rs = ps.executeQuery())
                {
                    return rs.next();
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    void deposit(String account_Number,long balance)
    {
        if (balance <= 0)
        {
            System.out.println("Enter a valid positive amount");
            return;
        }
        try(Connection con = DBConnection.getConnection())
        {
            if (con == null)
            {
                System.out.println("Database Connection Failed!");
                return;
            }
            else
            {
                String sql="update accounts set balance=balance+? where account_number=?";
                try (PreparedStatement ps = con.prepareStatement(sql))
                {
                    ps.setLong(1, balance);
                    ps.setString(2, account_Number);
                    int rows=ps.executeUpdate();
                    if (rows > 0)
                        System.out.println("Amount Deposited Successfully");
                    else
                        System.out.println("Account Not Found");
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    void withdraw(String account_Number,long balance)
    {
        if (balance <= 0)
        {
            System.out.println("Enter a valid positive amount");
            return;
        }
        try(Connection con = DBConnection.getConnection())
        {
            if (con == null)
            {
                System.out.println("Database Connection Failed!");
                return;
            }
            else
            {
                String sql="update accounts set balance=balance-? where account_number=? and balance>=?";
                try (PreparedStatement ps = con.prepareStatement(sql))
                {
                    ps.setLong(1, balance);
                    ps.setString(2, account_Number);
                    ps.setLong(3, balance);
                    int rows=ps.executeUpdate();
                    if (rows > 0)
                        System.out.println("Amount Withdrawn Successfully");
                    else
                        System.out.println("Insufficient Balance or Account Not Found");
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void checkBalance(String account_Number)
    {
        try(Connection con = DBConnection.getConnection())
        {
            if (con == null)
            {
                System.out.println("Database Connection Failed!");
                return;
            }
            else
            {
                String sql="select account_holder,balance from accounts where account_number=?";
                try (PreparedStatement ps = con.prepareStatement(sql))
                {
                    ps.setString(1, account_Number);
                    try(ResultSet rs=ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            long currentBalance = rs.getLong("balance");
                            String account_Name=rs.getString("account_holder");
                            System.out.println("Mr/Ms "+account_Name+" Your Current Balance is: " + currentBalance);
                        }
                        else
                        {
                            System.out.println("Account Not Found");
                        }
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        AccountDAO ad=new AccountDAO();
        String account_Number;
        String account_Name;
        long balance;
        String n="";
        while(!n.equals("5")) {
            System.out.println("\n1.Add account");
            System.out.println("2.Deposit the Amount");
            System.out.println("3.Withdraw the Amount");
            System.out.println("4.Check Balance");
            System.out.println("5.Exit");
            System.out.println("Enter your choice: ");
            n = sc.next();
            try {
                switch (n) {
                    case "1":
                        System.out.println("Enter Account Number: ");
                        account_Number = sc.next();
                        if (!account_Number.matches("[0-9]+")) {
                            System.out.println("Invalid Account Number! Digits only.");
                            break;
                        }
                        if (ad.accountExists(account_Number)) {
                            System.out.println("Account Number Already Exists!");
                            break;
                        }
                        sc.nextLine();
                        System.out.println("Enter Account Name: ");
                        account_Name = sc.nextLine();
                        if (!account_Name.matches("[a-zA-Z ]+")) {
                            System.out.println("Invalid Account Holder Name!");
                            break;
                        }
                        System.out.println("Enter Initial Balance: ");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid Balance! Enter numbers only.");
                            sc.next();
                            break;
                        }
                        balance = sc.nextLong();
                        if (balance <= 0) {
                            System.out.println("Invalid Initial Balance");
                            break;
                        }
                        Account a = new Account(account_Number, account_Name, balance);
                        ad.addAccount(a);
                        break;
                    case "2":
                        System.out.println("Enter Account Number:");
                        account_Number = sc.next();
                        if (!account_Number.matches("[0-9]+")) {
                            System.out.println("Invalid Account Number! Digits only.");
                            break;
                        }
                        if (!ad.accountExists(account_Number)) {
                            System.out.println("Account Not Found");
                            break;
                        }
                        System.out.println("Enter Deposit Amount:");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid Amount! Enter numbers only.");
                            sc.next();
                            break;
                        }
                        balance = sc.nextLong();
                        ad.deposit(account_Number, balance);
                        ad.checkBalance(account_Number);
                        break;
                    case "3":
                        System.out.println("Enter Account Number:");
                        account_Number = sc.next();
                        if (!account_Number.matches("[0-9]+")) {
                            System.out.println("Invalid Account Number! Digits only.");
                            break;
                        }
                        if (!ad.accountExists(account_Number)) {
                            System.out.println("Account Not Found");
                            break;
                        }
                        System.out.println("Enter Withdrawal Amount:");
                        if (!sc.hasNextLong()) {
                            System.out.println("Invalid Amount! Enter numbers only.");
                            sc.next();
                            break;
                        }
                        balance = sc.nextLong();
                        ad.withdraw(account_Number, balance);
                        ad.checkBalance(account_Number);
                        break;
                    case "4":
                        System.out.println("Enter Account Number:");
                        account_Number = sc.next();
                        if (!account_Number.matches("[0-9]+")) {
                            System.out.println("Invalid Account Number! Digits only.");
                            break;
                        }
                        ad.checkBalance(account_Number);
                        break;
                    case "5":
                        System.out.println("Thank You");
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;
                }
            }
            catch(RuntimeException e)
            {
                System.out.println("Database Error: "+e.getMessage());
            }
        }
    }
}
