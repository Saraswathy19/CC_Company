package testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testbase.BaseClass;

public class InvestorsPage extends BaseClass {

	@Test
	public void investors(){
		SoftAssert sa=new SoftAssert();
		WebElement investors=driver.findElement(By.linkText("Investors"));
		Actions act=new Actions(driver);
		act.moveToElement(investors).build().perform();
		
		
		List<WebElement> investAreas= driver.findElements(By.xpath("//ul[@class='cc-menu-dropdown cc-menu-accent cc-menu-accent--purple']//a"));
		int total_act_investAreas = investAreas.size();
		System.out.println("Total no of investing areas:"+total_act_investAreas);
		
		List<String> mylist=new ArrayList<String>(Arrays.asList("Financials", "Events & Presentations", "Investor News", "Corporate Governance", "ESG Reporting",
                                                                "Stock Performance", "Shareholder Services"));
        int total_exp_investAreas=mylist.size();
        System.out.println("Total no of exp industries:" +total_exp_investAreas);
        
        if(total_act_investAreas==total_exp_investAreas)
    	{
    		for(int i=0; i<mylist.size(); i++)
    		{
    			String expinvest=mylist.get(i);
    			String actinvest=investAreas.get(i).getText();
    			System.out.println("Exp:"+expinvest+ " Actual:"+actinvest);
    			sa.assertEquals(actinvest, expinvest);	
    		}
    	}
    	else
    	{
    		Assert.fail("Industry is greater/lesser than expected");
    	}

    	sa.assertAll();
	}
	
	@Test
	public void investorsDomain() throws InterruptedException{
		driver.navigate().refresh();
		HashMap <String, String>hm=new HashMap<String, String>();
		hm.put("Comcast Corporation", "https://corporate.comcast.com/");
		hm.put("Financials | Comcast Corporation", "https://www.cmcsa.com/financials");
		hm.put("Events & Presentations | Comcast Corporation", "https://www.cmcsa.com/events-and-presentations");
		hm.put("Investor News | Comcast Corporation", "https://www.cmcsa.com/investor-news");
		hm.put("Corporate Governance | Comcast Corporation", "https://www.cmcsa.com/corporate-governance");
		hm.put("ESG Reporting | Comcast Corporation", "https://www.cmcsa.com/esg-reporting");
		hm.put("Stock Performance | Comcast Corporation", "https://www.cmcsa.com/stock-performance");
		hm.put("Shareholder Services | Comcast Corporation", "https://www.cmcsa.com/shareholder-services");
		
		
		
		WebElement investors=driver.findElement(By.linkText("Investors"));
		Actions act=new Actions(driver);
		act.moveToElement(investors).build().perform();
		
		HashMap <String, String>hmacttitle=new HashMap<String, String>();
		List<WebElement> investAreas= driver.findElements(By.xpath("//ul[@class='cc-menu-dropdown cc-menu-accent cc-menu-accent--purple']//a"));
		for(WebElement investArea: investAreas)
		{
			System.out.println(investArea.getText());
			String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
			investArea.sendKeys(clicklnk);
		}
		
		Set<String> winids=driver.getWindowHandles();
		for(String id: winids)
		{
			String pageTitle=driver.switchTo().window(id).getTitle();
			Thread.sleep(3000);
			String currentUrl= driver.getCurrentUrl();
			//System.out.println(pageTitle+": "+currentUrl);
			hmacttitle.put(pageTitle, currentUrl);		
		}
		
		System.out.println(hmacttitle);
		System.out.println(hm);
		
		SoftAssert sa=new SoftAssert();
		
		
		if(hmacttitle.equals(hm)) 
		{
			sa.assertTrue(true);
		}
		else 
		{
			System.out.println("I entered");
			for (Map.Entry<String, String> entry : hm.entrySet()) { 
	            String key = entry.getKey();  
	            String value1 = entry.getValue(); 
	            String value2 = hmacttitle.get(key); 

	            if (value2 == null || !value1.equals(value2)) {
	                //System.out.println("Key: " + key + " | Value in map1: " + value1 + " | Value in map2: " + value2);
	                sa.fail("Key: " + key + " | Value in map1: " + value1 + " | Value in map2: " + value2);
	            }
	         }
			
			for (String extrakey : hmacttitle.keySet()) {
				System.out.println(extrakey);
			    if (!hm.containsKey(extrakey)) {
			        sa.fail("Extra entry in UI - Key: " + extrakey + " | Value: " + hmacttitle.get(extrakey));
			    }
			}
			sa.assertAll();
        }
	}
}
