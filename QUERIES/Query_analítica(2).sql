CREATE TABLE IF NOT EXISTS "1T2024_despesas_QA(2)" (
    reg_ans VARCHAR(10), 
    descricao TEXT, 
    razao_social TEXT, 
    soma_vl_saldo_inicial MONEY
);

INSERT INTO "1T2024_despesas_QA(2)" (reg_ans, descricao, razao_social, soma_vl_saldo_inicial)
SELECT 
    t1.reg_ans,
    t1.descricao,
    r.razao_social,
    SUM(CAST(t1.vl_saldo_inicial AS MONEY)) AS soma_vl_saldo_inicial
FROM 
    "1T2024" t1
INNER JOIN 
    relatorio_cadop r ON t1.reg_ans = r.registro_ans
WHERE 
    t1.descricao ILIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR%'
    AND CAST(t1.vl_saldo_inicial AS numeric) < 0
GROUP BY 
    t1.reg_ans, r.razao_social, t1.descricao
ORDER BY 
    soma_vl_saldo_inicial ASC;

-----------------------------------------------------

CREATE TABLE IF NOT EXISTS "2T2024_despesas_QA(2)" (
    reg_ans VARCHAR(10), 
    descricao TEXT, 
    razao_social TEXT, 
    soma_vl_saldo_inicial MONEY
);

INSERT INTO "2T2024_despesas_QA(2)" (reg_ans, descricao, razao_social, soma_vl_saldo_inicial)
SELECT 
    t2.reg_ans,
    t2.descricao,
    r.razao_social,
    SUM(CAST(t2.vl_saldo_inicial AS MONEY)) AS soma_vl_saldo_inicial
FROM 
    "2T2024" t2
INNER JOIN 
    relatorio_cadop r ON t2.reg_ans = r.registro_ans
WHERE 
    t2.descricao ILIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR%'
    AND CAST(t2.vl_saldo_inicial AS numeric) < 0
GROUP BY 
    t2.reg_ans, r.razao_social, t2.descricao
ORDER BY 
    soma_vl_saldo_inicial ASC;

-----------------------------------------------------

CREATE TABLE IF NOT EXISTS "3T2024_despesas_QA(2)" (
    reg_ans VARCHAR(10), 
    descricao TEXT, 
    razao_social TEXT, 
    soma_vl_saldo_inicial MONEY
);

INSERT INTO "3T2024_despesas_QA(2)" (reg_ans, descricao, razao_social, soma_vl_saldo_inicial)
SELECT 
    t3.reg_ans,
    t3.descricao,
    r.razao_social,
    SUM(CAST(t3.vl_saldo_inicial AS MONEY)) AS soma_vl_saldo_inicial
FROM 
    "3T2024" t3
INNER JOIN 
    relatorio_cadop r ON t3.reg_ans = r.registro_ans
WHERE 
    t3.descricao ILIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR%'
    AND CAST(t3.vl_saldo_inicial AS numeric) < 0
GROUP BY 
    t3.reg_ans, r.razao_social, t3.descricao
ORDER BY 
    soma_vl_saldo_inicial ASC;

-----------------------------------------------------

CREATE TABLE IF NOT EXISTS "4T2024_despesas_QA(2)" (
    reg_ans VARCHAR(10), 
    descricao TEXT, 
    razao_social TEXT, 
    soma_vl_saldo_inicial MONEY
);

INSERT INTO "4T2024_despesas_QA(2)" (reg_ans, descricao, razao_social, soma_vl_saldo_inicial)
SELECT 
    t4.reg_ans,
    t4.descricao,
    r.razao_social,
    SUM(CAST(t4.vl_saldo_inicial AS MONEY)) AS soma_vl_saldo_inicial
FROM 
    "4T2024" t4
INNER JOIN 
    relatorio_cadop r ON t4.reg_ans = r.registro_ans
WHERE 
    t4.descricao ILIKE '%EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR%'
    AND CAST(t4.vl_saldo_inicial AS numeric) < 0
GROUP BY 
    t4.reg_ans, r.razao_social, t4.descricao
ORDER BY 
    soma_vl_saldo_inicial ASC;

-------------------------------------------------

CREATE TABLE "2024_despesas_QA(2)" (
    reg_ans VARCHAR(255),
    descricao TEXT,
    razao_social TEXT,
    soma_vl_saldo_inicial MONEY
);

INSERT INTO "2024_despesas_QA(2)" (reg_ans, descricao, razao_social, soma_vl_saldo_inicial)
SELECT 
    reg_ans, 
    descricao, 
    razao_social,
    SUM(CAST(soma_vl_saldo_inicial AS MONEY)) AS soma_vl_saldo_inicial
FROM (
    SELECT reg_ans, descricao, razao_social, soma_vl_saldo_inicial
    FROM "1T2024_despesas_QA(2)"

    UNION ALL

    SELECT reg_ans, descricao, razao_social, soma_vl_saldo_inicial
    FROM "2T2024_despesas_QA(2)"

    UNION ALL

    SELECT reg_ans, descricao, razao_social, soma_vl_saldo_inicial
    FROM "3T2024_despesas_QA(2)"

    UNION ALL

    SELECT reg_ans, descricao, razao_social, soma_vl_saldo_inicial
    FROM "4T2024_despesas_QA(2)"
) AS all_tables
GROUP BY reg_ans, descricao, razao_social
ORDER BY soma_vl_saldo_inicial ASC
LIMIT 10;

SELECT * FROM "2024_despesas_QA(2)";