# Game House - Sistema de Gerenciamento de Lan House

## Integrantes e Responsabilidades

| Integrante | Função | Parte Responsável |
| :--- | :--- | :--- |
| **Daniel Barros Pinto do Rego** | Desenvolvedor | Estruturação do projeto, concepção da ideia e codificação. |
| **Davi Fernando Costa Nunes** | Desenvolvedor | Implementação do Maven e testes com JUnit. |
| **Fernando Ariel Dias Nobre** | Desenvolvedor | Estruturação do código, codificação e implementação da API. |
| **Leonardo Augusto Gonçalves das Neves** | Desenvolvedor | Estruturação do código, codificação e implementação da API. |

---

## Descrição do Problema

O gerenciamento manual de uma Lan House apresenta desafios na organização de setores, alocação de equipes, controle de saldo de horas dos clientes e definição dinâmica dos preços dos equipamentos.

O **Game House** é uma solução desenvolvida em Java com Maven que centraliza o controle operacional da Lan House. O sistema permite cadastrar e vincular setores, equipes, funcionários e consoles, realizar a cobrança por horas reservadas dos clientes e integrar dinamicamente novos modelos de computadores através do consumo de dados na nuvem via Google Sheets CSV.

---

## Funcionalidades

- **Gerenciamento da Lan House e Setores:**
  - Cadastro, consulta, listagem e remoção de setores.
  - Relatório de indicadores operacionais por setor e do faturamento geral da empresa.
- **Gerenciamento de Funcionários e Equipes:**
  - Cadastro, consulta, edição, listagem e remoção de funcionários.
  - Validação para impedir o cadastro de funcionários com CPFs duplicados na mesma equipe.
- **Gerenciamento de Consoles e Equipamentos:**
  - Cadastro, consulta, listagem e remoção de consoles por setor.
  - Controle contra duplicação de IDs de console dentro do mesmo setor.
- **Controle de Clientes e Saldo de Horas:**
  - Cadastro, consulta, edição, listagem e remoção de clientes.
  - Adição e remoção controlada de horas reservadas (impedindo saldos negativos ou remoções superiores ao saldo disponível).
- **Integração de Catálogo Online (Google Sheets):**
  - Carga e processamento automatizado via API HTTP de novos modelos de notebooks/PCs e respectivas faixas de preço diretamente da nuvem.
- **Testes Automatizados:**
  - Suíte de testes unitários para verificação das regras de negócio críticas.

---

## Principais Classes

- **`LanHouse`**: Entidade principal do sistema que agrupa os setores e calcula o faturamento global.
- **`Setor`**: Agrupa uma equipe de colaboradores, uma plataforma, a lista de consoles e os clientes atendidos no setor.
- **`Cliente`**: Armazena o nome e o saldo de horas reservadas, contendo lógica para adição/remoção segura de horas.
- **`Equipe`**: Gerencia a lista de funcionários vinculados a um setor, garantindo a unicidade pelo CPF.
- **`Funcionario`**: Representa o colaborador com nome e CPF.
- **`Plataforma`**: Define a modalidade do equipamento (tipo, modelo e preço por hora).
- **`Console`**: Representa os consoles/equipamentos físicos identificados por ID.
- **`ControleDeCarga`**: Serviço responsável por efetuar requisições HTTP (`HttpClient`) para consumir e processar dados do Google Sheets em tempo real.
- **`RegrasDeNegocioTest`**: Classe contendo os testes unitários utilizando JUnit 5.
- **`Main`**: Interface de linha de comando (CLI) interativa com suporte a limpar tela e menu de navegação.

---

## Regras de Negócio

1. **Validação do Saldo de Horas (`Cliente`):** A quantidade inicial ou atualizada de horas reservadas do cliente não pode ser negativa. Não é permitido remover mais horas do que as que o cliente possui.
2. **Unicidade de CPF (`Equipe`):** Não é permitido cadastrar dois funcionários com o mesmo CPF na mesma equipe.
3. **Unicidade de ID (`Setor`):** Não é permitido cadastrar dois consoles com o mesmo ID em um mesmo setor.
4. **Precificação por Categoria (`ControleDeCarga`):** Plataformas importadas do catálogo online com categoria `High` são precificadas em **R$ 15,00/h**, enquanto as demais ficam em **R$ 10,00/h**.
5. **Cálculo do Faturamento (`Setor` / `LanHouse`):** O faturamento total do setor é o resultado do total de horas reservadas multiplicado pelo preço/hora da plataforma. O faturamento geral é a soma de todos os setores.

---

## Tecnologias Utilizadas

- **Gerenciador de Dependências e Build:** Apache Maven
- **Testes Automatizados:** JUnit Jupiter 5.10.2
- **Requisições HTTP & APIs:** `java.net.http.HttpClient` (Java Standard HTTP Client)
- **Fonte de Dados Externa:** Google Sheets (CSV via HTTP GET)

---

## Instruções para Configuração

### Pré-requisitos
- **Java Development Kit (JDK):** Versão 17 ou superior.
- **Apache Maven:** Versão 3.8+ (ou plugin Maven integrado na IDE).

### Estrutura de Pastas
Certifique-se de que a raiz do projeto contém o arquivo `pom.xml` e a estrutura de pastas configurada da seguinte forma:

