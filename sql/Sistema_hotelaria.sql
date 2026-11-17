-- Criar DATABASE

CREATE DATABASE Sistema_Hotelaria;

--Criar Tabelas
-- hotel
CREATE TABLE hotel (
	cod_hotel INT PRIMARY KEY NOT NULL,
	nome_hotel VARCHAR(100) NOT NULL,
	CNPJ VARCHAR(20) UNIQUE NOT NULL,
	tell_hotel VARCHAR(20),
	endereco_hotel VARCHAR(100)
);
-- funcionario
CREATE TABLE funcionario (
	cod_fun INT PRIMARY KEY,
	nome_fun VARCHAR(100),
	CPF_fun VARCHAR(15) UNIQUE NOT NULL,
	cargo_fun VARCHAR(50),
	cod_hotel INT NOT NULL,
	FOREIGN KEY (cod_hotel) REFERENCES hotel (cod_hotel)
				ON DELETE CASCADE ON UPDATE CASCADE
);
-- quarto
CREATE TABLE quarto (
	cod_quarto INT PRIMARY KEY NOT NULL,
	num_quarto INT NOT NULL,
	andar_quarto INT NOT NULL,
	status_qaurto BOOLEAN,
	cod_hotel INT NOT NULL,
	FOREIGN KEY (cod_hotel) REFERENCES hotel (cod_hotel)
				ON DELETE CASCADE ON UPDATE CASCADE
);
-- hospede
CREATE TABLE hospede (
	cod_hospede INT PRIMARY KEY NOT NULL,
	nome_hospede VARCHAR(100) NOT NULL,
	CPF_hospede VARCHAR(15) UNIQUE,
	tell_hospede VARCHAR(15) NOT NULL,
	email_hospede VARCHAR(100)
);
-- reserva
CREATE TABLE reserva (
	cod_rsv INT PRIMARY KEY NOT NULL,
	dt_entrada DATE,
	dt_saida DATE,
	qt_hospede INT,
	cod_quarto int,
	cod_hospede INT,
	cod_fun INT,
	FOREIGN KEY (cod_quarto) REFERENCES quarto (cod_quarto)
				ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_hospede) REFERENCES hospede (cod_hospede)
				ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_fun) REFERENCES funcionario (cod_fun)
				ON DELETE CASCADE ON UPDATE CASCADE
);
-- servico
CREATE TABLE servico (
	cod_servico INT PRIMARY KEY NOT NULL,
	tipo_servico VARCHAR(30),
	valor_servico INT
);

-- inserir na tabela hotel
INSERT INTO hotel (cod_hotel, nome_hotel, CNPJ, tell_hotel, endereco_hotel)
values      	  (1, 'Park Hotel', 63558449864300, 999456700, 'Rua Dom Pedro II');

INSERT INTO hotel (cod_hotel, nome_hotel, CNPJ, tell_hotel, endereco_hotel)
values       (2, 'Oasis', 99845376219800, 452679123, 'AV. Luis Eduardo');

-- inserir funcionario
INSERT INTO funcionario (cod_fun, nome_fun, cpf_fun, cargo_fun, cod_hotel)
values 					(1, 'Luiza Nunes', 09876345678, 'Rcepicionista', 1);

INSERT INTO funcionario (cod_fun, nome_fun, cpf_fun, cargo_fun, cod_hotel)
values 					(2, 'Tiago Neves', 83562783899, 'Camareira', 1);

INSERT INTO funcionario (cod_fun, nome_fun, cpf_fun, cargo_fun, cod_hotel)
values 					(3, 'Maria Costa', 19800245678, 'Gerente', 1);

INSERT INTO funcionario (cod_fun, nome_fun, cpf_fun, cargo_fun, cod_hotel)
values 					(4, 'Antonio Carlos', 00034567813, 'Recepicionista', 2);

INSERT INTO funcionario (cod_fun, nome_fun, cpf_fun, cargo_fun, cod_hotel)
values 					(5, 'Joao Batista', 22314567009, 'Camareira', 2);

INSERT INTO funcionario (cod_fun, nome_fun, cpf_fun, cargo_fun, cod_hotel)
values 					(6, 'Ana Clara', 11230098567,'Gerente', 2);

-- inserir quarto 
INSERT INTO quarto (cod_quarto, num_quarto, andar_quarto, status_qaurto, cod_hotel)
values             (4, 100, 1, true, 1);

INSERT INTO quarto (cod_quarto, num_quarto, andar_quarto, status_qaurto, cod_hotel)
values             (3, 205, 2, true, 1);

INSERT INTO quarto (cod_quarto, num_quarto, andar_quarto, status_qaurto, cod_hotel)
values             (1, 107, 1, true, 2);

INSERT INTO quarto (cod_quarto, num_quarto, andar_quarto, status_qaurto, cod_hotel)
values             (2, 202, 2, true, 2);

-- inserir hospedes 
INSERT INTO hospede (cod_hospede, nome_hospede, cpf_hospede, tell_hospede, email_hospede)
values     			(1, 'João Pedro', 09834562770, 199922541, 'xxxx@gmail.com');

