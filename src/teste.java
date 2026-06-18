void main() {
    Pessoa gui = new Pessoa("Gui", 24, "Masculino");

    Livro meuLivro = new Livro("As Aventuras de alguém", "Guilherme Fonseca",
            200, gui);
    meuLivro.detalhes();
    meuLivro.folhear();
    meuLivro.avancarPagina();
    gui.detalhesPessoais(meuLivro);

}

