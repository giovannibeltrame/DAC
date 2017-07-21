
CREATE SEQUENCE public.seq_idunidadefederativa;

CREATE TABLE public.UnidadeFederativa (
                idUnidadeFederativa BIGINT NOT NULL DEFAULT nextval('public.seq_idunidadefederativa'),
                nome VARCHAR(64),
                situacao CHAR(1),
                CONSTRAINT pk_unidadefederativa PRIMARY KEY (idUnidadeFederativa)
);


ALTER SEQUENCE public.seq_idunidadefederativa OWNED BY public.UnidadeFederativa.idUnidadeFederativa;

CREATE SEQUENCE public.seq_idcidade;

CREATE TABLE public.Cidade (
                idCidade BIGINT NOT NULL DEFAULT nextval('public.seq_idcidade'),
                idUnidadeFederativa BIGINT NOT NULL,
                nome VARCHAR(64),
                situacao CHAR(1),
                CONSTRAINT pk_cidade PRIMARY KEY (idCidade, idUnidadeFederativa)
);


ALTER SEQUENCE public.seq_idcidade OWNED BY public.Cidade.idCidade;

CREATE SEQUENCE public.seq_idfuncionario;

CREATE TABLE public.Funcionario (
                idFuncionario BIGINT NOT NULL DEFAULT nextval('public.seq_idfuncionario'),
                nome VARCHAR(255),
                celular VARCHAR(16),
                email VARCHAR(64),
                senha VARCHAR(32),
                cpf VARCHAR(16),
                rg VARCHAR(16),
                idUnidadeFederativaRG BIGINT NOT NULL,
                idUnidadeFederativaReside BIGINT NOT NULL,
                CONSTRAINT pk_funcionario PRIMARY KEY (idFuncionario)
);


ALTER SEQUENCE public.seq_idfuncionario OWNED BY public.Funcionario.idFuncionario;

CREATE TABLE public.Holerite (
                idFuncionario BIGINT NOT NULL,
                mes INTEGER NOT NULL,
                ano INTEGER NOT NULL,
                quantidadeHorasTrabalhadas INTEGER,
                salarioBruto NUMERIC(8,2),
                salarioLiquido NUMERIC(8,2),
                CONSTRAINT pk_holerite PRIMARY KEY (idFuncionario, mes, ano)
);


CREATE SEQUENCE public.seq_iddepartamento;

CREATE TABLE public.Departamento (
                idDepartamento BIGINT NOT NULL DEFAULT nextval('public.seq_iddepartamento'),
                nome VARCHAR(255),
                situacao CHAR(1),
                localizacao VARCHAR(255),
                CONSTRAINT pk_iddepartamento PRIMARY KEY (idDepartamento)
);


ALTER SEQUENCE public.seq_iddepartamento OWNED BY public.Departamento.idDepartamento;

CREATE TABLE public.DepartamentoAlocaFuncionario (
                idDepartamento BIGINT NOT NULL,
                idFuncionario BIGINT NOT NULL,
                dataAlocacao DATE NOT NULL,
                dataDesalocacao DATE,
                CONSTRAINT pk_iddepartamentoalocafuncionario PRIMARY KEY (idDepartamento, idFuncionario, dataAlocacao)
);


CREATE SEQUENCE public.seq_idrequisito;

CREATE TABLE public.Requisito (
                idRequisito BIGINT NOT NULL DEFAULT nextval('public.seq_idrequisito'),
                descricao VARCHAR(255),
                CONSTRAINT pk_idrequisito PRIMARY KEY (idRequisito)
);


ALTER SEQUENCE public.seq_idrequisito OWNED BY public.Requisito.idRequisito;

CREATE SEQUENCE public.seq_idcargo;

CREATE TABLE public.Cargo (
                idCargo BIGINT NOT NULL DEFAULT nextval('public.seq_idcargo'),
                nome VARCHAR(255),
                situacao CHAR,
                percentualImposto NUMERIC(4,4),
                quantidadeMinimaHorasMes INTEGER,
                salario NUMERIC(8,2),
                gerente CHAR(1),
                CONSTRAINT pk_cargo PRIMARY KEY (idCargo)
);


ALTER SEQUENCE public.seq_idcargo OWNED BY public.Cargo.idCargo;

CREATE TABLE public.CargoAtribuidoFuncionario (
                idCargo BIGINT NOT NULL,
                idDepartamento BIGINT NOT NULL,
                idFuncionario BIGINT NOT NULL,
                dataAlocacao DATE NOT NULL,
                dataDesatribuicao DATE,
                CONSTRAINT pk_cargoatribuidofuncionario PRIMARY KEY (idCargo, idDepartamento, idFuncionario, dataAlocacao)
);


CREATE TABLE public.RequisitoCargo (
                idCargo BIGINT NOT NULL,
                idRequisito BIGINT NOT NULL,
                CONSTRAINT pk_requisitocargo PRIMARY KEY (idCargo, idRequisito)
);


