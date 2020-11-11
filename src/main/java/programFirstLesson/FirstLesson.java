package programFirstLesson;

import programFirstLesson.itSpecialists.Developer;
import programFirstLesson.itSpecialists.ITSpecialist;
import programFirstLesson.itSpecialists.QA;

import java.util.*;

public class FirstLesson {
  public static void main (String  args[]){

// 1)-2) Наследование и инкапсуляция; полиморфизм на примере переопределения и перегрузки
    // класс родитель - Shape, классы потомки Triangle, Rectangle
    System.out.println("1)-2) Наследование и инкапсуляция, полиморфизм на примере переопределения и перегрузки");
    Triangle t1 = new Triangle ("равнобедренный", 8,3);
    Triangle t2 = new Triangle("разносторонний", 7., 4.15);
    Rectangle r1 = new Rectangle(5.15, 4.0);
    Rectangle r2 = new Rectangle(2,3);

    t2.showInfo();// вызов метода родительского класса
    t2.getArea(); // метод переопределен в подклассе
    System.out.println();
    r1.showInfo();
    double area = r1.getArea();// вызов метода родителя в подклассе

    System.out.println();
    System.out.println("__________________________________________________________");

// 3) Задание ITSpecialists
        /* Так и не поняла, что значит полиморфизм на примере интерфейсов.
           Пыталась придумать, чтобы в метод передавался атрибут position и
           перегрузить его, но с реализацией не срослось. Выполнила через switch*/
    System.out.println("3) Полиморфизм на примере интерфейсов ");
    ITSpecialist dev = new ITSpecialist("Алексей", "Программист");
    dev.getName();
    dev.doTasks();
    ITSpecialist qa = new ITSpecialist("Лев", "Тестировщик");
    qa.getName();
    qa.doTasks();
    ITSpecialist ba = new ITSpecialist("Катя", "Бизнес-аналитик");
    ba.getName();
    ba.doTasks();

// 4) Задание абстрактный класс
    // Экземпляр абстрактного класса создать нельзя
    System.out.println("4) Абстрактный класс AbstractClassITSpecialist, абстрактный метод do.Tasks");
    Developer d = new Developer("Sasha");
    QA qa2 = new QA ("Petr");
    d.doTasks();
    qa2.doTasks();

// 5) Предотвращение наследования 'final'
    System.out.println("5) Предотвращение наследования 'final'");
    FinalClassBooks book = new FinalClassBooks("Старик и море", "Хемингуэй", "Художественная", 200);
    book.getInfo();

/* 6)
    Создай класс, который содержит пару статических методов.
    В рамках main вызови эти статические методы.
    Нужно ли создавать экземпляр класса для вызова статического метода?
 */
    System.out.println();
    System.out.println("6) Статистические методы");
    System.out.println(StaticDemo.sum(1.141414, 2.34567)); // вызов статистического метода sum(),
                                                                // экземпляр класса создавать не нужно
                                                                //для вызова статистического метода
    StaticDemo.print("Hello, world!");

/* 7) - 8) В предыдущем классе из пункта 6 создай блок статической инициализации вида
static { <тут что-то> }. Когда он будет выполнен? Покажи в main.
    Раз создали класс с блоком статической инициализации, создай класс с блоком
    НЕстатической инициализации вида { <тут что-то> }*/
    System.out.println(StaticDemo.result);          /*статистический блок будет выполнятся при первом обращении к классу
                                                    в котором он содержится: см упр 6*/
    StaticDemo staticD = new StaticDemo(5, 4);

 //9) Создай массив из 3 элементов. Какой индекс имеет первый элемент массива?
    // Размести в массиве 3 разных ITSpecialist в доступных элементах.
    // Что произойдет, если попробуешь добавить четвертого? Что нужно сделать,
    // чтобы программа продолжила выполняться после попытки добавить четвертого?
    System.out.println();
    System.out.println("Задание 9) - 10). Массив объектов");
    ITSpecialist employee[] = {dev,qa,ba};

   /*При попытке добавить четвертого при выполнении программы возникает ошибка ArrayIndexOutOfBoundsException
    Чтобы программа продолжила выполняться нужно обработать ошибку (обработать исключение)*/
    try {
      employee[3] = dev;
    }

    catch (ArrayIndexOutOfBoundsException exc){
      System.out.println("Выход за границы массива!");
    }

//10) Циклом пройдись по массиву и распечатай имена добавленных специалистов
    for (int i=0; i<3; i=i+1){ //Пример цикла for. index первого элемента массива равен 0
      System.out.println("должность " + employee[i].getPosition() + " имя сотрудника " + employee[i].getName());
    }

// 11) Создай три любых реализации интерфейса List на твой выбор. Но одна из них должна быть ArrayList=)
// В каждую положи трех специалистов. Пройдись по каждому из них циклами
// (для разнообразия не используй цикл из 10го пункта, почитай, какие есть циклы в Java) и распечатай имена специалистов.
    System.out.println();
    System.out.println("11 Interface List");
    ArrayList <ITSpecialist>  listA = new ArrayList<ITSpecialist>();
    listA.add(dev);
    listA.add(qa);
    listA.add(ba);
    for(ITSpecialist x: listA){     // Разновидность цикла for: for-each
      System.out.println(x.getName() + " "+ x.getPosition());
    }

    System.out.println("LinkedList");  // очень хотела сделать, чтобы в этом списке хранились объекты класс ITSpecialists
                                      //но либо не нашла, либо объекты не могут хранится в нем
    LinkedList myLinkedList = new LinkedList();

    myLinkedList.add(dev.getName());
    myLinkedList.add(qa.getName());
    myLinkedList.add(ba.getName());
    System.out.println(myLinkedList);

    int i=0;
    while (i<myLinkedList.size()){  //пример цикла while
      System.out.println(myLinkedList.get(i));
      i++;
    }

/*12) Создай HashMap опять-таки из трех специалистов, где ключ - имя специалиста, а значение - сам объект-специалист.
Циклом пройдись по HashMap и распечатай текст, включающий в себя имя спеца и работу, которую он делает
(т.е. текст включает в себя и значение ключа, и результат выполнения работы).
А можно положить в HashMap специалистов с одинаковым именем? А почему?* (это вопрос со звездочкой=)
 */
    System.out.println("Задание 12 HashMap");
    Map<String, ITSpecialist > hashMap = new HashMap<String, ITSpecialist>();
    hashMap.put(dev.getName(),dev); //Заполнение карты значениями
    hashMap.put(qa.getName(),qa);  // В HashMap не может быть специалистов с одинаковым именем, т.к. имя в данном случае - ключ
    hashMap.put(ba.getName(), ba); // при добавлении еще одного специалиста с одинак именем, предыдущее значение затрется

    for (Map.Entry<String, ITSpecialist> entry : hashMap.entrySet()) {
      ITSpecialist itSpec = new ITSpecialist();
      itSpec = entry.getValue();

      System.out.print( " Name " + entry.getKey() + "  " );
      itSpec.doTasks();
    }

    // Если все лежит в одном пакете, то модификатор доступа protected не продемонстрировать
    Developer develp = new Developer("Ivan");
    // develp.surname = "Petrov"; // Aтрибут Surname protected, в этом месте появится ошибка


  }
}
