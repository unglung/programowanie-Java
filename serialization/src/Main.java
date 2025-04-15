public class Main {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();

        lm.addBook(new Book("Lalka", "Bolesław Prus", 1889));
        lm.addBook(new Book("Pan Tadeusz", "Adam Mickiewicz", 1834));

        lm.saveToFile("books.dat");
        lm.loadFromFile("books.dat");
        lm.displayBooks();
    }
}
