package rec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceExistException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.BasicConfigurator;

import config.ConstantData;


public class HBaseConnector {
	private Admin admin=null;
	private Connection connection=null;
	private Configuration configuration;
	
	public HBaseConnector() {
		System.out.println("--- Initialize hbase connector ---");
		//配置
		configuration= HBaseConfiguration.create();
		//TODO 配置 HBase
		configuration.set("hbase.zookeeper.quorum", ConstantData.HbaseConfig);
		//参数应该是ip:2181
		
	}
	
	//连接HBase
	public void connect()
	{
		System.out.println("--- Connect to hbase ---");	
		try {
			connection=ConnectionFactory.createConnection(configuration);
			admin=connection.getAdmin();
			System.out.println(connection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}
	//关闭HBaseConnector
	public void close() {
		System.out.println("--- Close hbase connection ---");
		try {
			if(admin!=null) {
				admin.close();
				admin=null;
			}
			if(connection!=null) {
				connection.close();
				connection=null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}
	//判断表是否存在
	private boolean isTableExist(String tableName) {
		try {
			TableName tn = TableName.valueOf(tableName);
			boolean exists = admin.tableExists(tn);
			if(exists == true)
				System.out.println("table "+tableName+" exist！");
			else
				System.out.println("table "+tableName+" not exist！");
			return exists;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
			return false;
		}
	}
	/**
	 * create table
	 * @param tableName
	 * @param cfs
	 */
	public void createTable(String tableName, String... cfs) {
		//TODO test
		System.out.println("--- Create table \""+tableName+"\" ---");
		//检查表是否存在
		if(isTableExist(tableName))
			return;
		//创建表
		HTableDescriptor hTableDestriptor = new HTableDescriptor(TableName.valueOf(tableName));
		for(String s:cfs) {
			HColumnDescriptor hcd = new HColumnDescriptor(s);
			hTableDestriptor.addFamily(hcd);//列族
		}
		try {
			//加上这个才能使用aggregation
			hTableDestriptor.addCoprocessor("org.apache.hadoop.hbase.coprocessor.AggregateImplementation");
			
			admin.createTable(hTableDestriptor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	//删除表
	private void dropTable(String tableName) {
		System.out.println("--- Drop table \""+tableName+"\" ---");
		if(!isTableExist(tableName))
			return;
		try {
			TableName tn = TableName.valueOf(tableName);
			//让表下线
			if(admin.isTableEnabled(tn))
				admin.disableTable(tn);
			//删除表
			admin.deleteTable(tn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}
	//创建命名空间（没有测试这个函数）
	public void createNamespace(String nspName) {
		//TODO test
		System.out.println("--- Create namespace ---");
		//命名空间描述器
		NamespaceDescriptor nspDsp = NamespaceDescriptor.create(nspName).build();
		//NamespaceDescriptor nspDsp = admin.getNamespaceDescriptor(nspName);//用nspDsp是否为null判断命名空间是否存在
		try {
			//创建命名空间
			admin.createNamespace(nspDsp);
		} catch(NamespaceExistException e) {
			System.out.println(nspName+": Namespace exists!");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}
	//插入一个Record
	public void putData(String tableName, String cf, Record rec) {
		try {
			TableName tn = TableName.valueOf(tableName);
			if(!admin.isTableEnabled(tn))
				admin.enableTable(tn);
			Table table = connection.getTable(tn);
			String rowKey = rec.getKey();
			Put put = new Put(Bytes.toBytes(rowKey));
			for(Record.item ri:rec.getData())
			{
				put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(ri.getName()), Bytes.toBytes(ri.getValue()));
			}
			table.put(put);
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
		}
	}
	//获取一行数据
	public ArrayList<RawCell> getData(String tableName, String rowKey) {
		try {
			TableName tn = TableName.valueOf(tableName);
			//表上线
			if(!admin.isTableEnabled(tn))
				admin.enableTable(tn);
			Table table = connection.getTable(tn);
			Get get = new Get(Bytes.toBytes(rowKey));
			Result result = table.get(get);
			ArrayList<RawCell> res = new ArrayList<RawCell>();
			//解析result
			for(Cell cell:result.rawCells()) {
				RawCell rc = new RawCell(
						Bytes.toString(CellUtil.cloneFamily(cell)),
						Bytes.toString(CellUtil.cloneQualifier(cell)),
						Bytes.toString(CellUtil.cloneValue(cell))
						);
				res.add(rc);
			}
			table.close();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
			return null;
		}
	}
	public Record getRecord(String tableName, String rowKey) {
		try {
			TableName tn = TableName.valueOf(tableName);
			//表上线
			if(!admin.isTableEnabled(tn))
				admin.enableTable(tn);
			Table table = connection.getTable(tn);
			Get get = new Get(Bytes.toBytes(rowKey));
			Result result = table.get(get);
			
			Record rec = new Record();
			String[] meta = rowKey.split("\\.");
			rec.setDate(meta[0]);
			rec.setTime(meta[1]);
			rec.setType1(meta[2]);
			rec.setType2(meta[3]);
			rec.setIndex(meta[4]);
			for(Cell cell:result.rawCells())
			{
				Record.item ri = new Record.item();
				ri.setName(Bytes.toString(CellUtil.cloneQualifier(cell)));
				ri.setValue(Bytes.toString(CellUtil.cloneValue(cell)));
				rec.getData().add(ri);
			}
			table.close();
			return rec;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(e.toString());
			return null;
		}
	}
	public static class RawCell{
		public String family;
		public String qualifier;
		public String value;
		public RawCell(String cf, String cn, String v) {
			family = cf;
			qualifier = cn;
			value = v;
		}
	}
	//HBase聚合操作 统计一个快照文件里有多少个飞行计划记录
	public long countDemo1(String tableName, String rkPrefix, String family) {
		System.out.println("--- Aggregation ---");
		try {
			TableName tn = TableName.valueOf(tableName);
			//表上线
			if(!admin.isTableEnabled(tn))
				admin.enableTable(tn);
		    //统计
			AggregationClient aggregationClient = new AggregationClient(configuration);  
			LongColumnInterpreter columnInterpreter = new LongColumnInterpreter();  
	
		    Scan scan = new Scan();
		    scan.addFamily(Bytes.toBytes(family));
		    PrefixFilter filter1 = new PrefixFilter(Bytes.toBytes(rkPrefix));
			scan.setFilter(filter1);
		    long rst=0;
		
			rst=aggregationClient.rowCount(TableName.valueOf(tableName), columnInterpreter, scan);
			aggregationClient.close();
			return rst;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	//统计某个时间点（一个快照文件）中分别有多少个航班是从北京、上海、广州起飞的
	//key: 时间点
	//列1：是否在空域内
	//列2：起飞机场
	//起飞机场的值
	public long countDemo2(String tableName, String key, String qualifier1, String qualifier2, String value, String family) {
		System.out.println("--- Aggregation ---");
		try {
			TableName tn = TableName.valueOf(tableName);
			//表上线
			if(!admin.isTableEnabled(tn))
				admin.enableTable(tn);
		    //统计
			AggregationClient aggregationClient = new AggregationClient(configuration);  
			LongColumnInterpreter columnInterpreter = new LongColumnInterpreter();  
	
		    Scan scan = new Scan();
		    scan.addFamily(Bytes.toBytes(family));
		    List<Filter> filters = new ArrayList<Filter>();
		    PrefixFilter filter1 = new PrefixFilter(Bytes.toBytes(key));
		    SingleColumnValueFilter filter2 = new SingleColumnValueFilter(Bytes.toBytes("family"), Bytes.toBytes(qualifier1), CompareOp.EQUAL, Bytes.toBytes("0"));
		    SingleColumnValueFilter filter3 = new SingleColumnValueFilter(Bytes.toBytes("family"), Bytes.toBytes(qualifier2), CompareOp.EQUAL, Bytes.toBytes(value));
		    filters.add(filter1);
		    filters.add(filter2);
		    filters.add(filter3);
		    FilterList filter = new FilterList(filters);
		    scan.setFilter(filter);
		    long rst=0;
		
			rst=aggregationClient.rowCount(TableName.valueOf(tableName), columnInterpreter, scan);
			aggregationClient.close();
			System.out.println("airChart test, In area: "+rst);
			return rst;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public static void main(String[] args) {
		BasicConfigurator.configure(); //使用默认Log4j配置
//		HBaseConnector HBC = new HBaseConnector();
//		HBC.connect();
//		HBC.createTable("testTable", "col1","col2");
//		String cn[] = {"id1","id2","id3"};
//		String values[] = {"v1","v2","v3"};
//		HBC.putData("testTable", "123", "col1",cn, values);
//		ArrayList<RawCell> alrc= HBC.getData("testTable", "123");
//		for(RawCell rc:alrc)
//		{
//			System.out.println(rc.family+'.'+rc.qualifier+"."+rc.value);
//		}
//		HBC.close();
	}
}
