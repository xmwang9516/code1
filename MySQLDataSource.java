
import com.lakeside.data.sqldb.MysqlDataSource;


/**
 * @author zhufb
 *
 */
public class MySQLDataSource {

	private static MysqlDataSource dataSource;
	public static synchronized MysqlDataSource get(){
		if(dataSource == null){
			String jdbcurl = "jdbc:mysql://172.18.109.56:3306/sense_deeplearning?useUnicode=true&amp;characterEncoding=UTF-8&amp;charSet=UTF-8";
			String userName = "sense";
			String password = "sense123";
			dataSource = new MysqlDataSource(jdbcurl, userName, password);
		}
		return dataSource;
	}
	
}