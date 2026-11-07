-- 1. CRIANDO AS SEQUENCES (Requerido para IDs no Oracle)
CREATE SEQUENCE SQ_FIN_CLIENTE START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE SQ_FIN_CONTA START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE SQ_FIN_TRANSACAO START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- 2. TABELA DE CLIENTE
CREATE TABLE T_FIN_CLIENTE (
                               id_cliente NUMBER(19) NOT NULL,
                               nm_cliente VARCHAR2(100) NOT NULL,
                               ds_email VARCHAR2(100) NOT NULL,
                               ds_cpf VARCHAR2(11) NOT NULL,
                               dt_nascimento DATE,
                               CONSTRAINT PK_FIN_CLIENTE PRIMARY KEY (id_cliente),
                               CONSTRAINT UN_FIN_CLIENTE_EMAIL UNIQUE (ds_email),
                               CONSTRAINT UN_FIN_CLIENTE_CPF UNIQUE (ds_cpf)
);

-- 3. TABELA DE CONTAS
CREATE TABLE T_FIN_CONTA (
                             id_conta NUMBER(19) NOT NULL,
                             nr_agencia VARCHAR2(10) NOT NULL,
                             nr_conta VARCHAR2(20) NOT NULL,
                             vl_saldo NUMBER(15,2) DEFAULT 0 NOT NULL,
                             id_cliente NUMBER(19) NOT NULL,
                             CONSTRAINT PK_FIN_CONTA PRIMARY KEY (id_conta),
                             CONSTRAINT FK_FIN_CONTA_CLIENTE FOREIGN KEY (id_cliente) REFERENCES T_FIN_CLIENTE(id_cliente)
);

-- 4. TABELA DE TRANSAÇÕES
CREATE TABLE T_FIN_TRANSACAO (
                                 id_transacao NUMBER(19) NOT NULL,
                                 tp_transacao VARCHAR2(20) NOT NULL,
                                 vl_transacao NUMBER(15,2) NOT NULL,
                                 dt_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                 id_conta NUMBER(19) NOT NULL,
                                 CONSTRAINT PK_FIN_TRANSACAO PRIMARY KEY (id_transacao),
                                 CONSTRAINT FK_FIN_TRANS_CONTA FOREIGN KEY (id_conta) REFERENCES T_FIN_CONTA(id_conta)
);