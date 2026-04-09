## README – Olimpiada UCSAL - SOLID

## Descrição do Projeto

Este projeto simula uma olimpíada de questões, permitindo o cadastro de participantes, provas, questões e o registro de tentativas. A versão atual foi refatorada seguindo boas práticas de SOLID, melhorando manutenibilidade, testabilidade e separação de responsabilidades.

---

## Principais Mudanças da Versão Refatorada

A versão anterior concentrava todas as responsabilidades em uma única classe App, com métodos estáticos e listas globais, o que dificultava manutenção e expansão. A versão refatorada introduziu:

Atualização na classe App – Classe de inicialização da aplicação.
Responsável apenas por criar serviços, repositórios e iniciar o menu.
Cumpre Single Responsibility Principle (SRP): apenas inicialização e injeção de dependências.
AppMenu – Classe de interface de usuário e controle de fluxo.
Contém apenas lógica de interação com o usuário e chamadas aos serviços.
Toda a lógica de negócio e persistência foi removida da classe principal, facilitando testes e alterações futuras.
Serviços e Repositórios
ParticipanteService, ProvaService, QuestaoService, RespostaService, TentativaService
Cada serviço encapsula operações de CRUD e lógica de negócio específica, permitindo Open/Closed Principle (OCP): novas funcionalidades podem ser adicionadas sem alterar classes existentes.
Repositórios concretos (ParticipanteRepository, etc.) são injetados nos serviços, respeitando Dependency Inversion Principle (DIP).
AplicacaoProva
Responsável apenas por aplicar uma prova e gerar tentativas.
A separação desta lógica da UI e do menu reforça SRP.
TabuleiroPrinter
Classe dedicada apenas a imprimir o tabuleiro de xadrez, isolando a responsabilidade de apresentação visual.

---

## Aplicação dos Princípios SOLID
Princípio	Como foi aplicado na versão refatorada
SRP (Single Responsibility Principle)	Cada classe tem uma responsabilidade clara: App inicializa, AppMenu controla UI, serviços manipulam dados, AplicacaoProva aplica provas, TabuleiroPrinter imprime tabuleiros.
OCP (Open/Closed Principle)	Serviços podem ser estendidos sem modificar classes existentes. Por exemplo, é possível adicionar novas regras de validação ou cálculos de nota sem alterar AppMenu.
LSP (Liskov Substitution Principle)	Repositórios implementam interfaces (RepositoryInterface), permitindo trocar implementações (ex: persistência em memória ou banco de dados) sem alterar a lógica do serviço.
ISP (Interface Segregation Principle)	Interfaces são específicas para cada entidade (ParticipanteRepositoryInterface), evitando métodos desnecessários para classes que não usam todas as operações.
DIP (Dependency Inversion Principle)	Classes de alto nível (AppMenu, AplicacaoProva) dependem de abstrações (serviços e interfaces), não de implementações concretas. Isso facilita testes unitários e substituição de implementações.

---

## Aplicação dos Princípios SOLID

| Princípio | Como foi aplicado na versão refatorada |
|-----------|----------------------------------------|
| **SRP (Single Responsibility Principle)** | Cada classe tem uma responsabilidade clara: `App` inicializa, `AppMenu` controla a interface, serviços contêm regras de negócio e repositórios lidam com dados. |
| **OCP (Open/Closed Principle)** | O sistema pode ser estendido sem modificar classes existentes, por exemplo ao adicionar novas regras de negócio ou novos tipos de persistência. |
| **LSP (Liskov Substitution Principle)** | Repositórios implementam interfaces, permitindo trocar implementações sem quebrar o sistema. |
| **ISP (Interface Segregation Principle)** | Interfaces específicas por entidade evitam dependências desnecessárias em métodos que não são usados. |
| **DIP (Dependency Inversion Principle)** | Classes de alto nível dependem de abstrações (interfaces e serviços), não de implementações concretas. |

---

## Benefícios da Refatoração
Maior manutenibilidade – mudanças em cadastro ou aplicação de provas não afetam a inicialização nem a UI.
Testabilidade – cada serviço e aplicação de prova pode ser testada isoladamente.
Clareza de responsabilidades – código organizado por funcionalidade, seguindo princípios de design.
Redução de acoplamento – dependências injetadas via construtor (App → AppMenu), facilitando futuras melhorias ou migração de persistência.

---

## Estrutura do Projeto


br.com.ucsal.olimpiadas
│
├── App.java
├── AppMenu.java
│
├── participante
│ ├── Participante.java
│ ├── ParticipanteService.java
│ ├── ParticipanteRepository.java
│ └── ParticipanteRepositoryInterface.java
│
├── prova
│ ├── Prova.java
│ ├── ProvaService.java
│ ├── ProvaRepository.java
│ ├── ProvaRepositoryInterface.java
│ └── AplicacaoProva.java
│
├── questao
│ ├── Questao.java
│ ├── QuestaoService.java
│ ├── QuestaoRepository.java
│ └── QuestaoRepositoryInterface.java
│
├── resposta
│ ├── Resposta.java
│ ├── RespostaService.java
│ ├── RespostaRepository.java
│ └── RespostaRepositoryInterface.java
│
├── tentativa
│ ├── Tentativa.java
│ ├── TentativaService.java
│ ├── TentativaRepository.java
│ └── TentativaRepositoryInterface.java
│
├── tabuleiro
│ └── TabuleiroPrinter.java
│
└── test
└── java
└── br.com.ucsal.olimpiadas
└── ExemploTest.java

---

## Fluxo de Execução da Aplicação

A versão refatorada separa claramente a responsabilidade de inicialização, interface e regras de negócio. O fluxo de execução funciona da seguinte forma:

        +----------------+
        |      App       |
        | (main apenas)  |
        +----------------+
                |
                v
   instancia serviços + repositórios
                |
                v
        +----------------+
        |    AppMenu     |
        | (interface CLI)|
        +----------------+
        |        |
        |        v
        |  chama serviços
        |        |
        v        v
+------------------------+
|        Services        |
| (regra de negócio)     |
| ParticipanteService    |
| ProvaService           |
| QuestaoService         |
| RespostaService        |
| TentativaService       |
+------------------------+
        |
        v
+------------------------+
|      Repositories      |
| (persistência em memória) |
+------------------------+

---

## Explicação do Fluxo

## App
Responsável apenas por iniciar a aplicação.
Cria instâncias de serviços e injeta dependências.

---

## AppMenu
Atua como camada de interface (CLI).
Recebe comandos do usuário e delega ações aos serviços.

---

## Services
Contêm regras de negócio.
Exemplo: cadastrar participante, aplicar prova, calcular nota.

---

## Repositories
Responsáveis por armazenar e recuperar dados em memória.
Implementados via interfaces, permitindo substituição futura (ex: banco de dados).

## Resultado Arquitetural

---

Com essa estrutura, o sistema evoluiu de um modelo monolítico (tudo em uma classe) para uma arquitetura em camadas:

UI (AppMenu)
   ↓
Services (regras de negócio)
   ↓
Repositories (dados)




