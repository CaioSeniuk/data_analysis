package org.intuitiveCare.webScraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class webScraping {

    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

        String diretorioAtual = System.getProperty("user.dir") + "/src/main/java/org/intuitiveCare/webScraping/Anexos/";

        gerenciadorAnexo gerenciadorAnexo = new gerenciadorAnexo(diretorioAtual);

        // Bloco de execução para identificar os links dentro das tags <a> da página
        try{
            Document pagina = Jsoup.connect(url).get();
            Elements links = pagina.select("a[href]");

            for (Element link : links) {

                String referencia = link.attr("abs:href");


                // Filtragem dos arquivos desejados
                if (referencia.matches(".*\\.pdf$")) {

                    // Se o link corresponder com o nome do anexo desejado, será baixado
                    if (referencia.contains("Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf")) {
                        String anexo1 = link.attr("abs:href");
                        gerenciadorAnexo.baixarAnexos(anexo1, diretorioAtual);

                    } else if (referencia.contains("Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf")) {
                        String anexo2 = link.attr("abs:href");
                        gerenciadorAnexo.baixarAnexos(anexo2, diretorioAtual);

                    }
                }
            }

            // Depois dos arquivos serem validados e baixados, serão compactados em um único arquivo
            compactarAnexo.compactarAnexos(diretorioAtual);

        } catch (IOException e){
            System.out.println("Erro ao conectar a URL: " + e.getMessage());
        }
        finally {
            System.out.println("Fim da execução");
        }
    }
}