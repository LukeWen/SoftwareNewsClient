package com.icephone.softwarenewsclient.util;

public final class Constant {
	/**
	 * 是否连接网络
	 */
	public static boolean isHasNetWork = false;
	
	
	public static boolean isHasData = false;
	
	public static String NewsClassName = "com.software.data.News";

	public static final class SQLiteProperty{
		/**
		 * 数据库名称
		 */
		public final static String DBNAME = "collegeClient";
		
		/**
		 * 数据库版本
		 */
		public static final int DATABASE_VERSION = 6;
		
		/**
		 * 数据库表名称
		 */
		 public static final String TABLE_NEWS = "news";
		/**
		 * 创建news数据库sql
		 */
		public static final String TabelNews_CREATE =
		        "CREATE TABLE IF NOT EXISTS news (_id integer primary key, "
		        	+"typeId integer not null, "+"adminId integer, "+" newsContent Text,"
		        		+ "newsTitle VARCHAR not null, newsTime VARCHAR, seeNum integer,subType integer);";
		/**
		 * 创建文章主题数据库sql
		 */
		public static final String TabelPicture_CREATE =
				"CREATE TABLE IF NOT EXISTS pictures (_id integer primary key autoincrement, " +
				"picUrl VARCHAR not null, _newsId integer);";
	}
	
	private static class CourseSystem{
		final static String courseUrl = "http://222.194.15.1:7777/zhxt_bks/zhxt_bks.html";
		
	}
	 
	/**
	 * 
	 */
	public final static String TAB_TAG_NEWS="tab_tag_news";
	public final static String TAB_TAG_Notify="tab_tag_notify";
	public final static String TAB_TAG_CALENDAR="tab_tag_calendar";
	public final static String TAB_TAG_SEARCH="tab_tag_search";
	public final static String TAB_TAG_MORE="tab_tag_more";
	
	/**
	 * Webservice的命名空间http://software.hitwh.edu.cn/
	 */
	public final static String namespaceOfWebservice = "http://software.hitwh.edu.cn/";
	
	
	/**
	 * Webservice的函数
	 */
	public final static class WebserviceMethodName{
		/**
		 * 一次从webservice获取的数据条数
		 */
		public final static int GETNewsNumber = 10;
		
		/**
		 * 查看时候需要更新
		 */
		public final static String CheckUpdate = "GetMaxNewId";

		/**
		 * 获取更新数据方法
		 */
		public final static String GetUpdateNews = "getUpdateNews";
		
		/**
		 * 获取之前的数据
		 */
		public final static String getPreviousNew = "getPreviousNew";
		
		/**
		 * 根据id获取新闻
		 */
		public final static String getNewsByid = "getNewsByid";
		
		/**
		 * 获取最新的新闻
		 */
		public final static String getTopNewsByTypeid = "getTopNewsByTypeid";
	}
	
	
	/**
	 * 10.0.2.2/是在虚拟机上的地址http://10.0.2.2/collegeWeb/Service1.asmx
	 * http://software.hitwh.edu.cn/CollegeWebService/Service1.asmx
	 */
	public final static String enterPoint = "http://software.hitwh.edu.cn/CollegeWebService/Service1.asmx?AspxAutoDetectCookieSupport=1";
	
	public static class IntentFilterAction{
		public static String DATALOADED = "software.data.loadFinished";
	}
	/**
	 * 所有表字段
	 */
	public static final String KEY_ID = "_id";
	public static final String KEY_TITLE = "newsTitle";
    public static final String KEY_TIME = "newsTime";
    public static final String KEY_SEENUM = "seeNum";
    public static final String KEY_NewsID = "newsId";
}
