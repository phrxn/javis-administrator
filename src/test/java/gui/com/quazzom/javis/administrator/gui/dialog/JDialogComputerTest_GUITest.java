package gui.com.quazzom.javis.administrator.gui.dialog;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import com.quazzom.javis.administrator.gui.dialog.JDialogComputer;
import com.quazzom.javis.administrator.lang.Text;
import com.quazzom.javis.administrator.lang.TextNotFoundException;
import com.quazzom.javis.administrator.vnc.Computer;
import com.quazzom.javis.administrator.vnc.Computer.PowerStatus;
import com.quazzom.javis.administrator.vnc.Computer.TypeOfLoginSession;
import com.quazzom.javis.administrator.vnc.ComputerException;
import com.quazzom.javis.administrator.vnc.IPException;

public class JDialogComputerTest_GUITest {

  public class FakeLanguageComputer implements Text {

    private Map<String, String> theTexts;

    public FakeLanguageComputer() {
      theTexts = new HashMap<String, String>();

      theTexts.put("POWEROFF", "Power Off");
      theTexts.put("POWERON", "Power On");

      theTexts.put("INVALID", "INVALID");
      theTexts.put("CONSOLE_CONNECT", "CONSOLE_CONNECT");
      theTexts.put("CONSOLE_DISCONNECT", "CONSOLE_DISCONNECT");
      theTexts.put("REMOTE_CONNECT", "REMOTE_CONNECT");
      theTexts.put("REMOTE_DISCONNECT", "REMOTE_DISCONNECT");
      theTexts.put("SESSION_LOGON", "SESSION_LOGON");
      theTexts.put("SESSION_LOGOFF", "SESSION_LOGOFF");
      theTexts.put("SESSION_LOCK", "SESSION_LOCK");
      theTexts.put("SESSION_UNLOCK", "SESSION_UNLOCK");
      theTexts.put("SESSION_REMOTE_CONTROL", "SESSION_REMOTE_CONTROL");
      theTexts.put("SESSION_CREATE", "SESSION_CREATE");
      theTexts.put("SESSION_TERMINATE", "SESSION_TERMINATE");
      theTexts.put("SESSION_LIX_LOGON", "SESSION_LIX_LOGON");
    }

    @Override
    public String getText(String key, Object... args) throws TextNotFoundException {

      String text = theTexts.get(key);

      return String.format(text, args);
    }

    @Override
    public boolean containsKey(String key) {
      return theTexts.containsKey(key);
    }
  }

  public class FakeLanguageDialog implements Text {

    private Map<String, String> theTexts;

    public FakeLanguageDialog() {
      theTexts = new HashMap<String, String>();

      theTexts.put("STATUS", "Status");
      theTexts.put("TYPE_OF_SESSION", "Type of session");
      theTexts.put("USERNAME", "Username");
      theTexts.put("HOSTNAME", "Hostname");
      theTexts.put("IP", "IP");
      theTexts.put("JAVIS_CLIENT_VERSION", "Javis Client Version");
      theTexts.put("DETAILS", "Details");
      theTexts.put("OK", "OK");
      theTexts.put("CANCEL", "Cancel");
    }

    @Override
    public String getText(String key, Object... args) throws TextNotFoundException {

      String text = theTexts.get(key);

      return String.format(text, args);
    }

    @Override
    public boolean containsKey(String key) {
      return theTexts.containsKey(key);
    }
  }

  @Test
  public void testJDialogComputerCreate() throws InterruptedException {

    FakeLanguageComputer flc = new FakeLanguageComputer();
    FakeLanguageDialog fld = new FakeLanguageDialog();

    JDialogComputer dialog = new JDialogComputer(null, null, null, flc, fld);

    while (dialog.isVisible()) {
      Thread.sleep(1000L);
    }
  }

  @Test
  public void testJDialogComputerUpdate()
      throws InterruptedException, ComputerException, IPException {
    FakeLanguageComputer flc = new FakeLanguageComputer();
    FakeLanguageDialog fld = new FakeLanguageDialog();

    Computer computerUpdate = new Computer();
    computerUpdate.setId(20);
    computerUpdate.setPowerStatus(PowerStatus.POWERON);
    computerUpdate.setSessionType(TypeOfLoginSession.REMOTE_DISCONNECT);
    computerUpdate.setUsername("test name");
    computerUpdate.setHostname("test Hostname");
    computerUpdate.setManual(true);
    computerUpdate.setDetails("test Details");

    JDialogComputer dialog = new JDialogComputer(computerUpdate, null, null, null, flc, fld);

    while (dialog.isVisible()) {
      Thread.sleep(1000L);
    }
  }
}
