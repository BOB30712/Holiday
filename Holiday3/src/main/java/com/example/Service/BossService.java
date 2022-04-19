package com.example.Service;

import java.util.List;

import com.example.entity.Boss;

public interface BossService {
	
	//新增資料
	Boss saveBoss(Boss boss);
	//取得全部資料
	List<Boss> getAllBoss();
	//取得單一資料
	Boss getBossId(Long id);
	//修改資料
	Boss updateBoss(Boss boss,Long id);
	//刪除資料
	void deteleBoss(Long id);

}
