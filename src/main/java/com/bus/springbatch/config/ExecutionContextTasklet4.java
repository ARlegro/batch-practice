package com.bus.springbatch.config;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet4 implements Tasklet {

    // 목표
    // 인자로 넘어온 StepContribution을 이용하여 StepExecution을 가져온다.
    // 그 후, ExecutionContext객체를 가져와서 활용하기
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Step4 입니다");
        return RepeatStatus.FINISHED;
    }
}
