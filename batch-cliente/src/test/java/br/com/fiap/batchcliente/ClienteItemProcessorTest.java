package br.com.fiap.batchcliente;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.fiap.batchcliente.Pojo.ClientePojo;

@RunWith(SpringRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class})
@ContextConfiguration(classes = {BatchCliente.class, BatchClienteConfigurationTest.class})
public class ClienteItemProcessorTest {


    @Autowired
    private ClienteItemProcessor processor;

    public StepExecution getStepExecution() {
        return MetaDataInstanceFactory.createStepExecution();
    }

    @Test
    public void testProcessor() throws Exception {
        //arrange
        final ClientePojo cliente = new ClientePojo();
        cliente.setNome("nome teste    ");

        //act
        ClientePojo clientProcess = processor.process(cliente);

        //result
        Assert.assertEquals(clientProcess.getNome(), "NOME TESTE" );
    }

}