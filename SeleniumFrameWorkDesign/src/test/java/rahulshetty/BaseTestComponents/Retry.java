package rahulshetty.BaseTestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count =0;
	int maxCount = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count < maxCount)
		{
			count++;
			return true;
		}
		return false;
	}

}
