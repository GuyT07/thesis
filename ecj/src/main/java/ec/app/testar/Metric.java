package ec.app.testar;

public class Metric {

    public static final String[] headers = {
            "Sequence", // Sequence
            "Duration", // Time it took to execute sequence
            "States", // # of abstract states visited
            "Actions", // # of actions executed
            "UniqueStates", // # of unique states visited
            "UniqueActions", // # of unique actions executed
            "NotFoundActions", // # of actions that were selected but not found (eg. click action is selected, only type actions are available)
            "IrregularActions",
            "Severity"
    };

    private int sequenceNo;
    private long sequenceDuration;
    private int visitedStates;
    private int executedActions;
    private int uniqueStates;
    private int uniqueActions;
    private int notFoundActions;
    private int irregularActions;
    private double severity;

    Metric(final int sequenceNo, final long sequenceDuration, final int visitedStates, final int executedActions, final int uniqueStates, final int uniqueActions, final int notFoundActions, final int irregularActions, final double severity) {
        this.sequenceNo = sequenceNo;
        this.sequenceDuration = sequenceDuration;
        this.visitedStates = visitedStates;
        this.executedActions = executedActions;
        this.uniqueStates = uniqueStates;
        this.uniqueActions = uniqueActions;
        this.notFoundActions = notFoundActions;
        this.irregularActions = irregularActions;
        this.severity = severity;
    }

    int getSequenceNo() {
        return sequenceNo;
    }

    long getSequenceDuration() {
        return sequenceDuration;
    }

    int getVisitedStates() {
        return visitedStates;
    }

    int getExecutedActions() {
        return executedActions;
    }

    int getUniqueStates() {
        return uniqueStates;
    }

    int getUniqueActions() {
        return uniqueActions;
    }

    int getNotFoundActions() {
        return notFoundActions;
    }

    int getIrregularActions() {
        return irregularActions;
    }

    double getSeverity() {
        return this.severity;
    }
}
