package mn.digz6666.gm4java;

import java.io.File;
import java.io.IOException;
import org.gm4java.engine.GMException;
import org.gm4java.engine.GMServiceException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MethoD
 */
public class ImageUtilTest {

    ImageUtil imageUtil = new ImageUtil();

    private String workingDirectory;
    private final String inputFile = "input.jpg";
    private final String outputOptimize = "output_optimize.jpg";
    private final String outputResize = "output_resize.jpg";
    private final String outputWatermark = "output_watermark.jpg";

    private final String inputFilePng = "input.png";
    private final String outputOptimizePng = "output_optimize.png";
    private final String outputWatermarkPng = "output_watermark.png";

    @Before
    public void init() {
        imageUtil.init();
        workingDirectory = new File(ClassLoader.getSystemResource("test_files").getFile()).getAbsolutePath();
    }

    @Test
    public void test() throws GMServiceException, GMException, IOException {
        System.out.println(imageUtil.optimize(
                    workingDirectory + File.separator + inputFile,
                    75,
                    workingDirectory + File.separator + outputOptimize));

        System.out.println(imageUtil.optimize(
                    workingDirectory + File.separator + inputFilePng,
                    75,
                    workingDirectory + File.separator + outputOptimizePng));

        System.out.println(imageUtil.resize(
                    workingDirectory + File.separator + inputFile,
                    1280, 720,
                    true, 70,
                    workingDirectory + File.separator + outputResize));

        // bottom
        System.out.println(imageUtil.applyWatermark(
                    workingDirectory + File.separator + inputFile,
                    new File(ClassLoader.getSystemResource("test_files/watermark_bottom.png").getFile()).getAbsolutePath(),
                    "+30+20",
                    "SouthEast",
                    true, 70,
                    workingDirectory + File.separator + outputWatermark));

        // center
        System.out.println(imageUtil.applyWatermark(
                    workingDirectory + File.separator + outputWatermark,
                    new File(ClassLoader.getSystemResource("test_files/watermark_center.png").getFile()).getAbsolutePath(),
                    "+0+0",
                    "Center",
                    true, 70,
                    workingDirectory + File.separator + outputWatermark));

        // bottom
        System.out.println(imageUtil.applyWatermark(
                    workingDirectory + File.separator + inputFilePng,
                    new File(ClassLoader.getSystemResource("test_files/watermark_bottom.png").getFile()).getAbsolutePath(),
                    "+30+20",
                    "SouthEast",
                    true, 70,
                    workingDirectory + File.separator + outputWatermarkPng));

        // center
        System.out.println(imageUtil.applyWatermark(
                    workingDirectory + File.separator + outputWatermarkPng,
                    new File(ClassLoader.getSystemResource("test_files/watermark_center.png").getFile()).getAbsolutePath(),
                    "+0+0",
                    "Center",
                    true, 70,
                    workingDirectory + File.separator + outputWatermarkPng));
    }
}
