import java.util.ArrayList;
import java.util.Comparator;

public class CinemaService {

    private ArrayList<Film> films = new ArrayList<>();

    public void addFilm(Film film) {
        films.add(film);
    }
    public Film findFilmByTitle(String title) {
        for (Film f : films) {
            if (f.getTitle().equals(title)) {
                return f;
            }
        }
        return null;
    }

    public void sortByPrice() {
        films.sort(Comparator.comparingInt(Film::getPrice));
    }

    public ArrayList<Film> getFilms() {
        return films;
    }
    public ArrayList<Film> filterByMaxPrice(int maxPrice) {
        ArrayList<Film> result = new ArrayList<>();
        for (Film f : films) {
            if (f.getPrice() < maxPrice) {
                result.add(f);
            }
        }
        return null;
    }
}
