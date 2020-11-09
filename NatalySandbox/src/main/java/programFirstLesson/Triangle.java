package programFirstLesson;

public class Triangle extends Shape {
   private String type;

  Triangle (){
    super("Треугольник");
    this.type = "";
  }

  Triangle (String t, double w, double h){
    super(w, h, "Треугольник"); // вызов конструктора родителя
    this.type = t;
  }


    double getArea(){   //Метод родителя переопределен
      double s = getWidth()*getHeight()/2;
      System.out.println("Площадь треугольника равна: "+ s);
      return s;
    }

}
