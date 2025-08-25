import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

public class Main {

  static String[] coveted = { "" };
  static String[] sails = {
    "aimbot.exe",
    "wallhack.exe",
    "wallhax.exe",
    "esp.exe",
    "triggerbot.exe",
    "multihack.exe",
    "multihax.exe",
    "speedhack.exe",
    "speedhax.exe",
    "hax.exe",
    "hacks.exe",
    "hacktool.exe",
    "haxtool.exe",
    "modmenu.exe",
    "trainer.exe",
    "no_recoil.exe",
    "cronus.exe",
    "silentaim.exe",
    "xim.exe",
    "esp_hack.exe",
    "esp_hax.exe",
    "wireshark.exe",
    "proxo.exe",
    "mitproxy.exe",
    "nmap.exe",
    "charles.exe",
    "fiddler.exe",
    "sandboxie.exe",
    "vmware.exe",
    "vbox.exe",
    "hwidspoofer.exe",
    "macchanger.exe",
    "spoofer.exe",
    "darkcomet.exe",
    "nanocore.exe",
    "keylogger.exe",
    "stealer.exe",
    "rat.exe",
    "malware.exe",
  };
  static ArrayList<String> quill = new ArrayList<>();
  static ArrayList<String> scope = new ArrayList<>();
  static ArrayList<String> tarots = new ArrayList<>();
  static ArrayList<String> syringe = new ArrayList<>();
  static ArrayList<String> boatFlag = new ArrayList<>();
  static ArrayList<String> inSails = new ArrayList<>();
  static ArrayList<String> seaMines = new ArrayList<>();
  static ArrayList<String> pirate = new ArrayList<>();
  static boolean pirateDetects = false;
  static int errcode = 0;

  public static void main(String[] args) {
    if (args.length > 0) {
      System.out.println("Charybdis launched by: " + args[0]);
      coveted[0] = args[0];
    } else {
      System.out.println("Poseidon does not control Charybdis");
      coveted[0] = "null parent/ran locally";
    }
    tampered();
    //runCharybdis();
  }