```text
lanhouse/
├── pom.xml
└── src/
    ├── Cliente.java
    ├── Console.java
    ├── ControleDeCarga.java
    ├── Equipe.java
    ├── Funcionario.java
    ├── LanHouse.java
    ├── Main.java
    ├── Plataforma.java
    ├── RegrasDeNegocioTest.java
    ├── Setor.java
    └── Teste.java
```

## Instruções para Execução

### 1. Executando o Sistema (Menu Interativo)
1. No VS Code, abra o arquivo `src/Main.java`.
2. Clique no botão de **Run** (Tecla F3 ou no ícone de ▶️ Play no canto superior direito do editor) ou clique no atalho **Run** exibido logo acima do método `public static void main(String[] args)`.
3. O terminal integrado do VS Code abrirá automaticamente e você poderá navegar interativamente por todas as 24 funcionalidades do sistema.

### 2. Executando os Testes Unitários (JUnit 5)
1. No VS Code, abra o arquivo `src/RegrasDeNegocioTest.java`.
2. Clique no **ícone verde** (Play / ✔) localizado na margem esquerda do editor, logo ao lado da declaração da classe (`public class RegrasDeNegocioTest`), para executar todos os testes de uma só vez.
3. O VS Code executará a suíte de testes e exibirá o resultado das validações no painel do terminal.

## Exemplos de Utilização

### Exemplo 1: Cadastrar um Setor com Importação do Catálogo Online (Google Sheets)
Ao selecionar a opção **3 - Cadastrar setor** no menu principal, o sistema permite carregar automaticamente modelos de notebooks e preços a partir da nuvem:

## Exemplos de Utilização

### Exemplo 1: Cadastrar Setor com Importação do Catálogo Online (Google Sheets)
Ao escolher a opção **3 - Cadastrar setor** no menu principal, o sistema identifica se a plataforma é do tipo PC ou Notebook e disponibiliza o catálogo dinâmico via API do Google Sheets:

```text
===== CADASTRAR SETOR =====
Nome do setor: Setor Gamers
Código da equipe: 10
Tipo da plataforma (Ex: PC, PlayStation, Xbox): Notebook

[SISTEMA] Tipo 'Notebook' detectado. Liberando Catálogo Online do Google Sheets...
1 - Escolher modelo do Catálogo Online
2 - Digitar o modelo manualmente
Escolha uma opção: 1

Buscando modelos disponíveis na nuvem...

--- Modelos de Notebooks Encontrados na Planilha ---
1 - Notebook (Dell) | Modelo: G15 (Preço sugerido: R$ 15,00/h)
2 - Notebook (Lenovo) | Modelo: IdeaPad (Preço sugerido: R$ 10,00/h)

Selecione o número do modelo para importar: 1

Plataforma importada com sucesso!
Setor cadastrado com sucesso.
```

---

### Exemplo 2: Cadastrar Cliente e Adicionar Horas Reservadas
Para vincular um cliente a um setor e adicionar o tempo de uso dos equipamentos:

1. **Cadastrar o Cliente (Opção 16):**
   ```text
   ===== CADASTRAR CLIENTE =====
   ===== SETORES =====
   Nome do setor: Setor Gamers
   Digite o nome do setor: Setor Gamers
   Nome do cliente: Carlos Silva
   Quantidade inicial de horas: 2
   Cliente cadastrado com sucesso.
   ```

2. **Adicionar Horas ao Cliente (Opção 20):**
   ```text
   ===== ADICIONAR HORAS =====
   Digite o nome do setor: Setor Gamers
   Nome do cliente: Carlos Silva
   Quantidade de horas: 3
   Total de horas: 5
   ```

---

### Exemplo 3: Consultar Indicadores e Faturamento do Setor
Ao selecionar a opção **22 - Indicadores do setor**, o sistema calcula em tempo real o tempo total e a receita gerada:

```text
===== INDICADORES DO SETOR =====
Digite o nome do setor: Setor Gamers

Setor: Setor Gamers
Total de horas reservadas: 5
Média de horas por cliente: 5,00
Maior quantidade de horas: 5
Menor quantidade de horas: 5
Faturamento total: R$ 75,00
```

---

## Uso de IA

| Ferramenta | Objetivo | Resumo do uso | Revisão pelo Dev |
| :--- | :--- | :--- | :--- |
| Gemini | Documentação e README | Auxiliou na estruturação, formatação e redação do arquivo README.md. | Validação dos dados do projeto, verificação dos comandos de execução e ajustes no texto. |
| ChatGPT | Interface CLI e Classe `ControleDeCarga` | Ajudou na criação e estruturação do menu interativo no terminal e na construção da classe `ControleDeCarga` (integração e carga de dados do Google Sheets via HTTP). | Testes manuais dos fluxos de navegação, validação das requisições HTTP e tratamento de exceções. |
| Gemini | Utilitários de Console | Auxiliou no desenvolvimento das funções de "limpar tela" e "pressione ENTER para continuar". | Testes de comportamento e compatibilidade no terminal (Windows/PowerShell). |
| Gemini | Resolução de Erros (Maven e JUnit) | Apoiou exclusivamente na depuração e resolução de erros durante a configuração do `pom.xml` (Maven) e na execução dos testes unitários com JUnit 5. | Validação do build do Maven, aplicação das correções no VS Code e execução bem-sucedida da suíte de testes. |
