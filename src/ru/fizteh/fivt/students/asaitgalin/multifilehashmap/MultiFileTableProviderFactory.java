package ru.fizteh.fivt.students.asaitgalin.multifilehashmap;

import ru.fizteh.fivt.students.asaitgalin.multifilehashmap.extensions.ChangesCountingTableProvider;
import ru.fizteh.fivt.students.asaitgalin.multifilehashmap.extensions.ChangesCountingTableProviderFactory;

import java.io.File;

public class MultiFileTableProviderFactory implements ChangesCountingTableProviderFactory {
    @Override
    public ChangesCountingTableProvider create(String dir) {
        if (dir == null || dir.isEmpty()) {
            throw new IllegalArgumentException("factory: directory name is null");
        }
        return new MultiFileTableProvider(new File(dir));
    }
}
