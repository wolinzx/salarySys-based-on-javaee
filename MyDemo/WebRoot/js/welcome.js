window.onload = function(){
	slider();
	tiaozhuan();
	
}

function slider(){
	var mainLeftUl = document.getElementById("main-left-ul");
	var mainLeftUlLi = document.getElementsByClassName("main-left-ul-li");
	var mainLeftUlChild = document.getElementsByClassName("main-left-ul-child");
	
	for (var i=0;i<mainLeftUlLi.length;i++) {
		mainLeftUlLi[i].index=i;
		
		mainLeftUlLi[i].onclick = function(){
			//将所有下拉标签收回
			for(var j=0;j<mainLeftUlChild.length;j++){
				mainLeftUlChild[j].style.marginTop="-90px";
				mainLeftUlLi[j].style.backgroundColor="#2B3946";
				mainLeftUlLi[j].style.color="#1BB196";
				mainLeftUlLi[j].style.borderLeft="2px solid #2B3946"
			}
			mainLeftUlChild[this.index].style.marginTop="0px";
			this.style.backgroundColor="#1D262F";
			this.style.color="white";
			this.style.borderLeft="2px solid #34B8A1"
		}
	}
}
