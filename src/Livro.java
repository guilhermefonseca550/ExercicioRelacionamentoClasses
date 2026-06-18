import java.util.Random;

public class Livro implements Publicacao {
    private String livro;
    private String autor;
    private int totPaginas;
    private int pagAtual;
    private boolean aberto;
    private Pessoa leitor;

    public Livro(String livro, String autor, int totalPag, Pessoa leitor){
        this.leitor = leitor;
        this.livro = livro;
        this.autor = autor;
        this.totPaginas = totalPag;
        setPagAtual(0);
        setAberto(false);
    }

    public void detalhes(){
        System.out.println("O livro " + getLivro() + " foi escrito por "
        + getAutor() + " e conta com " + getTotPaginas() + " páginas" +
                " e está sendo lido por " + leitor.getNome());

    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getTotPaginas() {
        return totPaginas;
    }

    public void setTotPaginas(int totPaginas) {
        this.totPaginas = totPaginas;
    }

    public int getPagAtual() {
        return pagAtual;
    }

    public void setPagAtual(int pagAtual) {
        this.pagAtual = pagAtual;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public Pessoa getLeitor() {
        return leitor;
    }

    public void setLeitor(Pessoa leitor) {
        this.leitor = leitor;
    }


    @Override
    public void abrir() {
        if(!this.aberto){
            setAberto(true);
            System.out.println("Abrindo o livro...");
        }else{
            System.out.println("O livro já está aberto!");
        }
    }

    @Override
    public void fechar() {
        if(aberto){
            setAberto(false);
            System.out.println("Fechando o livro...");
        }else{
            System.out.println("O livro já está fechado!");
        }
    }

    @Override
    public void folhear() {
        if(aberto){
        Random aleatorio = new Random();
        int pagAleatoria = aleatorio.nextInt(totPaginas);
            setPagAtual(pagAleatoria);
        System.out.println("Página " + getPagAtual());
    }else{
            System.out.println("Abrindo o livro...");
            setAberto(true);
            Random aleatorio = new Random();
            int pagAleatoria = aleatorio.nextInt(totPaginas);
            setPagAtual(pagAleatoria);
            System.out.println("Página " + getPagAtual());
        }
    }

    @Override
    public void avancarPagina() {
        if(this.aberto && this.pagAtual < this.totPaginas){
            setPagAtual(getPagAtual() + 1);
        }else if(!this.aberto && this.pagAtual < this.totPaginas){
            System.out.println("Abrindo seu livro...");
            setAberto(true);
            setPagAtual(getPagAtual() + 1);
        }

        else if(this.aberto && this.pagAtual >= this.totPaginas){
            System.out.println("Você já está na última página!");
        }
        else if(!this.aberto && this.pagAtual >= this.totPaginas){
            System.out.println("Abrindo seu livro...");
            setAberto(true);
            System.out.println("Você já está na última página!");
    }
    }


    @Override
    public void voltarPagina() {
        if(this.aberto && this.pagAtual > 0){
            setPagAtual(getPagAtual() - 1);
        }else{
            System.out.println("Abra o livro e avance de página primeiro :p");
        }
    }
}
