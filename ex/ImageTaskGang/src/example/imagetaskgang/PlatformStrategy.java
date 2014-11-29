package example.imagetaskgang;

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/** 
 * @class PlatformStrategy
 *
 * @brief Provides methods that define a platform-independent
 *        mechanism for ... This class is a singleton that also plays
 *        the role of the "Strategy" in the Strategy pattern and the
 *        Product in the Factory Method pattern.  Both the
 *        PlatformStrategyConsole and PlatformStrategyAndroid
 *        subclasses extend this class.
 */
public abstract class PlatformStrategy {
    /** 
     * The singleton @a PlatformStrategy instance. 
     */
    private static PlatformStrategy sUniqueInstance = null;

    /** 
     * Method to return the one and only singleton instance. 
     */
    public static PlatformStrategy instance() {
        return sUniqueInstance;
    }

    /** 
     * Method that sets a new PlatformStrategy singleton and returns the one
     * and only singleton instance.
     */
    public static PlatformStrategy instance(PlatformStrategy platform) {
        return sUniqueInstance = platform;
    }

    /**
     * Return the path for the directory where images are stored.
     */
    public abstract String getDirectoryPath();

    /**
     * Factory method that creates an @a Image from a byte array.
     */
    public abstract Image makeImage(byte[] imageData);

    /**
     * Apply a grayscale filter to the @a inputEntity and return it.
     */
    public abstract InputEntity applyGrayscaleFilter(InputEntity inputEntity);

    /**
     * Store the @a image in the given @outputFile.
     */
    public abstract void storeImage(Image image,
                                    FileOutputStream outputFile);

    /**
     * Error log formats the message and displays it for the debugging
     * purposes.
     */
    public abstract void errorLog(String javaFile,
                                  String errorMessage);
    
    public static enum InputSource {
    	DEFAULT, USER, FILE, ERROR
    }
    
    public abstract Iterator<List<URL>> getUrlIterator(InputSource source);
    
    protected List<List<URL>> getDefaultList() throws MalformedURLException {
    	final List<List<URL>> variableNumberOfInputURLs = 
                new ArrayList<List<URL>>();

        final URL[] urls1 = {        
            new URL("http://www.mariowiki.com/images/thumb/1/19/GoldMushroomNSMB2.png/200px-GoldMushroomNSMB2.png"),
            new URL("http://png-1.findicons.com/files/icons/2297/super_mario/256/mushroom_life.png")
        };
        final URL[] urls2 = {
            new URL("http://img4.wikia.nocookie.net/__cb20080812195802/nintendo/en/images/1/12/1upshroom.png"),
            new URL("http://www.mariowiki.com/images/thumb/5/57/Powerup-mini-mushroom-sm.png/200px-Powerup-mini-mushroom-sm.png"),
            new URL("http://a66c7b.medialib.glogster.com/media/92/92a90af3755a6e3de9faad540af216bc3cdd7839add09a7735c22844b725d55b/propeller-mushroom-jpg.jpg")
        };
        variableNumberOfInputURLs.add(Arrays.asList(urls1));
        variableNumberOfInputURLs.add(Arrays.asList(urls2));

    	return variableNumberOfInputURLs;
    }
    
    public InputSource getInputSource(String inputSource) {
		if (inputSource.equalsIgnoreCase("DEFAULT")) {
			return InputSource.DEFAULT;
		}
		else if (inputSource.equalsIgnoreCase("USER")) {
			return InputSource.USER;
		}
		else if (inputSource.equalsIgnoreCase("FILE")) {
			return InputSource.FILE;
		}
		else {
			return InputSource.ERROR;
		}
	}

    /**
     * Make the constructor protected to ensure singleton access.
     */
    protected PlatformStrategy() {}
}
