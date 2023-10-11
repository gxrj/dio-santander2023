package me.dio.gxrj.desafiofinalbackenddiosantander2023.service;


import java.util.UUID;

import org.springframework.stereotype.Service;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.ClienteRepository;

@Service
public class ClienteService {
 
    private final ClienteRepository repository;

    public ClienteService( ClienteRepository  repository ) {
        this.repository = repository;
    }

    public void salvar( Cliente cliente ) {
        repository.save( cliente );
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

    public void deletar( Cliente cliente ) {
        repository.delete( cliente );
    }

}
