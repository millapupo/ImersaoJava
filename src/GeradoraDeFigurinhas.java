import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public void cria (String texto) throws Exception {
		
			
		//leitura da imagem	
//      InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
//                    .openStream();		
		//BufferedImage imagemOriginal = ImageIO.read(InputStream); TIRAR MARCAÇÃO
		
		BufferedImage imagemOriginal = ImageIO.read(new File("src/Entrada/capa-maior.jpg")); //TIRAR PARA SUBIR 
				
		//cria nova imagem em memória e com novo tamanho
		int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		
		//copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 86);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);
		
		//escrever uma frase na nova imagem e centralizar
        String textoFigurinha = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(textoFigurinha, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) /2;        
        int posicaoTextoY = novaAltura - 100;
        graphics.drawString(texto, posicaoTextoX, posicaoTextoY);
        
        //para dar o contorno preto
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();     
        var TextLayout = new TextLayout(texto, fonte, fontRenderContext);
        Shape outline = TextLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);
        
        var outlineStroke =  new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);
        
        graphics.setColor(Color.black);
        graphics.draw(outline);
        graphics.setClip(outline);
        
		//escrever a nova imagem em um arquivo  
        
        //ImageIO.write(novaImagem, "png", new File(figurInha)); TIRAR MARCAÇÃO PARA SUBIR
        
        ImageIO.write(novaImagem, "png", new File("src/Saida/figurinha2.png"));    //TIRAR PARA SUBIR 
        

	}
	// TIRAR PARA SUBIR
	public static void main(String[] args) throws Exception {
		
		var geradora = new GeradoraDeFigurinhas();
		geradora.cria("TOPSTER");
		
	}

}