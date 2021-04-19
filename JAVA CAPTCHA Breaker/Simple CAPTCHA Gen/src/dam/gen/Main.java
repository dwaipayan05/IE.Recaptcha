package dam.gen;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.*;
import nl.captcha.gimpy.*;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends JPanel {
    public void paint(Graphics g) {
        Captcha captcha = new Captcha.Builder(152, 80)
                .addText(new NumbersAnswerProducer(), new CustomWordRenderer())
                //.gimp(new BlockGimpyRenderer())
                .gimp(new FishEyeGimpyRenderer())
                .addBackground(new FlatColorBackgroundProducer(Color.WHITE))
                //.addBackground(new GradiatedBackgroundProducer())
                //.addBackground(new SquigglesBackgroundProducer())
                .addNoise(new CurvedLineNoiseProducer(Color.black, 2))
                .build();

        Image img = captcha.getImage();
        g.drawImage(img, 20, 20, this);
    }

    public static void main(String[] args) throws IOException {
//        JFrame frame = new JFrame();
//        frame.getContentPane().add(new Main());
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 300);
//        frame.setVisible(true);

      generateDatasets("imgs", 10);
      generateDatasets("test_imgs", 10);
    }

    public static void generateDatasets(String folder, long datasetSize) throws IOException {
//        OutputStream f = new FileOutputStream("data");

        Files.createDirectories(Paths.get(folder));

        for (int i = 0; i < datasetSize; i++) {
            Captcha captcha = new Captcha.Builder(152, 80)
                    .addText(new CustomWordRenderer())
                    //.gimp(new BlockGimpyRenderer())
                    .gimp(new FishEyeGimpyRenderer())
                    //.addBackground(new SquigglesBackgroundProducer())
                    .addBackground(new FlatColorBackgroundProducer(Color.WHITE))
                    //.addBackground(new GradiatedBackgroundProducer())
                    .addNoise(new CurvedLineNoiseProducer(Color.black, 2))
                    .build();

//            byte[] b = captcha.getAnswer().getBytes(StandardCharsets.US_ASCII);
//            byte[] pixels = ((DataBufferByte) captcha.getImage().getRaster().getDataBuffer()).getData();

            File outputfile = new File(folder + "/" + captcha.getAnswer() + ".jpg");
            ImageIO.write(captcha.getImage(), "jpg", outputfile);

//            f.write(b);
//            f.write(pixels);
//            for (int i1 = 0; i1 < pixels.length; i1++) {
//
//            }
//
//            for (byte sb :
//                    pixels) {
//                System.out.println(Byte.toUnsignedInt(sb));
//            }

        }
//        f.flush();
//        f.close();
    }
}
