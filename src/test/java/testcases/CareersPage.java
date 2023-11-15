package testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testbase.BaseClass;

public class CareersPage extends BaseClass {

	@Test
	public void searchJob() throws InterruptedException {
		
		SoftAssert sa=new SoftAssert();
		
		driver.findElement(By.linkText("Careers")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[normalize-space()='Comcast']")).click();
		driver.findElement(By.xpath("//a[text()='Search Jobs'][1]")).click();
		driver.findElement(By.id("keyword-search")).sendKeys("SDET Engineer 2");
		driver.findElement(By.id("location-search")).sendKeys("Chennai");
		driver.findElement(By.xpath("//mat-icon[normalize-space()='search']")).click();
		
		WebElement results=driver.findElement(By.id("search-results-indicator"));
		String res=results.getText();
		char finalres=res.charAt(0);
		System.out.println("Total no of jobs found for the search made:"+ finalres);
		
		Assert.assertEquals(finalres, '5');
		
		List<String> expreqIds=new ArrayList<String>(Arrays.asList("R375988", "R373309", "R368914", "R365817", "R368701"));
		
		List<WebElement> actreqIds=driver.findElements(By.xpath("//p[@class='req-id ng-star-inserted']//span"));
		
		List<String> actualIds = new ArrayList<>();
		
		for (WebElement element : actreqIds) {
            actualIds.add(element.getText());
        }
		
		
		if (expreqIds.equals(actualIds)) {
            System.out.println("Lists are equal");
        } 
		else 
		{
            System.out.println("Lists are not equal");
            js.executeScript("window.scrollBy(0,650)", "");
            // Print the elements that are not equal
            for (int i = 0; i < expreqIds.size(); i++) {
                if (!expreqIds.get(i).equals(actualIds.get(i))) {
                    sa.fail("Element at index " + i + " is not equal. Expected: " + expreqIds.get(i) + ", Actual: " + actualIds.get(i));
                }
            }
        }

		sa.assertAll();
		
	}
}
