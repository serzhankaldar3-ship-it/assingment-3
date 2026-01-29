public class Cinema {

    private Film film;
    private Viewer viewer;

    public Cinema(Film film, Viewer viewer) {
        this.film = film;
        this.viewer = viewer;
    }

    public Film getFilm() {
        return film;
    }
    public Viewer getViewer() {
        return viewer;
    }
    @Override
    public String toString() {
        return viewer.getName() + " booked film " + film.getTitle();}
}