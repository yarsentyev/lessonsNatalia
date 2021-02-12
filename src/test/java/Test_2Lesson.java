import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import programFirstLesson.itSpecialists.ITSpecialist;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

public class Test_2Lesson {



/*1 Сравнение двух переменных примитивного типа int.Результат сравнения true.
  вызвать hashcode, equals у них нельзя, так как это не объекты. Методы hashcode, equals - это методы класса Object*/
  @Test
  public void testComparisonSmallInt(){
    int s = 5;
    int k = 5;
    assert (s==k);
  }

  /*Два больших одинаковых Integer, результат сравнения = true, потому что тип Integer ссылочный тип и
  * все значения в пределах -128 и до 127 хранятся в кэше, поэтому переменные i и g ссылаются на один
  * и тот же объект из кэша. Т.к. обе переменные ссылаются на объект, то у них можно вызывать методы
  * hashCode и equals */
  @Test
  public void testComparisonBigInteger(){
    Integer i = 8;
    Integer g = 8;
    assert (i==g);
    System.out.println("HashCode for Integer i "+ i.hashCode());
    System.out.println("HashCode for Integer g " + g.hashCode());
    assert (i.equals(g));

    /*Создаю один из Integer объектов через new, тогда переменная будет
    ссылаться на новый объект   */
    Integer c = new Integer(8);
    assert (i==c);
    assert(i.equals(c));
  }

//Сравнение строк
  @Test
  public void testComparisonString(){

    String str1 = "Comparison of strings!";
    String str2 = "Comparison of strings!";
    assert (str1==str2);
    assert (str1.hashCode()==str2.hashCode());
    assert (str1.equals(str2)); /* в данных случаях возвращается true, потому что обе переменные
    ссылаются на одну и ту же строку, т.к. при создании String ="" проверяется наличие идентичной
     строки в пуле строк и если она существует, то переменной присваивается ссылка на сущ строку
     В данном случае переменные str1 и str2 ссылаются на одну и ту же строку*/

    String str3 = new String("Comparison of strings!");
    String str4 = new String("Comparison of strings!");
   // assert (str3==str2); // сравниваются две ссылки, которые разные, ? при дебаге значения ссылок одинаковые,
                           // как посмотреть значение ссылок, кот сравниваются?????
    assert (str3.hashCode()==str2.hashCode());
    assert (str1.equals(str3)); // метод Equals переопределен в классе String и сравнивает
                                // посимвольно строку и объект типа строка
    //assert (str3==str4);// ?????
    assert (str4.hashCode()==str3.hashCode());
    assert (str4.equals(str3));

  }
/*  3. Сделай самописный класс Laptop. Пусть у него будут поля brand, frequency (например, 3000), memory (например, 16).
  Создай два идентичных лаптопа.
  Проведи сравнение через ==, .hashcode(), .equals. Объясни каментом, почему в
  том или ином случае вернулось true или false.
  Теперь мы решили, что считаем лэптопы равными друг другу, если у них совпадает частота и количество гигов оперативки. Переопредели внутри самописного класса equals так, чтобы он
  проводил сравнение только по этим полям, игнорируя производителя.*/
  @Test
  public void testComparisonOfLaptops(){
    Laptop laptop1 = new Laptop("aser",3000,16);
    Laptop laptop2 = new Laptop("dell",3000,16);
    //assert (laptop1==laptop2); // return false, сравниваются две ссылки на объект, они ссылаются на разные ячейки памяти
    //assert (laptop1.hashCode()==laptop2.hashCode());// возвращает false у объектов разные хэшкоды. Почему?
    assertTrue(laptop1.equals(laptop2)); // Почему просто assert выдает ошибку
    System.out.println(laptop2.hashCode());
    System.out.println(laptop1.hashCode());
  }
  
/*4. Таки нам надо разобраться с хэшмапой (надеюсь, про хэшкод ты уже читала-перечитала).
Вернемся к примеру из прошлого занятия, когда у тебя есть самописный класс с единственным полем String имя.
Создай три экземпляра такого класса, используй разные имена. Создай хэшмапу,
где ключ - имя, значение - сам объект. Положи все три экземпляра в эту хэшмапу.
Сколько после этого внутри хэшмапы пар? Почему произошло то, что произошло?*/
  @Test
  public void testHashMap(){
    ITSpecialist spec1 = new ITSpecialist("Grisha");
    ITSpecialist spec2 = new ITSpecialist("Vitalyi");
    ITSpecialist spec3 = new ITSpecialist("Veronika");

    HashMap<String,ITSpecialist> mapOfSpec = new HashMap<>();
    mapOfSpec.put(spec1.name,spec1);
    mapOfSpec.put(spec2.name,spec2);
    mapOfSpec.put(spec3.name,spec3);
    System.out.println(mapOfSpec);

  }

  /*5. Напиши произвольный тест, в котором происходит выброс проверяемого исключения,
  например, тот же ArrayIndexOutOfBoundsException. Прокинь его в сигнатуру метода.*/
  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testException() throws ArrayIndexOutOfBoundsException {
    int [] myArray = {2,4,5};
    myArray[3]=10;
  }

  @Test
  public void testTryCatchOfException() throws ArrayIndexOutOfBoundsException {
    int [] myArray = {2,4,5};
    try{  myArray[3]=10; }
    catch (ArrayIndexOutOfBoundsException thrown){
      Assert.assertNotEquals("",thrown.getMessage());
    }
  }
  /*7. Теперь пусть экспешен перехватывается try/catch, но catch ожидает эксепшен другого типа. К чему это приводит?*/
  // ? как увидеть из консоли в какой строчке ошибка
  @Test
  public void testTryCatchOfExceptionDiffType() throws ArrayIndexOutOfBoundsException {
    int [] myArray = {2,4,5};
    try{  myArray[3]=10; }
    catch (ArrayStoreException thrown){
      Assert.assertNotEquals("",thrown.getMessage());
    }
  }

/*  8. Теперь пусть экспешен перехватывается try/catch, но catch ожидает эксепшен-родителя,
т.е. базовый Exception. К чему это приводит?*/
  @Test
  public void testTryCatchOfParentException() throws ArrayIndexOutOfBoundsException {
    int [] myArray = {2,4,5};
    try{  myArray[3]=10; }
    catch (Exception thrown){
      Assert.assertNotEquals("",thrown.getMessage());
    }
  }
 /* finally. Напиши тест, в котором finally выполняется.*/
 @Test
   public void testTryCatchFinallyException() throws ArrayIndexOutOfBoundsException {
     int [] myArray = {2,4,5};
     try{  myArray[3]=10; }
     catch (ArrayIndexOutOfBoundsException thrown){
       Assert.assertNotEquals("",thrown.getMessage());
     }
     finally {
       myArray = null;
     }
  }


  /* finally. Напиши тест, в котором finally не выполняется. (больше чем один?)*/
  @Test
  public void testTryCatchFinallyNotExecuted() throws ArrayIndexOutOfBoundsException {
    int [] myArray = {2,4,5};
    try{
      myArray[3]=10;
      System.exit(0);}
    catch (ArrayIndexOutOfBoundsException thrown){
      Assert.assertNotEquals("",thrown.getMessage());
     // System.exit(0);
    }
    finally {

      myArray = null;
    }
  }

/*  В следующих случаях блок finally не будет выполнен: -

  Когда System.exit(0) вызывается из блока try.
  Когда JVM исчерпывает память
  Когда ваш java-процесс принудительно убит из задачи mgr или консоли
  Условие взаимоблокировки в блоке try
  Когда ваш компьютер отключается из-за сбоя питания*/
}