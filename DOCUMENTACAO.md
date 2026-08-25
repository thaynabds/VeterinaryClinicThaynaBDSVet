# Documentacao tecnica - ThaynaBDSVet

## Visao geral

Sistema de cadastro em Java puro (sem bibliotecas extras) para a loja **ThaynaBDSVet**.

Camadas:

1. `App` - menu
2. `controllers` - Scanner e mensagens
3. `services` - regras de negocio
4. `repositories` - lista estatica (banco simulado)
5. `models` - entidades

O Controller nao chama o Repository.

## Modelos

### Animal

Campos obrigatorios no cadastro: `nome`, `documentoTutor`, `especie`.

Outros campos: raca, sexo, pelagem, idade, historico de vacinas, alergias, condicoes de saude, castrado, observacoes, anotacoes (texto livre), lista de IDs de tutores.

Tres construtores:

- vazio
- cadastro (`nome`, `documentoTutor`, `especie`) com `++contador`
- completo (com `id`)

### Tutor

Campos obrigatorios: `nomeCompleto` e `documento`.

Tambem tem endereco, lista de contatos (criterio livre) e lista de animais (sem limite).

### Setor

Nome obrigatorio e descricao.

Ao iniciar o programa, oito setores da loja ja sao cadastrados.

### Produto

Nome, valor, quantidade e setor. Valor e quantidade nao podem ser negativos.

## Retornos

| Operacao | Retorno |
|----------|---------|
| cadastrar | objeto criado ou `null` |
| listar / busca por filtro | `List` |
| findById | objeto ou `null` |
| atualizar / deletar | `boolean` |

## Pesquisas extras

- Tutor: por nome e por documento
- Animal: por nome, especie e documento do tutor
- Setor: por nome
- Produto: por nome, setor e faixa de valor

## Validacoes no Service

- Tutor: nome e documento obrigatorios
- Animal: nome, documento do tutor e especie obrigatorios
- Setor: nome obrigatorio
- Produto: nome obrigatorio; valor e quantidade >= 0
- Faixa de valor: min <= max e nenhum negativo

## Relacionamentos

- Um tutor tem varios animais
- Um animal pode estar na ficha de varios tutores
- Um setor agrupa varios produtos (pelo nome do setor)

## Limitacao

Os dados existem so enquanto o programa esta aberto.
