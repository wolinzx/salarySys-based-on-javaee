window.onload = function (){
	searchTran();
	searchAction();
}

function searchTran(){
	var mainHeadSearch = document.getElementById("main-head-search");
	mainHeadSearch.onfocus = function (){
		this.style.width="200px";
	}
	mainHeadSearch.onblur = function (){
		this.style.width="100px";
	}
}

function searchAction(){
	var mainHeadSearch = document.getElementById("main-head-search");
	mainHeadSearch.onkeyup = function (e) {//按键信息对象以函数参数的形式传递进来了，就是那个e  
	    var code = e.charCode || e.keyCode;  //取出按键信息中的按键代码(大部分浏览器通过keyCode属性获取按键代码，但少部分浏览器使用的却是charCode)  
	    if (code == 13) {  
	    	searchAjax(0,"searchsingle","");
	    } 
	    
	}  
}

function searchAjax(up,type,mainHeadSearch){
	
	
	var xmlHttp = initAjax();
	var url="";
	var mainPage = document.getElementById("main-page");
	var infoTitle = document.getElementById("info-title");
	var infoBtn = document.getElementById("info-btn");
	var indexicon = document.getElementById("indexicon");
	if(mainHeadSearch==""){
		mainHeadSearch = document.getElementById("main-head-search").value;
	}
	if(type=="searchall"){
		url="servlet/AdminServlet?para=searchstaff&searchvalue=&up="+up;
	}else if(type=="searchsingle"){
		if(mainHeadSearch==""){
			alert("请输入关键字！");
			return;
		}
		url="servlet/AdminServlet?para=searchstaff&searchvalue="+mainHeadSearch+"&up="+up;
	}	
	xmlHttp.open("get",url,true);
	xmlHttp.send(null);		
	xmlHttp.onreadystatechange = function ()
	{
		if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) { 
            	var jsonDate = xmlHttp.responseText;
        		var jsonObj = JSON.parse(jsonDate);
            	if(jsonDate!="[]"){
            		clearTable();
            		clearDiv();   
            		
                	if(type=="searchall"){             		
                		//动态创建表格
                    	addTable(jsonObj.length,getJsonLength(jsonObj[0]),jsonObj);     	
                    	mainPage.style.display="block";
                    	infoTitle.style.display="none";
                    	infoBtn.style.display="none";
                    	indexicon.style.display="none";
                	}else{ 
                		addStinfo(jsonObj.length,getJsonLength(jsonObj[0]),jsonObj);
                		mainPage.style.display="none";
                		infoTitle.style.display="block";
                		infoBtn.style.display="block";
                		indexicon.style.display="none";
                	}          	
            	}else{
            		alert("查无记录！");
            	}
      		}
            	
			}
	    }
	
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

//动态创建表格
function addTable(row,col,json){
	var mainContent = document.getElementById("main-content");
	var table = document.createElement('table');

	mainContent.appendChild(table);
//	alert(Object.keys(jsonObj[0])[7]);
	
	for(var i=0;i<row+1;i++){
		var tr = document.createElement('tr');
		tr.index = i;
		tr.onclick = function (){
			var trname = this.getElementsByTagName("td")[1].innerHTML;
			searchAjax(0,"searchsingle",trname);
		}
		table.appendChild(tr);
		for(var j=0;j<col;j++){
			if(i==0){
				var th = document.createElement('th');
				tr.appendChild(th);
				var jsonkey1 = Object.keys(json[0])[col-j-1];
				
				var jsonkey = beanTrun(jsonkey1);
				
				th.innerHTML = jsonkey;
				continue;
			}
			var jsonkey = Object.keys(json[0])[col-j-1];
			var td = document.createElement('td');
			
			tr.appendChild(td);
			td.innerHTML = json[i-1][jsonkey];
		}
	}
}

