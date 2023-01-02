
package pharmacy_drug_store_manager;

import java.io.*;
import java.util.*;


public class ITEM {
    
    String DName,ex_date,Quantity;
    double price;
    int serial_no;
   
    //An array list to store the Drugs on the stock
    public List<Datatype> item = new ArrayList<>();
    //An array list to store Sold Drugs 
    public List<Datatype> sold = new ArrayList<>();

//to Accepth an string value from the user and validate it
    public String getstring() {
        String In_Str="";
     
        try {

            Scanner input = new Scanner(System.in);
            In_Str = input.nextLine();
        }catch(Exception e){
            System.out.println("INVALID INPUT \n Enter AGAIN -->");
            getstring();
        }
        return In_Str;
    }
    
    //to Accepth an Integer value from the user and validate it
    public int getInt() {
        int In_Int=0;
        Scanner input =new Scanner(System.in);
        try {
            In_Int = input.nextInt();
        }catch (Exception e){System.out.println("INVALID INPUT ONLY NUMBER IS ALLOWED\n Enter AGAIN -->");}
        return In_Int;
          }
    
   protected void AddE()
        {
            System.out.println("""
                    \t\t**************************************:   ¶
                    \t\t* press 1 To Add a New Drug            :   ¶
                    \t\t* press 2 To Display Drug Information  :   ¶
                    \t\t* press 3 To Sell Drug                 :   ¶
                    \t\t* press 4 To SORT Drug                 :   ¶
                    \t\t* press 5 To Search A Drug             :   ¶
                    \t\t* press 6 To Update Drug Statues       :   ¶
                    \t\t* press Any Key to Logout and Exit     :   ¶
                    \t\t**************************************:    ¶
                    \t\t\t\t ||--> \s""");

            int choice;
            System.out.println("Enter your choice");
            choice = getInt();
            switch (choice) {

                case 1:
                    Adder();
                    choice("Add");
                    break;
                case 2:
                    Display();
                    choice("Display");
                    break;
                case 3:
                    Delete();
                    choice("Delete");
                    break;
                case 4:
                    Sort();
                    choice("Sort");
                    break;
                case 5:
                    Search();
                    choice("Search");
                    break;
                case 6:
                    Update();
                    choice("update");
                    break;
                default:
                    System.out.println("GOODBYE");




            }
        }
       //accept the new drug and store in the array list
    protected void Adder() {


    System.out.println("Enter the Name of the drug");
    DName = getstring();
    System.out.println("Enter the Serial number of the drug");
    serial_no = getInt();
    System.out.println("Enter the expire date of the drug");
    ex_date = getstring();
    System.out.println("Enter the Quantity of the drug in gram");
    Quantity = getstring();
    System.out.println("Enter the Price");
    price = getInt();
    int found=0;
    int d;
    
     for(Datatype D:item)
               {
                   if(D.serial_no==serial_no) {
                       
                       found++;
                   }
               }
    if(found==0)
    {
    item.add(new Datatype(DName, serial_no, ex_date, price, Quantity));   
     System.out.println("\n\n\tThe Item is Added Successfully\n");
        file_out("Drugs");//update the inputed value to the file
    }
    else
    {
        System.out.println("\n\n\tTHE DRUG IS ALREADY ON THE LIST\n\n");
    }
    
   
   
    }
    
