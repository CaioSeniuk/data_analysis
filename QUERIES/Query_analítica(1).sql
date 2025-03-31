SELECT o.registro_ans, o.razao_social, s.descricao, s.vl_saldo_inicial
FROM relatorio_cadop o
INNER JOIN "4T2024" s ON o.registro_ans = s.reg_ans
WHERE s.descricao ILIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR%'
AND CAST(s.vl_saldo_inicial AS numeric) < 0
ORDER BY s.vl_saldo_inicial ASC
LIMIT 10;