package com.avnet.ams.vto;

import java.util.ArrayList;

public class ViewRequestCountVTO {
	ArrayList<ViewRequestsVTO> viewRequestsAdminVTOList;
	public ArrayList<ViewRequestsVTO> getViewRequestsAdminVTOList() {
		return viewRequestsAdminVTOList;
	}
	public void setViewRequestsAdminVTOList(
			ArrayList<ViewRequestsVTO> viewRequestsAdminVTOList) {
		this.viewRequestsAdminVTOList = viewRequestsAdminVTOList;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	int recordsFiltered;

}
