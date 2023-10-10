package me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
    
    private final RestauranteRepository repository;

    public RestauranteService( RestauranteRepository repository ) {
        this.repository = repository;
    }

    public void salvar( Restaurante restaurante ) {
        repository.save( restaurante );
    }

    public void deletar( Restaurante restaurante ) {
        repository.delete( restaurante );
    }

    public Restaurante buscarPorCnpj( String cnpj ) {
        return repository.findByCnpj( cnpj ).orElse( null );
    }

    public Restaurante buscarPorLogin( String login ) {
        return repository.findByLogin( login ).orElse( null );
    }

    public List<Restaurante> buscarRestaurantesPorNome( String nome ) {
        return repository.findByNomeLike( nome );
    }

    public List<Restaurante> buscarRestaurantesPorBairro( Bairro bairro ) {
        return repository.findByEndereco_Bairro( bairro );
    }

    public List<Restaurante> buscarRestaurantesPorCidade( Cidade cidade ) {
        return repository.findByEndereco_Bairro_Cidade( cidade );
    }

    public List<Restaurante> buscarRestaurantesPorItem( Cidade cidade, String descricaoItem ) {
        return repository.findByDescricaoItem( cidade, descricaoItem );
    }

    public List<Restaurante> buscarAbertos( Cidade cidade ) {
        var now = ajustarDia();
        return repository.findByAbertos( cidade, now.getDayOfWeek(), now.toLocalTime() );
    }

    public List<Restaurante> buscarAbertosEmBreve( Cidade cidade ) {
        var now = ajustarDia();
        return repository
                .findByAbertosEmBreve( 
                        cidade, now.getDayOfWeek(), now.toLocalTime(), Pageable.ofSize( 5 ) );
    }

    /** 
     * Altera para o seguinte dia da semana se faltar menos de 10 minutos para virar o dia 
    */
    private LocalDateTime ajustarDia() {
        var now = LocalDateTime.now();
        var hora = now.getHour();
        var minuto = now.getMinute();
        var toleranciaParaDiaSeguinte = 60 - minuto;

        if( hora == 23 && toleranciaParaDiaSeguinte < 10 )
            now = now.plusMinutes( minuto - toleranciaParaDiaSeguinte );
        
        return now;
    }
}
