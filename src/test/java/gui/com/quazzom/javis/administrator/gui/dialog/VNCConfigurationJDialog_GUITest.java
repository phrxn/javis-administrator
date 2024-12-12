package gui.com.quazzom.javis.administrator.gui.dialog;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.VNCProgramConfigurationJDialog;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.lang.LanguageIdiom;
import com.quazzom.javis.administrator.vnc.VNCProgramConfiguration;

public class VNCConfigurationJDialog_GUITest {

  @Test
  public void testVNCConfigurationJDialog() throws InterruptedException {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.PT_BR);

    SwingMediator mockSwingMediador = Mockito.mock(SwingMediator.class);
    when(mockSwingMediador.getJFrameAdministratorFrame()).thenReturn(null);

    VNCProgramConfiguration vncConfig = new VNCProgramConfiguration();

    VNCProgramConfigurationJDialog vncDialog =
        new VNCProgramConfigurationJDialog(null, mockSwingMediador, vncConfig);

    while (vncDialog.isVisible()) {
      Thread.sleep(1000L);
    }
  }
}
