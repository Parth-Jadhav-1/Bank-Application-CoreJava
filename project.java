
package correctbankApplication;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Bank {
    private String ifsc;
    private String name;

    Bank(String name) {
        this.name = name;
        this.ifsc = generateIFSC(name);
    }

    public String getBankName() {
        return name;
    }

    public String getIfsc() {
        return ifsc;
    }

    private String generateIFSC(String bankName) {

        String bankPrefix;
        switch (bankName.toLowerCase()) {
            case "icici":
                bankPrefix = "ICIC";
                break;
            case "bank of maharashtra":
                bankPrefix = "MAHB";
                break;
            case "union bank":
                bankPrefix = "UBIN";
                break;
            case "canara bank":
                bankPrefix = "CNRB";
                break;
            case "bank of baroda":
                bankPrefix = "BARB";
                break;
            default:
                bankPrefix = "XXXX";
        }

        Random random = new Random();
        int randomCode = 1000 + random.nextInt(9000);

        return bankPrefix + "000" + randomCode;
    }
}

class User {
    private long accountNumber;
    private String name;
    private LocalDate dob;
    private String address;
    private char gender;
    private String accountType;
    private double balance;

    User(String name, LocalDate date, String address, char gender,String accountType) {
        this.accountNumber = generateAccountNumber(date);
        this.name = name;
        this.dob = date;
        this.address = address;
        this.gender = gender;
        this.accountType = accountType;
        this.balance=0.0;

    }

   private long  generateAccountNumber(LocalDate date){
       int year=date.getYear();
       Random random=new Random();
       int randomSixDigits =100000+ random.nextInt(900000);
       String accountNumberStr = year + String.valueOf(randomSixDigits);
       return Long.parseLong(accountNumberStr);
   }

    public double getBalance() {
        return balance;
    }

    public String getAccountType(){
        return accountType;
    }

    public long getAccountNumber(){
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }
}



public class project {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please Create a username: ");
        String createdUsername = sc.nextLine().trim();

        String createdPassword;
while(true){

    System.out.println("Please Create a password (at least 8 characters): ");
    createdPassword = sc.nextLine().trim();
    if(createdPassword.length()<8){
        System.out.println("Password too short!");
    }
    else{
        break;
    }
}
        System.out.println("Account created successfully!\n");


        String username;
        String password;
        boolean loginSuccess = false;

        while (!loginSuccess) {
            System.out.println("Please enter your username to login: ");
            username = sc.nextLine().trim();

            System.out.println("Please enter your password to login: ");
            password = sc.nextLine().trim();


            if (username.equals(createdUsername) && password.equals(createdPassword)) {
                System.out.println("Login successful!\n");
                loginSuccess = true;
            } else {
                System.out.println("Invalid username or password. Please try again.\n");
            }
        }

        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank("ICICI"));
        banks.add(new Bank("Bank Of Maharashtra"));
        banks.add(new Bank("Union Bank"));
        banks.add(new Bank("Canara Bank"));

        banks.add(new Bank("Bank Of Baroda"));

        for (int i = 0; i < banks.size(); i++) {
            System.out.println((i + 1) + ". " + banks.get(i).getBankName());
        }

        int bankchoice = -1;
        boolean validinput = false;
        while (!validinput) {
            try {
                System.out.println("Please choose the bank in which you want to open your bank account (1 to " + banks.size() + "):");
                bankchoice = sc.nextInt();
                sc.nextLine();
                if (bankchoice >= 1 && bankchoice <= banks.size()) {
                    validinput = true;
                    System.out.println("you have chosen: " + ">>" + banks.get(bankchoice - 1).getBankName() + "<<");

                    boolean validConfirmation = false;
                    while (!validConfirmation) {
                        System.out.println("Do you Really! want to proceed further? (YES/NO)");

                        String confirm = sc.nextLine();

                        if (confirm.equalsIgnoreCase("NO")) {
                            validConfirmation = true;
                            validinput = false;
                        } else if (confirm.equalsIgnoreCase("YES")) {
                            System.out.println("Proceeding with your choice...");
                            validConfirmation = true;
                        } else {
                            System.out.println("Invalid response. Please enter YES or NO.");
                        }
                    }
                } else {
                    System.out.println("Invalid choice. Please select a valid bank number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.Please enter a valid number!");
                sc.next();
            }
        }
        System.out.println("Your IFSC code for " + banks.get(bankchoice - 1).getBankName() + " is " + banks.get(bankchoice - 1).getIfsc());


        System.out.println("Please enter your name: ");
        String name = sc.nextLine();

        LocalDate date=null;
        boolean validDate=false;

        while(!validDate) {
            System.out.println("Please enter your date of birth (YYYY-MM-DD): ");

            String dob = sc.nextLine();
            try {
                date = LocalDate.parse(dob);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter the date in the format YYYY-MM-DD.");
            }
        }

        String address = "";

        while (address.trim().isEmpty()) {
            System.out.println("Please enter your address: ");
            address = sc.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Address cannot be empty. Please enter a valid address.");
            }
        }



        char gender = '\0';
        while (true) {
            System.out.println("Please enter your gender (M/F): ");
            String input = sc.next();

            if (input.length() == 1) {
                gender = Character.toUpperCase(input.charAt(0));
                if (gender == 'M' || gender == 'F') {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter M for Male or F for Female.");
                }
            } else {
                System.out.println("Invalid input! Please enter only one character (M/F).");
            }
        }
        sc.nextLine();


        String accounttype = "";
        boolean validAccountType=false;
        while(!validAccountType){
            System.out.println("Please choose your account type (Savings/Current): ");
            accounttype=sc.nextLine().trim();
            if(accounttype.equalsIgnoreCase("savings")||accounttype.equalsIgnoreCase("current")){
                validAccountType=true;
            }
            else{
                System.out.println("Invalid account type.Please enter 'Savings' or 'Current'.");
            }
        }
        User user = new User(name, date, address, gender, accounttype);
        System.out.println("Your " + user.getAccountType() + " account has been created.");
        System.out.println("Account number: " + user.getAccountNumber());
        System.out.println("Current balance: " + user.getBalance());

// withdraw and deposit code nad display current balance
        boolean sessionActive = true;

        while (sessionActive) {

            System.out.println("\nPlease choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            int actionChoice=-1;

            try {
                actionChoice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("please enter a valid choice");
            }
            switch (actionChoice) {
                case 1:
                    boolean validDeposit=false;
                    double depositAmount = -1;
                    while(!validDeposit) {
                        try {
                            System.out.println("Enter the amount to deposit: ");
                            depositAmount = sc.nextDouble();
                            validDeposit=true;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid amount  ");
                            sc.next();
                        }
                    }
                    user.deposit(depositAmount);
                    break;

                case 2:
                    boolean validWithdraw=false;
                    double withdrawAmount = -1;
                    while(!validWithdraw) {
                         try {
                            System.out.println("Enter the amount to withdraw: ");
                            withdrawAmount = sc.nextDouble();
                            validWithdraw = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid amount  ");
                            sc.next();
                        }
                    }
                    user.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Your current balance is: " + user.getBalance());
                    break;

                case 4:
                    System.out.println("Exiting the application...");
                    sessionActive = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }

        sc.close();
        }


    }