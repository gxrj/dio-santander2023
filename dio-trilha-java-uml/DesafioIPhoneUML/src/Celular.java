public interface Celular 
                    extends 
                        AparelhoTelefonico, 
                            ReprodutorMusical, 
                                NavegadorInternet {
 
    void reproduzirVideo();
    void tirarFoto();
    void visualizarFoto();
    void deletarFoto();
    void acessarCalendario();
}
