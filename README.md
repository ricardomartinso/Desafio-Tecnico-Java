# Pagamentos-API

Um projeto de desafio técnico proposto para criação de uma  API para possibilitar o recebimento de pagamentos de débitos de pessoas físicas e jurídicas.

<p align="center">
  <img src="https://user-images.githubusercontent.com/87649154/217006765-eba4eb62-b105-48ff-8d03-ce8165e94d78.png"/>
</p>
<h1 align="center">
  Pagamentos-API
</h1>
<div align="center">

  <h3>Built With</h3>

  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" height="30px"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <!-- Badges source: https://dev.to/envoy_/150-badges-for-github-pnk -->
</div>

<br/>

# Descrição

Pagamentos-API simula uma REST API que possibilita o recebimento de pagamentos de débitos de pessoas físicas e jurídicas, utilizando Java 17, Spring Boot, H2 Database, Validações com Jakarta Validation.

</br>

## Features

- Crie seu próprio pagamento com 4 meios de pagamento
- Ao criar o pagamento, altere seu status de processamento para sucesso ou falha.
- Filtre os pagamentos por CPF/CPNJ, Código do Débito e Status de pagamento.

</br>

## API Referência

### Cadastre um pagamento.

```http
POST /api/payment
```

#### Request:

| Body              | Type     | Description                            |
| :---------------- | :------- | :------------------------------------- |
| `codigoDebito`    | `int` | **Required**. Código do débito a ser pago |
| `cpfCnpjPagador`  | `String` | **Required**. User password      |
| `metodoPagamento` | `ENUM (BOLETO, PIX, CARTAO_CREDITO, CARTAO_DEBITO` | **Required**. Métodos de pagamento disponíveis |
| `numeroCartao`        | `String Cartao de credito/debito válido` | **OPTIONAL**. Número de cartão            |
| `valorPagamento` | `double` | **Required**. Valor do débito a ser pago |

#### Response:

```json
{
  "data": "Pagamento",
  "httpStatus": "CREATED",
  "message": "Pagamento cadastrado com sucesso!"
}
```

#

### Mude o status de um pagamento

```http
PUT /api/process
```

#### Request:

| URL PARAM              | Type     | Description                            |
| :---------------- | :------- | :------------------------------------- |
| `id`           | `Long` | **Required**. ID do pagamento a ser atualizado               |
| `status`        | `String` | **Required**. Aceita somente (pendente, sucesso, falha)            |

`https://localhost:{porta}?status=sucesso&id=5` 

</br>

#### Response:

```json
{
  "data": "Pagamento",
  "httpStatus": "OK",
  "message": "Pagamento atualizado com sucesso!"
}
```

### Listar pagamentos por Código do débito.

```http
GET /api/payment/codigo-debito/{codigoDebito}
```

#### Request:

| PATH PARAM              | Type     | Description                            |
| :---------------- | :------- | :------------------------------------- |
| `codigoDebito`    | `int` | **Required**. Código do débito a ser filtrado |


#### Response:

```json
{
  "data": "Array de pagamentos filtrados",
  "httpStatus": "OK",
  "message": "Pagamentos por código débito filtrados com sucesso"
}
```

#

### Listar pagamentos por CPF/CNPJ.

```http
GET /api/payment/cpf-cnpj/{cpfCnpj}
```

#### Request:

| PATH PARAM              | Type     | Description                            |
| :---------------- | :------- | :------------------------------------- |
| `cpfCnpj`    | `String` | **Required**. CPF/CNPJ a ser filtrado |


#### Response:

```json
{
  "data": "Array de pagamentos filtrados",
  "httpStatus": "OK",
  "message": "Pagamentos por CPF/CNPJ filtrados com sucesso"
}
```

#

### Listar pagamentos por status de pagamento.

```http
GET /api/payment/status/{status}
```

#### Request:

| PATH PARAM              | Type     | Description                            |
| :---------------- | :------- | :------------------------------------- |
| `status`    | `String` | **Required**. Status dos pagamentos a serem filtrados |

`status precisa ser uma String do tipo 'pendente' , 'sucesso' ou 'falha'`

#### Response:

```json
{
  "data": "Array de pagamentos filtrados",
  "httpStatus": "OK",
  "message": "Pagamentos por status filtrados com sucesso"
}
```

#


## Rode Localmente

Clone the project

```bash
  git clone https://github.com/ricardomartinso/Desafio-Tecnico-Java
```

Go to the project directory

```bash
  cd Desafio-Tecnico-Java/
```


</br>

## Lições aprendidas

Nesse projeto aprendi muito sobre como melhor estruturar um projeto em JAVA utilizando SPRING BOOT.

</br>


## Autor

- Ricardo Martins é um Desenvolvedor FULL-STACK. Atualmente cursando ENGENHARIA DA COMPUTAÇÃO na UFPA e querendo me tornar um Dev.
  <br/>

#
