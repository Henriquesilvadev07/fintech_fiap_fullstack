CREATE SEQUENCE sq_t_users START WITH 1 INCREMENT BY 1;

CREATE TABLE t_fin_user
(
    id       NUMBER(38, 0) NOT NULL,
    ds_login VARCHAR2(255) NOT NULL,
    ds_senha VARCHAR2(255) NOT NULL,
    CONSTRAINT pk_t_fin_user PRIMARY KEY (id)
);

ALTER TABLE t_fin_user
    ADD CONSTRAINT uc_t_fin_user_ds_login UNIQUE (ds_login);