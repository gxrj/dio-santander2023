package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.util.UUID;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.ClienteRepository;

@Service
public class ClienteService {
 
    private final ClienteRepository repository;
    // Todo: injetar passwordEncoder

    public ClienteService( ClienteRepository  repository ) {
        this.repository = repository;
    }

    public Cliente criar( Cliente cliente ) {
        return repository.save( cliente );
    }

    public Cliente encontrarPorId( UUID id ) {
        return repository.findById( id ).orElse( null );
    }

    public Cliente encontrarPorLogin( String login ) {
        return repository.findByLogin( login ).orElse( null );
    }

    public Cliente encontrarPorCpf( String cpf ) {
        return repository.findByCpf( cpf ).orElse( null );
    }

    public Cliente editar( UUID id, Cliente clienteAtualizado ) {
        return repository.findById( id )
                    .map( 
                        el -> {
                            el.setNome( clienteAtualizado.getNome() );
                            el.setTelefones( clienteAtualizado.getTelefones() );
                            el.setEndereco( clienteAtualizado.getEndereco() );
                            el.setSenha( clienteAtualizado.getSenha() );
                            return repository.save( el );
                         } )
                    .orElse( null );
    }

    public boolean deletar( UUID id ) {
        return repository.findById( id )
                .map( 
                    el -> { 
                        repository.delete( el );
                        return true;
                    } )
                .orElse( false );
    }
}
