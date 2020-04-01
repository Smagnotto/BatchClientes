package br.com.fiap.batchcliente;


import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;

import br.com.fiap.batchcliente.Pojo.ClientePojo;

/**
 * ClienteFieldSetMapper
 */
public class ClienteFieldSetMapper implements FieldSetMapper<ClientePojo> {

    @Override
    public ClientePojo mapFieldSet(FieldSet fieldSet) throws BindException {

        if (fieldSet == null)
            return null;

        ClientePojo cliente = new ClientePojo();
        cliente.setNome(fieldSet.readString("nome"));

        return cliente;
    }
}