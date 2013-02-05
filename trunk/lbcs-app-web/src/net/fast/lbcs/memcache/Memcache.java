package net.fast.lbcs.memcache;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.ServiceID;

import com.google.appengine.api.datastore.*;

public class Memcache {
		
	public static List<AgedEntity> serviceCacheEntities = new ArrayList<AgedEntity>();
	public static List<AgedEntity> groupCacheEntities = new ArrayList<AgedEntity>();
	public static List<AgedEntity> itemCacheEntities = new ArrayList<AgedEntity>();
	public static List<AgedEntity> attrCacheEntities = new ArrayList<AgedEntity>();
	

	public static void cacheService(Entity entity){
		AgedEntity ae = new AgedEntity();
		ae.entity=entity;
		ae.time=new Date();
		for(int i=0 ; i < serviceCacheEntities.size() ; i++){
			AgedEntity aEntity = serviceCacheEntities.get(i);
			String id1 = (String)entity.getProperty("id");
			String id2 = (String)aEntity.entity.getProperty("id");
			if(id1.equals(id2)){
				serviceCacheEntities.remove(i);
			}
		}
		serviceCacheEntities.add(ae);
		removeOldEntries();
	}
	public static void cacheGroup(Entity entity){
		AgedEntity ae = new AgedEntity();
		ae.entity=entity;
		ae.time=new Date();
		for(int i=0 ; i < groupCacheEntities.size() ; i++){
			AgedEntity aEntity = groupCacheEntities.get(i);
			String id1 = (String)entity.getProperty("id");
			String id2 = (String)aEntity.entity.getProperty("id");
			if(id1.equals(id2)){
				groupCacheEntities.remove(i);
			}
		}
		groupCacheEntities.add(ae);
		removeOldEntries();		
	}
	public static void cacheItem(Entity entity){
		AgedEntity ae = new AgedEntity();
		ae.entity=entity;
		ae.time=new Date();
		for(int i=0 ; i < itemCacheEntities.size() ; i++){
			AgedEntity aEntity = itemCacheEntities.get(i);
			String id1 = (String)entity.getProperty("id");
			String id2 = (String)aEntity.entity.getProperty("id");
			if(id1.equals(id2)){
				itemCacheEntities.remove(i);
			}
		}
		itemCacheEntities.add(ae);
		removeOldEntries();
	}
	public static void cacheAttr(Entity entity){
		AgedEntity ae = new AgedEntity();
		ae.entity=entity;
		ae.time=new Date();
		for(int i=0 ; i < attrCacheEntities.size() ; i++){
			AgedEntity aEntity = attrCacheEntities.get(i);
			String id1 = (String)entity.getProperty("id");
			String id2 = (String)aEntity.entity.getProperty("id");
			if(id1.equals(id2)){
				attrCacheEntities.remove(i);
			}
		}
		attrCacheEntities.add(ae);
		removeOldEntries();
	}
	
	public static boolean isServiceInMemCache(ServiceID Id, String name){
		for(AgedEntity ae : serviceCacheEntities){
			String mName = (String) ae.entity.getProperty("name");
			String id = (String) ae.entity.getProperty("id");
			if(name.equals(mName) && !(id.equals(Id.getId()))){
				return true;
			}
		}
		return false;
	}
	

	public static boolean isGroupInMemCache(ServiceItemGroupID Id, String name, ServiceID serviceId){
		
		for(AgedEntity ae : groupCacheEntities){
			String mName = (String) ae.entity.getProperty("name");
			String mService = (String) ae.entity.getProperty("service id");
			String id = (String) ae.entity.getProperty("id");
			if(name.equals(mName) && mService.equals(serviceId.getId())
					&& (!id.equals(Id.getId())) ){
				return true;
			}
		}
		return false;
	}

	public static boolean isItemInMemCache(ServiceItemID Id, String name, ServiceID serviceId){
		for(AgedEntity ae : itemCacheEntities){
			String mName = (String) ae.entity.getProperty("name");
			String mService = (String) ae.entity.getProperty("service id");
			String id = (String) ae.entity.getProperty("id");
			if(name.equals(mName) && mService.equals(serviceId.getId())
					&& (!id.equals(Id.getId()))){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAttributeInMemCache(String Id, String name, ServiceID serviceId, ServiceItemID itemId){
		for(AgedEntity ae : attrCacheEntities){
			String mName = (String) ae.entity.getProperty("name");
			String mService = (String) ae.entity.getProperty("service id");
			String mItem = (String) ae.entity.getProperty("item id");
			String id = (String) ae.entity.getProperty("id");
			if(name.equals(mName) && mService.equals(serviceId.getId())
					&& mItem.equals(itemId.getId()) && (!id.equals(Id))){
				return true;
			}
		}
		return false;
	}
	
	
	
	public static void removeOldEntries(){
		// remove entries older than 30 sec.
	}

}
