package L3_StreamsAPI_Lymbda;

public class Phone {
  private String name;
  private int price;
  private String company;

  public Phone(){

  }
  public Phone(String name, int price){
    this.name = name;
    this.price = price;
  }

  public Phone(String name, int price, String company){
    this.name = name;
    this.price = price;
    this.company = company;
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }

  public int getPrice(){
    return this.price;
  }

  public void setPrice(int price){
    this.price = price;
  }

  public String getCompany(){
    return this.company;
  }


  public static int compare (Phone p1, Phone p2){ // метод реализован согласно сигнатуре интерфейса Comparator
    if(p1.getPrice()>p2.getPrice())
      return 1;
    return -1;
  }
}
