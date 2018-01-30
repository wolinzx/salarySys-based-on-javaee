function addSt(){
	var userName = document.getElementById("userName");
	var passWord = document.getElementById("passWord");
	//var sex = document.getElementById("sex").value;
	var sexradio = document.getElementsByClassName("sex");  
    for (i=0; i<sexradio.length; i++) {  
        if (sexradio[i].checked) {  
        	var sex = sexradio[i];
        }  
    }  
	
	var seniority = document.getElementById("seniority");
	var birth = document.getElementById("birth");
	//var marriage = document.getElementById("marriage").value;
	
	var postrC = document.getElementById("post");  
	var index=postrC.selectedIndex ;
	var post = postrC.options[index]; 
	
	var depid = document.getElementById("depid");
	var phone = document.getElementById("phone");
	//var staffid = document.getElementById("staffid").value;
	//var json = "{\"userName\":\"" + userName + "\",\"sex\":\"" + sex + "\",\"age\":\"" + age + "\",\"birth\":\"" + birth + "\",\"marriage\":\"" + marriage + "\",\"address\":\"" + address + "\",\"phone\":\"" + phone + "\"}";
	var json = {"userName":userName.value,"passWord":passWord.value,"sex":sex.value,"seniority":seniority.value,"birth":birth.value,"post":post.value,"depid":depid.value,"phone":phone.value};
	
	if(!yz()){
		return;
	}
	changeAjax(json);
}

function changeAjax(json){
	
	var xmlHttp = initAjax();
	xmlHttp.open("post","/MyDemo/servlet/AdminServlet?para=addSt",true);
	
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