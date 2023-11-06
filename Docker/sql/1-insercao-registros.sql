INSERT INTO dbjava.Usuario (IdUsuario, CPF, Nome, Email, Senha, Telefone, Endereco)
VALUES (2, '12345678911', 'Errico Malatesta', 'e.malatesta@proton.me', '81dc9bdb52d04dc20036dbd8313ed055', '21987132239', 'Av. Central, 13');

insert into dbjava.Perfil (IdPerfil, Nome)
values (2, 'Gerente Geral'),
       (3, 'Gerente de conta'),
       (4, 'Cliente');

insert into dbjava.Funcionalidade (IdFuncionalidade, Nome, URL)
values (2, 'NaoUsar Usuario', '/conta/cadastro.jsp'),
       (3, 'Contratar Funcionario', '/usuario/cadastro.jsp'),
       (4, 'Abrir conta', '/acesso/conta/cadastro'),
       (5, 'Fechar conta', '/acesso/cadastro/conta'),
       (6, 'Trocar Gerente de Conta', '/acesso/cadastro/conta'),
       (7, 'Gerenciar usuarios', '/acesso/lista/usuario'),
       (8, 'Ver Extrato', '/conta/index.jsp'),
       (9, 'Transferir', '/acesso/conta/transferir');

insert into Acesso (IdAcesso, IdUsuario, IdPerfil)
values (2, 2, 2),
       (3, 2, 4);

insert into dbjava.Permissao (IdPermissao, IdPerfil, IdFuncionalidade)
-- Permissoes de Gerente geral
values (2, 2, 2),
       (3, 2, 3),
       (4, 2, 4),
       (5, 2, 5),
       (6, 2, 6),
       (7, 2, 7)
       , -- Permissoes de cliente
       (8, 4, 8),
       (9, 4, 9)
        , -- Permissoes de Gerente de conta
       (10, 3, 4),
       (11, 3, 5),
       (12, 3, 2);

insert into dbjava.Conta (IdConta, IdAcesso, NumeroConta, SaldoAtual)
values (2, 3, '00000000001', '1000000.00'),
       (3, 3, '00000000009', '20.00');