ALTER TABLE public.Funcionario ADD CONSTRAINT fk_unidadefederativarg_funcionario
FOREIGN KEY (idUnidadeFederativaRG)
REFERENCES public.UnidadeFederativa (idUnidadeFederativa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Funcionario ADD CONSTRAINT fk_unidadefederativareside_funcionario
FOREIGN KEY (idUnidadeFederativaReside)
REFERENCES public.UnidadeFederativa (idUnidadeFederativa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Cidade ADD CONSTRAINT fk_unidadefederativa_cidade
FOREIGN KEY (idUnidadeFederativa)
REFERENCES public.UnidadeFederativa (idUnidadeFederativa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.DepartamentoAlocaFuncionario ADD CONSTRAINT fk_funcionario_departamentoalocafuncionario
FOREIGN KEY (idFuncionario)
REFERENCES public.Funcionario (idFuncionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Holerite ADD CONSTRAINT fk_funcionario_holerite
FOREIGN KEY (idFuncionario)
REFERENCES public.Funcionario (idFuncionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.DepartamentoAlocaFuncionario ADD CONSTRAINT fk_departamento_departamentoalocafuncionario
FOREIGN KEY (idDepartamento)
REFERENCES public.Departamento (idDepartamento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.CargoAtribuidoFuncionario ADD CONSTRAINT fk_departamentoalocafuncionario_cargoatribuidofuncionario
FOREIGN KEY (idDepartamento, idFuncionario, dataAlocacao)
REFERENCES public.DepartamentoAlocaFuncionario (idDepartamento, idFuncionario, dataAlocacao)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.RequisitoCargo ADD CONSTRAINT fk_requisito_requisitocargo
FOREIGN KEY (idRequisito)
REFERENCES public.Requisito (idRequisito)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.RequisitoCargo ADD CONSTRAINT fk_cargo_requisitocargo
FOREIGN KEY (idCargo)
REFERENCES public.Cargo (idCargo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.CargoAtribuidoFuncionario ADD CONSTRAINT fk_cargo_cargoatribuidofuncionario
FOREIGN KEY (idCargo)
REFERENCES public.Cargo (idCargo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;


INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (1, 'Acre', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (2, 'Alagoas', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (3, 'Amapá', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (4, 'Amazonas', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (5, 'Bahia', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (6, 'Ceará', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (7, 'Distrito Federal', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (8, 'Espírito Santo', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (9, 'Goiás', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (10, 'Maranhão', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (11, 'Mato Grosso', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (12, 'Mato Grosso do Sul', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (13, 'Minas Gerais', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (14, 'Pará', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (15, 'Paraíba', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (16, 'Paraná', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (17, 'Pernambuco', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (18, 'Piauí', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (19, 'Rio de Janeiro', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (20, 'Rio Grande do Norte', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (21, 'Rio Grande do Sul', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (22, 'Rondônia', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (23, 'Roraima', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (24, 'Santa Catarina', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (25, 'São Paulo', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (26, 'Sergipe', 'A');
INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (27, 'Tocantins', 'A');

INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (1, nextval('public.seq_cidade'), 'Rio Branco', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (2, nextval('public.seq_cidade'), 'Maceió', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (3, nextval('public.seq_cidade'), 'Macapá', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (4, nextval('public.seq_cidade'), 'Manaus', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (5, nextval('public.seq_cidade'), 'Salvador', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (6, nextval('public.seq_cidade'), 'Fortaleza', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (7, nextval('public.seq_cidade'), 'Brasília', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (8, nextval('public.seq_cidade'), 'Vitória', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (9, nextval('public.seq_cidade'), 'Goiânia', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (10, nextval('public.seq_cidade'), 'São Luís', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (11, nextval('public.seq_cidade'), 'Cuiabá', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (12, nextval('public.seq_cidade'), 'Campo Grande', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (13, nextval('public.seq_cidade'), 'Belo Horizonte', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (14, nextval('public.seq_cidade'), 'Belém', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (15, nextval('public.seq_cidade'), 'João Pessoa', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (16, nextval('public.seq_cidade'), 'Curitiba', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (17, nextval('public.seq_cidade'), 'Recife', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (18, nextval('public.seq_cidade'), 'Teresina', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (19, nextval('public.seq_cidade'), 'Rio de Janeiro', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (20, nextval('public.seq_cidade'), 'Natal', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (21, nextval('public.seq_cidade'), 'Porto Alegre', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (22, nextval('public.seq_cidade'), 'Porto Velho', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (23, nextval('public.seq_cidade'), 'Boa Vista', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (24, nextval('public.seq_cidade'), 'Florianópolis', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (25, nextval('public.seq_cidade'), 'São Paulo', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (26, nextval('public.seq_cidade'), 'Aracaju', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (27, nextval('public.seq_cidade'), 'Palmas', 'A');


INSERT INTO departamento(iddepartamento, nome, situacao, localizacao) VALUES (nextval('public.seq_departamento'), 'Financeiro', 'A', 'Primeiro andar');
INSERT INTO cargo(idcargo, nome, situacao, percentualimposto, quantidademinimahorasmes, salario, gerente) VALUES (nextval('public.seq_cargo'), 'Gerente', 'A', 0.1, 100, 4000., 'S');
INSERT INTO requisito(idrequisito, descricao) VALUES (nextval('public.seq_requisito'), 'Formação Superior');
INSERT INTO requisitocargo(idcargo, idrequisito) VALUES (1, 1);
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (16, nextval('public.seq_cidade'), 'Curitiba', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (25, nextval('public.seq_cidade'), 'São Paulo', 'A');
INSERT INTO funcionario(idfuncionario, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, senha, idcidade)
VALUES (nextval('public.seq_funcionario'), 'Santa Cândida', '(41)99672-4004', '82.630-490', 'Casa 06', '064.635.199-01', 'gio.beltrame@gmail.com', 'Giovanni', '1343', '10.425.488-8', 'Estrada de Santa Cândida', '1234', 16);