package pixery;

import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pixery.model.SelectorInfo;

import java.security.Timestamp;
import java.util.Random;

import static pixery.Methods.*;

public class StepImpl extends HookImpl {

    @Step("<key> Butonuna Tikla")
    public void ButtonClick (String key){
        clickButton(selector.getElementInfoToBy(key));
    }

    @Step("<key> Textine Yazdir <text>")
    public void TextSendKeys(String key, String text){

        sendKey(selector.getElementInfoToBy(key), text);
    }

    @Step("<sure> Saniye Bekle")
    public void Bekleme (int sure){

        bekleme(sure);
    }

    @Step("Ekrani Kaydir")
    public void EkranKaydir () {
        MoveToBottom(2);
    }

    @Step({"<text> değerini sayfa üzerinde olup olmadığını kontrol et."})
    public void getPageSourceFindWord(String text) {
        Assert.assertTrue(text + " sayfa üzerinde bulunamadı.",
                appiumDriver.getPageSource().contains(text));

        logger.info(text + " sayfa üzerinde bulundu");
    }

    @Step({"<key> li elementi bul, temizle ve rastgele email değerini yaz"})
    public void RandomeMail(String key){

        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue("tes" + randomNum(4) + "@gmail.com");
    }

    @Step({"<key> li elementi bul, temizle ve rastgele user değerini yaz"})
    public void RandomeUser(String key){

        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue("test" + randomNum(5));
    }

    public static String randomNum(int stringLength) {

        Random random = new Random();
        char[] chars = "1234567890".toCharArray();
        String stringRandom = "";
        for (int i = 0; i < stringLength; i++) {

            stringRandom = stringRandom + String.valueOf(chars[random.nextInt(chars.length)]);
        }

        return stringRandom;
    }

    public MobileElement findElementByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);

        MobileElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElement(selectorInfo.getBy())
                     : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Element not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElement;
    }

}
