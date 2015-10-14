package com.avnet.ams.vto;

public class ResolveVTO {

    private String approverId;
    private String requestId;
    private String assetId;
    private String adminStatus;
	private String ownerId;
    private String statusId;
    private String comments;
    private String assetIdOriginal;
       
	@Override
	public String toString() {
		return "ResolveVTO [approverId=" + approverId + ", requestId="
				+ requestId + ", assetId=" + assetId + ", adminStatus="
				+ adminStatus + ", ownerId=" + ownerId + ", statusId=" + statusId + ", comments=" + comments + ", assetIdOriginal=" + assetIdOriginal + "]";
	}
	 public String getAdminStatus() {
			return adminStatus;
		}
		public void setAdminStatus(String adminStatus) {
			this.adminStatus = adminStatus;
		}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getComments() {
		return comments;
	}
	public void setAssetIdOriginal(String assetIdOriginal) {
		this.assetIdOriginal = assetIdOriginal;
	}
	public String getAssetIdOriginal() {
		return assetIdOriginal;
	}
	
    
}
