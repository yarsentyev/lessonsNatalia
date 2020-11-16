package programFirstLesson.itSpecialists;

public class QA extends AbstractClassITSpecialists {

  QA() {
    super();
  }

  public QA(String n) {
    super(n);
    this.position = "tester";
  }

  @Override
  public void doTasks() {
    System.out.println("Name: " + this.getName()+"  "+ "Position: " + this.position);
    System.out.println("Делай работу: Тестируй!");
    System.out.println();
  }

}
