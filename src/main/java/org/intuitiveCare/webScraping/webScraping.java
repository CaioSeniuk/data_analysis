package org.intuitiveCare.webScraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class webScraping {
    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

        // Baixar os anexos | e ||
        try{
            Document pagina = Jsoup.connect(url).get();

            // Identificando as tags <a> dentro da página
            Elements links = pagina.select("a[href]");

            // Looping para percorrer todos os links resgatados da página
            for (Element link : links) {
                /*System.out.println(link.attr("href")); testando captura de links*/
                String referencia = link.attr("abs:href");

                //Condicionais para filtrar os anexos desejados
                if (referencia.matches(".*\\.pdf$") && referencia.contains("Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf")) {
                    String anexo1 = link.attr("abs:href");
                    baixarAnexo anexo = new baixarAnexo(anexo1);

                }
                else if (referencia.matches(".*\\.pdf$") && referencia.contains("Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf")) {
                    String anexo2 = link.attr("abs:href");
                    baixarAnexo anexo = new baixarAnexo(anexo2);
                }
            }

        } catch (IOException e){
            System.out.println(e);
        }
        finally {
            System.out.println("Fim da execução");
        }
    }
}