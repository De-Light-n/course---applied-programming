package lab_2;

public class Book {
    private int id;
    private String name;
    private  String author;
    private String publishing_house;
    private int release_year;
    private int number_of_pages;
    private float price;
    
    public Book(int id, String name, String author, String publishing_house,
     int release_year, int number_of_pages, float price){
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishing_house = publishing_house;
        this.release_year = release_year;
        this.number_of_pages = number_of_pages;
        this.price = price;
    }

    public Book(){}

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setPublishingHouse(String publishing_house){
        this.publishing_house = publishing_house;
    }
    public void setReleaseYear(int release_year){
        this.release_year = release_year;
    }
    public void setNumberOfPages(int number_of_pages){
        this.number_of_pages = number_of_pages;
    }
    public void setPrice(float price){
        this.price = price;
    }


    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getPublishingHouse(){
        return this.publishing_house;
    }
    public int getReleaseYear(){
        return this.release_year;
    }
    public int getNumberOfPages(){
        return this.number_of_pages;
    }
    public float getPrice(){
        return this.price;
    }

    public String toStringId(){
        return "ID: "+ this.id;
    }
    public String toStringName(){
        return "Name: "+ this.name;
    }
    public String toStringAuthor(){
        return "Author: "+ this.author;
    }
    public String toStringPublishingHouse(){
        return "Publishing house: "+ this.publishing_house;
    }
    public String toStringReleaseYear(){
        return "Release year: "+ this.release_year;
    }
    public String toStringNumberOfPages(){
        return "Number of pages: "+ this.number_of_pages;
    }
    public String toStringPrice(){
        return "Price: "+ this.price;
    }

    public String toString(){
        String str = new String();
        str += this.toStringId() + "\n";
        str += this.toStringName() + "\n";
        str += this.toStringAuthor() + "\n";
        str += this.toStringPublishingHouse() + "\n";
        str += this.toStringReleaseYear() + "\n";
        str += this.toStringNumberOfPages() + "\n";
        str += this.toStringPrice() + "\n";
        return str;
    }
}
