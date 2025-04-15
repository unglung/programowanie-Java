import java.io.*;
import java.util.ArrayList;

public class LibraryManager {
    ArrayList<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    void saveToFile(String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(books);
            out.close();
            System.out.println("Zapisano do pliku.");
        } catch (Exception e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    void loadFromFile(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            books = (ArrayList<Book>) in.readObject();
            in.close();
            System.out.println("Wczytano z pliku.");
        } catch (Exception e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
        }
    }

    void displayBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
