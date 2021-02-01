package programFirstLesson.itSpecialists;

public class ITSpecialist {
  private String name;
  private String position;

  public ITSpecialist(){
    this.name = " ";
    this.position = " ";
  }

  public ITSpecialist(String name, String position){
    this.name = name;
    this.position = position;
  }

  public void doTasks(){
    switch (this.position){
      case ("Программист"):
        System.out.println("Разрабатывай!");
        break;
      case ( "Тестировщик"):
        System.out.println("Тестируй!");
        break;
      case ("Бизнес-аналитик"):
        System.out.println("Анализируй!");
        break;
      default:
        System.out.println();
        break;
    }

}

  public String  getName(){
    return this.name;
  }
  public String  getPosition(){
    return this.position;
  }

}
