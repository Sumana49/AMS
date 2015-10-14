<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
            <html>
            <%@include file="header_secure.jspf" %>
                <!-- Local Script Include -->
                <script src="${pageContext.servletContext.contextPath}/js/admin/edit.js">
                </script>
                <style type="text/css">
                    .ui-autocomplete {
                        max-height: 200px;
                        height: auto;
                        overflow-y: scroll;
                        overflow-x: hidden;
                        width: 234px;
                    }
                </style>
 
                <div class="breadcrumbs"> <span class="breadcrumb-icon icon-cabinet">
            </span>
            <span class="error-container">
                  <div class="alert alert-error" id="ErrorFieldContent">
                  
                  </div>
            </span>
            <a href="#">
              Edit
              Assets
            </a>
                </div>
                <div class="contentcontainer clearfix">
                    <div class="navigation">
                        <ul class="menu" id="menu">
                            <li class="current-link-selected">
                                <a href="#editAssetDetails"> <span class="icon-cabinet">
                    </span>
                                    Edit Assets</a>
                            </li>
                        </ul>
                    </div>
                    <div class="contentsection current" id="editAssetDetails">
                        <div class="contentheader" style="height: 25px">
                            <div class="contentheading"> <span class="editlabel">
                    <label>
                      Asset Id: 
                    </label>
                    ${editAssetVTOObject.assetIdentity}
                  </span>
                            </div>
                        </div>
                        <div class="formcontainer">
                            <form:form class="editasset" id="editasset" method="POST" action="editAssetUpdate.do" modalAttribute="assetObject">
                                <input type="hidden" name="assetId" id="assetId" value='${editAssetVTOObject.assetId}' />
                                <div>
                                    <label>Asset Type</label>
                                    <label>${editAssetVTOObject.assetType}</label>
                                    <input type="hidden" name="assetType" id="assetType" value='${editAssetVTOObject.assetType}' />
                                </div>
                                <div class="changing">
                                    <!-- changing division -->
                                    <c:forEach items="${editAssetVTOObject.attributeTypeList}" var="attributeObject" varStatus="status">
										<c:choose>
											<c:when test="${attributeObject.unit!= 'date' }">
												<div>
													<label>${attributeObject.labelName}</label>
													<input type="text" name='attributeTypeList[${status.index}].value' id='${attributeObject.fieldName}' value='${attributeObject.value}' />
													<input type="hidden" name='attributeTypeList[${status.index}].fieldName' value='${attributeObject.fieldName}' /> 
													<input type="hidden" name='attributeTypeList[${status.index}].id' value='${attributeObject.id}' /> 
													<span class="bubble"><p></p> </span>
												</div>
											</c:when>
											<c:otherwise>
												<div>
													<label>${attributeObject.labelName}</label>
													<input type="text" name='attributeTypeList[${status.index}].value' id='${attributeObject.fieldName}' value='${attributeObject.value}' onmouseover="datepick(this)" readonly/>
													<input type="hidden" name='attributeTypeList[${status.index}].fieldName' value='${attributeObject.fieldName}' /> 
													<input type="hidden" name='attributeTypeList[${status.index}].id' value='${attributeObject.id}' /> 
													<span class="bubble"><p></p> </span>
												</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
                                </div>
                                <label>Owned by</label>
                                <label>${editAssetVTOObject.ownedBy}</label>
                                <p></p>
                                </span>
                                </a>
                                <div>
                                    <label>Date of capitalization</label>
                                    <input type="text" name='dateOfCapitalisation' id='dateOfCapitalisation' value='${editAssetVTOObject.dateOfCapitalisation}' onmouseover="datepick(this)" readonly/>
                                </div>
                                <div class="assetradio">
                                    <label>Carry out of office?</label>
                                    <input type="radio" name="carry" value="yes" id="yes" ${editAssetVTOObject.carry==true ? 'checked="checked" ' : ''} disabled/>
                                    <label for="yes" style="width: 100px;">Yes</label>
                                    <input type="radio" name="carry" value="no" id="no" ${editAssetVTOObject.carry==false ? 'checked="checked" ' : ''} disabled/>
                                    <label for="no">No</label>
                                </div>
                                <div class="assetradio">
                                    <label>Available for use?</label>
                                    <input type="radio" name="available" value="yes" id="use" ${editAssetVTOObject.available==true ? 'checked="checked" ' : ''}/>
                                    <label for="use" style="width: 100px;">Yes</label>
                                    <input type="radio" name="available" value="no" id="nouse" ${editAssetVTOObject.available==false ? 'checked="checked" ' : ''} />
                                    <label for="nouse">No</label>
                                </div>
                                <div class="poscenter">
                                    <input type="button" class="btn btnsave" onclick="window.location.href='assets.do#assets';" value="Cancel" id="canceledit">
                                    <input type="submit" class="btn btnsave" value="Save" id="saveedit">
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>

            </html>