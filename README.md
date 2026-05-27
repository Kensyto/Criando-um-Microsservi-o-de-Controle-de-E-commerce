# Desafio de Microsserviços de E-commerce - DIO

Este projeto foi desenvolvido como parte de um desafio prático da Digital Innovation One (DIO), focado na criação de uma arquitetura de microsserviços para o controle de um e-commerce.

## 🚀 Sobre o Desafio

O objetivo principal deste desafio é aplicar conceitos avançados de sistemas distribuídos utilizando Java e Spring Boot, construindo um ambiente escalável e resiliente. O projeto foca na integração de serviços e no uso de mensageria para garantir a eficiência operacional.

### Objetivos de Aprendizado:
- Implementar uma arquitetura de microsserviços do zero ou aperfeiçoar uma existente.
- Aplicar padrões de projeto em cenários reais de e-commerce.
- Documentar decisões técnicas e o raciocínio por trás da arquitetura.
- Utilizar ferramentas de versionamento e boas práticas de desenvolvimento.

## 🏗️ Arquitetura do Sistema

A solução é composta por dois microsserviços fundamentais que interagem entre si:

1.  **Warehouse (Armazém):** Gerencia o inventário, estoque e a disponibilidade dos produtos.
2.  **Storefront (Vitrine):** Responsável pela exibição de produtos aos clientes e pela captura de pedidos.

### Comunicação entre Microsserviços

Para garantir a flexibilidade e a performance, o sistema utiliza dois modelos de comunicação:

-   **Síncrona (via HTTP):** Utilizada para requisições que demandam resposta imediata entre os serviços.
-   **Assíncrona (via RabbitMQ):** Implementada para o processamento de tarefas em segundo plano e eventos de sistema, como atualizações de estoque após uma venda, garantindo o desacoplamento e a resiliência.

## 🛠️ Tecnologias e Ferramentas

-   **Java:** Linguagem de programação principal.
-   **Spring Boot:** Framework para criação célere dos microsserviços.
-   **RabbitMQ:** Broker de mensagens para comunicação assíncrona.
-   **Spring Cloud:** Ferramentas para auxílio na construção de sistemas distribuídos.
-   **Maven/Gradle:** Gerenciador de dependências.

## 📁 Estrutura do Repositório

-   `/warehouse`: Código-fonte do microsserviço de armazém.
-   `/storefront`: Código-fonte do microsserviço de vitrine.
-   `README.md`: Documentação principal do projeto.

---

Este projeto é uma excelente oportunidade para demonstrar habilidades em integração de sistemas, mensageria e desenvolvimento com Spring Boot.
