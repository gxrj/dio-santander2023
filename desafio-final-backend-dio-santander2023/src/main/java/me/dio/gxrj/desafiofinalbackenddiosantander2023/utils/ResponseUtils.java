package me.dio.gxrj.desafiofinalbackenddiosantander2023.utils;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResponseUtils {
    public static <E, ID> ResponseEntity<E> prepararPostResponse( E elemento, ID idElemento, HttpStatus status ) {

        if( elemento == null || idElemento == null )
            return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY ).build();

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                                        .path( "/{id}" )
                                        .buildAndExpand( idElemento )
                                        .toUri();

        return ResponseEntity.status( status ).location( location ).body( elemento );
    }

    public static <E, ID> ResponseEntity<E> prepararPutResponse( E elemento, ID idElemento, HttpStatus status ) {
        return prepararPostResponse( elemento, idElemento, status );
    }

    public static <E> ResponseEntity<E> prepararGetResponse( E elemento ) {
        if( elemento == null )
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();

        return ResponseEntity.status( HttpStatus.OK ).body( elemento );
    }

    public static ResponseEntity<?> prepararDeleteResponse( Boolean resultado ) {
        if( !resultado )
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();

        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }
}
