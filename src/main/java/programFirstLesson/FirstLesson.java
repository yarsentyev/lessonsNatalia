package programFirstLesson;

import L3_StreamsAPI_Lymbda.MyFunctionalInterface;
import L3_StreamsAPI_Lymbda.StringConCat;
import L3_StreamsAPI_Lymbda.Phone;
import L3_StreamsAPI_Lymbda.JThread;
import L3_StreamsAPI_Lymbda.PhoneComparator;
import programFirstLesson.itSpecialists.Developer;
import programFirstLesson.itSpecialists.ITSpecialist;
import programFirstLesson.itSpecialists.QA;
import programFirstLesson.robots.Robot;
import programFirstLesson.robots.RobotBattleRobot;
import programFirstLesson.robots.RobotSecretary;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstLesson {
  public static void main(String args[]) {

// 1)-2) Наследование и инкапсуляция; полиморфизм на примере переопределения и перегрузки
    // класс родитель - Shape, классы потомки Triangle, Rectangle
    System.out.println("1)-2) Наследование и инкапсуляция, полиморфизм на примере переопределения и перегрузки");
    Triangle t1 = new Triangle("равнобедренный", 8, 3);
    Triangle t2 = new Triangle("разносторонний", 7., 4.15);
    Rectangle r1 = new Rectangle(5.15, 4.0);
    Rectangle r2 = new Rectangle(2, 3);

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


    //3*) Полиморфизм через интерфейсы
    Robot cyberLady = new RobotSecretary();
    Robot terminator = new RobotBattleRobot();


    cyberLady.sayTheName();
    cyberLady.doTheJob();
    terminator.sayTheName();
    terminator.doTheJob();


// 4) Задание абстрактный класс
    // Экземпляр абстрактного класса создать нельзя
    System.out.println("4) Абстрактный класс AbstractClassITSpecialist, абстрактный метод do.Tasks");
    Developer d = new Developer("Sasha");
    QA qa2 = new QA("Petr");
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
    ITSpecialist employee[] = {dev, qa, ba};

   /*При попытке добавить четвертого при выполнении программы возникает ошибка ArrayIndexOutOfBoundsException
    Чтобы программа продолжила выполняться нужно обработать ошибку (обработать исключение)*/
    try {
      employee[3] = dev;
    } catch (ArrayIndexOutOfBoundsException exc) {
      System.out.println("Выход за границы массива!");
    }

//10) Циклом пройдись по массиву и распечатай имена добавленных специалистов
    for (int i = 0; i < 3; i = i + 1) { //Пример цикла for. index первого элемента массива равен 0
      System.out.println("должность " + employee[i].getPosition() + " имя сотрудника " + employee[i].getName());
    }

// 11) Создай три любых реализации интерфейса List на твой выбор. Но одна из них должна быть ArrayList=)
// В каждую положи трех специалистов. Пройдись по каждому из них циклами
// (для разнообразия не используй цикл из 10го пункта, почитай, какие есть циклы в Java) и распечатай имена специалистов.
    System.out.println();
    System.out.println("11 Interface List");
    ArrayList<ITSpecialist> listA = new ArrayList();
    listA.add(dev);
    listA.add(qa);
    listA.add(ba);
    for (ITSpecialist x : listA) {     // Разновидность цикла for: for-each
      System.out.println(x.getName() + " " + x.getPosition());
    }

    System.out.println("LinkedList");  // очень хотела сделать, чтобы в этом списке хранились объекты класс ITSpecialists
    //но либо не нашла, либо объекты не могут хранится в нем
    LinkedList<ITSpecialist> myLinkedList = new LinkedList();

    myLinkedList.add(dev);
    myLinkedList.add(qa);
    myLinkedList.add(ba);


    for (ITSpecialist x : myLinkedList) {     // Разновидность цикла for: for-each
      System.out.println(x.getName() + " " + x.getPosition());
    }

    int i = 0;
    while (i < myLinkedList.size()) {  //пример цикла while
      System.out.println(myLinkedList.get(i));
      i++;
    }

    //VIA STREAM
    myLinkedList.stream().forEach(ITSpecialist::getName);

/*12) Создай HashMap опять-таки из трех специалистов, где ключ - имя специалиста, а значение - сам объект-специалист.
Циклом пройдись по HashMap и распечатай текст, включающий в себя имя спеца и работу, которую он делает
(т.е. текст включает в себя и значение ключа, и результат выполнения работы).
А можно положить в HashMap специалистов с одинаковым именем? А почему?* (это вопрос со звездочкой=)
 */
    System.out.println("Задание 12 HashMap");
    Map<String, ITSpecialist> hashMap = new HashMap<String, ITSpecialist>();
    hashMap.put(dev.getName(), dev); //Заполнение карты значениями
    hashMap.put(qa.getName(), qa);  // В HashMap не может быть специалистов с одинаковым именем, т.к. хэшкод от имени в данном случае - ключ
    hashMap.put(ba.getName(), ba); // при добавлении еще одного специалиста с одинак именем, предыдущее значение затрется

    for (Map.Entry<String, ITSpecialist> entry : hashMap.entrySet()) {
      ITSpecialist itSpec = new ITSpecialist();
      itSpec = entry.getValue();

      System.out.print(" Name " + entry.getKey() + "  ");
      itSpec.doTasks();
    }

    // Если все лежит в одном пакете, то модификатор доступа protected не продемонстрировать
    Developer develp = new Developer("Ivan");
    // develp.surname = "Petrov"; // Aтрибут Surname protected, в этом месте появится ошибка


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Lesson 3

    //**********LYAMBDA***************/
    /*Лямбда-выражение — это такая функция.  обычный метод в Java,
            только его особенность в том, что его можно передавать в другие методы в качестве аргумента.*/

    System.out.println();
    System.out.println("LYMBDA");
    // лямбда выражение
    MyFunctionalInterface msg = () -> {
      return "Привет мир";
    };
    System.out.println(msg.sayHello());

    StringConCat lyambda = (str1, str2) -> str1 + str2;
    System.out.println("Result: "+lyambda.sconcat("Hello ", "World"));


    //**********Поток Thread***************/
    System.out.println();
    System.out.println("**********Поток Thread***************");
    Thread thread1 = Thread.currentThread(); // получаем главный поток
    System.out.println(thread1.getName()); // main
    System.out.println(thread1); // main [имя потока; приоритет; имя группы потоков, к кот относит текущий]
    thread1.interrupt();

    System.out.println("Main thread started...");
    new JThread("JThread").start();
    System.out.println("Main thread finished...");

    // сюда можно еще много чего добавить по работе с объектами класса Thread


    //**********Stream API***************/
    // Создание потока данных
    System.out.println();
    System.out.println("Создание потока данных и вывод отфильтрованных по длине равной 6 символам");
    ArrayList<String> cities = new ArrayList<String>();
    Collections.addAll(cities, "Париж", "Нью-Йорк", "Мадрид", "Токио", "Лондон");
    Stream<String> citiesStream = cities.stream(); // получаем поток
    citiesStream = citiesStream.filter(s -> s.length() == 6); //фильтрация по длине строки, в скобках лямбда-выражение (промежуточная операция)
    citiesStream.forEach(s -> System.out.println(s)); //вывод отфильтрованных строк на консоль (терминальная операция)

    //Пример сортировки потока объектов Phone
    System.out.println();
    System.out.println("Сортировка потока объектов");
    Stream<Phone> phoneStream = Stream.of(new Phone("iPhone X", 600), new Phone("Pixel 2", 500),
            new Phone("Nokia 9", 450), new Phone("Galaxy S9", 300));

    phoneStream.sorted(new PhoneComparator()).forEach(s -> System.out.printf("%s - %d\n", s.getName(), s.getPrice()));

    // Отображение. Метод Map
    System.out.println();
    System.out.println("Метод Map. Преобразование одного объекта в другой: от типа Phone к типу String");
    Stream<Phone> phoneStream1 = Stream.of(new Phone("iPhone X1", 800), new Phone("Pixel 21", 700),
            new Phone("Nokia 91", 850), new Phone("Galaxy S91", 770));
    phoneStream1
            .map(p -> p.getName()) //помещаем в поток только названия телефонов
            .forEach(s -> System.out.println(s));

/*? не находит методы takeWhile (выбирает из потока элементы, пока они соответствуют условию) and DropWhile
    //Получение подпотока и объединенные потоки на основе имеющихся
    System.out.println();
    System.out.println("Получение подпотока и объединенные потоки на основе имеющихся");
    Stream<Integer> numbers = Stream.of(-4,-3,-8,-5,0,8,7,6,-6,-7);
    numbers.takeWhile(n->n<0)
            .forEach(n->System.out.println(n));
 */

    // Методы Skip (пропустить элемент) и Limit (выбрать два следующих элемента)
    System.out.println();
    System.out.println("Методы Skip and limit");
    Stream<Phone> phoneStream2 = Stream.of(new Phone("iPhone X2", 800), new Phone("Pixel 2", 700),
            new Phone("Nokia 2", 850), new Phone("Galaxy S2", 770));
    phoneStream2.skip(1)
            .limit(3)
            .forEach(s -> System.out.println(s.getName()));

    //Метод count возвращает количество элементов в потоке данных
    System.out.println();
    System.out.println("Метод Count");
    ArrayList<String> names = new ArrayList<String>();
    names.add("Tom");
    names.add("Bob");
    names.add("Alice");
    names.add("Sam");
    System.out.println(names.stream().count());
    System.out.println(names.stream().filter(n -> n.length() <= 3).count());

    //Метод findFirst and FindAny, возвращает значение обернутое в объект типа Optional
    System.out.println();
    System.out.println("Метод FindFirst&FindAny");
    ArrayList<String> nam = new ArrayList<String>();
    nam.add("Tom");
    nam.add("Bob");
    nam.add("Alice");
    nam.add("Sam");

    System.out.println(nam.stream().findFirst().get());
    Optional<String> any = nam.stream().findAny();
    System.out.println(any.get());

    //Метод allMatch, anyMatch, noneMatch
    System.out.println();
    System.out.println("Методы allMatch, anyMatch, noneMatch ");
    Stream<Integer> number1 = Stream.of(-4, -3, -8, -5, 0, 8, 7, 6, -6, -7);
    System.out.println("Условие для всех элементов потока выполняется? " + number1.allMatch(s -> s < 0));
    Stream<Integer> number2 = Stream.of(-4, -3, -8, -5, 0, 8, 7, 6, -6, -7);
    System.out.println("Условие хотя бы для одного из элементов потока выполняется? " + number2.anyMatch(s -> s < 0));
    Stream<Integer> number3 = Stream.of(-4, -3, -8, -5, 0, 8, 7, 6, -6, -7);
    System.out.println("Условие для всех элементов потока не выполняется? " + number3.noneMatch(s -> s < 0));

    // Методы min и max
    System.out.println();
    System.out.println("Методы min и max ");
    Stream<Integer> numberMin = Stream.of(-4, -3, -8, -5, 0, 8, 7, 6, -6, -7);
    System.out.println(numberMin.min(Integer::compareTo).get());

    Stream<Integer> numberMax = Stream.of(-4, -3, -8, -5, 0, 9, 7, 6, -6, -7);
    Optional<Integer> max = numberMax.max(Integer::compareTo); // возвращает значение типа Optional
    System.out.println(max.get());

    //Для более сложных объектов используется метод compare
    Stream<Phone> phoneStreamCMin = Stream.of(new Phone("iPhone X", 600), new Phone("Pixel 2", 500),
            new Phone("Nokia 9", 450), new Phone("Galaxy S9", 300));
    Phone minPhone = new Phone();
    minPhone = phoneStreamCMin.min(Phone::compare).get();
    System.out.printf("Min Name:%s Price: %d \n", minPhone.getName(), minPhone.getPrice());

    Stream<Phone> phoneStreamCMax = Stream.of(new Phone("iPhone X", 600), new Phone("Pixel 2", 500),
            new Phone("Nokia 9", 450), new Phone("Galaxy S9", 300));
    Phone maxPhone = new Phone();
    maxPhone = phoneStreamCMax.max(Phone::compare).get();
    System.out.printf("Max Name:%s Price: %d \n", maxPhone.getName(), maxPhone.getPrice());

    // Метод reduce выполняет терминальные операции сведения, возвращая некоторое значение - рез-т операции
    System.out.println();
    System.out.println("Метод reduce ");
    Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6);
    Optional<Integer> result = numbersStream.reduce((x, y) -> x * y);
    System.out.println(result.get());

    System.out.println("Объединение слов в предложение");
    Stream<String> words = Stream.of("мама", "мыла", "раму");
    Optional<String> sentence = words.reduce((x, y) -> x + " " + y);
    System.out.println(sentence.get());

    // Вторая версия reduce T reduce(T identity, BinaryOperator<T> accumulator)
    Stream<Integer> numberStream = Stream.of(-4, 3, -2, 1);
    int identity = 5;
    int res = numberStream.reduce(identity, (x, y) -> x * y);//identity op n1 op n2 op n3 op n4...
    System.out.println(res);

    // Тип Optional, проверка наличия значения в Optional
    System.out.println();
    System.out.println("Optional, проверка наличия значения ");
    ArrayList<Integer> numbs = new ArrayList<Integer>();
    Optional<Integer> min = numbs.stream().min(Integer::compare);
    if (min.isPresent()) {  // проверка наличия значения, возвращает true
      System.out.println(min.get());
    }

    //orElse
    //Метод orElse() позволяет определить альтернативное значение, которое будет возвращаться,
    // если Optional не получит из потока какого-нибудь значения:
    // пустой список
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    Optional<Integer> minn = numbers.stream().min(Integer::compare);
    System.out.println("Минимальное значение пустого списка " + minn.orElse(-1));
    // непустой список
    numbers.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
    min = numbers.stream().min(Integer::compare);
    System.out.println("Минимальное значение непустого списка " + min.orElse(-1));


    // Метод collect - в качестве результата не поток, а в виде обычной коллекции ArrayList, HashSet
    System.out.println();
    System.out.println("метод collect toList ");
    List<String> phones = new ArrayList<String>();
    Collections.addAll(phones, "iPhone 8", "HTC U12", "Huawei Nexus 6P",
            "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
            "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

    List<String> filteredPhones = phones.stream()
            .filter(s -> s.length() < 10)
            .collect(Collectors.toList());
    for (String s : filteredPhones) {
      System.out.println(s);
    }

    // Метод collect ToMap
    System.out.println();
    System.out.println("метод collect toMap ");
    Stream<Phone> phoneStr = Stream.of(new Phone("iPhone 8", 54000),
            new Phone("Nokia 9", 45000),
            new Phone("Samsung Galaxy S9", 40000),
            new Phone("LG G6", 32000));


    Map<String, Integer> phone = phoneStr
            .collect(Collectors.toMap(p -> p.getName(), t -> t.getPrice())); // p - ключ, t - значение

    phone.forEach((k, v) -> System.out.println(k + " " + v));

    // Группировка данных по какому-то признаку.Метод collect объекта Stream и метод Collectors.groupingBy
    System.out.println();
    System.out.println("Группировка данных по признаку. Метод collect объекта Stream и метод Collectors.groupingBy() ");
    Stream<Phone> phoneSt = Stream.of(new Phone("iPhone X", 600, "Apple"),
            new Phone("Pixel 2", 500, "Google"),
            new Phone("iPhone 8", 450, "Apple"),
            new Phone("Galaxy S9", 440, "Samsung"),
            new Phone("Galaxy S8", 340, "Samsung"));

    Map<String, List<Phone>> phonesByCompany = phoneSt.collect( //в метод collect передается вызов функции groupingBy
            Collectors.groupingBy(Phone::getCompany)); // в итоге создан объект Map, в кот ключами являются названия компаний,
    // а значениями - список связанных с компаниями тел-в
    for (Map.Entry<String, List<Phone>> item : phonesByCompany.entrySet()) {

      System.out.println(item.getKey());
      for (Phone ph : item.getValue()) {

        System.out.println("    " + ph.getName());
      }

      // Параллельные потоки
      System.out.println();
      System.out.println("Параллельные потоки. Метод parallel");
      Stream<Integer> numStr = Stream.of(1, 2, 3, 4, 5, 6);
      Integer resultParall = numStr.parallel().reduce(1, (x, y) -> x * y);
      System.out.println(resultParall);

      // Без ущерба для точности выполнения переносятся с последовательных потоков на параллельные ассоциативные функции
      //которые дают слева -направо и справа налево один и тот же результат
      Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
      String sentence1 = wordsStream.parallel().reduce("Результат:", (x, y) -> x + " " + y);
      System.out.println(sentence1);
    }

  }
}
