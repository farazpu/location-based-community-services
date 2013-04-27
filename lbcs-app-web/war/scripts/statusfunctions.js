function updateNotificationStatus(id, value){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  }
	xmlhttp.open("GET","../adminuserhandling/datasourceinteracter.jsp?type=updateNotificationStatus&id="+id+"&value="+value,true);
	xmlhttp.send();
	
}


function updateReviewStatus(serviceId, itemId, productId, username, value){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  }
	xmlhttp.open("GET","../adminuserhandling/datasourceinteracter.jsp?type=updateReviewStatus&serviceId="+serviceId+"&itemId="+itemId+"&productId="+productId+"&username="+username+"&value="+value,true);
	xmlhttp.send();
	
}

function updateValueReviewStatus(serviceId, itemId, productId, attributeId, username, value){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  }
	xmlhttp.open("GET","../adminuserhandling/datasourceinteracter.jsp?type=updateValueReviewStatus&serviceId="+serviceId+"&itemId="+itemId+"&productId="+productId+"&username="+username+"&value="+value,true);
	xmlhttp.send();
	
}

