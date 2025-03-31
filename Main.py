import pandas as pd

df = pd.read_csv('./Relatorio_cadop.csv', delimiter=';')  

df = df.fillna('N/A')

df.to_csv('./Relatorio_cadop_novo.csv', index=False, sep=';') 
