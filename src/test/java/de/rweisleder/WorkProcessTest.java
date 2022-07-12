package de.rweisleder;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.processInstanceQuery;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;

@ExtendWith(ProcessEngineExtension.class)
class WorkProcessTest {

    @Test
    @Deployment(resources = "work-with-error.bpmn")
    @StdIo
    void process_with_error_should_fail(StdOut stdOut) throws Exception {
        runTest(stdOut);
    }

    @Test
    @Deployment(resources = "work-without-error.bpmn")
    @StdIo
    void process_without_error_should_succeed(StdOut stdOut) throws Exception {
        runTest(stdOut);
    }

    private void runTest(StdOut stdOut) throws Exception {
        runtimeService().startProcessInstanceByKey("StartWorkProcess");

        waitForProcessInstancesToComplete();

        assertThat(stdOut.capturedLines()).noneSatisfy(line -> {
            assertThat(line).contains("OptimisticLockingException");
        });
    }

    private void waitForProcessInstancesToComplete() throws Exception {
        while (processInstanceQuery().count() > 0) {
            Thread.sleep(100);
        }
    }
}
