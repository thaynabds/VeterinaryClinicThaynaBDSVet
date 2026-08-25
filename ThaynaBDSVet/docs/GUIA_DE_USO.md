# Guia de uso - ThaynaBDSVet

## Compilar e executar

```bash
cd src
javac models/*.java repositories/*.java services/*.java controllers/*.java App.java
java App
```

## Passo a passo sugerido

1. Liste os setores (opcao 18). Eles ja vem cadastrados.
2. Cadastre um tutor (opcao 1): nome, documento, endereco e contatos.
3. Cadastre um animal na ficha do tutor (opcao 2).
4. Confira a ficha com buscar tutor por ID (opcao 5).
5. Cadastre um produto (opcao 23) usando o nome de um setor.

## Cadastro de tutor

```
Nome completo: Maria Silva
Documento (CPF/RG): 123.456.789-00
Endereco: Rua das Flores, 123
Quantos contatos deseja cadastrar? 2
Contato 1: WhatsApp: 98765-4321
Contato 2: Telefone: 3333-3333
```

## Cadastro de animal na ficha

Informe o ID do tutor. Nome e especie sao obrigatorios. O documento do tutor e preenchido automaticamente.

Depois o sistema pergunta se o animal tambem pertence a outro tutor.

## Atualizar dados

Nas opcoes de atualizar, quase todos os campos podem ser mudados. No tutor e no animal, Enter mantem o valor atual.

## Erros comuns

- Digitar letra quando o sistema pede numero: aparece "Digite um numero valido".
- Deixar nome ou documento vazio: o Service recusa o cadastro.
- Valor de produto negativo: o Service recusa o cadastro.

## Observacao

Os dados nao sao gravados em arquivo. Feche o programa so quando terminar o teste.
