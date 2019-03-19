package cn.service.developer;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.dao.appinfo.AppInfoMapper;
import cn.dao.appversion.AppVersionMapper;
import cn.pojo.AppInfo;
import cn.pojo.AppVersion;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

@Service
public class AppInfoServiceImpl implements AppInfoService {
	
	@Resource
	private AppInfoMapper appInfoMapper;
	@Resource
	private AppVersionMapper appVersionMapper;

	@Override
	public List<Appinfo> getAppInfoList(String querySoftwareName,
			Integer queryStatus, Integer queryCategoryLevel1,
			Integer queryCategoryLevel2, Integer queryCategoryLevel3,
			Integer queryFlatformId, Integer devId, Integer currentPageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return appInfoMapper.getAppInfoList(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId, (currentPageNo-1)*pageSize, pageSize);
	}

	@Override
	public int getAppInfoCount(String querySoftwareName, Integer queryStatus,
			Integer queryCategoryLevel1, Integer queryCategoryLevel2,
			Integer queryCategoryLevel3, Integer queryFlatformId, Integer devId) {
		// TODO Auto-generated method stub
		return appInfoMapper.getAppInfoCount(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId,devId);
	}

	@Override
	public int addAppInfo(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return appInfoMapper.addAppInfo(appInfo);
	}

	@Override
	public boolean updateStatusByAppId(AppInfo appInfoObj) {
		AppInfo appInfo=appInfoMapper.getAppInfo(appInfoObj.getId(), null);
		Integer operator=appInfoObj.getModifyBy();

		if(appInfo==null){
			return false;
		}else{
			switch (appInfo.getStatus()) {
			case 2://��״̬Ϊ���ͨ���������ϼ�
				onSale(appInfo,operator,4,2);
				break;
			case 5://��״̬Ϊ�¼�ʱ�����Խ����ϼܲ���
				onSale(appInfo,operator,4,2);
				break;
			case 4://��״̬Ϊ�ϼ�ʱ�����Խ����¼ܲ���
				offSale(appInfo,operator,5);
				break;
			default:
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * �ϼ�
	 * @param appInfo
	 * @param operator
	 * @param appInfoStatus
	 * @param versionStatus
	 */
	private void onSale(AppInfo appInfo,Integer operator,Integer appInfoStatus,Integer versionStatus){
		offSale(appInfo,operator,appInfoStatus);
		setSaleSwitchToAppVersion(appInfo,operator,versionStatus);
	}
	
	private boolean offSale(AppInfo appInfo,Integer operator,Integer appInfStatus){
		AppInfo _appInfo = new AppInfo();
		_appInfo.setId(appInfo.getId());
		_appInfo.setStatus(appInfStatus);
		_appInfo.setModifyBy(operator);
		_appInfo.setOffSaleDate(new Date(System.currentTimeMillis()));
		appInfoMapper.modify(_appInfo);
		return true;
	}
	
	
	private boolean setSaleSwitchToAppVersion(AppInfo appInfo,Integer operator,Integer saleStatus){
		AppVersion appVersion = new AppVersion();
		appVersion.setId(appInfo.getVersionId());
		appVersion.setPublishStatus(saleStatus);
		appVersion.setModifyBy(operator);
		appVersion.setModifyDate(new Date(System.currentTimeMillis()));
		appVersionMapper.modify(appVersion);
		return false;
	}

	@Override
	public int modfi(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return appInfoMapper.modify(appInfo);
	}

	@Override
	public boolean delAppInfo(Integer id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int versionCount=appVersionMapper.getCountByAppId(id);
		//int versionCount=5;
		List<AppVersion> appVersions=new ArrayList<AppVersion>();
		if(versionCount>0){		//ɾ���汾��Ϣ
			//ɾ��apk�ļ�
			appVersions=appVersionMapper.getAppVersionByAppId(id);
			for (AppVersion appVersion : appVersions) {
				if(appVersion.getApkLocPath()!=null && !appVersion.getApkLocPath().equals("")){
					File file=new File(appVersion.getApkLocPath());
					file.delete();
				}
			}
			//ɾ��������
			appVersionMapper.delByAppId(id);
		}
		
		//ɾ��app������Ϣ
		//ɾ���ϴ���logoͼƬ
		AppInfo appInfo=appInfoMapper.getAppInfo(id, null);
		if(appInfo.getLogoLocPath()!=null&&!appInfo.getLogoLocPath().equals("")){
			File file=new File(appInfo.getLogoLocPath());
			file.delete();
		}
		//ɾ������Ϣ
		if(appInfoMapper.delById(id)>0){
			flag=true;
		}
		return flag;
	}

	@Override
	public int deleteAppLogo(Integer id) {
		// TODO Auto-generated method stub
		return appInfoMapper.deleteAppLogo(id);
	}

	


	

}
