import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class ApiServer {

    private static final FilmRepository filmRepo = new FilmRepository();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/films", exchange -> {
            try {
                if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                    exchange.sendResponseHeaders(405, -1);
                    return;
                }


                String query = exchange.getRequestURI().getQuery();
                Integer minPrice = null;

                if (query != null && query.contains("minPrice=")) {
                    try {
                        String value = query.split("minPrice=")[1].split("&")[0];
                        minPrice = Integer.parseInt(value);
                    } catch (Exception ignored) {
                    }
                }

                List<Film> films;
                if (minPrice != null) {
                    films = filmRepo.getFilmsByMinPrice(minPrice); // <-- жаңа метод
                } else {
                    films = filmRepo.getAllFilms();
                }

                String json = filmsToJson(films);

                exchange.getResponseHeaders()
                        .set("Content-Type", "application/json; charset=utf-8");
                exchange.sendResponseHeaders(200, json.getBytes().length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(json.getBytes());
                }

            } catch (Exception e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
            }
        });

        server.start();
        System.out.println("REST API started: http://localhost:8080/films");
        System.out.println("Filter example: http://localhost:8080/films?minPrice=5000");
    }

    

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String filmsToJson(List<Film> films) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < films.size(); i++) {
            Film f = films.get(i);

            sb.append("  {\n");
            sb.append("    \"idFilm\": ").append(f.getIdFilm()).append(",\n");
            sb.append("    \"title\": \"").append(escapeJson(f.getTitle())).append("\",\n");
            sb.append("    \"price\": ").append(f.getPrice()).append("\n");
            sb.append("  }");

            if (i < films.size() - 1) sb.append(",");
            sb.append("\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
