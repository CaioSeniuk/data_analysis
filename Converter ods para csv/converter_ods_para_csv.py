import pandas as pd
# Necessário instalar a dependência odfpy -> 'pip install odfpy' 
arquivo_ods = './dicionario_de_dados_das_operadoras_ativas.ods'
df = pd.read_excel(arquivo_ods, engine='odf')
df.to_csv('./dicionario_de_dados_das_operadoras_ativas.csv', index=False)

arquivo_csv = './dicionario_de_dados_das_operadoras_ativas.csv'
df = pd.read_csv(arquivo_csv, header=None)

header_row = df[df.iloc[:, 0] == 'Nome  do Campo'].index[0]
df_cleaned = pd.read_csv(arquivo_csv, skiprows=header_row)


csv_path = './dicionario_de_dados_das_operadoras_ativas.csv'
df_cleaned.to_csv(csv_path, index=False)