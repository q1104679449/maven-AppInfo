package cn.service.backend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.AppVersion;

public interface AppVersionService {
	/**
	 * ����id��ѯ�汾��Ϣ
	 * @param id
	 * @return
	 */
	public AppVersion getAppVersionById(@Param("id")Integer id);
	
	/**
	 * ����appid��ѯ��ʷ�汾
	 * @param appid
	 * @return
	 */
	public List<AppVersion> getAppVersionByAppId(@Param("appid")Integer appid);
	
	/**
	 * ����ʵ����Ӱ汾��Ϣ
	 * @param AppVersion
	 * @return
	 */
	public int addAppVersion(@Param("AppVersion")AppVersion appVersion);
	
	/**
	 * �޸�
	 * @param appVersion
	 * @return
	 */
	public int modfiy(AppVersion appVersion);
	
	/**
	 * ɾ��apk�ļ�
	 * @param id
	 * @return
	 */
	public int deleteApkFile(@Param("id")Integer id);
}
