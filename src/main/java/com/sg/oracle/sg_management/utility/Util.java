package com.sg.oracle.sg_management.utility;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Util
{
	
	private static final String PACKAGE_NAME = "com.sg.utility";
	private static final String FILE_NAME_GENERAL = "sistem_hata_kayit";
	private static final String FILE_NAME_EXCEPTION = "sistem_hata_kayit_istisna";
	private static final String LOG_PATH = "C:/sg/log/" + FILE_NAME_GENERAL;
	private static final String LOG_PATH_EXCEPTION = "D:/sg/log/" + FILE_NAME_EXCEPTION;
	private static final int TCKN_HANE = 11;

	private Util() {}

	// http://ahmet.butun.kim/index.php/2016/10/12/java-ile-tc-kimlik-numarasi-kontrolu-ve-dogrulanmasi
	private static boolean isValidTCKN(Long TCKN)
	{
		String tmp = TCKN.toString();

		if (tmp.length() == TCKN_HANE)
		{
			int totalOdd = 0;

			int totalEven = 0;

			for (int i = 0; i < 9; i++) {
				int val = Integer.valueOf(tmp.substring(i, i + 1));

				if (i % 2 == 0) {
					totalOdd += val;
				} else {
					totalEven += val;
				}
			}

			int total = totalOdd + totalEven
					+ Integer.valueOf(tmp.substring(9, 10));

			int lastDigit = total % 10;

			if (tmp.substring(10).equals(String.valueOf(lastDigit)))
			{
				int check = (totalOdd * 7 - totalEven) % 10;

				if (tmp.substring(9, 10).equals(String.valueOf(check)))
				{
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isValidTCKN(String TCKN)
	{
		if (null!=TCKN && TCKN.matches("^([1-9]{1}[0-9]{10})$"))
		{
			return isValidTCKN(Long.valueOf(TCKN));
		}

		return false;
	}

	public static <E> void showList(List<E> list)
	{
		for (E entity : list) 
		{
			System.out.println(entity);
		}
	}
	
	public static void createLogFile(Level level, String message)
	{
		Logger loggerGeneral = Logger.getLogger(PACKAGE_NAME);
		Logger loggerException = Logger.getLogger(PACKAGE_NAME);
		
		FileHandler fileHandlerGeneral = null;
		FileHandler fileHandlerException = null;
		try
		{
			fileHandlerGeneral = new FileHandler(LOG_PATH);
			fileHandlerException = new FileHandler(LOG_PATH_EXCEPTION);
			
			loggerGeneral.addHandler(fileHandlerGeneral);
			loggerGeneral.log(level, message);
		}
		catch (SecurityException e) 
		{
			
			loggerException.addHandler(fileHandlerException);
			loggerException.log(level, createGeneralException(e));
		}
		catch (IOException e) 
		{
			loggerException.addHandler(fileHandlerException);
			loggerException.log(level, createGeneralException(e));
		}
	}
	
	
	public static String createGeneralException(Exception e)
	{
		return e.getClass().getSimpleName() 
				+ " is occured. Error message: " + e.getMessage();
	}
	
}
