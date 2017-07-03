
CREATE SEQUENCE public.seq_iddepartamento;

CREATE TABLE public.Departamento (
                idDepartamento BIGINT NOT NULL DEFAULT nextval('public.seq_iddepartamento'),
                nome VARCHAR(64) NOT NULL,
                situacao CHAR(1) NOT NULL,
                localizacao VARCHAR(255) DEFAULT '' NOT NULL,
                CONSTRAINT pk_departamento PRIMARY KEY (idDepartamento)
);
COMMENT ON COLUMN public.Departamento.situacao IS 'A-Ativa, I-Inativa';


ALTER SEQUENCE public.seq_iddepartamento OWNED BY public.Departamento.idDepartamento;

CREATE SEQUENCE public.seq_idtipoatividade;

CREATE TABLE public.TipoAtividade (
                idTipoAtividade BIGINT NOT NULL DEFAULT nextval('public.seq_idtipoatividade'),
                nome VARCHAR(64) NOT NULL,
                situacao CHAR(1) NOT NULL,
                idDepartamento BIGINT NOT NULL,
                CONSTRAINT pk_tipoatividade PRIMARY KEY (idTipoAtividade)
);
COMMENT ON COLUMN public.TipoAtividade.situacao IS 'A-Ativa, I-Inativa';


ALTER SEQUENCE public.seq_idtipoatividade OWNED BY public.TipoAtividade.idTipoAtividade;

CREATE SEQUENCE public.seq_idfuncionario;

CREATE TABLE public.Funcionario (
                idFuncionario BIGINT NOT NULL DEFAULT nextval('public.seq_idfuncionario'),
                nome VARCHAR(64) NOT NULL,
                email VARCHAR(64),
                senha VARCHAR(32),
                situacao CHAR(1) NOT NULL,
                idDepartamento BIGINT NOT NULL,
                CONSTRAINT pk_funcionario PRIMARY KEY (idFuncionario)
);
COMMENT ON COLUMN public.Funcionario.situacao IS 'A-Ativa, I-Inativa';


ALTER SEQUENCE public.seq_idfuncionario OWNED BY public.Funcionario.idFuncionario;

CREATE SEQUENCE public.seq_idatividade;

CREATE TABLE public.Atividade (
                idAtividade BIGINT NOT NULL DEFAULT nextval('public.seq_idatividade'),
                idFuncionario BIGINT NOT NULL,
                idTipoAtividade BIGINT NOT NULL,
                descricao VARCHAR(255) DEFAULT '' NOT NULL,
                dataHoraCadastro TIMESTAMP NOT NULL,
                dataHoraPrevisaoInicio TIMESTAMP,
                dataHoraPrevisaoFim TIMESTAMP,
                dataHoraInicio TIMESTAMP,
                dataHoraFim TIMESTAMP,
                CONSTRAINT pk_atividade PRIMARY KEY (idAtividade)
);


ALTER SEQUENCE public.seq_idatividade OWNED BY public.Atividade.idAtividade;

CREATE SEQUENCE public.seq_idcargo;

CREATE TABLE public.Cargo (
                idCargo BIGINT NOT NULL DEFAULT nextval('public.seq_idcargo'),
                nome VARCHAR(255) DEFAULT '' NOT NULL,
                situacao CHAR(1) NOT NULL,
                gerente CHAR(1) NOT NULL,
                CONSTRAINT pk_cargo PRIMARY KEY (idCargo)
);

ALTER SEQUENCE public.seq_idcargo OWNED BY public.Cargo.idCargo;

ALTER TABLE public.Funcionario ADD CONSTRAINT fk_iddepartamento_funcionario
FOREIGN KEY (idDepartamento)
REFERENCES public.Departamento (idDepartamento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.TipoAtividade ADD CONSTRAINT fk_iddepartamento_tipoatividade
FOREIGN KEY (idDepartamento)
REFERENCES public.Departamento (idDepartamento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Atividade ADD CONSTRAINT fk_idtipoatividade_atividade
FOREIGN KEY (idTipoAtividade)
REFERENCES public.TipoAtividade (idTipoAtividade)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Atividade ADD CONSTRAINT fk_idfuncionario_atividade
FOREIGN KEY (idFuncionario)
REFERENCES public.Funcionario (idFuncionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;