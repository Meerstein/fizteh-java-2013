package ru.fizteh.fivt.students.ermolenko786.shell;

import java.io.IOException;

public interface Command<State> {

    public String getName();

    public void executeCmd(State inState, String[] args) throws IOException;
}
