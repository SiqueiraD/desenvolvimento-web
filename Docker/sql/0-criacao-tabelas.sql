-- Criando a tabela Usuario
CREATE TABLE Usuario
(
    IdUsuario INT PRIMARY KEY,
    CPF       VARCHAR(11) UNIQUE NOT NULL,
    Nome      VARCHAR(255)       NOT NULL,
    Email     VARCHAR(255)       NOT NULL,
    Senha     VARCHAR(255)       NOT NULL,
    Telefone  VARCHAR(24),
    Endereco  VARCHAR(1245)
);

-- Criando a tabela Perfil
CREATE TABLE Perfil
(
    IdPerfil INT PRIMARY KEY,
    Nome     VARCHAR(255) NOT NULL
);

-- Criando a tabela Funcionalidade
CREATE TABLE Funcionalidade
(
    IdFuncionalidade INT PRIMARY KEY,
    Nome             VARCHAR(255) NOT NULL,
    URL              VARCHAR(255) NOT NULL
);

-- Criando a tabela Permissao
CREATE TABLE Permissao
(
    IdPermissao      INT PRIMARY KEY,
    IdPerfil         INT NOT NULL,
    IdFuncionalidade INT NOT NULL,
    FOREIGN KEY (IdPerfil) REFERENCES Perfil (IdPerfil),
    FOREIGN KEY (IdFuncionalidade) REFERENCES Funcionalidade (IdFuncionalidade)
);

-- Criando a tabela Acesso
CREATE TABLE Acesso
(
    IdAcesso  INT PRIMARY KEY,
    IdUsuario INT NOT NULL,
    IdPerfil  INT NOT NULL,
    FOREIGN KEY (IdUsuario) REFERENCES Usuario (IdUsuario),
    FOREIGN KEY (IdPerfil) REFERENCES Perfil (IdPerfil),
    UNIQUE KEY (IdUsuario, IdPerfil)
);

-- Criando a tabela Conta
CREATE TABLE Conta
(
    IdConta     INT PRIMARY KEY,
    IdAcesso    INT                NOT NULL,
    FOREIGN KEY (IdAcesso) REFERENCES Acesso (IdAcesso),
    NumeroConta VARCHAR(64) UNIQUE NOT NULL,
    SaldoAtual DOUBLE NOT NULL
);

-- Criando a tabela Movimentacao
CREATE TABLE Movimentacao
(
    IdMovimentacao INT PRIMARY KEY,
    IdContaOrigem  INT  NOT NULL,
    IdContaDestino INT  NOT NULL,
    FOREIGN KEY (IdContaOrigem) REFERENCES Conta (IdConta),
    FOREIGN KEY (IdContaDestino) REFERENCES Conta (IdConta),
    Valor DOUBLE NOT NULL,
    Data           DATE NOT NULL
);