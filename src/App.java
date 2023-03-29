import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes     
        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbmostpopularmovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 
        System.out.println("\u001b[37;1m" + "\u001b[44;1m" + "Most Popular Movies on IMDB" + "\u001b[0m");
        System.out.println("💙️" + " " + "💙️" + " " + "💙️" + " " + "💙️");
        System.out.println("                                              ");
        
        
        for (Map<String,String> filme : listaDeFilmes) {
        	
        	System.out.println("\u001b[37;1m" + "\u001b[41;1m" + "Title: " + filme.get("title") + "\u001b[0m");
            System.out.println("\u001b[1m" + "Poster: " + "\u001b[0m" + filme.get("image") + "\u001b[0m");
            System.out.println("\u001b[1m" + "Rate: " + "\u001b[0m" + filme.get("imDbRating") + "\u001b[0m");
            double rating = Double.parseDouble(filme.get("imDbRating"));
            int estrelas = (int) rating;
            for( int e = 1; e <= estrelas; e++) {
            	 System.out.print("🌟");    
            }
            System.out.println("\n");
                
                                
           
        }
    }
}
