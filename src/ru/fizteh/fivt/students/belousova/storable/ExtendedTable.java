package ru.fizteh.fivt.students.belousova.storable;

import ru.fizteh.fivt.storage.structured.Table;

public interface ExtendedTable extends Table {
    int getChangesCount();

    boolean isClosed();
}
