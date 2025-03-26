package org.intuitiveCare.webScraping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class baixarAnexo {
    String anexo;

    public baixarAnexo(String anexo) {
        this.anexo = anexo;

        // Armazeno o diretório atual da máquina local
        String diretorioAtual = System.getProperty("user.dir");

        // Armazeno o nome do arquivo
        String nomeAnexo = anexo.substring(anexo.lastIndexOf("/") + 1);

        // Armazeno o diretório em que quero salvar os arquivos
        String caminhoAnexo = diretorioAtual + "/src/main/java/org/intuitiveCare/webScraping/Anexos/" + nomeAnexo;

        // try-with-resources para fechar os recursos automaticamente
        // Criaçao do objeto URL e fluxo de saída para salvar o arquivo na maquina local
        try(    InputStream inputStream = new URL(anexo).openStream();
                FileOutputStream outputStream =
                        new FileOutputStream(caminhoAnexo);)  {

            // Cria um buffer para transferir os dados do arquivo
            byte[] buffer = new byte[4096];
            int bytesLidos;

            // Lê os dados do arquivo remoto e grava no arquivo local
            while ((bytesLidos = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesLidos);
            }
            System.out.println("Anexo " + nomeAnexo + " criado com sucesso para " + caminhoAnexo);

        } catch (IOException e){
            System.out.println("Erro ao baixar o anexo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
