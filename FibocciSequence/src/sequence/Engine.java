package sequence;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Engine implements Runnable {
    private Integer M;
    private Integer N;
    private final String header;
    //ToDo write the correct paths
    private static final String inputString = "Some Path\\fibonacci.png";
    private static final String outputString = "Some Path\\fibonacciMeme.png";

    public Engine(int n, int m) {
        this.M = m;
        this.N = n;
        this.header = "ФибоНачи";
    }

    @Override
    public void run() {
        Variables variables = new Variables(M, N);
        Template template = new Template(inputString, outputString);

        try {
            imageProcessing(this.header, "png", template.getInput(), template.getOutput(), variables.getM(), variables.getN());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imageProcessing(String header, String typeOfPhoto, File source, File destination, int M, int N) throws IOException {

        BufferedImage image = ImageIO.read(source);
        int imageType = getImageType(typeOfPhoto);
        //
        BufferedImage bold = new BufferedImage(image.getWidth(), image.getHeight() + (32 * (M - N + 1)), imageType);

        int n1 = 0;
        int n2 = N;
        int sum = 0;
        int count = M - 1;

        Graphics2D boldGraphics = (Graphics2D) bold.getGraphics();

        boldGraphics.drawImage(image, 1, 2, null);
        //Setting the saturation of the text
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        boldGraphics.setComposite(alpha);
        boldGraphics.setColor(Color.WHITE);
        boldGraphics.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

        FontMetrics fontMetrics = boldGraphics.getFontMetrics();

        Rectangle2D rect = fontMetrics.getStringBounds(header, boldGraphics);

        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = 30;

        boldGraphics.drawString(header, centerX, centerY);


        boldGraphics.drawString(String.format("Като зема %d дърво...", n2), 10, centerY + image.getHeight());
        drawFibonacciSequence(boldGraphics, n1, n2, sum, count, 10, centerY + image.getHeight() - 30);

        ImageIO.write(bold, typeOfPhoto, destination);

        boldGraphics.dispose();
    }

    private static int getImageType(String typeOfPhoto) {
        return "png".equalsIgnoreCase(typeOfPhoto) ?
                BufferedImage.TYPE_INT_RGB :
                BufferedImage.TYPE_INT_ARGB;
    }

    private static void drawFibonacciSequence(Graphics2D boldGraphics, int n1, int n2, int sum, int count, int centerX, int centerY) {
        centerY += 30;
        if (count > 0) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
            if (sum == 1) {
                boldGraphics.drawString(String.format("Като зема %d дърво...", sum), centerX, centerY + 30);
            } else {
                boldGraphics.drawString(String.format("Като зема %d дървета...", sum), centerX, centerY + 30);
            }

            drawFibonacciSequence(boldGraphics, n1, n2, sum, count - 1, centerX, centerY);
        }
    }
}
