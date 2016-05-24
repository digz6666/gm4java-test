package mn.digz6666.gm4java;

import java.io.File;
import java.io.IOException;
import org.gm4java.engine.GMConnection;
import org.gm4java.engine.GMException;
import org.gm4java.engine.GMServiceException;
import org.gm4java.engine.support.GMConnectionPoolConfig;
import org.gm4java.engine.support.PooledGMService;

/**
 *
 * @author MethoD
 */
public class ImageUtil {

    private PooledGMService service;

    public void init() {
        GMConnectionPoolConfig config = new GMConnectionPoolConfig();
        config.setMaxActive(3);
        //System.out.println("min idle: " + config.getMinIdle());
        //System.out.println("max idle: " + config.getMaxIdle());
        //System.out.println("max active: " + config.getMaxActive());
        //System.out.println("max wait: " + config.getMaxWait());
        service = new PooledGMService(config);
    }

    public String optimize(String input, int quality, String output) throws GMServiceException, GMException, IOException {
        GMConnection connection = service.getConnection();
        return connection.execute(
                "convert",
                input,
                "-auto-orient",
                "-quality",
                "" + quality,
                "-compress",
                "LZMA",
                output);
    }

    public String resize(String input, int width, int height, boolean optimize, int quality, String output) throws GMServiceException, GMException, IOException {
        GMConnection connection = service.getConnection();
        if (optimize) {
            return connection.execute(
                    "convert",
                    "-size",
                    width + "x" + height,
                    input,
                    "-auto-orient",
                    "-resize",
                    width + "x" + height,
                    "-quality",
                    "" + quality,
                    "-compress",
                    "LZMA",
                    output);
        } else {
            return connection.execute(
                    "convert",
                    "-size",
                    width + "x" + height,
                    input,
                    "-auto-orient",
                    "-resize",
                    width + "x" + height,
                    "-compress",
                    "LZMA",
                    output);
        }
    }

    public String applyWatermark(String input, String watermarkPath, String offset, String gravity, boolean optimize, int quality, String output)
            throws GMServiceException, GMException, IOException {
        GMConnection connection = service.getConnection();
        if (optimize) {
            return connection.execute(
                    "composite",
                    "-geometry",
                    offset, // +10+10
                    "-gravity",
                    gravity, // SouthEast
                    watermarkPath,
                    input,
                    "-quality",
                    "" + quality,
                    "-compress",
                    "LZMA",
                    output);
        } else {
            return connection.execute(
                    "composite",
                    "-geometry",
                    offset, // +10+10
                    "-gravity",
                    gravity, // SouthEast
                    watermarkPath,
                    input,
                    "-compress",
                    "LZMA",
                    output);
        }       
    }
}
