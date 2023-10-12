package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
    
    private final RestauranteRepository repository;

    public RestauranteService( RestauranteRepository repository ) {
        this.repository = repository;
    }

    public Restaurante salvar( Restaurante restaurante ) {
        return repository.save( restaurante );
    }

    public Restaurante editar( UUID id, Restaurante restaurante ) {
        return repository.findById( id )
                .map(
                    el -> {
                        el.setNome( restaurante.getNome() );
                        el.setCnpj( restaurante.getCnpj() );
                        el.setSenha( restaurante.getSenha() );
                        el.setEndereco( restaurante.getEndereco() );
                        el.setDescricao( restaurante.getDescricao() );
                        el.setHorarioFuncionamento( restaurante.getHorarioFuncionamento() );
                        return repository.save( el );
                    }
                )
                .orElse( null );
    }

    public boolean deletar( UUID id ) {
        return repository.findById( id )
                .map(
                    el -> {
                        repository.delete( el );
                        return true;
                    }
                )
                .orElse( false );
    }

    public Restaurante encontrarPorId( UUID id ) {
        return repository.findById( id ).orElse( null );
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

    public List<Restaurante> buscarRestaurantesPorBairro( String nomeBairro ) {
        return repository.findByEndereco_Bairro_Nome( nomeBairro );
    }

    public List<Restaurante> buscarRestaurantesPorCidade( String nomeCidade ) {
        return repository.findByEndereco_Bairro_Cidade_Nome( nomeCidade );
    }

    public List<Restaurante> buscarRestaurantesPorItem( Cidade cidade, String descricaoItem ) {
        return repository.findByDescricaoItemCardapio( cidade, descricaoItem );
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