INSERT INTO hospede (cod_hospede, nome_hospede, cpf_hospede, tell_hospede, email_hospede)
values     			(2, 'Tania Teles', 09834562200, 0199922541, '----@gmail.com');

INSERT INTO hospede (cod_hospede, nome_hospede, cpf_hospede, tell_hospede, email_hospede)
values     			(3, 'Talita Novais', 09800562770, 1167322541, 'iiii@gmail.com');

INSERT INTO hospede (cod_hospede, nome_hospede, cpf_hospede, tell_hospede, email_hospede)
values     			(4, 'Analia Teles', 09111562770, 298722541, 'oooo@gmail.com');

-- inserir servico
INSERT INTO servico (cod_servico, tipo_servico, valor_servico)
values				(1, 'Cafe da manha', 32.00);

INSERT INTO servico (cod_servico, tipo_servico, valor_servico)
values				(2, 'Lavanderia', 73.00);

-- inserir reserva 
INSERT INTO reserva (cod_rsv, dt_entrada, dt_saida, qt_hospede, cod_quarto, cod_hospede, cod_fun)
values				(1, '12/06/2026', '20/06/2026', 2, 1, 2,4);

INSERT INTO reserva (cod_rsv, dt_entrada, dt_saida, qt_hospede, cod_quarto, cod_hospede, cod_fun)
values				(2, '12/06/2026', '20/06/2026', 1, 2, 4, 1);

INSERT INTO reserva (cod_rsv, dt_entrada, dt_saida, qt_hospede, cod_quarto, cod_hospede, cod_fun)
values				(3, '05/10/2026', '10/10/2026', 1, 4, 3, 1);

-- atualizar info
UPDATE hospede
	set tell_hospede = 190022549
WHERE cod_hospede = 1;

UPDATE hospede
	set email_hospede = 'aao@@gmail.com'
WHERE cod_hospede = 4

-- deletar
DELETE FROM hospede WHERE cod_hospede = 3;

DELETE FROM reserva WHERE cod_rsv = 3;

-- select com o join codigo da reserva e nome do hospede, hotel
SELECT r.cod_rsv, 
	   h.nome_hospede,
	   ht.nome_hotel,
	   q.num_quarto	   
FROM reserva r	 
INNER JOIN hospede h ON h.cod_hospede = r.cod_hospede
INNER JOIN quarto q ON q.cod_quarto = r.cod_quarto
INNER JOIN hotel ht ON ht.cod_hotel = q.cod_hotel;


-- select com o join todos os quartos mesmo sem reserva 
SELECT ht.nome_hotel, q.num_quarto, q.status_qaurto, r.cod_rsv
FROM quarto q
JOIN hotel ht ON ht.cod_hotel = q.cod_hotel
LEFT JOIN reserva r ON r.cod_quarto = q.cod_quarto
ORDER BY ht.nome_hotel, q.num_quarto;

-- select com o join funcionarios 
SELECT f.cod_fun, f.nome_fun, f.cargo_fun , ht.nome_hotel
FROM funcionario f
INNER JOIN hotel ht ON ht.cod_hotel = f.cod_hotel
ORDER BY ht.nome_hotel, f.nome_fun;

-- teste view 
CREATE OR REPLACE VIEW vw_rsv_detalhe AS
SELECT r.cod_rsv,
	   h.nome_hospede,
	   q.num_quarto,
	   q.andar_quarto,
	   r.dt_entrada,
	   r.dt_saida,
	   (r.dt_saida - r.dt_entrada) AS qt_dias,
	   f.nome_fun AS atendente
FROM reserva r
INNER JOIN hospede h ON h.cod_hospede = r.cod_hospede
INNER JOIN quarto q ON q.cod_quarto = r.cod_quarto
INNER JOIN hotel ht ON ht.cod_hotel = q.cod_hotel
LEFT JOIN funcionario f ON f.cod_fun = r.cod_fun;


SELECT * FROM vw_rsv_detalhe;


-- teste function 
CREATE OR REPLACE FUNCTION fn_qt_dias (p_cod_rsv INT)
RETURNS INT
AS $$
	SELECT (dt_saida - dt_entrada)
	FROM reserva
	WHERE cod_rsv = p_cod_rsv;
$$ LANGUAGE SQL;

SELECT fn_qt_dias(1);

SELECT r.cod_rsv, 
	   h.nome_hospede,
	   r.dt_saida,
	   r.dt_entrada,
	   fn_qt_dias(r.cod_rsv) AS qt_dias
FROM reserva r
INNER JOIN hospede H ON h.cod_hospede = r.cod_hospede
ORDER BY r.cod_rsv;

-- teste da procedure 
INSERT INTO reserva (cod_rsv, dt_entrada, dt_saida, qt_hospede, cod_quarto, cod_hospede, cod_fun)
values				(4, '12/09/2026', '26/09/2026', 2, 3, 1,2); 

SELECT cod_quarto, num_quarto, status_qaurto
FROM quarto
WHERE cod_quarto = 3;

SELECT * FROM reserva WHERE cod_rsv = 4;


