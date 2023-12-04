window.addEventListener("scroll", function () {
	var dropdownMenu = document.querySelector("#header_body");
	var scrollThreshold = 209; // 특정 높이(픽셀)로 지정
	var originalBorderColor = "red";

	if (window.pageYOffset >= scrollThreshold) {
		dropdownMenu.style.position = "fixed";
		dropdownMenu.style.top = "0";
		dropdownMenu.style.backgroundColor = "red"; // 원하는 스타일로 변경
		dropdownMenu.style.color = "white"; // 원하는 스타일로 변경
		dropdownMenu.style.borderBottom = "none";
	} else {
		dropdownMenu.style.position = "relative";
		dropdownMenu.style.top = "initial";
		dropdownMenu.style.backgroundColor = "white"; // 원래 스타일로 변경
		dropdownMenu.style.color = "black"; // 원래 스타일로 변경
		dropdownMenu.style.borderBottom = "3px solid " + originalBorderColor;
	}
});
window.addEventListener("scroll", function () {
	var dropdownMenu = document.querySelector(".main-menu");
	var scrollThreshold = 209; // 특정 높이(픽셀)로 지정
	if (window.pageYOffset >= scrollThreshold) {
		dropdownMenu.style.borderBottom = "3px solid white";
	}
});
