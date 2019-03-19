package cn.service.developer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.AppInfo;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

public interface AppInfoService {
	/**
	 * ������ѯ
	 * @param softwareName			�������
	 * @param queryStatus			app״̬id
	 * @param queryCategoryLevel1	����һ������id
	 * @param queryCategoryLevel2	������������id
	 * @param queryCategoryLevel3	������������id
	 * @param queryFlatformId		app����ƽ̨id
	 * @param devId					������id
	 * @param currentPage			��ǰҳ��
	 * @param pageSize				ÿҳ��ʾ����
	 * @return
	 */
	public List<Appinfo> getAppInfoList(String querySoftwareName,Integer queryStatus,
			Integer queryCategoryLevel1,Integer queryCategoryLevel2,
			Integer queryCategoryLevel3,Integer queryFlatformId,
			Integer devId,Integer currentPageNo,Integer pageSize);
	
	/**
	 * ��������ѯ����
	 * @param softwareName					�������
	 * @param queryStatus					app״̬id
	 * @param queryCategoryLevel1			����һ������id
	 * @param queryCategoryLevel2			������������id
	 * @param queryCategoryLevel3			������������id
	 * @param queryFlatformId				app����ƽ̨id
	 * @param devId							������id
	 * @return
	 */
	public int getAppInfoCount(String querySoftwareName, Integer queryStatus,
			Integer queryCategoryLevel1, Integer queryCategoryLevel2,
			Integer queryCategoryLevel3, Integer queryFlatformId, Integer devId);
	
	
	/**
	 * ���app��Ϣ
	 * @param appInfo
	 * @return
	 */
	public int addAppInfo(AppInfo appInfo);
	
	
	/**
	 * �޸�״̬
	 * @param appInfo
	 * @return
	 */
	public boolean updateStatusByAppId(AppInfo appInfo);
	
	
	/**
	 * �޸�
	 * @param appInfo
	 * @return
	 */
	public int modfi(AppInfo appInfo);
	
	/**
	 * ɾ��appinfo
	 * @param id
	 * @return
	 */
	public boolean delAppInfo(Integer id);
	
	/**
	 * ɾ��ͼƬ
	 * @param id
	 * @return
	 */
	public int deleteAppLogo(Integer id);
	
	
}
