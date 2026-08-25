# ThaynaBDSVet - Sistema de Cadastro

Sistema de cadastro para pet shop, feito em **Java** com arquitetura em camadas (Controller, Service, Repository e Model).

O nome da loja e **ThaynaBDSVet**.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Status](https://img.shields.io/badge/status-active-success)

## Sobre o projeto

O ThaynaBDSVet gerencia:

- Tutores com varios contatos (criterio livre)
- Animais na ficha do tutor, sem limite de quantidade
- Vinculo de um animal com mais de um tutor
- Setores da loja (os principais ja vem cadastrados)
- Produtos por setor, com valor e quantidade

Produto principal da loja: o **animal** (pet cadastrado na ficha do tutor), junto com o cadastro de **produtos** da loja.

## Arquitetura

```
App (menu no console)
    -> Controllers (Scanner)
        -> Services (regras de negocio)
            -> Repositories (List estatica)
                -> Models (entidades)
```

O Controller nao acessa o Repository. Cada camada so fala com a vizinha.

## Como executar

### Pre-requisitos

- Java 8 ou superior

### Compilar

```bash
cd src
javac models/*.java repositories/*.java services/*.java controllers/*.java App.java
```

### Rodar

```bash
java App
```

Os dados ficam em memoria. Quando o programa fecha, o cadastro e perdido.

## Menu

Ao iniciar, os setores da loja ja aparecem cadastrados.

**Tutores**

| Opcao | Acao |
|------:|------|
| 1 | Cadastrar tutor |
| 2 | Cadastrar animal na ficha do tutor |
| 3 | Vincular animal existente a outro tutor |
| 4 | Listar tutores |
| 5 | Buscar tutor por ID |
| 6 | Buscar tutor por nome |
| 7 | Atualizar tutor |
| 8 | Adicionar contato |
| 9 | Deletar tutor |

**Animais**

| Opcao | Acao |
|------:|------|
| 10 | Cadastrar animal |
| 11 | Listar animais |
| 12 | Buscar animal por ID |
| 13 | Buscar animal por nome |
| 14 | Buscar animal por especie |
| 15 | Atualizar animal |
| 16 | Deletar animal |

**Setores e produtos**

- Setores: opcoes 17 a 22
- Produtos: opcoes 23 a 30 (incluindo busca por setor e por faixa de valor)

## Regras de cadastro

### Animal (obrigatorio)

- Nome
- 1 documento do tutor (CPF ou RG)
- Especie

Todos os outros dados podem ser preenchidos e alterados depois: raca, sexo, pelagem, idade, vacinas, alergias, condicoes de saude, castracao, observacoes e anotacoes (texto livre).

O animal entra na ficha do tutor. Nao ha limite de animais por tutor. O mesmo animal pode ter mais de um tutor.

### Tutor

- Nome completo (obrigatorio)
- CPF ou RG (obrigatorio)
- Endereco
- Contatos em lista, com tipo livre (WhatsApp, telefone, e-mail, etc.)

### Produto

- Nome obrigatorio
- Valor e quantidade nao podem ser negativos

## Requisitos do trabalho

### Obrigatorios

- [x] Pacotes em minusculo e plural
- [x] Classe do modelo em singular e com inicial maiuscula
- [x] Construtor vazio + construtor de cadastro (contador estatico) + construtor completo (com id)
- [x] Repository com lista estatica simulando o banco
- [x] CRUD completo (Create, listar, Update e Delete)
- [x] `findById` retornando o objeto (nao void)
- [x] Funcoes com retorno adequado
- [x] Menu no `App` com Scanner e tratamento de erro de digitacao
- [x] Pesquisa extra alem do `findById` (nome, especie, setor, faixa de valor)

### Desejaveis

- [x] Validacoes de negocio no Service

## Documentacao

- [DOCUMENTACAO.md](DOCUMENTACAO.md)
- [docs/ARQUITETURA.md](docs/ARQUITETURA.md)
- [docs/GUIA_DE_USO.md](docs/GUIA_DE_USO.md)
- [docs/DIAGRAMA_CLASSES.txt](docs/DIAGRAMA_CLASSES.txt)

## Estrutura

```
ThaynaBDSVet/
├── src/
│   ├── models/
│   ├── repositories/
│   ├── services/
│   ├── controllers/
│   └── App.java
├── docs/
├── README.md
├── DOCUMENTACAO.md
├── LICENSE
└── .gitignore
```

## Licenca

MIT. Veja o arquivo [LICENSE](LICENSE).

## Sistema de cadastro da loja **ThaynaBDSVet**.

---

## 👩‍💻 Autora

<div align="center">

### Thayná Batista da Silva

<a href="https://br.linkedin.com/in/thaynabds" target="_blank">
  <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" />
</a>
<a href="https://www.instagram.com/thaynabdstec/" target="_blank">
  <img src="https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white" />
</a>
<a href="mailto:thaynabdstec@gmail.com">
  <img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" />
</a>

📧 thaynabdstec@gmail.com · 📱 +55 (81) 97912-6121

Estudante de **Análise e Desenvolvimento de Sistemas** — Faculdade Senac Recife · Previsão de formatura: 2027

<br/>

<img src="https://raw.githubusercontent.com/thaynabds/AppMedSmart/refs/heads/main/Cart%C3%A3o%20TEC%20Thayn%C3%A1%20Batista%20da%20Silva.png" alt="Cartão TEC Thayná Batista da Silva" />

</div>

---

<div align="center">
