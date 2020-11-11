package programFirstLesson;


public class Shape {
  private double width;
  private double height;
  public String typeShape;

  Shape(String typeShape){        // конструктор по умолчанию
    this.width = 0;
    this.height = 0;
    this.typeShape = typeShape;
  }

  Shape(double w, double h, String typeShape){ //пример перегрузки конструктора
    this.width = w;
    this.height = h;
    this.typeShape = typeShape;
  }

  Shape(int w, int h, String typeShape){    //пример перегрузки конструктора
    this.width = w;
    this.height = h;
    this.typeShape = typeShape;
  }

  Shape(float w, float h, String typeShape){ //пример перегрузки конструктора
    this.width = w;
    this.height = h;
    this.typeShape = typeShape;
  }


  // интерфейсы доступа к закрытым атрибутам и методу родителя
  double getWidth(){return width;}
  double getHeight(){return  height;}

  // вычисление площади фигуры
  private double area(double width, double height){
    return width*height;
  }

  private int area(int width, int height){ // пример перегрузки метода подсчета площади
    return width*height;
  }

  private float area(float width, float height){ // пример перегрузки метода подсчета площади
    return width * height;
  }

  double getArea(){
    System.out.println("Площадь " + this.typeShape + " равна: " + area(width, height));
    return area(width, height);
  }

  void showInfo(){
    System.out.println();
    System.out.println(this.typeShape + " параметры: ");
    System.out.println("ширина " + this.width);
    System.out.println("высота " + this.height);
   }





}
