import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lakeside.data.sqldb.MysqlDataSource;
import com.whi8per.sense.deeplearning.data.source.DeeplearningDataSource;

/**
 * @author wxm
 * 
 */
public class ImportData2Mysql {

	@Test
	public void test() {
		
		// save tag list;
		//saveTagsIntoSQL("/home/wxm/saveResults/getUniqueTags.txt");
		List<String>  list1 = new ArrayList<String>();
		list1.add("AAA");
		list1.add("BBB");
		
		List<String>  list2 = new ArrayList<String>();
		list1.add("CCC");
		list1.add("DDD");
		
	    list1.addAll(list2);
	    
	    System.out.println(list1.get(1)); 
		
		
		
		
		

		System.out.println("task finish!");
	}

	public void saveTagsIntoSQL(String filePath) {
		// resource;
		MysqlDao mysqlDao = new MysqlDao();
		MysqlDataSource mysql = mysqlDao.getDataSource();
		// get the data in the file ;
		// tag list;
		String sql = "INSERT INTO `sense_deeplearning`.`dp_tag` (`id`, `tag`) VALUES (NULL, :value );";
		List<String> list = new ArrayList<String>();
		try {
			list = getList(filePath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Map[] maps = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				HashMap<String, Object> paramMap = new HashMap();
				paramMap.put("value", list.get(i));
				maps[i] = paramMap;
			}
		}
		mysqlDao.execute(sql, maps);
	}

	public void saveGroupInfoIntoSQL(String filePath) {
		// resource;
		MysqlDao mysqlDao = new MysqlDao();
		MysqlDataSource mysql = mysqlDao.getDataSource();
		// get the data in the file ;
		// img_group list;
		 String sql =  "INSERT INTO `sense_deeplearning`.`dp_img_group` (`id`, `group_id`, `img_id`, `pos_neg_flag`, `order`) VALUES (NULL, :g_id, :img_id, :flag, :order);";
		// String filePath = "/home/wxm/saveResults/getImg_Group_Info.txt";
		List<String> list = new ArrayList<String>();
		try {
			list = getList(filePath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Map[] maps = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				HashMap<String, Object> paramMap = new HashMap();
				String strLine[] = list.get(i).split(",");
				paramMap.put("g_id", strLine[0]);
				paramMap.put("img_id", strLine[1]);
				paramMap.put("flag", strLine[2]);
				paramMap.put("order", strLine[3]);
				maps[i] = paramMap;
			}
		}
		mysqlDao.execute(sql, maps);
	}
	
	
	/**
	 * @param filePath
	 *            @ filePath : "/home/wxm/saveResults/uniqueTags.txt";
	 * @return
	 * @throws Exception
	 */
	public static List<String> getList(String filePath) throws Exception {
		List<String> list = new ArrayList<String>();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String sLine = br.readLine();
		while (sLine != null) {
			list.add(sLine);
			sLine = br.readLine();
		}
		br.close();
		return list;
	}

	/**
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> getImgGroupList(String filePath)
			throws Exception {
		List<String> list = new ArrayList<String>();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String sLine = br.readLine();
		while (sLine != null) {
			list.add(sLine);
			sLine = br.readLine();
		}
		br.close();
		return list;
	}
}