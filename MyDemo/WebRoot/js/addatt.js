function addSt(){
	var staffid = document.getElementById("staffid");

	var attdate = document.getElementById("attdate");
	
	var typeC = document.getElementById("type");  
	var index=typeC.selectedIndex ;
	var type = typeC.options[index]; 

	//var staffid = document.getElementById("staffid").value;
	//var json = "{\"userName\":\"" + userName + "\",\"sex\":\"" + sex + "\",\"age\":\"" + age + "\",\"birth\":\"" + birth + "\",\"marriage\":\"" + marriage + "\",\"address\":\"" + address + "\",\"phone\":\"" + phone + "\"}";
	var json = {"staffid":staffid.value,"attdate":attdate.value,"type":type.value};
	
	if(!yz()){
		return;
	}
	changeAjax(json);
}

function changeAjax(json){
	
	var xmlHttp = initAjax();
	xmlHttp.open("post","/MyDemo/servlet/AttServlet?para=addSt",true);
	
	xmlHttp.onreadystatechange = function ()
	{
		if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) { 
            	var tip = xmlHttp.responseText;
            	if(tip=="ok"){
            		alert("添加成功！");
            	}else{
            		alert("添加失败！");
            	}
        		
            }
		}
	}
	var data = JSON.stringify(json);
	xmlHttp.send(data);		
}

function initAjax(){
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();		
	}else if(window.ActiveXObject){
		try{
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				window.alert("该浏览器不支持Ajax");
			}
		}
	}
	return xmlHttp;
}

function yz(){
	var addtable = document.getElementById("addtable");
	var input = addtable.getElementsByTagName("input");
	for(var i=0;i<input.length;i++){
		if(input[i].value==""){
			alert("请输入"+input[i].name);
			return false;
		}
		
	}
	return true;
}