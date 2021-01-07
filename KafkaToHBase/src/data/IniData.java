package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class IniData
{
	private HashMap<String,HashMap<String,String>> m_cSysParas;
	
	public IniData()
	{
		m_cSysParas = new HashMap<String,HashMap<String,String>>();
	}
	
	public void init() throws Exception
	{
		initIni("catc");
	}
	
	public void initIni(String iniName) throws Exception
	{
		String path = iniName + ".ini";
		
		InputStreamReader isr = new InputStreamReader(this.getClass().getResourceAsStream("/"+path));
		BufferedReader br = new BufferedReader(isr);
		String str = br.readLine();
		String currentFieldName = null;
		while(str!=null)
		{
			String content = str.trim();
			if(content!="")
			{
				
				if(content.startsWith("[") && content.endsWith("]"))
				{
					currentFieldName = content.substring(1, content.length()-1);
					HashMap<String,String> fieldParas = new HashMap<String,String>();
					m_cSysParas.put(currentFieldName, fieldParas);
				}
				else if(content.indexOf("=") != -1)
				{
					HashMap<String,String> fieldParas = m_cSysParas.get(currentFieldName);				
					if(fieldParas == null)
					{
						System.out.println("Can't find this para field,pass! "+content);
					}
					else
					{
						int indexEqual = str.indexOf("=");
						String paraName = content.substring(0,indexEqual);
						String para = null;
						if(content.endsWith(";"))
							para = str.trim().substring(indexEqual+1,str.trim().length()-1);
						else
							para = str.trim().substring(indexEqual+1,str.trim().length());
						fieldParas.put(paraName, para);
					}
				}
			}
			str = br.readLine();		
		}
		br.close();
	}
	
	public String getSysPara(String fieldName,String paraName)
	{
		HashMap<String,String> fieldParas = m_cSysParas.get(fieldName);
		if(fieldParas == null)
			return null;
		else
			return fieldParas.get(paraName);
	}
	
	public HashMap<String,String> getFieldParas(String fieldName)
	{
		return m_cSysParas.get(fieldName);
	}
	
	public void setSysPara(String fieldName,String paraName,String paraValue)
	{
		HashMap<String,String> fieldParas = m_cSysParas.get(fieldName);
		fieldParas.put(paraName, paraValue);
	}
}
