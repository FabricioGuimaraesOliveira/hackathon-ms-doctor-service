CREATE TABLE DOCTOR (
                        ID UUID NOT NULL,  -- Mantido como UUID
                        NOME VARCHAR(255) NULL,
                        CPF VARCHAR(11) NULL,
                        NUMERO_CRM VARCHAR(20) NULL,
                        EMAIL VARCHAR(255) NULL,
                        SENHA VARCHAR(255) NULL,
                        ESPECIALIDADE VARCHAR(255) NULL,
                        CONSTRAINT DOCTOR_PK PRIMARY KEY (ID),
                        CONSTRAINT DOCTOR_UNIQUE UNIQUE (EMAIL)
);

CREATE TABLE CALENDAR (
                          ID UUID NOT NULL,  -- Mantido como UUID
                          DOCTOR_ID UUID NOT NULL,  -- Alterado para UUID para ser compatível com a tabela DOCTOR
                          DATA_AGENDA VARCHAR(10) NULL,
                          HORA_AGENDA VARCHAR(5) NULL,
                          STATUS VARCHAR(255) NULL,
                          CONSTRAINT CALENDAR_PK PRIMARY KEY (ID),
                          CONSTRAINT CALENDAR_FK FOREIGN KEY (DOCTOR_ID) REFERENCES DOCTOR(ID)
);
