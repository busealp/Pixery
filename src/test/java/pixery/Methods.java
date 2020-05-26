package pixery;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class Methods extends HookImpl {

    public static MobileElement findElement(By by){

        return (MobileElement) appiumFluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void sendKey (By by, String text){

        clickButton(by);
        findElement(by).sendKeys(text);
    }

    public static void clickButton (By by){

        findElement(by).click();
    }

    public static void bekleme (int sure){

        try {
            Thread.sleep(sure*1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void MoveToBottom (int bolum){

        Dimension dimension = appiumDriver.manage().window().getSize();
        int height = dimension.getHeight();
        int width = dimension.getWidth();
        int x = width / bolum;
        int top_y = (int) (height * 0.80);
        int bottom_y = (int) (height * 0.20);
        System.out.println("coordinates :" + x + "  " + top_y + " " + bottom_y);
        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction.press(point(x, top_y)).waitAction(waitOptions(ofMillis(2000))).moveTo(point(x, bottom_y)).release().perform();
    }

    public static void HorizontalSlider (double x, double ex, int y) {

        Dimension size = appiumDriver.manage().window().getSize();
        System.out.println(size);

        int startX = (int) (size.width * x);
        int endX = (int) (size.width * ex);
        int startY = size.height / y;

        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction.press(point(startX, startY)).waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(endX, startY)).release().perform();
    }

    public static Object getRandomContent (List<?> list){

        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);

    }


}
