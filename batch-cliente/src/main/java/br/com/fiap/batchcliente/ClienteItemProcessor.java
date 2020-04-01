package br.com.fiap.batchcliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.com.fiap.batchcliente.Pojo.ClientePojo;

/**
 * ClienteItemProcessor
 */
public class ClienteItemProcessor implements ItemProcessor<ClientePojo, ClientePojo> {

    private static final Logger logger = LoggerFactory.getLogger(ClienteItemProcessor.class);

    @Override
    public ClientePojo process(ClientePojo item) throws Exception {
        final String nome = item.getNome().trim().toUpperCase();

        final ClientePojo clientTransformed = new ClientePojo();
        clientTransformed.setNome(nome);

        logger.info("Convertendo (" + item + ") em (" + clientTransformed + ")");

        return clientTransformed;
    }
    
}