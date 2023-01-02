
package pharmacy_drug_store_manager;

import java.io.*;
import java.util.Scanner;


public class LOGIN {
    
     Scanner in = new Scanner(System.in);

    public void LOGIN_UI() {
        System.out.println("""
                            \t\t*****************************************************************
                            \t\t                           ||WELCOME||                              
                            \t\t***************************************************************** 
                """);

        System.out.println("\n\n\t\t**************************************:  |"
                + "\n\t\t* press 1 To Login as an Admin                 "
                + "\n\t\t* press 2 To Login as  Pharmacist              "
                + "\n\t\t* press Any Key to  Exit                       "
                + "\n\t\t**************************************:  |"
                + "\n\t\t\t\t ||-->  ");

        int choice = in.nextInt();
        switch (choice) {
            case 1:
                Authenticate("Manager");
                break;
            case 2:
                Authenticate("Pharmacist");
                break;
            default:
               System.out.println("\n\t\tINVALID INPUT\n\n");
               
        }
    }

    private void Authenticate(String User) {
        String Uname;
        String pass;
        System.out.flush();
        System.out.println("\tUsername:-->  ");
        Uname = in.next();
        System.out.println("\tPassword:-->  ");
        pass = in.next();

        fileO(User,Uname,pass,0);

    }

    public void fileO(String UserT, String Username, String password,int b) {
        File User_F = new File(UserT + "/" + Username + ".txt");
       
        if (User_F.exists()) {
            if(b==1)
            {   try {
                File User = new File(UserT + "/" + Username + ".txt");
              User.deleteOnExit();
              System.out.println("\n\tTHE DATA HAS BEEN DELETED\n");
                
              }
             catch(Exception e) {
                 System.out.println("\n\tTHE DATA HAS NOT BEEN DELETED\n");
                   }
            }
            else{
            try {
                BufferedReader in = new BufferedReader(new FileReader(User_F));
                String str = in.readLine();
                    String[] list = str.split(",");
                    if(password.equals(list[0])) {
                        if (UserT.equals("Manager")) {
                            Admin Add = new Admin();
                            Add.File("Drugs");
                            Add.File("Sold");
                            Add.AdminM();
                        } else if (UserT.equals("Pharmacist")) {
                            Pharmacist pharma=new Pharmacist();
                        }


                    } else {
                        System.out.println("\n\tINCORRECT PASSWORD\n");

                    }

            } catch (IOException ex) {
            }
          
        }
            
        }
        else {
            System.out.println("\n\tACCOUNT NOT FIND\n");
              }

    }

}
