function tiaozhuan(){
	getId("addstaff");
	getId("searchstaff");
	getId("basesalary");
	getId("addatt");
	getId("searchatt");
	getId("record");
}
//获取id方法并添加点击属性
function getId(id){
	var getId = document.getElementById(id);
	var imgdiv = document.getElementById("main-right-img");
	getId.onclick = function (){
		imgdiv.style.display="none";
		menuColor();
		window.parent.mainright.location.href="/MyDemo/admin/"+id+".jsp";
		this.style.backgroundColor="#34B8A1";
	}
}