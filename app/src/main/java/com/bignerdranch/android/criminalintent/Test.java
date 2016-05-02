package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nbens_000 on 5/2/2016.
 */
public class Test {
    public static void main(String[]args){

        //Scanner scan = new Scanner(System.in);

        GregorianCalendar start = new GregorianCalendar(2016, 3, 28);
        GregorianCalendar end = new GregorianCalendar(2015, 3, 28);

        ArrayList<StockDownloader> SD = new ArrayList<StockDownloader>();

		/*System.out.println("Enter all your ticker symbols that you have! Type 0 when done");
		String x = scan.nextLine();
		while(!x.equals("0")){
			SD.add(new StockDownloader(x, start, end));
			x = scan.nextLine();
		}
		*/

        StockDownloader ibm_SD = new StockDownloader("IBM", start, end);
        StockDownloader goog_SD = new StockDownloader("GOOG", start, end);
        StockDownloader fb_SD = new StockDownloader("FB", start, end);
        StockDownloader aapl_SD = new StockDownloader("AAPL", start, end);
        StockDownloader fis_SD = new StockDownloader("FIS", start, end);
        StockDownloader bud_SD = new StockDownloader("BUD", start, end);


        ArrayList<String> overweight = new ArrayList<String>();
        ArrayList<String> underweight = new ArrayList<String>();



        overweight.add(ibm_SD.getTicker());
        overweight.add(fb_SD.getTicker());
        overweight.add(fis_SD.getTicker());

        underweight.add(goog_SD.getTicker());
        underweight.add(aapl_SD.getTicker());
        underweight.add(bud_SD.getTicker());


        Map<String, ArrayList<Double>> map = new HashMap<String, ArrayList<Double>>();
        map.put(ibm_SD.getTicker(), ibm_SD.getAdjCloses());
        map.put(goog_SD.getTicker(), goog_SD.getAdjCloses());
        map.put(fb_SD.getTicker(), fb_SD.getAdjCloses());
        map.put(aapl_SD.getTicker(), aapl_SD.getAdjCloses());
        map.put(bud_SD.getTicker(), bud_SD.getAdjCloses());
        map.put(fis_SD.getTicker(), fis_SD.getAdjCloses());


        Algorithm n = new Algorithm(overweight, underweight, map);

    }
}
