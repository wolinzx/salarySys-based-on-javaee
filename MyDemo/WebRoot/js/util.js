//菜单还原
function menuColor(){
	var mainLi = document.getElementsByClassName("child-li");
	for (var i=0;i<mainLi.length;i++) {
		mainLi[i].style.backgroundColor="#2B3946";
	}
}

//获取json元素个数
function getJsonLength(json)
{
    var cc = 0;
    for(var i in json)
    {
        cc++;
    }
    return cc;
}

//获取当前时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

//将表名从bean类型转为中文
function beanTrun(jsonkey1){
	if(jsonkey1=="userName"){
		jsonkey="用户名";
	}else if(jsonkey1=="staffid"){
		jsonkey="员工编号";
	}else if(jsonkey1=="sex"){
		jsonkey="性别";
	}else if(jsonkey1=="seniority"){
		jsonkey="工龄";
	}else if(jsonkey1=="post"){
		jsonkey="岗位";
	}else if(jsonkey1=="phone"){
		jsonkey="联系电话";
	}else if(jsonkey1=="depid"){
		jsonkey="部门";
	}else if(jsonkey1=="birth"){
		jsonkey="出生日期";
	}else if(jsonkey1=="salary"){
		jsonkey="税前工资";
	}else if(jsonkey1=="lastdate"){
		jsonkey="最后修改日期";
	}else if(jsonkey1=="allsalary"){
		jsonkey="实发工资";
	}else if(jsonkey1=="type"){
		jsonkey="考勤类型";
	}else if(jsonkey1=="attdate"){
		jsonkey="登记日期";
	}else if(jsonkey1=="recorduser"){
		jsonkey="修改人";
	}else if(jsonkey1=="recordtype"){
		jsonkey="修改类型";
	}else if(jsonkey1=="recorddate"){
		jsonkey="修改时间";
	}else if(jsonkey1=="id"){
		jsonkey="ID";
	}
	return jsonkey;
}