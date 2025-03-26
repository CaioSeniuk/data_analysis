package org.intuitiveCare.webScraping;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class compactarAnexo {
    private static final ArrayList<File> linksAnexos = new ArrayList<>();

    public static void armazenarDiretorio(String diretorioAnexo) {
        linksAnexos.add(new File(diretorioAnexo));
    }

    public static void compactarAnexos(String diretorioAtual) throws IOException {

        File anexo1 = linksAnexos.get(0);
        File anexo2 = linksAnexos.get(1);

        try (FileOutputStream fos = new FileOutputStream(diretorioAtual + "/anexos.zip");
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            zos.putNextEntry(new ZipEntry("anexo1.pdf"));
            // Copia o conteúdo do PRIMEIRO arquivo
            try (FileInputStream fis1 = new FileInputStream(anexo1)) {
                byte[] buffer = new byte[16384];
                int length;
                while ((length = fis1.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            zos.closeEntry();
            // Copia o conteúdo do SEGUNDO arquivo (em sequência)
            zos.putNextEntry(new ZipEntry("anexo2.pdf"));
            try (FileInputStream fis2 = new FileInputStream(anexo2)) {
                byte[] buffer = new byte[16384];
                int length;
                while ((length = fis2.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            zos.closeEntry();
        } catch (Exception e) {
            System.out.println("Falha ao combinar arquivos: " + e.getMessage());
        }
    }
}

