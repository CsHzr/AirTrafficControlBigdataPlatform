package echarts;

import config.ConstantData;
import main.App;
import rec.HBaseConnector;

public class EctDemo1Obj {
	public EctDemo1Obj() {
		xAxis = new String[5];
		data = new long[5];
		
	}
	public void noHBase() {
		xAxis[0]="00:00";
		xAxis[1]="00:15";
		xAxis[2]="00:30";
		xAxis[3]="00:45";
		xAxis[4]="01:00";
		
		data[0]=10;
		data[1]=20;
		data[2]=30;
		data[3]=25;
		data[4]=10;
	}
	public void getFromHBase() {
		xAxis[0]="00:00";
		xAxis[1]="00:15";
		xAxis[2]="00:30";
		xAxis[3]="00:45";
		xAxis[4]="01:00";
		
		HBaseConnector HBC = new HBaseConnector();
		HBC.connect();
		data[0]=HBC.countDemo1(ConstantData.TableName,"191020.0000.fpl.snp.","family");
		data[1]=HBC.countDemo1(ConstantData.TableName,"191020.0015.fpl.snp.","family");
		data[2]=HBC.countDemo1(ConstantData.TableName,"191020.0030.fpl.snp.","family");
		data[3]=HBC.countDemo1(ConstantData.TableName,"191020.0045.fpl.snp.","family");
		data[4]=HBC.countDemo1(ConstantData.TableName,"191020.0100.fpl.snp.","family");
		HBC.close();
	}
	private String[] xAxis;
	private long[] data;
}
