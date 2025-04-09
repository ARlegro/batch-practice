package com.bus.springbatch.config;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ExecutionContextTasklet1 implements Tasklet {

    // 목표
    // 인자로 넘어온 StepContribution을 이용하여 StepExecution을 가져온다.
    // 그 후, ExecutionContext객체를 가져와서 활용하기
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Step1 입니다");
        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();

        // Job, Step 이름 꺼내기 : 이거 말고도 다양한 방법이 존재
        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();
        Thread.sleep(3000);
        return RepeatStatus.FINISHED;
    }
}
