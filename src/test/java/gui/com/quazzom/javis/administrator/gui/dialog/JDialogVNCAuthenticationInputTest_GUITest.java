package gui.com.quazzom.javis.administrator.gui.dialog;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogVNCAuthenticationInput;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.lang.LanguageIdiom;
import com.quazzom.javis.administrator.vnc.VNCAuthentication;

public class JDialogVNCAuthenticationInputTest_GUITest {

  @Test
  public void testVNCConfigurationJDialog() throws InterruptedException {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    SwingMediator mockSwingMediador = Mockito.mock(SwingMediator.class);
    when(mockSwingMediador.getJFrameAdministratorFrame()).thenReturn(null);

    VNCAuthentication ultraAu = new VNCAuthentication();

    JDialogVNCAuthenticationInput jDialogVNCAuthentication =
        new JDialogVNCAuthenticationInput(mockSwingMediador, ultraAu);

    jDialogVNCAuthentication.createJDialog();

    while (jDialogVNCAuthentication.isVisible()) {
      Thread.sleep(1000L);
    }
  }
}
