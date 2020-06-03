package pages.testDataCreator;

import config.ReadConfigFile;
import java.io.IOException;
import java.util.Random;


public class TestDataCreator {

    static Random random = new Random();
    public static String user;
    public static String rnd = String.valueOf(10 + random.nextInt(999));

    static {
        try {
            user = ReadConfigFile.getProperties("dataName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUser()
    {
        return user+rnd;
    }

    public static String getMail()
    {
        return user+rnd+"@mail.com";
    }

    public static String getPassword()
    {
        return user+rnd;
    }


}
