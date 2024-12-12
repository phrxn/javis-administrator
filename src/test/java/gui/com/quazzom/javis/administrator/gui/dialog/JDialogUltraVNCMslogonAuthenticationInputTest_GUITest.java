package gui.com.quazzom.javis.administrator.gui.dialog;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogUltraVNCMslogonAuthenticationInput;
import com.quazzom.javis.administrator.lang.LanguageFactory;
import com.quazzom.javis.administrator.lang.LanguageFactory.LanguageFrom;
import com.quazzom.javis.administrator.lang.LanguageIdiom;
import com.quazzom.javis.administrator.vnc.UltraVNCMslogonAuthentication;

public class JDialogUltraVNCMslogonAuthenticationInputTest_GUITest {

  @Test
  public void testVNCConfigurationJDialog() throws InterruptedException {

    // start factory
    LanguageFactory.setLanguageFrom(LanguageFrom.FILE);
    LanguageFactory.setLanguageIdiom(LanguageIdiom.EN_US);

    SwingMediator mockSwingMediador = Mockito.mock(SwingMediator.class);
    when(mockSwingMediador.getJFrameAdministratorFrame()).thenReturn(null);

    UltraVNCMslogonAuthentication ultraAu = new UltraVNCMslogonAuthentication();

    JDialogUltraVNCMslogonAuthenticationInput jDialogUltraAut =
        new JDialogUltraVNCMslogonAuthenticationInput(mockSwingMediador, ultraAu);

    jDialogUltraAut.createJDialog();

    while (jDialogUltraAut.isVisible()) {
      Thread.sleep(1000L);
    }
  }
}
