# 📖 Estudo de Relacionamento entre Classes (Livro e Pessoa)

Projeto em Java focado em entender e praticar **relacionamento entre classes** na Orientação a Objetos. O sistema simula a leitura de um livro por uma pessoa, e usa esse cenário simples para explorar como objetos diferentes podem se referenciar e se comunicar entre si.

## 🎯 Objetivo

O foco principal aqui **não é o livro em si**, mas sim **como as classes se conectam**. O projeto explora dois tipos de relacionamento:

- **Associação direta (`Livro` → `Pessoa`)**: o livro guarda uma referência permanente ao seu leitor como atributo.
- **Acoplamento via parâmetro (`Pessoa` → `Livro`)**: a pessoa não guarda o livro como atributo, mas recebe uma referência a ele apenas quando precisa interagir, através de parâmetro de método.

Além disso, o projeto usa uma **interface (`Publicacao`)** para entender como uma classe se relaciona com um contrato/abstração, e não apenas com outra classe concreta.

## 🧩 Estrutura do Projeto

```
.
├── Publicacao.java   # Interface com o contrato de comportamento
├── Livro.java         # Implementação concreta de Publicacao
├── Pessoa.java        # Representa o leitor do livro
└── Main.java          # Classe com o método main para testes
```

## 🔍 Descrição das Classes

### `Publicacao` (interface)

Define o contrato que qualquer publicação (livro, revista, etc.) deve seguir:

- `abrir()`
- `fechar()`
- `folhear()`
- `avancarPagina()`
- `voltarPagina()`

Esse é o terceiro tipo de relacionamento explorado no projeto: `Livro implements Publicacao` é um **relacionamento de realização** (a classe concreta se compromete a cumprir um contrato), diferente da associação entre objetos vista entre `Livro` e `Pessoa`. Aqui não existe uma referência a um objeto guardada em atributo — existe uma promessa de comportamento. Qualquer classe que implemente `Publicacao` é obrigada a fornecer esses cinco métodos, o que permite no futuro relacionar outras classes (como `Revista`) ao mesmo contrato, sem que elas tenham relação direta entre si.

### `Livro`

Implementa `Publicacao` e representa um livro físico com estado próprio (aberto/fechado, página atual). O ponto central para este estudo é o atributo `leitor`:

```java
private Pessoa leitor;
```

Isso é um **relacionamento de associação (1 para 1)**: cada `Livro` mantém uma referência fixa a um objeto `Pessoa`, recebida no construtor. Essa referência é guardada durante todo o ciclo de vida do livro, e é usada dentro do próprio `Livro` (no método `detalhes()`) para acessar dados da pessoa, como o nome:

```java
leitor.getNome()
```

Ou seja, o `Livro` **conhece** a `Pessoa` e pode chamar métodos dela diretamente, mas não o contrário (a `Pessoa` não guarda o `Livro` como atributo — veja a seção sobre a classe `Pessoa` abaixo).

Demais atributos:

- `livro`: título do livro
- `autor`: autor do livro
- `totPaginas`: total de páginas
- `pagAtual`: página em que o leitor está
- `aberto`: estado do livro (aberto ou fechado)

Comportamentos:

- **`abrir()` / `fechar()`**: alternam o estado do livro, evitando ações redundantes (ex.: abrir um livro já aberto).
- **`folhear()`**: abre o livro automaticamente se estiver fechado, e pula para uma página aleatória usando `java.util.Random`.
- **`avancarPagina()` / `voltarPagina()`**: navegam pelas páginas, respeitando os limites (não passa da última página, não volta antes da primeira) e abrindo o livro automaticamente quando necessário.
- **`detalhes()`**: imprime um resumo do livro, incluindo quem está lendo.

### `Pessoa`

Representa o leitor. Diferente do `Livro`, a `Pessoa` **não guarda uma referência a `Livro` como atributo**. Em vez disso, o relacionamento acontece de forma temporária, via **parâmetro de método**:

```java
public void detalhesPessoais(Livro livroAtual) {
    int pagina = livroAtual.getPagAtual();
    ...
}
```

Esse é um tipo de relacionamento mais fraco que a associação direta usada em `Livro`: a `Pessoa` só "conhece" o `Livro` durante a execução desse método específico, e não mantém esse vínculo depois que o método termina. É um bom contraste para visualizar duas formas diferentes de uma classe se relacionar com outra: **por atributo** (vínculo permanente) vs. **por parâmetro** (vínculo temporário, usado sob demanda).

Atributos:

- `nome`
- `idade`
- `sexo`

Comportamentos:

- **`fazerAniversario()`**: incrementa a idade da pessoa.
- **`detalhesPessoais(Livro livroAtual)`**: imprime informações da pessoa e em qual página do livro ela está, demonstrando a interação entre as duas classes.

### `Main`

Classe de testes que:

1. Cria uma `Pessoa`.
2. Cria um `Livro` associado a essa pessoa.
3. Exibe os detalhes do livro.
4. Folheia o livro (página aleatória).
5. Avança uma página.
6. Exibe os detalhes pessoais do leitor, incluindo a página atual.

## ▶️ Como Executar

Compile e execute as classes com o JDK instalado:

```bash
javac *.java
java Main
```

> ```java
> public static void main(String[] args) {
>     ...
> }
> ```

## 💡 Tipos de Relacionamento Explorados

| Relacionamento | Onde aparece | Característica |
|---|---|---|
| **Associação (por atributo)** | `Livro` → `Pessoa` (`private Pessoa leitor`) | Vínculo permanente, definido no construtor e mantido durante todo o ciclo de vida do objeto. |
| **Acoplamento via parâmetro** | `Pessoa.detalhesPessoais(Livro livroAtual)` | Vínculo temporário, que existe apenas durante a execução do método. |
| **Realização (interface)** | `Livro implements Publicacao` | Contrato de comportamento, sem referência direta a um objeto — a classe se compromete a fornecer certos métodos. |
| **Encapsulamento** | Todas as classes | Atributos privados acessados por getters/setters, o que protege o estado interno mesmo quando há referências externas a esses objetos. |


---

*Projeto desenvolvido para fins de estudo em Java e Orientação a Objetos.*
