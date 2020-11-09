package programFirstLesson.itSpecialists;

abstract class AbstractClassITSpecialist { //Объект абстрактного класса нельзя создать
  private String name;
  public String position;
  protected String surname; //к данному атрибуту имеют доступ только подклассы этого класса
                            // или те, классы, что находятся в этом же пакете

  AbstractClassITSpecialist(){
    this.name = "";
    this.position = "";
    this.surname = "";
  }

  AbstractClassITSpecialist (String name, String position, String surname){
    this.name = name;
    this.position = position;
    this.surname = surname;
  }

  AbstractClassITSpecialist(String name){
  this.name = name;
  this.position = "";
  }

  public String getName(){ return this.name;  }

  abstract void doTasks();
}