function addStinfo(row,col,json,index){
	var mainContent = document.getElementById("main-content");
	var iconWc = document.createElement('div');
	iconWc.setAttribute("id", "newD");
	mainContent.appendChild(iconWc);
	
	
	var icon = document.createElement('div');
	icon.style.background=" url('/MyDemo/img/touxiang.png') no-repeat";
	icon.style.backgroundSize="100% 100%";
	iconWc.appendChild(icon);
	var ul = document.createElement('ul');
	var icon = document.createElement('div');
	iconWc.appendChild(icon);
	icon.appendChild(ul);
	for(var i=0;i<col;i++){
		var li = document.createElement('li');
		ul.appendChild(li);
		var jsonkey1 = Object.keys(json[0])[col-i-1];
		
		var jsonkey = beanTrun(jsonkey1);
		li.innerHTML = jsonkey+":   ";
		var input = document.createElement('input');
		input.setAttribute("id", jsonkey1);
		li.appendChild(input);
		input.value=json[0][jsonkey1];
	}	
}

function clearTable(){
	var table = document.getElementsByTagName("table")[0];
	if(table){
		table.parentNode.removeChild(table);
	}
}
function clearDiv(){
	var div = document.getElementById("newD");
	if(div){
		div.parentNode.removeChild(div);
	}
}


function next(){
	var nextBtn = document.getElementById("next");
	var upBtn = document.getElementById("up");
	var up = 0;
	nextBtn.onclick=function (){
			up+=6;
			upBtn.style.color="#454545";
			searchAjax(up,"searchall","");
			upBtn.onclick = function (){
				if(up==0){
					return;
				}
				up-=6;
				searchAjax(up,"searchall","");
				if(up==0){
					upBtn.style.color="#B4B4B4";
				}
			}
	}
	return up;
}


function searchAll(){
	searchAjax(0,"searchall",""); 	
	next();
}

function changeSt(){
	var userName = document.getElementById("userName").value;
	var sex = document.getElementById("sex").value;
	var seniority = document.getElementById("seniority").value;
	var birth = document.getElementById("birth").value;
	var post = document.getElementById("post").value;
	var depid = document.getElementById("depid").value;
	var phone = document.getElementById("phone").value;
	var staffid = document.getElementById("staffid").value;
	//var json = "{\"userName\":\"" + userName + "\",\"sex\":\"" + sex + "\",\"age\":\"" + age + "\",\"birth\":\"" + birth + "\",\"marriage\":\"" + marriage + "\",\"address\":\"" + address + "\",\"phone\":\"" + phone + "\"}";
	var json = {"userName":userName,"sex":sex,"seniority":seniority,"birth":birth,"post":post,"depid":depid,"phone":phone,"staffid":staffid};
	changeAjax(json);
}

function deleteSt(){
	var staffid = document.getElementById("staffid").value;
	var flag =  confirm("确定删除“"+staffid+"”员工信息?");
	if(flag){
		deleteAjax(staffid);
	}else{
		return;
	}
	
}


function changeAjax(json){
	
	var xmlHttp = initAjax();
	xmlHttp.open("post","servlet/AdminServlet?para=changeSt",true);
	
	xmlHttp.onreadystatechange = function ()
	{
		if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) { 
            	var tip = xmlHttp.responseText;
            	if(tip=="ok"){
            		alert("修改成功！");
            	}else{
            		alert("修改失败！");
            	}
        		
            }
		}
	}
	var data = JSON.stringify(json)
	xmlHttp.send(data);		
}

function deleteAjax(id){
	var xmlHttp = initAjax();
	xmlHttp.open("get","servlet/AdminServlet?para=deleteSt&staffid="+id,true);
	xmlHttp.send(null);	
	xmlHttp.onreadystatechange = function ()
	{
		if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) { 
            	var tip = xmlHttp.responseText;
            	if(tip=="ok"){
            		alert("删除成功！");
            		searchAll();
            	}else{
            		alert("删除失败！");
            	}
            }
		}
	}

}
