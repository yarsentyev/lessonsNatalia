package programFirstLesson;

final class FinalClassBooks {
  private String name;
  private int pages;
  private String author;
  private String genre;

  FinalClassBooks(){
    name = "";
    author = "";
    genre = "";
    pages = 0;
  }

  FinalClassBooks(String n, String a, String g, int i){
    name = n;
    author = a;
    genre = g;
    pages = i;
  }

  public int getPages (){return this.pages;}
  public void getInfo (){
    System.out.println("Название: " + this.name + " Автор: "+ this.author + " Жанр: "+ this.genre + " Объем: " + this.pages);
  }
}
