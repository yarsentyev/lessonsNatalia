package programFirstLesson.itSpecialists;

public class QA extends AbstractClassITSpecialist {

  QA() {
    super();
  }

  QA(String name, String position) {
    super(name, "tester");
  }

  public QA(String name) {
    super(name);
    this.position = "tester";
  }

  @Override
  public void doTasks() {
    System.out.println("Name: " + this.getName()+"  "+ "Position: " + this.position);
    System.out.println("Делай работу: Тестируй!");
    System.out.println();
  }

}
