package programFirstLesson.itSpecialists;

import programFirstLesson.StaticDemo;

public class Developer extends AbstractClassITSpecialists {

  Developer(){
    super();
  }

  public Developer(String name){
    super (name);
    this.position = "developer";
  }


  @Override
  public void doTasks() {
    System.out.println("Name: " + this.getName() + "Position: " + this.position);
    System.out.println("Делай работу: Программируй!");
    System.out.println();
  }
}
