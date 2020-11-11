package programFirstLesson;

public class StaticDemo {
  int x;
  int y;
  static double result;

  StaticDemo(){
    x=0;
    y=0;
    System.out.println("Внутри конструктора");
  }

  StaticDemo(int x, int y){
    System.out.println("Внутри конструктора");
    this.x = x;
    this.y = y;
  }

  static double sum(double a, double b){ return a+b;  } // Статистический метод

  static void print(String msg){ System.out.println(msg);   } // Статистический метод

  static {    //Этот блок выполняется при загрузке класс
    System.out.println("Статистический блок выполняется при загрузке класса, даже перед конструктором");
    result = 12*12;
  }

  // Нестатистический блок
  {
    System.out.println("Нестатистический блок реализации");
  }

}
