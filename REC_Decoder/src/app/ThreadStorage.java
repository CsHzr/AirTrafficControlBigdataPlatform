package app;

import java.util.ArrayList;

import decoder.Record;
import hbs.HBaseConnector;
import kfk.RECConsumer;

public class ThreadStorage implements Runnable {

	@Override
	public void run() {
		//连接HBase
		HBaseConnector hbc = new HBaseConnector();
		hbc.connect();
		hbc.createTable(App.getApp().getAppConfig().getTableName(), "family");
		
		RECConsumer recCons = new RECConsumer();
		recCons.init(App.getApp().getAppConfig().getTopicName());
		recCons.receiveMessage(hbc);
		recCons.close();
		hbc.close();
	}

}
