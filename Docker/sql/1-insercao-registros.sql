INSERT INTO dbjava.Usuario (IdUsuario, CPF, Nome, Email, Senha, Telefone, Endereco)
VALUES (2, '12345678911', 'Errico Malatesta', 'e.malatesta@proton.me', '81dc9bdb52d04dc20036dbd8313ed055', '21987132239', 'Av. Central, 13');

insert into dbjava.Perfil (IdPerfil, Nome)
values (2, 'Gerente Geral');

insert into dbjava.Funcionalidade (IdFuncionalidade, Nome, URL)
values (2, 'Cadastrar Usuario', '/cadastro/conta'),
       (3, 'Contratar Funcionario', '/acesso/cadastro/usuario'),
       (4, 'Abrir conta', '/acesso/cadastro/conta'),
       (5, 'Fechar conta', '/acesso/cadastro/conta'),
       (6, 'Trocar Gerente de Conta', '/acesso/cadastro/conta'),
       (7, 'Gerenciar usuarios', '/acesso/lista/usuario');

insert into Acesso (IdAcesso, IdUsuario, IdPerfil)
values (2, 2, 2);

insert into dbjava.Permissao (IdPermissao, IdPerfil, IdFuncionalidade)
values (2, 2, 2),
       (3, 2, 3),
       (4, 2, 4),
       (5, 2, 5),
       (6, 2, 6),
       (7, 2, 7);
