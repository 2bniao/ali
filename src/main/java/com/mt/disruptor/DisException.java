package com.mt.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class DisException implements ExceptionHandler {

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
    }

    @Override
    public void handleOnStartException(Throwable ex) {
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
    }

}
