import com.formdev.flatlaf.FlatDarkLaf;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

  public static void main(String[] args) {
    new Gui().update();
    new Gui().makeGui();
  }


  static class Gui extends JFrame {
    JLabel jLabel = new JLabel();
    JPanel jPanel = new JPanel();
    JTextField jTextField = new JTextField("Please Enter HWID",50);
    JButton jButton = new JButton("Post");
    public void makeGui() {
      setTitle("HWID ADDER");
      setVisible(true);
      setSize(600, 300);

      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      jPanel.add(jTextField);
      jTextField.addActionListener(e -> {
        String input = jTextField.getText();
        jLabel.setText("Now click Post");
      });

      jPanel.add(jButton);
      jButton.addActionListener(e -> {
        String input = jTextField.getText();
        Post post = new Post();
        try {
          post.sendPost(input);
          jLabel.setText("Success posting HWID: " + input);
          System.out.println("Success posting HWID: " + input);
        } catch (Exception exception) {
          jLabel.setText("Error Posting HWID");
          System.out.println("Error Posting HWID");
          exception.printStackTrace();
        }
      });
      jPanel.add(jLabel);
      add(jPanel);
    }

    public void update() {
      try {
        UIManager.setLookAndFeel(new FlatDarkLaf());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  static class Post {
    private void sendPost(String hwid) throws Exception {

      String url = "https://example.com/auth.php?hwid=" + hwid;

      HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

      //add request header
      httpClient.setRequestMethod("POST");
      httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
      httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

      String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

      // Send post request
      httpClient.setDoOutput(true);
      try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
        wr.writeBytes(urlParameters);
        wr.flush();
      }

      try (BufferedReader in = new BufferedReader(
          new InputStreamReader(httpClient.getInputStream()))) {

        String line;
        StringBuilder response = new StringBuilder();

        while ((line = in.readLine()) != null) {
          response.append(line);
        }

      }

    }

  }
}