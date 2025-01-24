package gui.com.quazzom.javis.administrator.gui.dialog;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.quazzom.javis.administrator.gui.SwingMediator;
import com.quazzom.javis.administrator.gui.dialog.JDialogConnectionVNC;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.net.TCPPortException;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformations;
import com.quazzom.javis.administrator.vnc.ComputerConnectionInformationsException;
import com.quazzom.javis.administrator.vnc.MutexConnectionVNC;

public class JDialogVNCAccessLoadTest_GUITest {

  @Test
  public void testJDialogVNCAccessLoad()
      throws InterruptedException, ComputerConnectionInformationsException, TCPPortException {
    SwingMediator mockSwingMediador = Mockito.mock(SwingMediator.class);
    when(mockSwingMediador.getJFrameAdministratorFrame()).thenReturn(null);

    ComputerConnectionInformations computerInfos =
        new ComputerConnectionInformations("hostname", 5900, true);

    Text theLanguageMock = Mockito.mock(Text.class);
    when(theLanguageMock.getText(anyString())).thenReturn("XXXX");
    when(theLanguageMock.getText(anyString(), anyString())).thenReturn("XXXX");

    MutexConnectionVNC mutexConnectionMock = Mockito.mock(MutexConnectionVNC.class);
    doNothing().when(mutexConnectionMock).stopConnection();

    JDialogConnectionVNC jdm = new JDialogConnectionVNC(null, true, computerInfos, theLanguageMock);
    jdm.setMutexConnectionVNCMonitor(mutexConnectionMock);
    jdm.setVisible(true);

    while (jdm.isVisible()) {
      Thread.sleep(1000L);
    }
  }
}
