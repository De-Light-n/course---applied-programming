package lab_2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean isValidInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int enterInt(String request, Scanner scanner){
        String userInput;
        while (true) {
            System.out.print(request);
            userInput = scanner.nextLine();
            if (isValidInt(userInput)) {
                return Integer.parseInt(userInput);
            } else {
                System.out.print("Error: Please enter a valid positive integer: ");
            }
        }
    }

    public static float enterFloat(String request, Scanner scanner){
        String userInput;
        while (true) {
            System.out.print(request);
            userInput = scanner.nextLine();
            if (isValidFloat(userInput)) {
                return Float.parseFloat(userInput);
            } else {
                System.out.print("Error: Please enter a valid positive real number: ");
            }
        }
    }

    public static void getFromAuthor(String Author, ArrayList<Book> books){
        for(int i = 0; i<books.size(); i++){
            Book book = books.get(i);
            if(book.getAuthor().equals(Author)){
                System.out.println(book.toString());
            }
        }
    }

    public static void getFromPublishingHouse(String publishing_house, ArrayList<Book> books){
        for(int i = 0; i<books.size(); i++){
            Book book = books.get(i);
            if(book.getPublishingHouse().equals(publishing_house)){
                System.out.println(book.toString());
            }
        }
    }

    public static void getFromYear(int year, ArrayList<Book> books){
        for(int i = 0; i<books.size(); i++){
            Book book = books.get(i);
            if(book.getReleaseYear()>=year){
                System.out.println(book.toString());
            }
        }
    }

    public static void addBook(ArrayList<Book> books, Scanner scanner){
        int id = enterInt("Enter ID: ", scanner);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter publishing house: ");
        String publishing_house = scanner.nextLine();
        int year, number_of_pages;
        float price;
        do{
            year = enterInt("Enter release year: ", scanner);
        }while(year<=0);
        do{
            number_of_pages = enterInt("Enter number of pages: ", scanner);
        }while(number_of_pages<=0);
        do{
            price = enterFloat("Enter price: ", scanner);
        }while(price<=0);
        books.add(new Book(id, name, author, publishing_house,
        year, number_of_pages, price));
    }

    public static void showAll(ArrayList<Book> books){
        for(int i = 0; i<books.size(); i++){
            Book book = books.get(i);
            System.out.println(book.toString());        
        }
    }
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1) Add book\n2) Find books after entered year\n3) Find books by author\n4) Find books by publishing house\n5) Show all\n0) Exit");
            int choise = enterInt("Choise: ", scanner);
            System.out.println("--------------------------------------------");
            if(choise==1){
                addBook(books, scanner);
            }else if(choise==2){
                int year = enterInt("Enter year: ", scanner);
                getFromYear(year, books);
            }else if(choise==3){
                System.out.print("Enter author: ");
                String autor = scanner.nextLine();
                getFromAuthor(autor, books);
            }else if(choise==4){
                System.out.print("Enter publishing house: ");
                String publishing_house = scanner.nextLine();
                getFromPublishingHouse(publishing_house, books);
            }else if(choise==5){
                showAll(books);
            }else if(choise==0){
                System.out.println("Goodbye");
                break;
            }else{
                System.out.println("Invalid number.");
            }
            System.out.println("--------------------------------------------");
        }
        scanner.close();
    }
}
