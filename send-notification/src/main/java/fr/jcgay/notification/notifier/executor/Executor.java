package fr.jcgay.notification.notifier.executor;

public interface Executor {
    Process exec(String[] command);
}
