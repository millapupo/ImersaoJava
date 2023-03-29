import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP      
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=7PT1fOjvuQl2y3FRSkOpshykSsOTcL23Uad0Ycgv&start_date=2022-10-12&end_date=2022-10-14";
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        // extrair só os dados que interessam 
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudo = parser.parse(json);
        
        var diretorio = new File("Saida/");
        diretorio.mkdir();
              
        // exibir e manipular os dados 
        var geradora = new GeradoraDeFigurinhas();
        for (int index = 0; index < 3; index++) {
        	var conteudo = listaDeConteudo.get(index);        	
        	
            String urlImagem = conteudo.get("url");
            String titulo = conteudo.get("title");              
            String texto = "porra";
                      
            String urlImage = conteudo.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");            
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";
            geradora.cria(inputStream, nomeArquivo, texto);
            System.out.println(titulo);
            System.out.println();                                
            }
    }
}
