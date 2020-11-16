package programFirstLesson;

public class Rectangle extends Shape {

  Rectangle(){
    super("Прямоугольник");
  }

  Rectangle(double h, double w){
    super(h,w, "Прямоугольник"); //Пример вызова конструктора родителя (супер-класса)

  }

  boolean checkSquare(){
    if (getHeight() == getWidth()) return true;
    return  false;
  }

  double getArea(){ //вызов в подклассе метода родителя
    return super.getArea();
  }
}
