package com.menu;

import java.io.IOException;
import java.util.List;

public interface MenuCommand {
    void execute(List<String> pr) throws InterruptedException, IOException;
}
