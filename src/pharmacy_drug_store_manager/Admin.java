
package pharmacy_drug_store_manager;

import java.io.*;

public class Admin extends ITEM{
    
     public void AdminM() {
        System.out.flush();
        System.out.println("""


                \t\t**************************************:    ¶
                \t\t* press 1 To Add new User              :   ¶
                \t\t* press 2 To Display Drug Information  :   ¶
                \t\t* press 3 To Display the Total Capital :   ¶
                \t\t* press 4 To Display sold drug         :   ¶
                \t\t* press 5 To Add a New Drug            :   ¶
                \t\t* press 6 To Delete(Sell) Drug         :   ¶
                \t\t* press 7 To SORT Drug                 :   ¶
                \t\t* press 8 To Search A Drug             :   ¶
                \t\t* press 9 To Update Drug Statues       :   ¶
                \t\t* press 10 To Delete All               :   ¶
                \t\t* press Any Key to Logout and Exit     :   ¶
                \t\t************************************** :   ¶
                \t\t\t\t ||--> \s""");

        int choice;
        System.out.println("Enter your choice");
        choice = getInt();
        switch (choice) {
            case 1:
                ADDU();
                choiceA("ADD_User");
                break;
            case 2:
                Display();
                choiceA("Display");
                break;
            case 3:
                Capital();
                choiceA("Display_Capital");

                break;
            case 4:
                view();
                choiceA("Display_Sold");

                break;
            case 5:
                Adder();
                choiceA("Add");
                break;
            case 6:
                Delete();
                choiceA("Delete");
                break;
            case 7:
                Sort();
                choiceA("Sort");
                break;
            case 8:

                Search();
                choiceA("Search");
                break;
            case 9:
                Update();
                choiceA("update");
                break;
            case 10:
                DeleteUser();
                choiceA("Deleteuser");
                break;
            default:
                System.out.println("");
        }
    }
     
     
    private void ADDU()
    {
        System.out.println("""
                                \n\t\t* press 1 To Add new Manager (Admin)           :   ¶
                                \n\t\t* press 2 To Add new Pharmacist                :   ¶
                                \t\t\t\t ||--> """);
        int choice = getInt();
        switch (choice){
            case 1:
                fileAD("Manager");
                break;
            case 2:
                fileAD("Pharmacist");
                break;
            default:
                System.out.println("\nIncorrect input try again\n\n");
                ADDU();
        }
    }
    
    
    private void view()
    {
        System.out.println("****************************************************************");
        System.out.println("\t\tNAME\t\tSerial_no\t\texpire Date\t\tPrice\t\tQuantity");
        for(Datatype D:sold)
        {
            System.out.println(D);
        }
    }
    
    
    private void Capital()
        {
            System.out.println("""
                                \n\t\t* press 1 To Display the Total Capital As Item :   ¶
                                \n\t\t* press 2 To Display the Total Capital As Cash :   ¶
                                \n\t\t* press 3 To Display Profit                    :   ¶ 
                                \t\t\t\t ||--> """);
            int n = getInt();
            if (n == 1) {
                int OnStock = 0, Sold = 0;
                for (Datatype f : item) {
                    OnStock++;
                }
                for (Datatype f : sold) {
                    Sold++;
                }
                System.out.println("\n\n\t\tThe total Capital that are on the stock are --> " + OnStock);
                System.out.println("\n\t\tThe total Capital that are Sold are --> " + Sold+"\n\n");
            } else if (n == 2) {
                double Onstock_Cap = 0.0;
                for (Datatype f : item) {
                    Onstock_Cap = Onstock_Cap + f.price;
                }
                System.out.println("\n\t\tThe total Capital in cash --> " + Onstock_Cap+"\n\n");
            } else if (n==3) {
                double Profit_S = 0.0;
                for (Datatype f : sold) {
                    Profit_S = Profit_S + f.price;
                }
                System.out.println("\n\t\tThe total Sold Profit in price --> " + Profit_S*0.2+"\n\n");

            } else {
                System.out.println("\n\t\tINVALID INPUT\t");
                Capital();
            }
        }
    
    
    private void fileAD(String user)
    {
        int id=0;
        System.out.println("Enter ID Number");
        id=getInt();
        System.out.println("Enter First Name");
        String FName=getstring();
        System.out.println("Enter Last Name");
        String LName=getstring();
        System.out.println("Enter Location or branch");
        String Location=getstring();
        System.out.println("Enter Phone number");
        int phone=getInt();
        System.out.println("Enter username");
        String username=getstring();
        System.out.println("Enter the Password");
        String password=getstring();

        File u = new File(user+"/"+username+".txt");
        if(u.exists()){
            System.out.println("\tUSER NAME ALREADY TAKEN\n");
        }
        else if("".equals(FName)||username.length()<=3||"".equals(LName)) {
            System.out.println("\n\tFILL THE FORM CORRECTLY \n");
        }
        else if(password.length()<5){
            System.out.println("\n\tPASSWORD IS TOO SHORT\n");
        }
        else{

            try {
                u.createNewFile();
                PrintWriter out=new PrintWriter(u);
                out.print(password+","+username+","+FName+","+LName+","+id+","+phone+","+Location);
                out.flush();
                out.close();
                System.out.println("\n\t\tYOUR  SUCCESSFULLY REGISTER A USER\n");
            } catch (IOException ex) {

            }


        }


        }
    
    
    public void choiceA(String Type)
    {
        System.out.println("\n\t\t\t***********SELECT YOUR CHOICE*************\n" +
                "\t\t**Enter C/c to back to main menu              :\n" +
                "\t\t**Enter B/b to "+Type+"  Information Again :\n" +
                "\t\t**Enter any key to exit                       :\n");
        String v =getstring();
        if (v.equals("B")||v.equals("b")) {
            switch(Type)
            {

                case "ADD_User":
                    ADDU();
                    choiceA("ADD_User");
                    break;
                case "Display_Capital":
                      Capital();
                    choiceA("Display_Capital");
                    break;
                case "Display":
                    Display();
                    choiceA("Display");
                    break;
                case "Display_Sold":
                    view();
                    choiceA("Display_Sold");
                    break;
                case "Add":
                    Adder();
                    choiceA("Add");
                    break;
                case "Delete":
                    Delete();
                    choiceA("Delete");
                    break;
                case "Search":
                    Search();
                    choiceA("Search");
                    break;
                case "Sort":
                    Sort();
                    choiceA("Sort");
                    break;
                case "update":
                    Update();
                    choiceA("update");
                    break;
                case "Deleteuser":
                    DeleteUser();
                    choice("Deleteuser");
            }

        }
        else if (v.equals("C") ||v.equals("c"))
        {

            AdminM();

        }
    }
    
    
    private void DeleteUser()
           
    {      String Name="",UserT="";
    LOGIN t=new LOGIN();
           System.out.println("""
                                \n\t\t* press 1 To Delete an admin  :   ¶
                                \n\t\t* press 2 To Delete the user  :   ¶
                                \n\t\t* press 3 To Delete All Drugs :   ¶
                                \t\t\t\t ||--> """);
            int n = getInt();
            if(n==1)
            {
                System.out.println("Enter the Name you Want to delete");
                Name=getstring();
                UserT="Manager";
                   t.fileO(UserT,Name , "", 1); 
            }
            else if(n==2)
            {
                System.out.println("Enter the Name you Want to delete");
                Name=getstring();
                UserT="Pharmacist";
                t.fileO(UserT,Name , "", 1);
            }
             else if(n==3)
            {
               
                UserT="Items";
                 t.fileO(UserT, "Drugs", "", 1);
                 t.fileO(UserT, "Sold", "", 1);
            }
       item.removeAll(item);
       sold.removeAll(sold);
       
       
    }
}