    //to store values to the file
    protected void file_out(String Type)
{

    File f = new File("Items/"+Type+".txt");
    try {
        f.createNewFile();
        BufferedWriter out=new BufferedWriter(new FileWriter(f));
        out.flush();

        if(Type.equals("Drugs"))
        {
            out.write(String.valueOf(item));
        }
        else if(Type.equals("Sold"))
        {
            out.write(String.valueOf(sold));
        }

        out.newLine();
        out.close();

    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    catch (IOException e) {
        throw new RuntimeException(e);
    }

}
    
    protected void Delete() {

     String Name;int Ser_no,index_of_item=0,found=0;
     int size= sold.size();
     System.out.println("Enter the Name of the Drug you want to Sell");
     Name = getstring();
     System.out.println("Enter the Serial number of the Drug you want to Sell");
     Ser_no=getInt();
     try{
        for(Datatype D:item)
        {
            if(D.serial_no==Ser_no) {
                System.out.println("\n\t\the Drug is Successfully Sold \n\n");
                sold.add(new Datatype(D.DName,D.serial_no,D.ex_date,D.price,D.Quantity));
                item.remove(index_of_item);
                found++;
            }
            index_of_item++;
        }
        if(found==0)
        {
            System.out.println("\t\tTHE ITEM ID NOT ON THE LIST\n\n");
        }

}catch (Exception e){
         System.out.println("\n\t\tThe Item is not on the list");
     }
        file_out("Drugs");
        file_out("Sold");


 }
    
    
 public void File(String Type) {
     File f = new File("Items/"+Type+".txt");

     if (f.exists()) {
         try {
             BufferedReader read = new BufferedReader(new FileReader(f));
             String  text;

             // Closes the reader
             text = read.readLine();

             String[] list = text.split("\\*");
             int i=0;
             while (i<((list.length)-1))
             {
                 String[] list1 = list[i].split("  \t  ");
                 DName = list1[1].replaceAll("\\s", "");
                 serial_no = Integer.parseInt(list1[2]);
                 ex_date = list1[3].replaceAll("\\s", "");
                 price= Double.parseDouble(list1[4]);
                 Quantity=(list1[5]).replaceAll("[^a-zA-Z1\\d]","");
                 if(Type.equals("Drugs"))
                 {
                     item.add(new Datatype(DName, serial_no, ex_date, price, Quantity));
                 }
                 else if(Type.equals("Sold"))
                 {
                     sold.add(new Datatype(DName, serial_no, ex_date, price, Quantity));
                 }

                 i++;

             }


         } catch (IOException e) {
             throw new RuntimeException(e);
             }

     }


 }
    public void Display()
   {
       System.out.println("\t****************************************************************************\n\n");
       System.out.println("  \t  NAME\t\t\tSerial_no\t\texpire Date\tPrice\tQuantity\t\n");

       for(Datatype D:item)
       {
           System.out.println(D);
       }
       System.out.println("\n\n\t****************************************************************************\n\n");

   }
    
    
    public void choice(String Type)
{
    System.out.println("\n\t\t\t***********SELECT YOUR CHOICE*************\n" +
            "\t\t**Enter C/c to back to main menu              :\n" +
            "\t\t**Enter B/b to "+Type+" Item Information Again :\n" +
            "\t\t**Enter any key to exit                       :\n");
       String v =getstring();
    if (v.equals("B")||v.equals("b")) {
        switch(Type)
        {
            case "Display":
                Display();
                break;
            case "Add":
                Adder();
                break;
            case "Delete":
                Delete();
                break;
            case "Search":
                Search();
                break;
            case "Sort":
                Sort();
                break;
            case "update":
                Update();
                break;
        }

    }
    else if (v.equals("C") ||v.equals("c"))
    {

        AddE();

    }
}
    
    
    public void Search()
   {
    System.out.println("""
            \t\t*************************************************:   **
            \t\t* press 1 to Search by Name                      :   **
            \t\t* press 2 to Search by serial Number             :   **
            \t\t* press Any Key to Exit                          :   **
            \t\t*************************************************:   **
            """);
    int choice=getInt();
    switch(choice)
    {
        case 1:
            String name;
            int found=0;
            System.out.println("Name of the drug");
            name = getstring();
            Datatype p = new Datatype(name, 0, "", 0, "");
            int index = Collections.binarySearch(item, p, (o1, o2) -> {
                return o1.DName.compareTo(o2.DName);
            });
            System.out.println("\t****************************************************************************\n\n");
            for(Datatype D:item)
            {
                if(D.DName.compareTo(name)==0) {
                    System.out.println(D);
                    found++;
                }
            }
            System.out.println("\n\n\t****************************************************************************\n\n");
            if(found==0)
            {
                System.out.println("\n\t\tTHE DATA IS NOT ON THE LIST");
            }
            break;


        case 2:


               System.out.println("Serial number of the drug");
               int Ser;
               Ser = getInt();

               found=0;

               System.out.println("\t****************************************************************************\n\n");
               for(Datatype D:item)
               {
                   if(D.serial_no==Ser) {
                       System.out.println(D);
                       found++;
                   }
               }
               System.out.println("\n\n\t****************************************************************************\n\n");
               if(found==0)
               {
                   System.out.println("\n\t\tTHE DATA IS NOT ON THE LIST\n");
               }

       }

   }

    
    public void Sort()
    {
        System.out.println("""
                \t\t***********************************************:   **
                \t\t* press 1 to Sort by Name                      :   **
                \t\t* press 2 to Sort by Price                     :   **
                \t\t* press Any Key to Exit                        :   **
                \t\t***********************************************:   **
                """);
       int pri=getInt();
       switch(pri)
       {
         case 1:
             item.sort((o1, o2) -> {
                 return o1.DName.compareTo(o2.DName);
             });
            Display();

            break;

         case 2:
             item.sort((o1, o2) -> {
                 return o1.price > o2.price ? -1 : o1.price < o2.price ? 1 : 0;
             });
            Display();
            break;

       }


    }
    
    
    public void Update()
    {
        String name,exd,qu;
        int sno,found=0;
        double pr;
          System.out.println("What is the Name of item you want to update");
        name = getstring();
        System.out.println("Enter the Serial number of the drug");
        sno= getInt();
        for(Datatype D:item)
        {
            if((D.serial_no==sno)&&(D.DName.compareTo(name)==0)) {
                System.out.println("\t\tTHE DRUG IS FOUND\n\n");
                System.out.println("Enter the new expire date of the drug");
                D.ex_date = getstring();
                System.out.println("Enter the new Quantity of the drug");
                D.Quantity = getstring();
                System.out.println("Enter the new Price");
                D.price= getInt();
                System.out.println("\t\tTHE DRUG IS UPDATED SUCCESSFULLY\n\n");
                found++;
            }
        }

        file_out("Drugs");
    }

}
