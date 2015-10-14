/**
 * dephin - notification ticker
 */

$(document).ready(function() {
	
	
	
		$.ajax({
			url:"getNotification.do",
			success: function(data){
				if(data.notification.length>0){
					for(var i=0;i<data.notification.length;i++){
						var message = "<div class='message'><div class='messageheading'><span class='icon-pushpin'></span><span class='notificationRequestId'>"+" "+data.notification[i].AssetIdentity+"</span><span class='notificationDueDate'>"+convertDate(data.notification[i].dueDate)+"</span></div><div class='message-body'>Owned By: "+data.notification[i].OwnedBy+"</div></div>";
						$('.content-wrapper').append(message);
					}
				}
				else{
					$('.content-wrapper').append("<div class='message'><div class='message-body-noFeed'>No Feeds Available</div></div>");
				}
				
			},
			failure: function(){
				$('.content-wrapper').append("<div class='message'><div class='message-body-noFeed'>Failure in Fetching Feeds</div></div>");
			},
			error: function(){
				$('.content-wrapper').append("<div class='message'><div class='message-body-noFeed'>Error in Fetching Feeds</div><div>");
			}
			
		});
		
	    function convertDate(data){
	    	var date = new Date(data);

			var curr_date = date.getDate();
		    var curr_month = date.getMonth() + 1;
		    var curr_year = date.getFullYear();
		    
			return ("0" + curr_date).slice(-2)+"-"+("0" + curr_month).slice(-2)+"-"+curr_year;
			
	    }
	
	
});
//$(document).ajaxStart(function(){
//	popupInfoFixed("Please wait...Loading..","alert-info");
// }).ajaxStop(function(){
//	hideAlertFixed();
// });