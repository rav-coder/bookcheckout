package sait.bms.application;

import java.io.IOException;

import sait.bms.managers.RetailManager;
/**
 * 
 * Description: Runs our application
 *
 * @author: YunZe (David) Wei, Saurav Adhikari, Rafael Oporto 
 * @version: Feb 11/2021
 */
public class AppDriver {

	public static void main(String[] args) throws IOException 
	{
		new RetailManager();
	}

}
