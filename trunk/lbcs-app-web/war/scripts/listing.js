function loadPopup(url, height, width) {
	$.modal("<iframe class='popup-iframe' src='" + url + "' style='height:100%; width: 100%'></iframe>", {
		    containerCss:{
		        backgroundColor:"#fff",
		        borderColor:"#fff",
		        height: height,
		        padding:0,
		        width: width,
		    },
		    onShow : function() {
		    	$("#simplemodal-overlay").css("opacity" , 0.9);
		    }
		}
	);

}