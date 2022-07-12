package de.rweisleder;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteWorkItemDelegate implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(ExecuteWorkItemDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int workItem = (int) execution.getVariable("workItem");
        log.info("Execute work item {}", workItem);
        execution.setVariable("workResult", "Result " + workItem);
    }
}
