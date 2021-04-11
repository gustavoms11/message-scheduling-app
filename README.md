# Aplicação de agendamento de mensagens

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Tecnologias

- Spring Boot 2.4.4
- Java 11
- Springfox 3.0.0
- MySQL 8
- JUnit 5 (Jupiter)
- RestAssured 4.2.1
- TestContainers 1.15.2

### Pré Requisitos

- Devido ao TestContainers para rodar testes de integração, é necessário que o usuário corrente possa realizar operações Docker
- Projeto construído com Maven
- JDK 11, preferêncialmente OpenJDK, pois foi utilizado OpenJDK11 para desenvolvimento e construção da imagem Docker
- Por padrão o projeto sobe na porta 8080, portanto ela deve estar livre caso queira subir o projeto sem alterações de parâmetros
- MySQL rodando no mesmo ambiente caso suba o projeto em ambiente local, os parâmetros de usuário e senha da base padrões são user:msgapp e pass:root

### Parâmetros de ambiente

- DATABASE_URL: Endereço JDBC da base de dados, por padrão é jdbc:mysql:http://localhost:3306/msgapp
- DATABASE_USER: Usuário da base de dados, por padrão msgapp
- DATABASE_PASS: Senha da base de dados, por padrão root

O projeto usa o autocreate do hibernate para criar a base de dados, está configurado como update por padrão

### Execução

Para compilar o projeto, utilizar

`
mvn clean install
`

Assumindo que mvn é o comando padrão para execução do Maven

Caso houver algum erro na execuçao do TestContainers, verificar se o docker está liberado para o usuário corrente realizar pull de imagens

Para executar, é possível realizar pela IDE, pelo Maven ou através do executável em target/

### Imagem Docker

Para criação da imagem docker, na raiz do projeto, após compilado, utilizar o comando

`
docker build -t message-scheduling .
`

Ainda não possui suporte a uso do plugin do maven, deploy automático e parametrização de banco externo, para utilizar o banco realizar link ao subir com imagem do mysql, essas funções estão planejadas para o futuro

### Funções da aplicação

Para acessar a documentação, o endereço padrão é:

`swagger-ui`:<http://localhost:8080/message-scheduling/swagger-ui/>

A aplicação possui um Controller contendo três Endpoints

POST: /api/v1/scheduled-messages constrói um novo agendamento, para tal é necessário passar:

- receiverId: Código do usuário envolvido, neste primeiro momento há uma variável interna gravada como receiverType que por padrão é USER, sendo planejada para expandir a outras possibilidades caso seja necessário em desenvolvimentos futuros
- scheduledTo: Data para a qual a mensagem está agendada, importante que todas as datas enviadas para o sistema através de endpoints devem seguir a base do padrão ISO-8601, que é yyyy-MM-ddTHH:mm:ssZ
- platforms: Uma lista de plataformas para as quais as mensagens serão enviadas, sendo possível enviar por: Torpedo (SMS), WhatsApp (WHATSAPP), Notificação de celular (PUSH) ou e-mail (MAIL)
- message: Mensagem a ser enviada, feita em texto puro pois tem-se como base que outros serviços terão a responsabilidade de tratar um template pré envio caso necessário

GET: /api/v1/scheduled-messages busca por listagem, podendo ser paginada e ordenada, os parâmetros possíveis são:

- page: Página a ser exibida
- pageSize: Quantidade de itens por página
- receiverId: Identificador do receptor da mensagem
- createdAtStartDate: Buscar a partir desta data de criação
- createdAtEndDate: Buscar até esta data de criação
- scheduledToStartDate: Buscar a partir desta data de agendamento
- scheduledToEndDate: Buscar até esta data de agendamento
- status: Estado do agendamento corrente, sendo atualizado após o envio concluído (em construção), podendo ser agendado (SCHEDULED) ou enviado (SENT)
- order: Ordenação, podendo ser data de criação (createdAt) e agendamento (scheduledTo), pode-se também buscar com ordenação contrária pré fixando a ordenação com - (-createdAt, -scheduledTo)

GET: /api/v1/scheduled-messages/{id} Busca uma mensagem por seu identificador

DELETE: /api/v1/scheduled-messages/{id} Exclui uma mensagem do sistema

Importante notar que a documentação do Swagger está em inglês