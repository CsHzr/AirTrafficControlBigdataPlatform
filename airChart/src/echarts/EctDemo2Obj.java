package echarts;

import config.ConstantData;
import rec.HBaseConnector;

public class EctDemo2Obj {
	public EctDemo2Obj() {
		xAxis = new String[5];
		data = new long[3][5];
		
	}
	public void noHBase() {
		xAxis[0]="00:00";
		xAxis[1]="00:15";
		xAxis[2]="00:30";
		xAxis[3]="00:45";
		xAxis[4]="01:00";
		
		for(int i=0;i<data.length;++i)
		{
			for(int j=0;j<data[i].length;++j)
				data[i][j] = j+1;
		}
	}
	public void getFromHBase() {
		xAxis[0]="00:00";
		xAxis[1]="00:15";
		xAxis[2]="00:30";
		xAxis[3]="00:45";
		xAxis[4]="01:00";
		
		HBaseConnector HBC = new HBaseConnector();
		HBC.connect();
		//北京
		data[0][0] = HBC.countDemo2(ConstantData.TableName,"191020.0000","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZBAA","family");
		data[0][1] = HBC.countDemo2(ConstantData.TableName,"191020.0015","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZBAA","family");
		data[0][2] = HBC.countDemo2(ConstantData.TableName,"191020.0030","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZBAA","family");
		data[0][3] = HBC.countDemo2(ConstantData.TableName,"191020.0045","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZBAA","family");
		data[0][4] = HBC.countDemo2(ConstantData.TableName,"191020.0100","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZBAA","family");
		//上海
		data[1][0] = HBC.countDemo2(ConstantData.TableName,"191020.0000","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZSSS","family");
		data[1][1] = HBC.countDemo2(ConstantData.TableName,"191020.0015","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZSSS","family");
		data[1][2] = HBC.countDemo2(ConstantData.TableName,"191020.0030","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZSSS","family");
		data[1][3] = HBC.countDemo2(ConstantData.TableName,"191020.0045","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZSSS","family");
		data[1][4] = HBC.countDemo2(ConstantData.TableName,"191020.0100","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZSSS","family");
		//广州
		data[2][0] = HBC.countDemo2(ConstantData.TableName,"191020.0000","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZGGG","family");
		data[2][1] = HBC.countDemo2(ConstantData.TableName,"191020.0015","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZGGG","family");
		data[2][2] = HBC.countDemo2(ConstantData.TableName,"191020.0030","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZGGG","family");
		data[2][3] = HBC.countDemo2(ConstantData.TableName,"191020.0045","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZGGG","family");
		data[2][4] = HBC.countDemo2(ConstantData.TableName,"191020.0100","IS_FLIGHT_OUTSIDE_FDRG","ADEP","ZGGG","family");
		HBC.close();
	}
	private String[] xAxis;
	private long[][] data;
}
