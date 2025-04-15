import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tytul;
    private String autor;
    private int rokWydania;
    private String id;

    public Book(String tytul, String autor, int rokWydania) {
        this.tytul = tytul;
        this.autor = autor;
        this.rokWydania = rokWydania;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Book: {" +
                "tytul='" + tytul + '\'' +
                ", autor='" + autor + '\'' +
                ", rokWydania=" + rokWydania +
                ", id='" + id + '\'' +
                '}';
    }
}
