
package pharmacy_drug_store_manager;


 class Datatype {
    String DName,ex_date,Quantity;
    int serial_no;
    double price;

     public Datatype(String DName,int serial_no,String ex_date,double price,String Quantity)
    {
        this.DName=DName;
        this.serial_no=serial_no;
        this.ex_date=ex_date;
        this.price=price;
        this.Quantity=Quantity;
    }
    @Override
    public String toString()
    {
        String out="  \t  "+DName+"  \t  "+serial_no+"  \t  "+ex_date+"  \t  "+price+"  \t  "+Quantity+"  \t  "+"\\*";
        return out;
    }

}
