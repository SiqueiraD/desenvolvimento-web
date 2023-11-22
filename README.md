# Projeto de Software com JSP, MVC e Docker

## Arquitetura do Projeto

O projeto de software é baseado na arquitetura **Model-View-Controller (MVC)** com algumas adaptações. Aqui está uma descrição detalhada das camadas:

- **Model**: Esta camada é representada pelos domínios (`src\main\webapp\domain`) do projeto. Eles são responsáveis por representar os objetos e a lógica de negócios do sistema. 

- **View**: As páginas JSP (`*.jsp`) representam a camada de visualização. Elas são responsáveis por exibir os dados ao usuário.

- **Controller**: Os WebServlets (`src\main\webapp\servlets`) atuam como controladores. Eles são responsáveis por receber as requisições do usuário, chamar o serviço adequado (`src\main\webapp\service`) e exibir a JSP correspondente.

## Docker e docker-compose.yml

O Docker é uma plataforma que permite desenvolver, enviar e executar aplicativos em containers. Um container é uma unidade padrão de software que empacota o código e todas as suas dependências para que o aplicativo seja executado de forma rápida e confiável de um ambiente de computação para outro.

O arquivo `docker-compose.yml` define os serviços que compõem o aplicativo e as configurações necessárias para executá-los. No caso deste projeto, existem três serviços definidos:

- **build-runner**: Este serviço é responsável por compilar o código Java e empacotá-lo em um arquivo WAR. Ele usa a imagem `openjdk:17` e monta os volumes necessários para o código-fonte e o destino da compilação.

- **tomcat-runner**: Este serviço é responsável por executar o aplicativo em um servidor Tomcat. Ele depende do serviço `build-runner` para garantir que o código seja compilado antes de tentar executá-lo. Ele também expõe as portas necessárias e define algumas variáveis de ambiente.

- **db-server**: Este serviço é responsável por executar o banco de dados MySQL. Ele expõe a porta padrão do MySQL e define algumas variáveis de ambiente para a configuração do banco de dados.

Os containers são gerenciados pelo Docker, que garante que eles sejam executados nas condições especificadas e possam se comunicar entre si conforme necessário. A orquestração desses containers é feita através do Docker Compose, que lê o arquivo `docker-compose.yml` e cria os containers conforme especificado.

## Executando o Projeto

Para executar o projeto, você pode usar o seguinte comando do Docker Compose:

```bash
docker compose up 
```

Infelizmente, a recompilação automática para cada vez que o projeto for alterado não foi implementada. Portanto, sempre que você fizer uma alteração no projeto, precisará parar a execução e iniciar novamente para ver as alterações refletidas.

Para parar a execução, você pode usar Ctrl+C no terminal onde o Docker Compose está sendo executado. Depois disso, você pode usar o comando acima para iniciar a execução novamente.