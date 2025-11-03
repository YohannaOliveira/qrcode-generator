# QR Code Generator

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen)
![AWS SDK](https://img.shields.io/badge/AWS%20SDK-2.24.12-yellow)
![Google ZXing](https://img.shields.io/badge/Google%20ZXing-3.5.2-blue)
![Docker](https://img.shields.io/badge/Docker-✓-blue)
![Maven](https://img.shields.io/badge/Maven-3.5.3-red)

Este projeto é uma aplicação Spring Boot desenvolvida para gerar e armazenar QR Code.
Ela utiliza o AWS S3 (ou LocalStack para desenvolvimento local) para armazenar os QR Codes gerados.

---

## Features
- Geração de QR Code usando a biblioteca ZXing.
- Armazenamento dos QR Codes no AWS S3 ou LocalStack.
- API RESTful para geração e recuperação dos QR Codes.

---

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.5.3**
- **Maven** para gerenciamento de dependências
- **AWS SDK v2** para integração com o S3
- **ZXing** para geração de QR Code


## Como Usar

Esta seção fornece instruções completas para configurar e executar a aplicação **QR Code Generator**.


### Pré-requisitos

- **Java 21 JDK**
- **Maven**
- **Docker**
- **Conta AWS** com acesso ao S3
- **AWS CLI** configurado com credenciais válidas

---

### Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
AWS_ACCESS_KEY_ID=sua_access_key
AWS_SECRET_ACCESS_KEY=sua_secret_key
AWS_REGION=sua_regiao
AWS_BUCKET_NAME=nome_do_seu_bucket
```

### Executando a Aplicação

#### Desenvolvimento Local

1. Crie o arquivo .env conforme descrito acima.
2. Compile o projeto:
   ```bash
   mvn clean package
   ```
3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

#### Implantação com Docker

1. Crie a imagem Docker:
   ```bash
   docker build -t qrcode-generator:X.X . 
   ```
> Lembre-se de substituir a versão e o nome da imagem, se desejar.

2. Execute o container:
   ```bash
   docker run --env-file .env -p 8080:8080 qrcode-generator:X.X 
   ```
> Lembre-se de substituir o caminho do arquivo .env pelo caminho correto do arquivo de variáveis de ambiente que você criou.

### Configuração do AWS S3

1. Crie um bucket S3 na sua conta AWS.
2. Atualize o valor de `AWS_BUCKET_NAME` no seu arquivo `.env` ou no comando Docker.
3. Garanta que suas credenciais AWS tenham as permissões necessárias para acessar o bucket.

## API Endpoints

### POST /qrcode
Gera um QR Code a partir do texto fornecido e o armazena no AWS S3.
O QR Code será gerado como uma imagem PNG com dimensões de 200x200 pixels.

**Parâmetros**

| Nome | Obrigatório | Tipo | Descrição                                                                                                            |
|------|-------------|------|----------------------------------------------------------------------------------------------------------------------|
| `text` | sim         | string | O conteúdo de texto que será codificado no QR Code. Pode ser qualquer valor que você deseje converter em um QR Code. |

**Response**

```json
{
  "url": "https://seu-bucket.s3.sua-regiao.amazonaws.com/uuid-aleatorio"
}

```

**Error Response**

Se ocorrer um erro durante a geração do QR Code ou o upload para o S3, a API retornará um 500 Internal Server Error.

**Exemplo de Uso**

```bash
curl -X POST http://localhost:8080/qrcode \
     -H "Content-Type: application/json" \
     -d '{"text": "https://example.com"}'
```

## License

This project is licensed under the MIT License - see the LICENSE file for details. 