package org.intuitiveCare.webScraping;

import java.io.*;
import java.net.URL;

public class gerenciadorAnexo {
    protected static String diretorioAtual;

    // Verifica se a pasta em que os arquivos serão baixados já existe
    protected gerenciadorAnexo(String diretorioAtual) {
        gerenciadorAnexo.diretorioAtual = diretorioAtual;
        criarDiretorio();
    }
    private void criarDiretorio() {
        File diretorio = new File(diretorioAtual);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    protected void baixarAnexos(String linkAnexo, String diretorioAtual) {

        // Resgatar o nome do arquivo e o caminho onde será salvo
        String nomeAnexo = linkAnexo.substring(linkAnexo.lastIndexOf("/") + 1);
        File caminhoAnexo = new File(diretorioAtual + "/" + nomeAnexo);

        // Verifica se o arquivo já existe
        // Caso o(s) arquivo(s) já exista(m), não será(ão) baixado(s) novamente e será(ão) compactado(s) em seguida
        if (caminhoAnexo.exists()) {

            System.out.println("Arquivo " + nomeAnexo + " já existe");
            compactarAnexo.armazenarDiretorio(System.getProperty("user.dir") + "/src/main/java/org/intuitiveCare" + "/webScraping/Anexos/" + nomeAnexo);

        } else {
            // Bloco de execução para baixar o anexo
            try (
                    InputStream inputStream = new URL(linkAnexo).openStream();
                    FileOutputStream outputStream =
                            new FileOutputStream(caminhoAnexo)) {

                byte[] buffer = new byte[16384];
                long bytesLidos;

                while ((bytesLidos = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, (int) bytesLidos);
                }

                System.out.println("Anexo " + nomeAnexo + " baixado com sucesso");

                compactarAnexo.armazenarDiretorio(System.getProperty("user.dir") + "/src/main/java/org/intuitiveCare" +
                        "/webScraping/Anexos/" + nomeAnexo);

            } catch (IOException e) {
                System.out.println("Erro ao baixar o anexo: " + e.getMessage());
            }
        }

    }
}
