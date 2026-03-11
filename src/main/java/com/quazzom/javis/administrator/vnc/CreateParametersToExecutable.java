package com.quazzom.javis.administrator.vnc;

import java.util.List;
import java.util.Optional;

public interface CreateParametersToExecutable {

  Optional<List<String>> createParameters(
      ComputerConnectionInformations computer, VNCProgramConfiguration vncProgramConfig);
}
