
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



INSERT INTO unidadefederativa(idunidadefederativa, nome, situacao) VALUES (0, 'Paraná', 'A');
INSERT INTO cidade(idunidadefederativa, idcidade, nome, situacao) VALUES (0, 0, 'Curitiba', 'A');
INSERT INTO departamento(iddepartamento, nome, situacao, localizacao) VALUES (0, 'Financeiro', 'A', 'Primeiro andar');
INSERT INTO cargo(idcargo, nome, situacao, percentualimposto, quantidademinimahorasmes, salario, gerente) VALUES (0, 'Gerente', 'A', 0.1, 100, 4000, 'S');
INSERT INTO requisito(idrequisito, descricao) VALUES (0, 'Formação Superior');
INSERT INTO requisitocargo(idcargo, idrequisito) VALUES (0, 0);
INSERT INTO funcionario(idfuncionario, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, senha, idunidadefederativareside, idcidadereside, idunidadefederativarg)
VALUES (0, 'Santa Cândida', '99672-4004', '82630-490', 'Casa 06', '064.635.199-01', 'gio.beltrame@gmail.com', 'Giovanni', '1343', '10.425.488-8', 'Estrada de Santa Cândida', '1234', 0, 0, 0);