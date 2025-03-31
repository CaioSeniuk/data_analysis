import pandas as pd

df = pd.read_csv('./Relatorio_cadop.csv', delimiter=';')  

df.to_csv('./Relatorio_cadop_novo.csv', index=False, sep=';') 
