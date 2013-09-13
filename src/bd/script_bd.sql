CREATE TABLE usuario (
                codigo serial,
                nome VARCHAR(255) NOT NULL,
                email VARCHAR(255) NOT NULL,
                senha VARCHAR(255) NOT NULL,
                ativo BOOLEAN NOT NULL DEFAULT false,
                CONSTRAINT usuario_pk PRIMARY KEY (codigo)
);

CREATE TABLE perfil (
                codigo serial,
                nome VARCHAR(255) NOT NULL,
                descricao VARCHAR(255) NOT NULL,
                CONSTRAINT perfil_pk PRIMARY KEY (codigo)
);

CREATE TABLE perfis (
                codigo serial,
                codigo_usuario INTEGER NOT NULL,
                codigo_perfil INTEGER NOT NULL,
                CONSTRAINT perfis_pk PRIMARY KEY (codigo)
);

CREATE TABLE empresa (
                codigo serial,
                nome VARCHAR(255) NOT NULL,
                cnpj VARCHAR(15) NOT NULL,
                CONSTRAINT empresa_pk PRIMARY KEY (codigo)
);


CREATE TABLE supervisor (
                codigo serial,
                nome VARCHAR(255) NOT NULL,
                cod_empresa INTEGER NOT NULL,
                CONSTRAINT supervisor_pk PRIMARY KEY (codigo)
);


CREATE TABLE convenio (
                codigo serial,
                descricao VARCHAR(255) NOT NULL,
                documento BYTEA NOT NULL,
                data_inicio DATE NOT NULL,
                data_fim DATE NOT NULL,
                empresa INTEGER NOT NULL,
                CONSTRAINT convenio_pk PRIMARY KEY (codigo)
);
COMMENT ON COLUMN convenio.documento IS 'Arquivo pdf com o contrato de convênio.';


CREATE TABLE curso (
                codigo serial,
                nome VARCHAR(255) NOT NULL,
                CONSTRAINT curso_pk PRIMARY KEY (codigo)
);


CREATE TABLE estagiario (
                codigo serial,
                nome VARCHAR(255) NOT NULL,
                matricula VARCHAR(10) NOT NULL,
                curso INTEGER NOT NULL,
                CONSTRAINT estagiario_pk PRIMARY KEY (codigo)
);


CREATE TABLE professor (
                codigo serial,
                siape INTEGER NOT NULL,
                nome VARCHAR(255) NOT NULL,
                curso INTEGER NOT NULL,
                CONSTRAINT professor_pk PRIMARY KEY (codigo)
);


CREATE TABLE estagio (
                codigo serial,
                descricao VARCHAR(255) NOT NULL,
                data_inicio DATE NOT NULL,
                data_fim DATE NOT NULL,
                data_cadastro VARCHAR NOT NULL,
                estagiario INTEGER NOT NULL,
                orientador INTEGER NOT NULL,
                relatorio BYTEA NOT NULL,
                empresa INTEGER NOT NULL,
                situacao VARCHAR(50) NOT NULL,
                media REAL NOT NULL,
                CONSTRAINT estagio_pk PRIMARY KEY (codigo)
);
COMMENT ON COLUMN estagio.media IS 'Média optida pelas notas emitidas pelos avaliadores.';


CREATE TABLE avaliacao (
                codigo serial,
                avaliador INTEGER NOT NULL,
                comentario VARCHAR(255) NOT NULL,
                estagio INTEGER NOT NULL,
                nota REAL NOT NULL,
                CONSTRAINT avaliacao_pk PRIMARY KEY (codigo)
);


ALTER TABLE estagio ADD CONSTRAINT empresa_estagio_fk
FOREIGN KEY (empresa)
REFERENCES empresa (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE convenio ADD CONSTRAINT empresa_convenio_fk
FOREIGN KEY (empresa)
REFERENCES empresa (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE supervisor ADD CONSTRAINT empresa_supervisor_fk
FOREIGN KEY (cod_empresa)
REFERENCES empresa (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE professor ADD CONSTRAINT curso_professor_fk
FOREIGN KEY (curso)
REFERENCES curso (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE estagiario ADD CONSTRAINT curso_estagiario_fk
FOREIGN KEY (curso)
REFERENCES curso (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE estagio ADD CONSTRAINT estagiario_estagio_fk
FOREIGN KEY (estagiario)
REFERENCES estagiario (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE estagio ADD CONSTRAINT professor_estagio_fk
FOREIGN KEY (orientador)
REFERENCES professor (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE avaliacao ADD CONSTRAINT professor_avaliacao_fk
FOREIGN KEY (avaliador)
REFERENCES professor (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE avaliacao ADD CONSTRAINT estagio_avaliacao_fk
FOREIGN KEY (estagio)
REFERENCES estagio (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE perfis ADD CONSTRAINT perfil_perfis_fk
FOREIGN KEY (codigo_perfil)
REFERENCES perfil (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE perfis ADD CONSTRAINT usuario_perfis_fk
FOREIGN KEY (codigo_usuario)
REFERENCES usuario (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;