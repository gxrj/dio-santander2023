package set;

public class Contato implements Comparable<Contato> {
    private String nome;
    private int numero;

    Contato( String nome, int numero ) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() { return nome; }
    public int getNumero() { return numero; }
    public void setNome( String nome ) {
        this.nome = nome;
    }
    public void setNumero( int numero ) {
        this.numero = numero;
    }
    @Override 
    public int compareTo( Contato contato ) {
        return nome.compareTo( contato.nome );
    }
}