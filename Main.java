import java.util.List;
public class Main {
    public static void main(String[] args) {
        Film f1=new Film("avenger", 3300);
        Film f2=new Film("silk", 2230);

        FilmRepository filmRepo = new FilmRepository();
        System.out.println("Starting work with DB");

        filmRepo.addFilm(f1);
        filmRepo.addFilm(f2);

        System.out.println("Films on base:");
        List<Film> dbFilms = filmRepo.getAllFilms();
        for (Film f : dbFilms) {
            System.out.println(f);
        }

        CinemaService service = new CinemaService();
        for (Film f : dbFilms) {
            service.addFilm(f);
        }

        Viewer v = new Viewer("Amir", 18);
        Person p = v;
        System.out.println("Role" + p.getRole());

        if(!dbFilms.isEmpty()){
            Cinema cinema = new Cinema(dbFilms.get(0), v);
            System.out.println(cinema);
        }

        System.out.println("Checking changes in the base");

        if(!dbFilms.isEmpty()){
            int firstFilmId = dbFilms.get(0).getIdFilm();
            filmRepo.updatePrice(firstFilmId, 3000);
        }

        System.out.println("Program completed");
    }
}