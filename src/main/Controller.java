package main;
/**
 * controller dung de dieu khien file sample.fxml
 * sample.fxml co the sua bang open screenbuider
 */

import backend.DictornaryManagement;
import java.beans.PropertyVetoException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.speech.AudioException;
import javax.speech.EngineException;
import sun.security.mscapi.CPublicKey;

public class Controller   {
  @FXML
  protected TextField Height;
  @FXML
  protected TextArea textArea;
  @FXML
  protected ListView<String> listView;
  private  DictornaryManagement dic = new DictornaryManagement();
  private AnchorPane achorpane = null;

  List<String> list = new LinkedList<String>();

  /**
   * cai nay la nhap tu vao textField
   */
  public void inputText(KeyEvent event) {
    try {
      dic.insertFromFile();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    String word_Input = Height.getText();
    if (word_Input != null)
    listView.getItems().setAll(dic.dictionarySearcher(word_Input));
    if (dic.searhWord(word_Input) != null) {
      textArea.setText(dic.searhWord(word_Input));
    }
    if (word_Input.equals("")) {
      textArea.clear();
    }
  }
  /**
   * cai nay dung chuot chon tu nhung tu goi y
   */
  @FXML
  public void HandleMouseListWord (MouseEvent event) {
    String word = listView.getSelectionModel().getSelectedItem();
    if (word.equals("")) return;
   else{ Height.setText(word);
    textArea.setText(dic.searhWord(word));
   }
  }
  public void HandleButtonSpeech(ActionEvent event)
      throws PropertyVetoException, AudioException, EngineException, InterruptedException {
    String word = Height.getText();
    TextToSpeech textToSpeech = new TextToSpeech();
    textToSpeech.init("kevin16");
    textToSpeech.doSpeak(word);
  }
}
