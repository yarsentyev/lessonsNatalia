public class Laptop {
  String brand;
  int frequency;
  int memory;

  public Laptop(String brand,int frequency,int memory){
  this.brand = brand;
  this.frequency = frequency;
  this.memory= memory;
  }

  public boolean equals(Laptop obj){
    if ((this.frequency == obj.frequency)&&(this.memory==obj.memory))
      return true;
    return false;
  }

}
