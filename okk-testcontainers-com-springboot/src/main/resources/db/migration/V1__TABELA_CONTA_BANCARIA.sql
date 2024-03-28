CREATE TABLE IF NOT EXISTS CONTA_BANCARIA
(
    ID          SERIAL       NOT NULL,
    UUID        VARCHAR(50) NOT NULL,
    NOME        VARCHAR(20) NOT NULL,
    AGENCIA     VARCHAR(10) NOT NULL,
    CONTA       VARCHAR(10) NOT NULL,
    BANCO       VARCHAR(5)  NOT NULL,
    GERENTE     VARCHAR(20),
    OBSERVACAO  VARCHAR(500),
    DATA_INSERT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    DATA_UPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);