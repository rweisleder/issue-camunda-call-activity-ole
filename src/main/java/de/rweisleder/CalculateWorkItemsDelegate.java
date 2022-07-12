package de.rweisleder;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CalculateWorkItemsDelegate implements JavaDelegate {

    private static final int WORK_COUNT = 50;

    @Override
    public void execute(DelegateExecution execution) {
        List<Integer> workItems = IntStream.rangeClosed(1, WORK_COUNT).boxed().collect(toList());
        execution.setVariable("workItems", workItems);
    }
}