  private static void tampered() {
    try {
      URI uri = new URI("https://leechlab.github.io/Charybdis/");
      URL url = uri.toURL();
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setConnectTimeout(3000);
      conn.connect();
      if (conn.getResponseCode() == 200) {
        String localFile = Files.readString(Paths.get("Main.java"));
        String githubUrl =
          "https://raw.githubusercontent.com/LeechLab/Charybdis/refs/heads/main/Main.java";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(githubUrl))
          .build();
        HttpResponse<String> response = client.send(
          request,
          HttpResponse.BodyHandlers.ofString()
        );
        String remoteFile = response.body();
        if (localFile.equals(remoteFile)) {
          runCharybdis();
        } else {
          System.out.println("Update available, launching updater...");
          new ProcessBuilder("java", "-jar", "Updater.jar")
            .directory(new File("."))
            .inheritIO()
            .start();
          System.exit(0);
        }
      } else {
        errcode = 2;
        System.out.println("Couldn't connect to internet");
        createWindow();
      }
    } catch (IOException | InterruptedException | URISyntaxException e) {
      errcode = 2;
      System.out.println("Couldn't attempt to connect to internet");
      createWindow();
    }
  }

  private static void createWindow() {
    JFrame frame = new JFrame("Charybdis");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setUndecorated(true);
    frame.setSize(640, 480);
    Image ficon = Toolkit.getDefaultToolkit().getImage("images/favicon.png");
    frame.setIconImage(ficon);
    final Color green = new Color(0, 255, 166);
    Font major;
    try {
      major = Font.createFont(
        Font.TRUETYPE_FONT,
        new File("fonts/MajorMonoDisplay-Regular.ttf")
      ).deriveFont(Font.PLAIN, 14f);
      GraphicsEnvironment ge =
        GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(major);
    } catch (IOException | FontFormatException e) {
      System.out.println("Could not load font");
      major = new Font("Monospaced", Font.PLAIN, 14);
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(
      (screenSize.width - frame.getWidth()) / 2,
      (screenSize.height - frame.getHeight()) / 2
    );
    JLayeredPane layered = new JLayeredPane();
    layered.setPreferredSize(new Dimension(640, 480));
    GifPlayer gifPanel = new GifPlayer("images/chargif.gif");
    gifPanel.setBounds(0, 0, 640, 480);
    layered.add(gifPanel, JLayeredPane.DEFAULT_LAYER);

    final JLabel claim = new JLabel("");
    claim.setFont(major);
    claim.setForeground(Color.BLACK);
    claim.setBounds(10, 50, 540, 30);
    layered.add(claim, JLayeredPane.PALETTE_LAYER);
    final JLabel help = new JLabel("");
    help.setFont(major);
    help.setForeground(Color.WHITE);
    help.setBounds(10, 65, 400, 480);
    layered.add(help, JLayeredPane.PALETTE_LAYER);
    String claimText = "";
    String helpText = "";
    if (errcode == 1) {
      claimText = "charybdis spies treachery upon these waters...";
      helpText =
        "a cheat has been detected running on your computer during charybdis's scan. a report(id:00000000) has been filed by parent: <u>" +
        coveted[0] +
        "</u> at " +
        LocalDateTime.now()
          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
        ". you can find information about this report at <u>------------</u>. you can dispute this claim at <u>------------</u>.";
    }
    if (errcode == 2) {
      claimText = "charybdis is severed from the threads of aether...";
      helpText =
        "charybdis scan canceled due to inability to connect to the internet. possible errors include: connection to server has timed out; server is down; a firewall has blocked connection; no valid network route to host; ssl/tls certificate issue; security manger forbids network access.";
    }
    final String cText =
      "<html><body style='width: 540px;'>" + claimText + "</body></html>";
    claim.setVerticalAlignment(SwingConstants.TOP);
    help.setVerticalAlignment(SwingConstants.TOP);
    new Thread(() -> typewrite(claim, cText)).start();
    final String hTextContent = helpText;
    final String hText =
      "<html><body style='width: 320px;'>" + hTextContent + "</body></html>";
    new Thread(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        System.out.println("Typewriter couldn't wait");
      }
      typewrite(help, hText);
    }).start();
    ImageIcon icon = new ImageIcon("images/logo.png");
    JLabel label = new JLabel(icon);
    label.setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());
    layered.add(label, JLayeredPane.PALETTE_LAYER);
    ImageIcon cicon = new ImageIcon("images/close.png");
    ImageIcon cicon2 = new ImageIcon("images/mouse-close.png");
    JLabel close = new JLabel(cicon);
    close.setBounds(
      630 - cicon.getIconWidth(),
      10,
      cicon.getIconWidth(),
      cicon.getIconHeight()
    );
    close.addMouseListener(
      new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
          close.setIcon(cicon2);
          close.repaint();
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
          System.exit(0);
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
          close.setIcon(cicon);
          close.repaint();
        }
      }
    );
    layered.add(close, JLayeredPane.PALETTE_LAYER);
    final float[] phase = { 0f };
    final int[] bitrate = { 0 };
    new javax.swing.Timer(15, e -> {
      bitrate[0]++;
      if (bitrate[0] > 100) {
        bitrate[0] = 0;
      }
      if (bitrate[0] % 10 == 0) {
        phase[0] += 0.05f;
        if (phase[0] > 2f) phase[0] = 0f;
      }
      float t = (phase[0] <= 1f) ? phase[0] : (2f - phase[0]);
      int r = (int) (t * green.getRed());
      int g = (int) (t * green.getGreen());
      int b = (int) (t * green.getBlue());
      claim.setForeground(new Color(r, g, b));
      claim.repaint();
    }).start();
    help.setForeground(Color.WHITE);
    frame.setContentPane(layered);
    frame.pack();
    frame.setVisible(true);
  }

  private static void typewrite(JLabel label, String htmlText) {
    StringBuilder currentText = new StringBuilder();
    boolean insideTag = false;
    for (int i = 0; i < htmlText.length(); i++) {
      char ch = htmlText.charAt(i);
      currentText.append(ch);
      if (ch == '<') insideTag = true;
      if (ch == '>') insideTag = false;

      final String display = currentText.toString();
      if (!insideTag) {
        SwingUtilities.invokeLater(() -> label.setText(display));
        try {
          Thread.sleep((int) (Math.random() * 85));
        } catch (InterruptedException ex) {
          label.setText(htmlText);
        }
      }
    }
    SwingUtilities.invokeLater(() -> label.setText(htmlText));
  }

  static class GifPlayer extends JPanel {

    private final ArrayList<BufferedImage> frames = new ArrayList<>();
    private int currentFrame = 0;
    private Timer timer = null;

    public GifPlayer(String path) {
      try {
        ImageInputStream stream = ImageIO.createImageInputStream(
          new File(path)
        );
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(
          "gif"
        );
        if (!readers.hasNext()) throw new RuntimeException(
          "No GIF reader found"
        );
        ImageReader reader = readers.next();
        reader.setInput(stream);

        int numFrames = reader.getNumImages(true);
        for (int i = 0; i < numFrames; i++) {
          frames.add(reader.read(i));
        }

        if (!frames.isEmpty()) {
          setPreferredSize(
            new Dimension(frames.get(0).getWidth(), frames.get(0).getHeight())
          );
        }

        timer = new Timer(100, e -> {
          currentFrame = (currentFrame + 1) % frames.size();
          repaint();
        });
        timer.start();
      } catch (IOException | RuntimeException e) {
        System.out.println("Failed to load image");
      }
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (!frames.isEmpty()) {
        g.drawImage(
          frames.get(currentFrame),
          0,
          0,
          getWidth(),
          getHeight(),
          null
        );
      }
    }

    public void stop() {
      timer.stop();
    }

    public void start() {
      timer.start();
    }
  }

  private static void runCharybdis() {
    ArrayList<String> boats = new ArrayList<>();
    try {
      ProcessBuilder builder = new ProcessBuilder(
        "powershell.exe",
        "-Command",
        "Get-Process | Select-Object -ExpandProperty Path"
      );
      Process process = builder.start();
      BufferedReader sea = new BufferedReader(
        new InputStreamReader(process.getInputStream())
      );
      String strand;
      while ((strand = sea.readLine()) != null) {
        strand = strand.trim();
        if (!strand.isEmpty() && !strand.equalsIgnoreCase("ExecutablePath")) {
          boats.add(strand);
        }
      }
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      System.out.println("Charybdis is sleeping");
    }
    if (!boats.isEmpty()) {
      for (int captain = 0; captain < boats.size(); captain++) {
        for (int nextCaptain = 0; nextCaptain < boats.size(); nextCaptain++) {
          if (
            boats.get(captain).equals(boats.get(nextCaptain)) &&
            captain != nextCaptain
          ) {
            boats.remove(captain);
          }
        }
      }
    }
    for (int boat = 2; boat < boats.size(); boat++) {
      File map = new File(boats.get(boat));
      if (map.exists()) {
        checkInventory(map, boat);
      } else {
        System.out.println("No vessel(" + boat + ") inventory in sight");
      }
    }
    for (int boat = 2; boat < boats.size(); boat++) {
      File map = new File(boats.get(boat));
      if (map.exists()) {
        getVessel(map, boat);
      } else {
        System.out.println("No vessel(" + boat + ") in sight");
      }
    }
    try {
      String exeFile = "CharybdisScan.exe";
      ProcessBuilder runBuilder = new ProcessBuilder(exeFile);
      runBuilder.redirectErrorStream(true);
      Process runProcess = runBuilder.start();
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(runProcess.getInputStream())
      );
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println("Quote from Tentacle " + line);
      }

      int exitCode = runProcess.waitFor();
      System.out.println("Tentacle punctured with quote: " + exitCode);
    } catch (IOException | InterruptedException e) {
      System.out.println("Tentacle couldn't puncture: " + e.getMessage());
    }
    for (String shipPath : seaMines) {
      File stern = new File(shipPath);
      if (stern.exists()) {
        destroyStern(stern);
      }
    }
    if (pirateDetects) {
      errcode = 1;
      createWindow();
    }else{
      try{
        System.out.println("No pirates detected — exiting now.");
        System.exit(0);
      } catch (Exception e) {
      }
    }
  }

  public static void checkInventory(File ship, int boat) {
    String shipCourse = ship.getAbsolutePath();
    for (String sail : sails) {
      if (shipCourse.toLowerCase().contains(sail)) {
        inSails.add(shipCourse);
      }
    }
    try {
      byte[] shipInventory = Files.readAllBytes(Path.of(shipCourse));
      StringBuilder boatSwain = new StringBuilder();
      StringBuilder chef = new StringBuilder();
      for (byte item : shipInventory) {
        char c = (char) (item & 0xFF);
        if ((c >= 32 && c <= 126) || c == '\n' || c == '\t') {
          boatSwain.append(c);
        } else {
          if (chef.length() > 4) {
            boatSwain.append(chef).append("\n");
          }
          chef.setLength(0);
        }
      }
      for (String sea : coveted) {
        if (boatSwain.toString().toUpperCase().contains(sea)) {
          pirate.add(shipCourse);
        }
      }
      if (boatSwain.toString().toLowerCase().contains("readprocessmemory")) {
        scope.add(shipCourse);
      }
      if (boatSwain.toString().toLowerCase().contains("writeprocessmemory")) {
        quill.add(shipCourse);
      }
      if (boatSwain.toString().toLowerCase().contains("openprocess")) {
        tarots.add(shipCourse);
      }
      if (
        boatSwain.toString().toLowerCase().contains(".dll") ||
        boatSwain.toString().toLowerCase().contains("dllinject")
      ) {
        syringe.add(shipCourse);
      }
      if (
        boatSwain.toString().toUpperCase().contains("PROCESS_VM_WRITE") ||
        boatSwain.toString().toUpperCase().contains("PROCESS_VM_READ")
      ) {
        boatFlag.add(shipCourse);
      }
      if (
        boatSwain.toString().toUpperCase().contains("HACK") ||
        boatSwain.toString().toUpperCase().contains("CHEAT") ||
        boatSwain.toString().toUpperCase().contains("AIMBOT") ||
        boatSwain.toString().toUpperCase().contains("TRIGGERBOT")
      ) {
        seaMines.add(shipCourse);
      }
    } catch (FileNotFoundException e) {
      System.out.println(
        "Charybdis couldn't find the vessel(" + boat + ")'s inventory"
      );
    } catch (IOException e) {
      System.out.println(
        "The vessel(" + boat + ")'s inventory is invisibile to Charybdis"
      );
    }
  }

  public static void getVessel(File ship, int boat) {
    String shipCourse = ship.getAbsolutePath();
    try {
      byte[] shipInventory = Files.readAllBytes(Path.of(shipCourse));
      StringBuilder boatSwain = new StringBuilder();
      StringBuilder chef = new StringBuilder();
      for (byte item : shipInventory) {
        char c = (char) (item & 0xFF);
        if ((c >= 32 && c <= 126) || c == '\n' || c == '\t') {
          boatSwain.append(c);
        } else {
          if (chef.length() > 4) {
            boatSwain.append(chef).append("\n");
          }
          chef.setLength(0);
        }
      }
      if (
        boatSwain.toString().toUpperCase().contains("PROCESS_VM_WRITE") ||
        boatSwain.toString().toUpperCase().contains("PROCESS_VM_READ")
      ) {
        System.out.println(shipCourse + " CHEAT");
      }
    } catch (FileNotFoundException e) {
      System.out.println("Charybdis couldn't find the vessel(" + boat + ")");
    } catch (IOException e) {
      System.out.println("The vessel(" + boat + ") snuck past Charybdis");
    }
  }

  private static void destroyStern(File ship) {
    try {
      ProcessBuilder cppProcess = new ProcessBuilder(
        "CharybdisScan.exe",
        ship.getName()
      );
      Process process = cppProcess.start();

      BufferedReader reader = new BufferedReader(
        new InputStreamReader(process.getInputStream())
      );
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
        if (line.toLowerCase().contains("Pirate on board")) {
          pirateDetects = true;
        }
      }
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      System.out.println("The ship was a mirage " + ship.getName());
    }
  }
}

