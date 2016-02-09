package kg.jarkyn.cobspecserver.doubles;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDouble extends ThreadPoolExecutor {
    private boolean executed;
    private Runnable runnable;
    private int timesExecuted;

    public ExecutorServiceDouble() {
        super(10, 10, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
    }

    @Override
    public void execute(Runnable command) {
        timesExecuted++;
        runnable = command;
        executed = true;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public int getTimesExecuted() {
        return timesExecuted;
    }

    public boolean hasExecuted() {
        return executed;
    }
}
