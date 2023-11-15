package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.BaseClass;

public class PressPage extends BaseClass {
	
	@Test
	public void companyTimeliness() throws InterruptedException {
		WebElement press=driver.findElement(By.xpath("//a[text()='Press']"));
		WebElement company=driver.findElement(By.xpath("//a[text()='Company Timeline']"));
		
		Actions act = new Actions(driver);
		act.moveToElement(press).click(company).build().perform();
		
		List<WebElement> timelines=driver.findElements(By.xpath("//section[@aria-label='Sky timeline part 1']//h2"));
		boolean found2022 = false;
		for(WebElement timeline: timelines)
		{
			if(timeline.getText().equals("2022"))
			{
				Assert.assertTrue(true);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView();", timeline);
				Thread.sleep(2000);
				found2022 = true;
                break;
			}		
		}
		
		 Assert.assertTrue(found2022, "Timeline with text '2022' not found");
	}
	
}
