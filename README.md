# BancoJaver
Projeto Final - Banco Javer

INSTRUÇÕES:

-INICIALIZAR AMBAS APLICAÇÕES
-FAZER A REQUISIÇÃO PARA REDIRECIONAMENTO NA APLICAÇÃO 1

-FAZER AS REQUISIÇÕES DE USUÁRIO DA APLICAÇÃO 2


APLICAÇÃO 1 - Serviço de redirecionamento e cálculo de score

Nome: "cliente-service"

Funcionalidades:

- Redirecionamento para urls dos serviços principais:
  - "localhost:8080/clientes"

- Url para cálculo de score de um cliente através do ID do mesmo:
  - (inserir número ID após "score/")
  - "localhost:8080/score/{id}"
  

APLICAÇÃO 2 - Serviços relacionados ao cliente

Nome: "data-service"

Funcionalidades:

- Criar cliente
  - "localhost:8081/clientes" - POST
- Atualizar cliente através do ID
  - "localhost:8081/clientes/{id}" - PUT
- Excluir cliente da base de dados através do ID
  - "localhost:8081/clientes/{id}" - DELETE
- Listar informações de todos os clientes
  - "localhost:8081/clientes" - GET
- Listar informações de apenas um cliente através do ID
  - "localhost:8081/clientes/{id}" - GET



