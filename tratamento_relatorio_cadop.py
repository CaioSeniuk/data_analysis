import pandas as pd

df = pd.read_csv('./Relatorio_cadop.csv', delimiter=';')  

df['DDD'] = df['DDD'].apply(lambda x: str(x).replace('.', '') if pd.notnull(x) else x)
df['Regiao_de_Comercializacao'] = df['Regiao_de_Comercializacao'].apply(lambda x: str(x).replace('.', '') if pd.notnull(x) else x)
df['Numero'] = df['Numero'].apply(lambda x: str(x).replace('.', '') if pd.notnull(x) else x)
df['Telefone'] = df['Telefone'].apply(lambda x: str(x).replace('.', '') if pd.notnull(x) else x)
df['Regiao_de_Comercializacao'] = df['Regiao_de_Comercializacao'].apply(lambda x: str(x).replace('.', '') if pd.notnull(x) else x)

df.to_csv('./Relatorio_cadop_novo.csv', index=False, sep=';') 
