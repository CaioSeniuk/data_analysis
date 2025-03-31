import tabula
import pandas as pd
import os
from zipfile import ZIP_DEFLATED, ZipFile

def mudarTabelas(csv_path):
    try:
        df = pd.read_csv(csv_path, sep=',')
        df.rename(columns={'OD': 'Seg. Odontológica'}, inplace = True) 
        df.rename(columns={'AMB': 'Seg. Ambulatorial'}, inplace = True) 
        df.to_csv(csv_path, index=False)
        print("Tabelas alteradas com sucesso!")
        compactarAnexo(csv_path)
    except Exception as e:
        print("Erro ao alterar tabelas: {e}")

def compactarAnexo(csv_path):
    zip_path = "Teste_Caio_Seniuk.zip"
    if os.path.exists(zip_path):
        print("Arquivo ZIP já existente")
    else:
        try:
            arquivoZip = ZipFile("Teste_Caio_Seniuk.zip", "w", compression=ZIP_DEFLATED)
            arquivoZip.write(csv_path)
            arquivoZip.close()
            print("Arquivo ZIP criado com sucesso")
        except Exception as e:
            print("Erro ao compactar arquivo: {e}")
    

pdf_path = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"
csv_path = "Anexo_1.csv"
pages = []

if os.path.exists(pdf_path):
    if os.path.exists(csv_path):
        print("CSV já existente")
        compactarAnexo(csv_path)
    else:
        try:
            for i in range (3,182):
                pages.append(i)
            tabula.convert_into(pdf_path, "Anexo_1.csv", output_format="csv", pages=pages)
            print("Arquivo CSV criado com sucesso")
            mudarTabelas(csv_path)
            
        except Exception as e:
            print(f"Ocorreu um erro: {e}")
else:
    print("Arquivo PDF inexistente")