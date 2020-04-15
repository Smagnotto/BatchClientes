package br.com.fiap.batchcliente;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BatchCliente.class, BatchClienteConfigurationTest.class})
public class BatchClienteStepTest  {

	@Autowired
    private JobLauncherTestUtils testUtils;

    @Test
    public void testStep() {
        Assert.assertEquals(BatchStatus.COMPLETED, testUtils.launchStep("Upload Clientes").getStatus());
    }

}
