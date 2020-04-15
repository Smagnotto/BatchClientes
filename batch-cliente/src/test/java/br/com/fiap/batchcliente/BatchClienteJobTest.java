package br.com.fiap.batchcliente;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BatchCliente.class, BatchClienteConfigurationTest.class})
public class BatchClienteJobTest  {

	@Autowired
    private JobLauncherTestUtils testUtils;

    @Autowired
    private BatchConfiguration config;

    @Autowired
    private JobCompletionNotificationListener listener;

    @Autowired
    private Step step;

    @Test
    public void testJob() throws Exception {
        //arrange
        //act
        final JobExecution result = testUtils.getJobLauncher().run(config.NotificationUser(listener, step), testUtils.getUniqueJobParameters());
        
        //result
        Assert.assertNotNull(result);
        Assert.assertEquals(BatchStatus.COMPLETED, result.getStatus());
    }

}
