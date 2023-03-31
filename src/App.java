import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP      
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "http://localhost:8080/linguagens";
    	ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
    	
        //String url = "https://api.nasa.gov/planetary/apod?api_key=7PT1fOjvuQl2y3FRSkOpshykSsOTcL23Uad0Ycgv&start_date=2022-06-14&end_date=2022-06-17";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);        
        
        var diretorio = new File("Saida/");
        diretorio.mkdir();
              
        // exibir e manipular os dados         
        List<Conteudo> conteudos = extrator.extraiConteudos(json);        
        
        var geradora = new GeradoraDeFigurinhas();
        
        for (int i = 0; i < 5; i++) {
        	Conteudo conteudo = conteudos.get(i);
            
        	String texto = "Uau";             
         	InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "Saida/" + conteudo.getTitulo() + ".png";
            
            geradora.cria(inputStream, nomeArquivo, texto);
             
            System.out.println(conteudo.getTitulo());
            System.out.println();                                
            }
    }
}
